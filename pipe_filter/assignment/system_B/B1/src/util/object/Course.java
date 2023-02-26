package util.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String courseId;
	private String profName;
	private String courseName;
	private ArrayList<String> prerequisite;
	
	public Course() { return; }
	
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
	public String getCourseId() { return courseId; }
	public String getProfName() { return profName; }
	public String getCourseName() { return courseName; }
	public ArrayList<String> getPrerequisite() { return prerequisite; }
	
	public boolean match(String courseId) {
        return this.courseId.equals(courseId);
    }
  
	public String toString() {
		String courseData = courseId+" "+profName+" "+courseName;
		for(String course: this.prerequisite)
			courseData+=" "+course;
		return courseData;
	}

}
