import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class EditStudent extends JPanel {

	Connection con = DBInfo.getConn();
	public EditStudent() {

		Vector<String> colName = new Vector<>();
		Vector<Vector<String>> rowName = new Vector<>();		
		String query = "Select name, username, password from student";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			ResultSetMetaData rsmd = res.getMetaData();
			int colCount = rsmd.getColumnCount();
			for(int i = 1;i<=colCount;i++)
			{
				String names = rsmd.getColumnName(i);
				colName.add(names);
			}
			while(res.next())
			{
				Vector<String> rows = new Vector<>();
				for(int i = 1;i<=colCount;i++)
				{
					String value = res.getString(i);
					rows.add(value);
				}
				rowName.add(rows);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JTable table = new JTable(rowName, colName);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
	}

}
