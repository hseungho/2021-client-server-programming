package components.addFilter;

import framework.CommonFilterImpl;
import util.Utils;
import util.student.Student;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CSAddCourseFilter extends CommonFilterImpl {

	@Override
	public boolean specificComputationForFilter() throws IOException {
		try {
			String course_12345 = "12345";
			String course_23456 = "23456";
			String major = "CS";
			
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(super.in));
			ObjectOutputStream oos = new ObjectOutputStream(super.out);
			while(true) {
				Student student = (Student) ois.readObject();
				if(student.getStudentMajor().equals(major)) {
					Utils.addCourse(student, course_12345);
					Utils.addCourse(student, course_23456);
				}
				oos.writeObject(student);
			}
		}
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		return false;
	}


}
