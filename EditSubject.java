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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditSubject extends JFrame {

	Connection con = DBInfo.getConn();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditSubject frame = new EditSubject();
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
	public EditSubject() {
		setTitle("Edit Subject");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 292);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblOldSubjectName = new JLabel("Old Subject Name");
		
		JLabel lblNewSubjectName = new JLabel("New Subject Name");
		
		textField = new JTextField();
		textField.setText("  ");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("  ");
		textField_1.setColumns(10);
		
		JButton btnReser = new JButton("Reset");
		btnReser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			textField.setText(null);
			textField_1.setText(null);
			}
		});
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String oldSubName = textField.getText();
				String newSubName = textField_1.getText();
				String query = "update author set name = ? where name = ?";
				String query1 = "select name from author";
				String dbName = "";
				
				if(oldSubName.equals("")||newSubName.equals(""))
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
						if(dbName.equalsIgnoreCase(oldSubName)) {
							ps.setString(1, newSubName.toUpperCase());
							ps.setString(2, oldSubName);
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOldSubjectName, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewSubjectName, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReser, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(textField)
						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOldSubjectName))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewSubjectName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnReser))
					.addGap(51))
		);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(this);
	}

}
