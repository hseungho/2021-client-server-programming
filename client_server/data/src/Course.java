import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String courseId;
	protected String profName;
	protected String courseName;
	protected ArrayList<String> prerequisite;
	
	public Course(String inputString) {
		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
		this.courseId = stringTokenizer.nextToken();
		this.profName = stringTokenizer.nextToken();
		this.courseName = stringTokenizer.nextToken();
		this.prerequisite = new ArrayList<String>();
		while(stringTokenizer.hasMoreTokens()) {
			this.prerequisite.add(stringTokenizer.nextToken());
		}
	}

    public boolean match(String courseId) {
        return this.courseId.equals(courseId);
    }

    public String getCourseName() {
        return this.courseName;
    }
    
    public String getProfName() {
    	return this.profName;
    }

    public ArrayList<String> getPrerequisite() {
        return this.prerequisite;
    }

    public String toString() {
        String stringReturn = this.courseId + " " + this.profName + " " + this.courseName;
        for (int i = 0; i < this.prerequisite.size(); i++) {
            stringReturn = stringReturn + " " + this.prerequisite.get(i).toString();
        }
        return stringReturn;
    }

	
}
