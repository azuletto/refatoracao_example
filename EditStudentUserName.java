import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class EditStudentUserName extends JFrame {
	
	Connection con = DBInfo.getConn();
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField;

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
					EditStudentUserName frame = new EditStudentUserName();
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
	public EditStudentUserName() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("Edit Username");
		setLocationRelativeTo(this);
		
		JButton button = new JButton("Reset");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText(null);
				textField.setText(null);
				passwordField.setText(null);
			}
		});
		
		JButton button_1 = new JButton("Save");
		button_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int flag = 0;
				String query = "update student set Username = ? where Password = ?";
				String query2 = "select * from student where username = ? AND password = ?";
				String query3 = "update login set Username = ? where Password = ? AND usertype = ?";
				String newUserName = textField_1.getText();
				String password = passwordField.getText();
				String oldUserName = textField.getText();
				
				if(oldUserName.equals("")||newUserName.equals(""))
					JOptionPane.showMessageDialog(getParent(), "Empty fields not allowed");
				else
				{
				try 
				{
					PreparedStatement ps2 = con.prepareStatement(query2);
					ps2.setString(1, oldUserName);
					ps2.setString(2, password);
					ResultSet res = ps2.executeQuery();
					while(res.next())
					{
						flag = 1;
					
					}
				}
				
				catch (Exception ae) {
					System.out.println(ae);
				}
			
				if(flag == 1)
				{
					
						try {
							PreparedStatement ps = con.prepareStatement(query);
							ps.setString(1, newUserName);
							ps.setString(2, password);
							ps.executeUpdate();
							PreparedStatement ps3 = con.prepareStatement(query3);
							ps3.setString(1, newUserName);
							ps3.setString(2, password);
							ps3.setString(3, "student");
							ps3.executeUpdate();
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						
					}
					
				}
				
				else
				{
					JOptionPane.showMessageDialog(getParent(), "Incorrect username or password", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				textField_1.setText(null);
				textField.setText(null);
				passwordField.setText(null);
				
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
		
		JLabel lblNewUsername = new JLabel("New Username");
		
		JLabel label_1 = new JLabel("Confirm Password");
		
		JLabel lblOldUsername = new JLabel("Old Username");
		
		passwordField = new JPasswordField();
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(lblNewUsername, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOldUsername, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOldUsername)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewUsername)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1)))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
