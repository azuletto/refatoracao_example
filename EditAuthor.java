import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditAuthor extends JFrame {

	Connection con = DBInfo.getConn();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAuthor frame = new EditAuthor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public EditAuthor() {
		setTitle("Edit Author");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 353, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblOldAuthorName = new JLabel("Old Author Name");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblNewAuthorName = new JLabel("New Author Name");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String oldAuthName = textField.getText();
				String newAuthName = textField_1.getText();
				String query = "update author set name = ? where name = ?";
				String query1 = "select name from author";
				String dbName = "";
				
				if(oldAuthName.equals("")||newAuthName.equals(""))
					JOptionPane.showMessageDialog(getParent(), "Empty fields not allowed");
				
				else
				{
					try {
						PreparedStatement ps = con.prepareStatement(query);
						PreparedStatement ps1 = con.prepareStatement(query1);
						ResultSet res = ps1.executeQuery();
						while(res.next()) {
							dbName = res.getString(1);
						}
						if(dbName.equalsIgnoreCase(oldAuthName)) {
							ps.setString(1, newAuthName.toUpperCase());
							ps.setString(2, oldAuthName);
							ps.executeUpdate();
							JOptionPane.showMessageDialog(getParent(), "Record Updated !");
						}
						else
								JOptionPane.showMessageDialog(getParent(), "No record found");
						
						
					} 
					catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					textField.setText(null);
					textField_1.setText(null);
			}
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			textField.setText(null);
			textField_1.setText(null);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOldAuthorName)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewAuthorName, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(textField_1)
						.addComponent(textField)
						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(55)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOldAuthorName)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewAuthorName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSave))
					.addGap(46))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(this);
	}
}
