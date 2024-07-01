import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SubmitBook extends JFrame {
	
	LibrarianPage lp = new LibrarianPage();
	Connection con = DBInfo.getConn();
	private JPanel contentPane;
	private JTextField textField;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SubmitBook frame = new SubmitBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public SubmitBook() {
		setTitle("Submit Book");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 344, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblBookId = new JLabel("Book ID");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnSubmitBook = new JButton("Submit Book");
		btnSubmitBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				int flag = 0;
				String bookID = textField.getText();
				
				String query = "select * from issuedbooks where bookid = ?";
				String query1 = "update books set quantity = quantity+1 where id = ?";
				String query2 = "delete from issuedbooks where bookid = ?";
				
				try {
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, bookID);
					ResultSet res = ps.executeQuery();
					while(res.next()) {
						flag = 1;
					}
					if(flag == 1) {
					PreparedStatement ps1 = con.prepareStatement(query1);
					ps1.setString(1, bookID);
					PreparedStatement ps2 = con.prepareStatement(query2);
					ps2.setString(1, bookID);
					ps1.executeUpdate();
					ps2.executeUpdate();
					JOptionPane.showMessageDialog(getParent(), "Book Submitted");
					textField.setText(null);
					dispose();
					lp.dispose();
					lp.setVisible(true);
					setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(getParent(), "BookID not found");
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
					.addGap(24)
					.addComponent(lblBookId, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(73)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField)
						.addComponent(btnSubmitBook, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBookId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addComponent(btnSubmitBook)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(this);
	}

}
