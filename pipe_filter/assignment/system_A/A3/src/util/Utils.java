package util;

import util.student.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PipedOutputStream;

public class Utils {
	
	public static void writeToOutput(ObjectInputStream in, PipedOutputStream out) throws IOException, ClassNotFoundException {
		Object object = (Object) in.readObject();
		writeObjectToBuffer(object, out);
	}
	public static void writeObjectToBuffer(Object object, PipedOutputStream out) throws IOException {
		byte[] buffer = new byte[object.toString().length()];
		buffer = object.toString().getBytes();
		for(int i=0; i<buffer.length; i++)
			out.write(buffer[i]);
		out.write(13);
		out.write(10);
	}

	public static void addCourse(Student student, String course) {
		for(int i=0; i<student.getCompletedCourse().size(); i++)
			if(!student.getCompletedCourse().contains(course))
				student.getCompletedCourse().add(course);
	}

	public static void deleteCourse(Student student, String course) {
		for(int i=0; i<student.getCompletedCourse().size(); i++)
			if(student.getCompletedCourse().contains(course))
				student.getCompletedCourse().remove(course);
	}

	public static Student checkYearOfAdmissionInStudentId(Student student, String studentId) {
		if(student.getStudentId().contains(studentId)) return student;
		else return null;
	}
	
}
