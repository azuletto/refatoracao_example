import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPasswordField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EditStudentPassword extends JFrame {

	Connection con = DBInfo.getConn();
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudentPassword frame = new EditStudentPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditStudentPassword() {
		setTitle("Edit Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		
		JLabel lblConfirmUsername = new JLabel("Username");
		
		JLabel lblNewPassword = new JLabel("New Password");
		
		JLabel lblOldPassword = new JLabel("Old Password");
		
		JButton button = new JButton("Reset");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				passwordField.setText(null);
				passwordField_1.setText(null);
			}
		});
		
		JButton button_1 = new JButton("Save");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int flag = 0;
				String query = "Update student set password = ? where username = ?";
				String query1 = "select * from student where username = ? AND password = ?";
				String query2 = "Update login set password = ? where username = ? AND usertype = ?";
				String getUsername = textField.getText();
				String getPassword_1 = passwordField_1.getText();
				String getPassword = passwordField.getText();
				String dbPassword = "";
				String dbUsername = "";
				
				if(getUsername.equals("")||getPassword_1.equals(""))
					JOptionPane.showMessageDialog(getParent(), "Empty fields not allowed");
				else
				{
				try {
					PreparedStatement ps = con.prepareStatement(query);
					PreparedStatement ps1 = con.prepareStatement(query1);
					ps1.setString(1, getUsername);
					ps1.setString(2, getPassword);
					
					ps.setString(1, getPassword_1);
					ps.setString(2, getUsername);
					PreparedStatement ps2 = con.prepareStatement(query2);
					ps2.setString(1, getPassword_1);
					ps2.setString(2, getUsername);
					ps2.setString(3, "student");
					ResultSet res = ps1.executeQuery();
					while(res.next()) {
						flag = 1;
					}
					
					
					if(flag == 1)
					{
						ps.executeUpdate();
						ps2.executeUpdate();
				
					}
					
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Incorrect Username or Password !","Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textField.setText(null);
				passwordField.setText(null);
				passwordField_1.setText(null);
				
				LibrarianPage lp = new LibrarianPage();
				lp.dispose();
				lp.setVisible(true);
				dispose();
				
				lp.addStudent.setVisible(false);
				lp.addBook.setVisible(false);
				lp.addAuthor.setVisible(false);
				lp.addPublication.setVisible(false);
				lp.addCategory.setVisible(false);
				lp.addSubject.setVisible(false);
				lp.welcome.setVisible(false);
				lp.allBooks.setVisible(false);
				lp.editStudent.setVisible(true);
				lp.editStudentButtons.setVisible(true);
				
				
				}
			}
			
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		
		passwordField_1 = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblConfirmUsername)
							.addGap(104)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addComponent(lblOldPassword, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewPassword, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordField_1, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))))
					.addGap(30))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmUsername)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOldPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewPassword)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

}
