import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class IssuedBook extends JPanel {

    public IssuedBook() {
        List<String> columnNames = new ArrayList<>();
        List<List<String>> data = new ArrayList<>();

        String query = "SELECT * FROM issuedbooks";
        try (Connection con = Db.DBInfo.getConn();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(rsmd.getColumnName(i));
            }

            while (rs.next()) {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Melhor prática seria usar um logger
        }

        JTable table = new JTable(data.toArray(new Object[][] {}), columnNames.toArray());
        JScrollPane pane = new JScrollPane(table);
        add(pane);
    }
}
