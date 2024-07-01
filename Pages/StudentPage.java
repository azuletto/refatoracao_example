import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class StudentPage extends JFrame {

	private JPanel contentPane;
	private String uName;

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
					StudentPage frame = new StudentPage("");
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
	public StudentPage(String str) {
		
	  uName = str;
	   Vector<String> cols_vector=new Vector<>();
	   Vector<Vector<String>> totalRows_vector=new Vector<>();
	   Connection con=DBInfo.getConn();
	   String query="select bookid, bookname, issueddate, submitiondate from issuedbooks where studentusername = ?";
	   try
	   {
	   PreparedStatement ps=con.prepareStatement(query);
	   ps.setString(1, uName);
	   ResultSet res=ps.executeQuery();
	   
	   //separating columns name
	   ResultSetMetaData rsmd=res.getMetaData();
	   int colCount=rsmd.getColumnCount();
	   for(int i=1;i<=colCount;i++)
	   {
		   String colName=rsmd.getColumnName(i);
		   cols_vector.add(colName);//stored all cols name into a vector
	   }
	   
	   //separating rows
	   while(res.next())
	   {
		   Vector<String> rows=new Vector<>();
		   for(int i=1;i<=colCount;i++)
		   {
			   String value=res.getString(i);
			   rows.add(value);
		   }
		   
		   totalRows_vector.add(rows);
	   }
	   
	   
	   
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
	   
	   JTable table=new JTable(totalRows_vector, cols_vector);
	   JScrollPane pane=new JScrollPane(table);
	
	
	
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(pane, GroupLayout.PREFERRED_SIZE, 466, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(pane, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
		setTitle("Student Page");
		setLocationRelativeTo(this);
	}

}
