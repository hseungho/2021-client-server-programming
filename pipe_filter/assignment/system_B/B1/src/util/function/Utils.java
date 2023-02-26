package util.function;

import util.object.Course;
import util.object.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class Utils {
	
	public static boolean readFile(BufferedInputStream br, PipedOutputStream out) throws IOException {
		int byte_read;
		while(true) {
			byte_read = br.read();
			if (byte_read == -1) return true;
			out.write(byte_read);
		}
	}
	public static int readByteToBuffer(PipedInputStream in, ArrayList<Byte> buffer) throws IOException {
		int byte_read=0;
		while(byte_read != '\n' && byte_read != -1) {
			byte_read = in.read();
			if(byte_read != -1 && byte_read!=13 && byte_read!=10) buffer.add((byte)byte_read);
		}
		return byte_read;
	}
	public static void writeToSink(PipedOutputStream out, byte[] buffer) throws IOException {
		for(byte byte_data : buffer)
			out.write((char)byte_data);
		out.write(13);
		out.write(10);
	}
	public static boolean writeBufferToFile(FileWriter fw, PipedInputStream in) throws IOException {
		int byte_read;
		while(true) {
            byte_read = in.read(); 
            if (byte_read == -1) {
            	 fw.close();
                 System.out.println( "::Filtering is finished; Output file is created." );  
                 return true;
            }
            fw.write((char)byte_read);
        }
	}
	
	public static Object readByteToObject(PipedInputStream in, Object object) throws IOException {
		int byte_read;
		if(object instanceof Student) {
			ArrayList<Student> vStudent = new ArrayList<>();
			while(true) {
				ArrayList<Byte> buffer = new ArrayList<Byte>();
				byte_read = Utils.readByteToBuffer(in, buffer);
				if((byte_read == '\n'||byte_read==-1) && buffer.size()!=0) {
					Student student = (Student) Utils.createObject(buffer, object);
	    			vStudent.add(student);
				}
				if (byte_read == -1) break;
			}
			return vStudent;
		}
		else if(object instanceof Course) {
			Hashtable<String, ArrayList<String>> hCourse = new Hashtable<>();
			while(true) {
				ArrayList<Byte> buffer = new ArrayList<Byte>();
				byte_read = Utils.readByteToBuffer(in, buffer);
				if((byte_read == '\n'||byte_read==-1) && buffer.size()!=0) {
					Course course = (Course) Utils.createObject(buffer, object);
					hCourse.put(course.getCourseId(), course.getPrerequisite());
				}
				if (byte_read == -1) break;
			}
			return hCourse;
		}
		else return null;
	}
	public static Object createObject(ArrayList<Byte> buffer, Object object) throws IOException {
    	if(object instanceof Student) {
    		Student student = (Student) object;
    		String studentData="";
    		for(byte b : buffer) 
    			studentData+=(char)b;
    		if(!studentData.equals(""))
    			student = new Student(studentData);
    		return student;
    	}
    	else if(object instanceof Course) {
    		Course course = (Course) object;
    		String courseData="";
    		for(byte b : buffer) 
    			courseData+=(char)b;
    		if(!courseData.equals(""))
    			course = new Course(courseData);
    		return course;
    	}
    	else return null;
    }
	
	////////////////////////////////////////////////////////////////////////////
	/*
	 *  ---- About Checking Data ---- 
	 */
	public static Student checkYearOfAdmissionInStudentId(Student student, String studentId) {
		if(student.getStudentId().contains(studentId)) return student;
		else return null;
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
	public static boolean isContainElementInList(ArrayList<String> criteriaList, ArrayList<String> targetList) {
		ArrayList<String> comparisonTool = new ArrayList<String>(criteriaList);
		comparisonTool.removeAll(targetList);
		if(comparisonTool.size()==0) return true;
		else return false;
	}
	
}
