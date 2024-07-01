import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminPage extends JFrame {

	Connection con = DBInfo.getConn();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_4;

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
					AdminPage frame = new AdminPage();
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
	public AdminPage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 484, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblFullName = new JLabel("Full Name");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		JLabel lblMobileNo = new JLabel("Mobile No.");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		JButton btnCreate = new JButton("Save");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "insert into login(fullname,username,usertype,email,mobile,password) values(?,?,?,?,?,?)";
				String query2 = "select * from login";
				String Name = "";
				String id = "";
				String a = textField.getText();
				String b = textField_1.getText();
				String p1 = passwordField.getText();
				String p2 = passwordField_1.getText();
				
				PreparedStatement ps2;
				
				try {
					ps2 = con.prepareStatement(query2);
					ResultSet res = ps2.executeQuery();
					while(res.next())
					{
						id = res.getString(1);
						Name = res.getString(2);
					}
						
						if(id.equalsIgnoreCase(a) || Name.equalsIgnoreCase(b))
						{
							JOptionPane.showMessageDialog(getParent(), "User Already Exits","Error !",JOptionPane.ERROR_MESSAGE);
							textField.setText(null);
							textField_1.setText(null);
							textField_4.setText(null);
							textField_2.setText(null);
							textField_3.setText(null);
							passwordField.setText(null);
							passwordField_1.setText(null);
						}
						
						else
						{
						
							if(p1.equals(p2))
							{
								try {
									
									PreparedStatement ps = con.prepareStatement(query);
									ps.setString(1, textField.getText().toUpperCase());
									ps.setString(2, textField_1.getText().toUpperCase());
									ps.setString(3, textField_4.getText().toUpperCase());
									ps.setString(4, textField_2.getText().toUpperCase());
									ps.setString(5, textField_3.getText().toUpperCase());
									ps.setString(6, passwordField.getText());
									ps.executeUpdate();
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								textField.setText(null);
								textField_1.setText(null);
								textField_2.setText(null);
								textField_3.setText(null);
								textField_4.setText(null);
								passwordField.setText(null);
								passwordField_1.setText(null);
							}
							else
							{
								JOptionPane.showMessageDialog(getParent(), "Password didn't match", "Error", JOptionPane.ERROR_MESSAGE);
								passwordField_1.setText(null);
							}
							
							
							
						}
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_4.setText(null);
				passwordField.setText(null);
				passwordField_1.setText(null);
			}
		});
		
		JLabel lblAddLibrarian = new JLabel("Add User");
		lblAddLibrarian.setFont(lblAddLibrarian.getFont ().deriveFont (13.0f));
		
		passwordField = new JPasswordField();
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		
		passwordField_1 = new JPasswordField();
		
		JLabel lblUsertype = new JLabel("Usertype");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(192)
					.addComponent(lblAddLibrarian, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
					.addGap(165))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMobileNo, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfirmPassword, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblFullName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblUsername, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsertype, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnCreate, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(textField_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
						.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
						.addComponent(passwordField, Alignment.LEADING, 212, 231, Short.MAX_VALUE)
						.addComponent(passwordField_1, Alignment.LEADING, 212, 231, Short.MAX_VALUE)
						.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
						.addComponent(textField_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE))
					.addGap(41))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblAddLibrarian)
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFullName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsertype)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobileNo)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(48)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfirmPassword))
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCreate, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(18))
		);
		contentPane.setLayout(gl_contentPane);
		setTitle("Admin Page");
		setLocationRelativeTo(this);
	}
}
