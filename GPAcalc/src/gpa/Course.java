package gpa;


/**
 * @author khalid
 *
 * Exam Program by khalid
 * User: khalid
 * Date: 2/14/13
 */
public class Course {

    private String courseName;
    private String GPA;

    Course(String GPA, String courseName ){
        this.courseName  =   courseName;
        this.GPA  =   GPA;

    }

    public String getCourse() {
        return courseName;
    }
    public String getGPA() {
        return GPA;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(GPA+";");
        sb.append(courseName);
        return sb.toString();

    }


}
