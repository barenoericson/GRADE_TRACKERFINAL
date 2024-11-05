package gradetrackersample;

public class ViewReports {

    private config conf;

    public ViewReports() {
        conf = new config();
    }

    public void displayReports() {
        String sqlQuery = "SELECT sid, s_fname, s_lname, s_email, s_course, PRELIM_GRADE, MIDTERM_GRADE, PREFINAL_GRADE, FINAL_GRADE, AVERAGE, STATUS FROM tbl_report";
        
        String[] columnHeaders = {"ID", "First Name", "Last Name", "Email", "Course", "Prelim Grade", "Midterm Grade", "Prefinal Grade", "Final Grade", "Average", "Status"};
        String[] columnNames = {"sid", "s_fname", "s_lname", "s_email", "s_course", "PRELIM_GRADE", "MIDTERM_GRADE", "PREFINAL_GRADE", "FINAL_GRADE", "AVERAGE", "STATUS"};

        try {
            boolean recordsFound = conf.viewRecords(sqlQuery, columnHeaders, columnNames);
            if (!recordsFound) {
                System.out.println("No student records found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving reports: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
