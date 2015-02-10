import java.sql.*;
import java.util.ArrayList;

public class StudentsModel {
    private final static String USER = "zaxid";
    private final static String PSWD = "02580258";
    private final static String URL = "jdbc:postgresql:university";
    private final String TABLE = "students";
    private final String[] COLUMS  = {"surname", "name", "fname", "student_id", "group_id"};
    private Statement st;
    private ResultSet result;
    private Connection conn;
    private ArrayList<Students> students;

    public StudentsModel() {
        conn = null;
        this.students = new ArrayList<Students>();
        try {
            conn = DriverManager.getConnection(URL,
                    USER, PSWD);
            st = conn.createStatement();
        } catch (SQLException e) {
            System.out.println("Connection fail!");
            System.exit(0);
        }
    }

    public ArrayList<Students> getAllRecords() {
        result = null;
        try {
            result = st.executeQuery("SELECT * FROM " + TABLE + " ORDER BY surname");
            while (result.next()) {
                Students record = new Students(result.getString("surname"),
                        result.getString("name"), result.getString("fname"),
                        result.getInt("student_id"), result.getInt("group_id"));
                students.add(record);
            }
            result.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Some problem with database");
        }
        return students;
    }

    public String[] getColumsNames() {
        return COLUMS;
    }

    public void insertStudent(Students student) {
        try {
            PreparedStatement prepStat = conn.prepareStatement("INSERT INTO students VALUES (?, ?, ?, ?, ?)");
            prepStat.setString(1, student.getSurname());
            prepStat.setString(2, student.getName());
            prepStat.setString(3, student.getFname());
            prepStat.setInt(4, student.getStudentId());
            prepStat.setInt(5, student.getGroupId());
            prepStat.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error");
        }
    }

}
