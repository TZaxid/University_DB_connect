import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        StudentsModel students = new StudentsModel();
        ArrayList<Students> records = new ArrayList<Students>();
        Students s1 = new Students("Pupkin", "Vasia", "Zinov", 55, 4);
        students.insertStudent(s1);
        records = students.getAllRecords();
        for (Students st : records) {
            System.out.println(st.getStudentId() + " " + st.getName() + " " + st.getSurname() + " " + st.getGroupId());
        }
    }
}