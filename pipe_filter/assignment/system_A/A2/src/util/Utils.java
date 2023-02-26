package util;

import util.student.Student;

import java.io.IOException;
import java.io.PipedOutputStream;

public class Utils {

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
	
	
}
