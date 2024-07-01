import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.net.httpserver.Authenticator.Result;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class IssueBook extends JFrame {
	
	LibrarianPage lp = new LibrarianPage();
	Connection con = DBInfo.getConn();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public static LocalDate getLocalDate() {
	    return LocalDate.now();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueBook frame = new IssueBook();
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
	public IssueBook() {
		setTitle("Issue Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 379, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(this);
		
		JLabel lblBookid = new JLabel("BookID");
		
		JLabel lblStudentUsername = new JLabel("Student Username");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnIssueBook = new JButton("Issue Book");
		btnIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int flag = 0;
				int flag1 = 0;
				String qnt = "";
				int alreadyIssued = 0;
				String bookID = textField.getText();
				String studentUsername = textField_1.getText();
				String title = "";
				
				String query = "select id from books where id = "+bookID;
				String query1 = "select title from books where id = "+bookID;
				String query2 = "select * from student where username = ?";
				String query3 = "insert into issuedbooks values(?,?,?,?,?)";
				String query4 = "update books set quantity = quantity-1";
				String query5 = "select quantity from books where id = ?";
				String query6 = "select * from issuedbooks where StudentUsername = ?";
				
				try {
					PreparedStatement ps6 = con.prepareStatement(query6);
					ps6.setString(1, studentUsername);
					ResultSet res6 = ps6.executeQuery();
					while(res6.next()) {
						alreadyIssued = 1;
					}
					if(alreadyIssued == 0) {
					PreparedStatement ps5 = con.prepareStatement(query5);
					ps5.setString(1, bookID);
					ResultSet res5 = ps5.executeQuery();
					while(res5.next()) {
						qnt = res5.getString(1);
					}
					if(qnt.equals("")) {
						JOptionPane.showMessageDialog(getParent(), "Book out of stock");
					}
					else {
						PreparedStatement ps4 = con.prepareStatement(query4);
						PreparedStatement ps = con.prepareStatement(query);
						ResultSet res = ps.executeQuery();
						while(res.next()) {
							flag = 1;
						}
						
						PreparedStatement ps1 = con.prepareStatement(query1);
						
						if(flag == 1) {
						ResultSet res1 = ps1.executeQuery();
						while(res1.next()) {
							title = res1.getString(1);
						}
						}
						
						PreparedStatement ps2= con.prepareStatement(query2);
						ps2.setString(1, studentUsername);
						ResultSet res2 = ps2.executeQuery();
						while(res2.next()) {
							flag1 = 1;
						}
						
						if(flag == 1 && flag1 == 1) {
						PreparedStatement ps3 = con.prepareStatement(query3);
						ps3.setString(1, bookID);
						ps3.setString(2, title);
						ps3.setString(3, studentUsername);
						ps3.setString(4, getLocalDate().toString());
						ps3.setString(5, getLocalDate().plusDays(15).toString());
						ps3.executeUpdate();
						ps4.executeUpdate();
						JOptionPane.showMessageDialog(getParent(), "Book Issued");
						textField.setText(null);
						textField_1.setText(null);
						dispose();
						lp.dispose();
						lp.setVisible(true);
						setVisible(true);
						}
						
						else {
							JOptionPane.showMessageDialog(getParent(), "Incorrect BookId or Username");
						}
					
					}
					}
					else
						JOptionPane.showMessageDialog(getParent(), "Book already Issued");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblBookid, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStudentUsername))
					.addGap(49)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnIssueBook, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField_1, Alignment.TRAILING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(137, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookid)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(64)
							.addComponent(btnIssueBook))
						.addComponent(lblStudentUsername))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
