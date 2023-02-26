package components.filter;

import framework.CommonFilterImpl;
import util.Utils;
import util.student.Student;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DelCourseFilter extends CommonFilterImpl {

	@Override
	public boolean specificComputationForFilter() throws IOException {
		try {
			String studentId_2013 = "2013";
			String course_17651 = "17651";
			String course_17652 = "17652";
			String major = "CS";
			
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(super.in));
			ObjectOutputStream oos = new ObjectOutputStream(super.out);
			Student checkStudent = null;
			while(true) {
				Student student = (Student) ois.readObject();
				checkStudent = Utils.checkYearOfAdmissionInStudentId(student, studentId_2013);
				if(checkStudent!=null && !checkStudent.getStudentMajor().equals(major)) {
					Utils.deleteCourse(checkStudent, course_17651);
					Utils.deleteCourse(checkStudent, course_17652);
				}
				oos.writeObject(student);
			}
		}
		catch(ClassNotFoundException e) { e.printStackTrace(); }
		return false;
		
	}

}
