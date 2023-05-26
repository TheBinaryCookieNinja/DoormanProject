package gui;

import java.awt.EventQueue;

public class MainApp {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginScreen loginScreen = new LoginScreen();
                    loginScreen.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
