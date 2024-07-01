import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditStudentButtons extends JPanel {


	public EditStudentButtons() {
		
		JButton btnEditName = new JButton("Edit Name");
		btnEditName.setFocusable(false);
		btnEditName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStudentName editStudentName = new EditStudentName();
				editStudentName.setVisible(true);
			}
		});
		
		JButton btnEditUsername = new JButton("Edit Username");
		btnEditUsername.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStudentUserName editStudentUserName = new EditStudentUserName();
				editStudentUserName.setVisible(true);
			}
		});
		btnEditUsername.setFocusable(false);
		
		JButton btnEditUsername_1 = new JButton("Edit Password");
		btnEditUsername_1.setFocusable(false);
		btnEditUsername_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditStudentPassword editStudentPassword = new EditStudentPassword();
				editStudentPassword.setVisible(true);
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(btnEditName, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEditUsername, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(btnEditUsername_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(btnEditName, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnEditUsername_1, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
					.addComponent(btnEditUsername, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
