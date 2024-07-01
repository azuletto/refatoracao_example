import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddBook extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;

	public String rmid() {
		
		String id = "";
		for(int a = 0; a<10; a++)
		{
			id += (int)(Math.random()*10);
		}
		return id;
		
	}
	private void reset() {
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		textField_3.setText(null);
		textField_4.setText(null);
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		comboBox_2.setSelectedIndex(0);
		comboBox_3.setSelectedIndex(0);
		
	}
	
	public AddBook(){
	
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFocusable(false);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFocusable(false);
		
		JLabel lblPublication = new JLabel("Publication");
		lblPublication.setFocusable(false);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFocusable(false);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFocusable(false);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFocusable(false);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFocusable(false);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFocusable(false);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFocusable(false);
		
		textField = new JTextField(rmid());
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		JLabel lblAddNewBook = new JLabel("Add New Book");
		lblAddNewBook.setFocusable(false);
		lblAddNewBook.setFont(lblAddNewBook.getFont ().deriveFont (13.0f));
		
		comboBox = new JComboBox(DBInfo.getValues("author"));
		comboBox.setFocusable(false);
		
		
		comboBox_1 = new JComboBox(DBInfo.getValues("publication"));
		comboBox_1.setFocusable(false);
		
		
		comboBox_2 = new JComboBox(DBInfo.getValues("category"));
		comboBox_2.setFocusable(false);
	
		
		comboBox_3 = new JComboBox(DBInfo.getValues("subject"));
		comboBox_3.setFocusable(false);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String id = textField.getText();
			String title = textField_1.getText();
			String author = (String) comboBox.getSelectedItem();
			String publication = (String) comboBox_1.getSelectedItem();
			String category = (String) comboBox_2.getSelectedItem();
			String subject = (String) comboBox_3.getSelectedItem();
			String isbn = textField_2.getText();
			String price = textField_3.getText();
			String qnt = textField_4.getText();
			
			String query = "insert into books values(?,?,?,?,?,?,?,?,?)";
			
			Connection con = DBInfo.getConn();
			
			if(title=="" || isbn=="")
			{
				if(price=="" || qnt=="")
				{
					JOptionPane.showMessageDialog(getParent(), "Fill all section", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				if(author.equalsIgnoreCase("select") || category.equalsIgnoreCase("select"))
				{
					if(publication.equalsIgnoreCase("select") || subject.equalsIgnoreCase("select"))
					{
						JOptionPane.showMessageDialog(getParent(), "Fill all section", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					try {
						PreparedStatement ps = con.prepareStatement(query);
						ps.setString(1,id);
						ps.setString(2,title);
						ps.setString(3,author);
						ps.setString(4,publication);
						ps.setString(5,category);
						ps.setString(6,subject);
						ps.setString(7,isbn);
						ps.setString(8,price);
						ps.setString(9,qnt);
						ps.executeUpdate();
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					reset();
					LibrarianPage lp = new LibrarianPage();
					lp.dispose();
					lp.setVisible(true);
					lp.addStudent.setVisible(false);
					lp.addBook.setVisible(true);
					lp.addAuthor.setVisible(false);
					lp.addPublication.setVisible(false);
					lp.addCategory.setVisible(false);
					lp.addSubject.setVisible(false);
					lp.welcome.setVisible(false);
					lp.allBooks.setVisible(false);
					lp.editStudent.setVisible(false);
					lp.editStudentButtons.setVisible(false);
			
				}
			}
			}

			

		});
		btnSave.setFocusable(false);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFocusable(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblId, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrice, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIsbn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubject, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPublication, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAuthor, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(comboBox_3, Alignment.TRAILING, 0, 212, Short.MAX_VALUE)
						.addComponent(comboBox_2, 0, 212, Short.MAX_VALUE)
						.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(comboBox_1, 0, 212, Short.MAX_VALUE)
						.addComponent(comboBox, 0, 212, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(btnSave, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(183, Short.MAX_VALUE)
					.addComponent(lblAddNewBook, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addGap(156))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblAddNewBook)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblId))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTitle))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAuthor))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_1)
						.addComponent(lblPublication))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_3)
						.addComponent(lblSubject))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIsbn))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrice)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuantity)
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSave))
					.addGap(28))
		);
		setLayout(groupLayout);

	}

}
