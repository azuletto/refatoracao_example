package src.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.view.MainMenuView;

public class ViewUtils {
    public static JButton createBackButton(JFrame currentFrame) {
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 100, 30);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame.dispose();
                new MainMenuView();
            }
        });

        return backButton;
    }
}
