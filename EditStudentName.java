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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class EditStudentName extends JFrame {

	Connection con = DBInfo.getConn();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;

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
					EditStudentName frame = new EditStudentName();
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
	public EditStudentName() {
		setTitle("Edit Name");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 375, 303);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblOldName = new JLabel("Old Name");
		
		JLabel lblNewName = new JLabel("New Name");
		
		JLabel lblConfirmUsername = new JLabel("Confirm Password");
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

				String query = "update student set name = ? where password = ?";
				String query2 = "select name, password from student";
				String query3 = "update login set name = ? where name = ? AND usertype = ?";
				String newName = textField_1.getText();
				String password = passwordField.getText();
				String oldName = textField.getText();
				String dbName = "";
				String dbPassword = "";
				
				if(oldName.equals("")||newName.equals(""))
					JOptionPane.showMessageDialog(getParent(), "Empty fields not allowed");
				else
				{
				try 
				{
					PreparedStatement ps2 = con.prepareStatement(query2);
					ResultSet res = ps2.executeQuery();
					while(res.next())
					{
						dbName = res.getString(1);
						dbPassword = res.getString(2);
					}
					
					if(oldName.equalsIgnoreCase(dbName))
					{
						if(dbPassword.equals(password))
						{
							try {
								PreparedStatement ps = con.prepareStatement(query);
								ps.setString(1, newName);
								ps.setString(2, password);
								ps.executeUpdate();
								PreparedStatement ps3 = con.prepareStatement(query3);
								ps3.setString(1, newName);
								ps3.setString(2, oldName);
								ps3.setString(3, "student");
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(getParent(), "Password doesn't match !", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Name not found !", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (Exception ae)
				{
					System.out.println(ae);
				}
			
				
				
				textField.setText(null);
				textField_1.setText(null);
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
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textField.setText(null);
				textField_1.setText(null);
				passwordField.setText(null);
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		passwordField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblConfirmUsername, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblOldName, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(passwordField)
								.addComponent(textField_1)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOldName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblConfirmUsername)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSave))
					.addContainerGap(48, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(this);
	}
}
