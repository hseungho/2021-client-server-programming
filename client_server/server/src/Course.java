import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Course implements Serializable {

    private final String id;
    private String name;
    private String profName;
    private List<String> prerequisites;

    public Course(String inputString) {
        StringTokenizer stn = new StringTokenizer(inputString);
        this.id = stn.nextToken();
        this.profName = stn.nextToken();
        this.name = stn.nextToken();
        this.prerequisites = new ArrayList<>();
        while (stn.hasMoreTokens()) {
            this.prerequisites.add(stn.nextToken());
        }
    }

    public boolean match(String id) {
        return Objects.equals(this.id, id);
    }

    public String getName() {
        return name;
    }

    public String getProfName() {
        return profName;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profName='" + profName + '\'' +
                ", prerequisites=" + prerequisites +
                '}';
    }
}
