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
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class IssuedBook extends JPanel {

	public IssuedBook() {
		
		   Vector<String> cols_vector=new Vector<>();
		   Vector<Vector<String>> totalRows_vector=new Vector<>();
		   Connection con=DBInfo.getConn();
		   String query="select * from issuedbooks";
		   try
		   {
		   PreparedStatement ps=con.prepareStatement(query);
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
		   add(pane);

	}
}