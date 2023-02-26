package util.student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String studentId;
	private String studentName;
	private String studentMajor;
	private ArrayList<String> completedCourse;
	
	public Student(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.studentId = stringTokenizer.nextToken();
    	this.studentName = stringTokenizer.nextToken();
    	this.studentName = this.studentName + " " + stringTokenizer.nextToken();
    	this.studentMajor = stringTokenizer.nextToken();
    	this.completedCourse = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.completedCourse.add(stringTokenizer.nextToken());
    	}
    }
	
	public String getStudentId() { return studentId; }
	public String getStudentName() { return studentName; }
	public String getStudentMajor() { return studentMajor; }
	public ArrayList<String> getCompletedCourse() {	return completedCourse; }

	public String toString() {
		String studentData =studentId+" "+studentName+" "+studentMajor;
		for(String course : completedCourse)
			studentData  += " "+course;
		return studentData ;
	}
}
