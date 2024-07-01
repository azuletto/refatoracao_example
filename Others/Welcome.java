import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Welcome extends JPanel {

	/**
	 * Create the panel.
	 */
	public Welcome() {
		
		JLabel lblWelcomeToLibrary = new JLabel(" Welcome to Library Management System");
		lblWelcomeToLibrary.setFont(lblWelcomeToLibrary.getFont ().deriveFont (23.0f));
		
		JLabel lblCreatedBy = new JLabel("Created By ---> Mohit Yadav");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCreatedBy, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWelcomeToLibrary, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
					.addGap(52))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addComponent(lblWelcomeToLibrary, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblCreatedBy, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addGap(54))
		);
		setLayout(groupLayout);

	}

}
