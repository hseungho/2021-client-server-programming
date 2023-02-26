package components.filter;

import constants.Constants;
import framework.CommonFilterImpl;
import util.function.Utils;
import util.object.Course;
import util.object.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class CheckFilter extends CommonFilterImpl{
	public CheckFilter(int portCount) {
		super(portCount);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean specificComputationForFilter() throws IOException {
		ArrayList<Student> vStudent = new ArrayList<>();
		Student student = new Student();
		vStudent = (ArrayList<Student>) Utils.readByteToObject(vIn.get(Constants.PORT_1), student);
		
		Hashtable<String, ArrayList<String>> hCourse = new Hashtable<>();
		Course course = new Course();
		hCourse = (Hashtable<String, ArrayList<String>>) Utils.readByteToObject(vIn.get(Constants.PORT_2), course);
		
		boolean isSatisfied = true;
		for(Student checkStudent : vStudent) {
			byte[] trueBuffer = new byte[64];
			byte[] falseBuffer = new byte[64];
			ArrayList<String> vCompletedCourse = checkStudent.getCompletedCourse();
			if(!vCompletedCourse.isEmpty()) {
				for(String completedCourse : vCompletedCourse) {
					ArrayList<String> vPrerequisite = hCourse.get(completedCourse);
					if(!vPrerequisite.isEmpty() && !Utils.isContainElementInList(vPrerequisite, vCompletedCourse)) {
						isSatisfied=false;
						break;
					}
				}
			}
			if(isSatisfied) {
				trueBuffer = checkStudent.toString().getBytes();
				Utils.writeToSink(vOut.get(Constants.PORT_1), trueBuffer);
			}
			else {
				falseBuffer = checkStudent.toString().getBytes();
				Utils.writeToSink(vOut.get(Constants.PORT_2), falseBuffer);
				isSatisfied=true;
			}
		}
		return true;
	}
}
