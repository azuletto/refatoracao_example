import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class LibrarianPage extends JFrame {
	
	JPanel contentPane = new JPanel();
	AddBook addBook = new AddBook();
	AddStudent addStudent = new AddStudent();
	AddAuthor addAuthor = new AddAuthor();
	AddPublication addPublication = new AddPublication();
	AddCategory addCategory = new AddCategory();
	AddSubject addSubject = new AddSubject();
	AllBooks allBooks = new AllBooks();
	Welcome welcome = new Welcome();
	EditStudent editStudent = new EditStudent();
	EditStudentButtons editStudentButtons = new EditStudentButtons();
	IssuedBook issuedBook = new IssuedBook();

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
					LibrarianPage frame = new LibrarianPage();
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
	public LibrarianPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 498);
		getContentPane().setLayout(null);
		contentPane.setBounds(0, 0, 471, 461);
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(this);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFocusable(false);
		menuBar.setBounds(0, 0, 471, 22);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmAllBooks = new JMenuItem("All Books");
		mntmAllBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent.setVisible(false);
				addBook.setVisible(false);
				addAuthor.setVisible(false);
				addPublication.setVisible(false);
				addCategory.setVisible(false);
				addSubject.setVisible(false);
				welcome.setVisible(false);
				allBooks.setVisible(true);
				editStudent.setVisible(false);
				editStudentButtons.setVisible(false);
				issuedBook.setVisible(false);
			}
		});
		
		JMenuItem mntmIssueBook = new JMenuItem("Issue Book");
		mntmIssueBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				IssueBook issueBook = new IssueBook();
				issueBook.setVisible(true);
			}	
		});
		mnFile.add(mntmIssueBook);
		
		JMenuItem mntmSubmitBook = new JMenuItem("Submit Book");
		mntmSubmitBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				SubmitBook submitBook = new SubmitBook();
				submitBook.setVisible(true);
			}
		});
		mnFile.add(mntmSubmitBook);
		mnFile.add(mntmAllBooks);
		
		JMenuItem mntmIssuedBooks = new JMenuItem("Issued Books");
		mntmIssuedBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				addStudent.setVisible(false);
				addBook.setVisible(false);
				addAuthor.setVisible(false);
				addPublication.setVisible(false);
				addCategory.setVisible(false);
				addSubject.setVisible(false);
				welcome.setVisible(false);
				allBooks.setVisible(false);
				editStudent.setVisible(false);
				editStudentButtons.setVisible(false);
				issuedBook.setVisible(true);
				
			}
		});
		mnFile.add(mntmIssuedBooks);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnAdd = new JMenu("Add");
		mnAdd.setFocusable(false);
		menuBar.add(mnAdd);
		
		JMenuItem mntmStudent = new JMenuItem("Student");
		mntmStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent.setVisible(true);
				addBook.setVisible(false);
				addAuthor.setVisible(false);
				addPublication.setVisible(false);
				addCategory.setVisible(false);
				addSubject.setVisible(false);
				welcome.setVisible(false);
				allBooks.setVisible(false);
				editStudent.setVisible(false);
				editStudentButtons.setVisible(false);
				issuedBook.setVisible(false);
			}
		});
		mnAdd.add(mntmStudent);
		
		JMenuItem mntmBook = new JMenuItem("Book");
		mntmBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBook.setVisible(true);
				addStudent.setVisible(false);
				addAuthor.setVisible(false);
				addPublication.setVisible(false);
				addCategory.setVisible(false);
				addSubject.setVisible(false);
				welcome.setVisible(false);
				allBooks.setVisible(false);
				editStudent.setVisible(false);
				editStudentButtons.setVisible(false);
				issuedBook.setVisible(false);
			}
		});
		mnAdd.add(mntmBook);
		
		JMenuItem mntmAuthor = new JMenuItem("Author");
		mntmAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent.setVisible(false);
				addBook.setVisible(false);
				addAuthor.setVisible(true);
				addPublication.setVisible(false);
				addCategory.setVisible(false);
				addSubject.setVisible(false);
				welcome.setVisible(false);
				allBooks.setVisible(false);
				editStudent.setVisible(false);
				editStudentButtons.setVisible(false);
				issuedBook.setVisible(false);
			}
		});
		mnAdd.add(mntmAuthor);
		
		JMenuItem mntmPublication = new JMenuItem("Publication");
		mntmPublication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent.setVisible(false);
				addBook.setVisible(false);
				addAuthor.setVisible(false);
				addPublication.setVisible(true);
				addCategory.setVisible(false);
				addSubject.setVisible(false);
				welcome.setVisible(false);
				allBooks.setVisible(false);
				editStudent.setVisible(false);
				editStudentButtons.setVisible(false);
				issuedBook.setVisible(false);
			}
		});
		mnAdd.add(mntmPublication);
		
		JMenuItem mntmCategory = new JMenuItem("Category");
		mntmCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent.setVisible(false);
				addBook.setVisible(false);
				addAuthor.setVisible(false);
				addPublication.setVisible(false);
				addCategory.setVisible(true);
				addSubject.setVisible(false);
				welcome.setVisible(false);
				allBooks.setVisible(false);
				editStudent.setVisible(false);
				editStudentButtons.setVisible(false);
				issuedBook.setVisible(false);
			}
		});
		mnAdd.add(mntmCategory);
		
		JMenuItem mntmSubject = new JMenuItem("Subject");
		mntmSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent.setVisible(false);
				addBook.setVisible(false);
				addAuthor.setVisible(false);
				addPublication.setVisible(false);
				addCategory.setVisible(false);
				addSubject.setVisible(true);
				welcome.setVisible(false);
				allBooks.setVisible(false);
				editStudent.setVisible(false);
				editStudentButtons.setVisible(false);
				issuedBook.setVisible(false);
			}
		});
		mnAdd.add(mntmSubject);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setFocusable(false);
		menuBar.add(mnEdit);
		
		JMenuItem menuItem = new JMenuItem("Student");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent.setVisible(false);
				addBook.setVisible(false);
				addAuthor.setVisible(false);
				addPublication.setVisible(false);
				addCategory.setVisible(false);
				addSubject.setVisible(false);
				welcome.setVisible(false);
				allBooks.setVisible(false);
				editStudent.setVisible(true);
				editStudentButtons.setVisible(true);
				issuedBook.setVisible(false);
			}
		});
		mnEdit.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Book");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			SearchBook searchBook = new SearchBook();
			searchBook.setVisible(true);
			}
		});
		mnEdit.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("Author");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditAuthor editAuthor = new EditAuthor();
				editAuthor.setVisible(true);
			}
		});
		mnEdit.add(menuItem_2);
		
		JMenuItem menuItem_3 = new JMenuItem("Publication");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditPublication editPublication = new EditPublication();
				editPublication.setVisible(true);
			}
		});
		mnEdit.add(menuItem_3);
		
		JMenuItem menuItem_4 = new JMenuItem("Category");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			EditCategory editCategory = new EditCategory();
			editCategory.setVisible(true);
			}
		});
		mnEdit.add(menuItem_4);
		
		JMenuItem menuItem_5 = new JMenuItem("Subject");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditSubject editSubject = new EditSubject();
				editSubject.setVisible(true);
			}
		});
		mnEdit.add(menuItem_5);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(218, 218, 1, 1);
		contentPane.add(layeredPane);
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 471, Short.MAX_VALUE)
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 423, Short.MAX_VALUE)
		);
		layeredPane.setLayout(gl_layeredPane);
		
		welcome.setBounds(0, 102, 471, 265);
		contentPane.add(welcome);
		welcome.setVisible(true);
		
		addBook.setBounds(0, 18, 471, 443);
		contentPane.add(addBook);
		addBook.setVisible(false);
		
		addStudent.setBounds(0, 18, 471, 443);
		contentPane.add(addStudent);
		addStudent.setVisible(false);
		
		addAuthor.setBounds(0, 18, 471, 443);
		contentPane.add(addAuthor);
		addAuthor.setVisible(false);
		
		addPublication.setBounds(0, 18, 471, 443);
		contentPane.add(addPublication);
		addPublication.setVisible(false);
		
		addCategory.setBounds(0, 18, 471, 443);
		contentPane.add(addCategory);
		addCategory.setVisible(false);
		
		addSubject.setBounds(0, 18, 471, 443);
		contentPane.add(addSubject);
		addSubject.setVisible(false);
		
		allBooks.setBounds(0,18,471,443);
		contentPane.add(allBooks);
		allBooks.setVisible(false);
		
		editStudent.setBounds(0,18,471,370);
		contentPane.add(editStudent);
		editStudent.setVisible(false);
		
		issuedBook.setBounds(0,18,471,443);
		contentPane.add(issuedBook);
		issuedBook.setVisible(false);
		
		editStudentButtons.setBounds(0,400,471,60);
		contentPane.add(editStudentButtons);
		editStudentButtons.setVisible(false);
		
	}
}
