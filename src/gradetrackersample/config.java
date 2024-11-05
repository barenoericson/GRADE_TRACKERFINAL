package gradetrackersample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class config {

    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); 
            con = DriverManager.getConnection("jdbc:sqlite:gradetracker.db");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
        return con;
    }

    public boolean recordExists(String sql, int id) {
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if the record exists
        } catch (SQLException e) {
            System.out.println("Error checking record existence: " + e.getMessage());
            return false;
        }
    }

    // Overloaded recordExists method to check specifically by student ID
    public boolean recordExistsById(int id) {
        String query = "SELECT COUNT(*) FROM tbl_report WHERE sid = ?";
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0; // Returns true if count is greater than 0
        } catch (SQLException e) {
            System.out.println("Error checking record existence: " + e.getMessage());
            return false;
        }
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    public void addRecord(String sql, Object... values) {
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setPreparedStatementValues(pstmt, values);
            pstmt.executeUpdate();
            System.out.println("Record added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding record: " + e.getMessage());
        }
    }

    public boolean viewRecords(String sqlQuery, String[] columnHeaders, String[] columnNames) {
        if (columnHeaders.length != columnNames.length) {
            System.out.println("Error: Mismatch between column headers and column names.");
            return false;
        }

        try (Connection conn = connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No records found.");
                return false; // No records
            }

            System.out.println("-------------------------------------------------------------");
            for (String header : columnHeaders) {
                System.out.printf("%-20s | ", header);
            }
            System.out.println();
            System.out.println("-------------------------------------------------------------");

            while (rs.next()) {
                for (String colName : columnNames) {
                    try {
                        String value = rs.getString(colName);
                        System.out.printf("%-20s | ", value != null ? value : "N/A");
                    } catch (SQLException e) {
                        System.out.printf("%-20s | ", "N/A");
                    }
                }
                System.out.println();
            }
            System.out.println("-------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error retrieving records: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }

  public void updateRecord(String query, String fname, String lname, String email, 
                         String course, double pg, double mg, double pfg, 
                         double fg, double average, String status, int id) throws SQLException {
    
    try (Connection conn = this.connectDB();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        
        // Set the prepared statement parameters
        pstmt.setString(1, fname);
        pstmt.setString(2, lname);
        pstmt.setString(3, email);
        pstmt.setString(4, course);
        pstmt.setDouble(5, pg);
        pstmt.setDouble(6, mg);
        pstmt.setDouble(7, pfg);
        pstmt.setDouble(8, fg);
        pstmt.setDouble(9, average);
        pstmt.setString(10, status);
        pstmt.setInt(11, id);

        // Execute the update
        pstmt.executeUpdate();
        System.out.println("Record updated successfully!");
    } catch (SQLException e) {
        System.out.println("Error updating record: " + e.getMessage());
        throw e; // Rethrow the exception if needed
    }
}


    // Overloaded deleteRecord method for deleting all records without parameters
    public void deleteRecord(String sql) {
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("Records deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting records: " + e.getMessage());
        }
    }

    // Existing deleteRecord method with parameters
    public void deleteRecord(String sql, Object... values) {
        try (Connection conn = this.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof Integer) {
                    pstmt.setInt(i + 1, (Integer) values[i]);
                } else {
                    pstmt.setString(i + 1, values[i].toString());
                }
            }

            pstmt.executeUpdate();
            System.out.println("Record deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }

    // Updated method to set values in PreparedStatement, handling double values
    private void setPreparedStatementValues(PreparedStatement pstmt, Object... values) throws SQLException {
        for (int i = 0; i < values.length; i++) {
            if (values[i] instanceof Integer) {
                pstmt.setInt(i + 1, (Integer) values[i]);
            } else if (values[i] instanceof String) {
                pstmt.setString(i + 1, (String) values[i]);
            } else if (values[i] instanceof Double) {  // Added check for Double values
                pstmt.setDouble(i + 1, (Double) values[i]);
            } else {
                pstmt.setObject(i + 1, values[i]); // Fallback for any other types
            }
        }
    }
}
