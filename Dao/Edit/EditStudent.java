package Edit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import DBInfo;

public class EditStudent extends JPanel {

    private Connection con;

    public EditStudent() {
        this.con = DBInfo.getConn();
        Vector<String> colName = new Vector<>();
        Vector<Vector<String>> rowName = new Vector<>();
        String query = "SELECT name, username, password FROM student";
        populateTableData(query, colName, rowName);
        JTable table = new JTable(rowName, colName);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void populateTableData(String query, Vector<String> colName, Vector<Vector<String>> rowName) {
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet res = ps.executeQuery()) {

            ResultSetMetaData rsmd = res.getMetaData();
            int colCount = rsmd.getColumnCount();

            for (int i = 1; i <= colCount; i++) {
                colName.add(rsmd.getColumnName(i));
            }

            while (res.next()) {
                Vector<String> row = new Vector<>();
                for (int i = 1; i <= colCount; i++) {
                    row.add(res.getString(i));
                }
                rowName.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
