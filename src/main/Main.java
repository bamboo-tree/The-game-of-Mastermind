package main;

import window.Frame;
import window.WelcomeScreen;
import user.User;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        Frame frame = new Frame();
        MyListener listener = new MyListener();
        WelcomeScreen welcome = new WelcomeScreen(frame);
        welcome.usernameInput.addKeyListener(listener);

        User user;



        // creating user
        while(true){
            TimeUnit.MILLISECONDS.sleep(40);

            // waiting until enter is hit
            if(listener.getCode() == 10 && !listener.getReleased()){
                // checking if username is properly spelled
                if(!User.checkSpelling(welcome.usernameInput.getText())){
                    welcome.userInfo.setText("username is incorrect, try something else");
                    welcome.userInfo.setBackground(frame.RED);
                }
                else{
                    String path = "./allUsers/" + welcome.usernameInput.getText() + ".txt";

                    // creating a new user, or playing as existing one
                    if(new File(path).isFile()){
                        welcome.userInfo.setText("Welcome back " + welcome.usernameInput.getText() + "!");
                    }
                    else{
                        File file = new File(path);
                        file.createNewFile();
                        user = new User(file);
                        welcome.userInfo.setText("User " + user.getName() + " has been successfully created.");
                    }
                    welcome.userInfo.setBackground(frame.GREEN);
                    TimeUnit.MILLISECONDS.sleep(1000);
                    break;
                }
            }
        }
        welcome.killWelcomeScreen(frame);






    }
}
