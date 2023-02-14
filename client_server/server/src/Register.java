import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Register implements Serializable {

    private String studentId;
    private List<String> courseIds;

    public Register(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseIds.add(courseId);
    }

    public Register(String inputString) {
        StringTokenizer stn = new StringTokenizer(inputString);
        this.studentId = stn.nextToken();
        this.courseIds = new ArrayList<>();
        while (stn.hasMoreTokens()) {
            this.courseIds.add(stn.nextToken());
        }
    }

    public boolean checkAlreadyRegister(String courseId) {
        return this.courseIds.stream()
                .anyMatch(id -> Objects.equals(id, courseId));
    }

    public void addCourse(String courseId) {
        this.courseIds.add(courseId);
    }

    public boolean match(String studentId) {
        return Objects.equals(this.studentId, studentId);
    }
    public String getStudentId() {
        return studentId;
    }

    public List<String> getCourseIds() {
        return courseIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Register register = (Register) o;

        return studentId.equals(register.studentId);
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }

    @Override
    public String toString() {
        return "Register{" +
                "studentId='" + studentId + '\'' +
                ", courseIds=" + courseIds +
                '}';
    }
}
