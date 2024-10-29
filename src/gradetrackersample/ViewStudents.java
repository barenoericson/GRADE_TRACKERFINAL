
package gradetrackersample;

public class ViewStudents {
    public void getView() {
        config conf = new config();

        String sql = "SELECT id, s_fname, s_lname, s_email, s_course, PRELIM_GRADE, MIDTERM_GRADE, PREFINAL_GRADE, FINAL_GRADE, AVERAGE, STATUS FROM tbl_report";
        String[] headers = {"id, First Name", "Last Name", "Email", "Course", "Prelim Grade", "Midterm Grade", "Prefinal Grade", "Final Grade", "Average", "Status"};
        String[] columns = {"sid, s_fname", "s_lname", "s_email", "s_course", "PRELIM_GRADE", "MIDTERM_GRADE", "PREFINAL_GRADE", "FINAL_GRADE", "AVERAGE", "STATUS"};
        
        
        if (!conf.viewRecords(sql, headers, columns)) {
            System.out.println("No student records found.");
        }
    }
}
