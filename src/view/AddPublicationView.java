package src.view;

import src.controller.PublicationController;
import src.model.Publication;
import src.utils.ViewUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPublicationView {
    private JFrame frame;
    private JTextField nameField;
    private JButton addButton;

    public AddPublicationView() {
        frame = new JFrame("Add Publication");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Initialize components
        nameField = new JTextField();
        addButton = new JButton("Add");
        JButton backButton = ViewUtils.createBackButton(frame);

        // Set bounds
        nameField.setBounds(50, 50, 200, 20);
        addButton.setBounds(100, 100, 100, 20);

        // Add components to frame
        frame.add(nameField);
        frame.add(addButton);
        frame.add(backButton);

        // Add action listener to button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Publication publication = new Publication();
                publication.setName(nameField.getText());

                PublicationController publicationController = new PublicationController();
                publicationController.addPublication(publication);
                JOptionPane.showMessageDialog(frame, "Publication added successfully!");
            }
        });

        frame.setVisible(true);
    }
}
