package Edit;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;

public class EditStudentButtons extends JPanel {

    private static final int BUTTON_WIDTH = 159;
    private static final int BUTTON_HEIGHT = 60;
    private static final int GAP_SIZE = 6;

    public EditStudentButtons() {
        
        JButton btnEditName = createButton("Edit Name", e -> {
            EditStudentName editStudentName = new EditStudentName();
            editStudentName.setVisible(true);
        });

        JButton btnEditUsername = createButton("Edit Username", e -> {
            EditStudentUserName editStudentUserName = new EditStudentUserName();
            editStudentUserName.setVisible(true);
        });

        JButton btnEditPassword = createButton("Edit Password", e -> {
            EditStudentPassword editStudentPassword = new EditStudentPassword();
            editStudentPassword.setVisible(true);
        });

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(btnEditName, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(btnEditUsername, GroupLayout.DEFAULT_SIZE, BUTTON_WIDTH - GAP_SIZE, Short.MAX_VALUE)
                    .addGap(GAP_SIZE)
                    .addComponent(btnEditPassword, GroupLayout.PREFERRED_SIZE, BUTTON_WIDTH, GroupLayout.PREFERRED_SIZE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(btnEditName, GroupLayout.DEFAULT_SIZE, BUTTON_HEIGHT, Short.MAX_VALUE)
                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(btnEditPassword, GroupLayout.DEFAULT_SIZE, BUTTON_HEIGHT, Short.MAX_VALUE)
                    .addComponent(btnEditUsername, GroupLayout.DEFAULT_SIZE, BUTTON_HEIGHT, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFocusable(false);
        button.addActionListener(actionListener);
        return button;
    }
}
