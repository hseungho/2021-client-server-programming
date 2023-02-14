import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CourseList {
	protected ArrayList<Course> vCourse;
	private File courseFile;
	
	public CourseList(String cCourseFileName) throws FileNotFoundException, IOException {
		this.courseFile = new File(cCourseFileName);
		BufferedReader objCourseFile = new BufferedReader(new FileReader(this.courseFile));
		this.vCourse = new ArrayList<Course>();
		while(objCourseFile.ready()) {
			String corInfo = objCourseFile.readLine();
			if(!corInfo.equals(""))
				this.vCourse.add(new Course(corInfo));
		}
		objCourseFile.close();
	}
	
	public ArrayList<Course> getAllCourseRecords() throws MyException.NullDataException{
		if(this.vCourse.size()==0) throw new MyException.NullDataException("~~~~~~~~Course data is null~~~~~~~~~");
		return this.vCourse;
	}
	
	public boolean addCourseRecord(String courseInfo) throws MyException.DuplicationDataException {
		String[] strCourseInfo = courseInfo.split(" ");
		String courseId = strCourseInfo[0];
		for(Course course:this.vCourse)
			if(course.match(courseId)) throw new MyException.DuplicationDataException("~~~~~~~Course ID "+courseId+" is already exists!!!~~~~~");
		if(this.vCourse.add(new Course(courseInfo))) return true;
		else return false;
	}
	
	public boolean deleteCourseRecord(String courseId) {
		for(Course course : this.vCourse) {
			if(course.match(courseId)) {
				if(this.vCourse.remove(course)) return true;
				else return false;
			}
		}
		return false;
	}
	
	public boolean isRegisteredCourse(String cCID) {
		for(int i=0; i<this.vCourse.size(); i++) {
			Course objCourse = (Course) this.vCourse.get(i);
			if(objCourse.match(cCID))
				return true;
		}
		return false;
	}

	public void saveData() throws IOException {
		if(!this.courseFile.exists()) {
			if(this.courseFile.createNewFile())
				System.out.println("Create New File !!!");
			else {
				System.out.println("Cannot Create New File !!!");
				return;
			}
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(this.courseFile, false));
		for(int i=0; i<this.vCourse.size(); i++) {
			writer.write(this.vCourse.get(i).toString()+"\n");
		}
		writer.flush();
		writer.close();
	}

}
