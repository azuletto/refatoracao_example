import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
public class DBInfo1 {

	static
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public static Connection getConn()
	{
		Connection con=null;
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","");
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return con;
	}
	
	public static Vector<String> getValues(String name)
	{
		Vector<String> vector = new Vector<>();
		String q = "select name from "+name+" order by name";
		Connection con = DBInfo1.getConn();
		try {
			PreparedStatement ps = con.prepareStatement(q);
			ResultSet res = ps.executeQuery();
			while(res.next())
			{
				vector.add(res.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	
	
}
