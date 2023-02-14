import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Student implements Serializable {
    private final String id;
    private String name;
    private String department;
    private List<String> completedCourses;

    public Student(String inputString) {
        StringTokenizer stn = new StringTokenizer(inputString);
        this.id = stn.nextToken();
        this.name = stn.nextToken();
        this.name = this.name + " " + stn.nextToken();
        this.department = stn.nextToken();
        this.completedCourses = new ArrayList<>();
        while (stn.hasMoreTokens()) {
            this.completedCourses.add(stn.nextToken());
        }
    }

    public String getName() {
        return name;
    }

    public List<String> getCompletedCourses() {
        return completedCourses;
    }

    public boolean match(String id) {
        return Objects.equals(this.id, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", completedCourses=" + completedCourses +
                '}';
    }
}
