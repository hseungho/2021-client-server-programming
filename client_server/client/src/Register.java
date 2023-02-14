import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Register implements Serializable{
	private static final long serialVersionUID = 1L;
	
	protected String studentId;
	protected ArrayList<String> courseList;
	
	public Register(String studentId, String courseId) {
		this.studentId = studentId;
		this.courseList.add(courseId);
	}
	
	public Register(String inputString) {
		StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.courseList = new ArrayList<String>();
    	while(stringTokenizer.hasMoreTokens())
    		this.courseList.add(stringTokenizer.nextToken());
	}
	
    public boolean match(String studentId) {
        return this.studentId.equals(studentId);
    }
    
    public boolean checkAlreadyRegister(String courseId) {
    	for(String course: this.courseList) {
    		if(course.equals(courseId)) return true;
    	}
    	return false;
    }
    
    public void addCourse(String courseId) {
    	this.courseList.add(courseId);
    }
    
    public String getStudentId() {
    	return this.studentId;
    }
    public ArrayList<String> getCourseList(){
    	return this.courseList;
    }

	@Override
    public String toString() {
//		String stringReturn = this.studentId + " ";
		String stringReturn = "";
        for (int i = 0; i < this.courseList.size(); i++) {
            stringReturn = stringReturn + " " + this.courseList.get(i).toString();
        }
        return stringReturn;
    }
}
