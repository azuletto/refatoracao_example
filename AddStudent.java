import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AddStudent extends JPanel {
	Connection con = DBInfo.getConn();
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	public AddStudent() {
		
		JLabel lblAddNewStudent = new JLabel("Add New Student");
		lblAddNewStudent.setFocusable(false);
		lblAddNewStudent.setFont(lblAddNewStudent.getFont ().deriveFont (13.0f));
		
		JLabel lblName = new JLabel("Name");
		lblName.setFocusable(false);
		
		JLabel lblUserame = new JLabel("Userame");
		lblUserame.setFocusable(false);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFocusable(false);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);

		passwordField = new JPasswordField();
		passwordField_1 = new JPasswordField();
		
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField_1.setText(null);
				textField_2.setText(null);
				passwordField.setText(null);
				passwordField_1.setText(null);
			}
		});
		btnReset.setFocusable(false);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "insert into student values(?,?,?)";
				String query2 = "select * from student";
				String query3 = "insert into login(fullname, username, usertype, password) values(?,?,?,?)";
				String Name = "";
				String userName = "";
				String userType = "student";
				String a = textField_1.getText();
				String b = textField_2.getText();
				char[] p1 = passwordField.getPassword();
				char[] p2 = passwordField_1.getPassword();
				String pass1 = new String(p1);
				String pass2 = new String(p2);
				
				PreparedStatement ps2;
				
				try {
					ps2 = con.prepareStatement(query2);
					ResultSet res = ps2.executeQuery();
					while(res.next())
					{
						Name = res.getString(2);
						userName = res.getString(3);
					}
						
						if(userName.equalsIgnoreCase(a) || Name.equalsIgnoreCase(b))
						{
							JOptionPane.showMessageDialog(getParent(), "Student Already Exits","Error !",JOptionPane.ERROR_MESSAGE);
							
							textField_1.setText(null);
							textField_2.setText(null);
							passwordField.setText(null);
							passwordField_1.setText(null);
						}
						
						else
						{
							if(pass1.equals(pass2))
								{
								try 
								{
									
									PreparedStatement ps = con.prepareStatement(query);
									PreparedStatement ps3 = con.prepareStatement(query3);
									
									ps.setString(1, textField_1.getText());
									ps.setString(2, textField_2.getText());
									ps.setString(3, passwordField.getText());
									ps.executeUpdate();
									ps3.setString(1, textField_1.getText());
									ps3.setString(2, textField_2.getText());
									ps3.setString(3, userType);
									ps3.setString(4, passwordField.getText());
									ps3.executeUpdate();
									
								} 
								catch (SQLException e1) 
								{
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
									
									textField_1.setText(null);
									textField_2.setText(null);
									passwordField.setText(null);
									passwordField_1.setText(null);
								}
							else
								{
								JOptionPane.showMessageDialog(getParent(), "Password didn't match", "Error", JOptionPane.ERROR_MESSAGE);
								passwordField.setText(null);
								passwordField_1.setText(null);
								}
							LibrarianPage lp = new LibrarianPage();
							lp.dispose();
							lp.setVisible(true);
							lp.addStudent.setVisible(true);
							lp.addBook.setVisible(false);
							lp.addAuthor.setVisible(false);
							lp.addPublication.setVisible(false);
							lp.addCategory.setVisible(false);
							lp.addSubject.setVisible(false);
							lp.welcome.setVisible(false);
							lp.allBooks.setVisible(false);
							lp.editStudent.setVisible(false);
							lp.editStudentButtons.setVisible(false);
					
							
						}	
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnSave.setFocusable(false);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFocusable(false);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblConfirmPassword, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUserame, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblAddNewStudent, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
										.addComponent(textField_2)
										.addComponent(textField_1)
										.addComponent(passwordField)
										.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)))
							.addGap(56))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddNewStudent)
					.addGap(48)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(50)
							.addComponent(lblName)
							.addGap(47)
							.addComponent(lblUserame))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmPassword)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSave))
					.addGap(40))
		);
		setLayout(groupLayout);

	}
}
