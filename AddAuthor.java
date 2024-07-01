import javax.swing.JPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddAuthor extends JPanel {
	Connection con = DBInfo.getConn();
	private JTextField textField;
	private JTextField textField_1;

	public AddAuthor() {
		
		JLabel lblAddNewAuthor = new JLabel("Add New Author");
		lblAddNewAuthor.setFocusable(false);
		lblAddNewAuthor.setFont(lblAddNewAuthor.getFont ().deriveFont (13.0f));
		
		JLabel lblName = new JLabel("Name");
		lblName.setFocusable(false);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFocusable(false);
		
		JLabel label = new JLabel("");
		label.setFocusable(false);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "insert into author values(?,?)";
				String query2 = "select * from author";
				String authName = "";
				String id = "";
				String a = textField.getText();
				String b = textField_1.getText();
				
				PreparedStatement ps2;
				if(a=="" || b=="")
				{
					JOptionPane.showMessageDialog(getParent(), "Empty fields not allowed !","Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						ps2 = con.prepareStatement(query2);
						ResultSet res = ps2.executeQuery();
						while(res.next())
						{
							id = res.getString(1);
							authName = res.getString(2);
						}
							
							if(id.equalsIgnoreCase(a) || authName.equalsIgnoreCase(b))
							{
								JOptionPane.showMessageDialog(getParent(), "Author Already Exits","Error !",JOptionPane.ERROR_MESSAGE);
								textField.setText(null);
								textField_1.setText(null);
							}
							
							else
							{
							
								try {
									
									PreparedStatement ps = con.prepareStatement(query);
									ps.setString(1, textField.getText().toUpperCase());
									ps.setString(2, textField_1.getText().toUpperCase());
									ps.executeUpdate();
									
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
								textField.setText(null);
								textField_1.setText(null);
								
								
							}
						
					}
					catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
					LibrarianPage lp = new LibrarianPage();
					lp.dispose();
					lp.setVisible(true);
					lp.addStudent.setVisible(false);
					lp.addStudent.setVisible(false);
					lp.addBook.setVisible(false);
					lp.addAuthor.setVisible(true);
					lp.addPublication.setVisible(false);
					lp.addCategory.setVisible(false);
					lp.addSubject.setVisible(false);
					lp.welcome.setVisible(false);
					lp.allBooks.setVisible(false);
					lp.editStudent.setVisible(false);
					lp.editStudentButtons.setVisible(false);
				
			}
			}
		});
		btnSave.setFocusable(false);
		
		JButton btnRe = new JButton("Reset");
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
			}
		});
		btnRe.setFocusable(false);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(199)
					.addComponent(lblAddNewAuthor, GroupLayout.PREFERRED_SIZE, 71, Short.MAX_VALUE)
					.addGap(159))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnRe, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
							.addGap(67)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddNewAuthor)
					.addGap(62)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblId)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblName)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(41)
					.addComponent(label)
					.addGap(37)
					.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(btnRe, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
