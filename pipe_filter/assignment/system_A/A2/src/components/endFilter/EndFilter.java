package components.endFilter;

import framework.CommonFilterImpl;
import util.Utils;
import util.student.Student;

import java.io.IOException;
import java.io.ObjectInputStream;

public class EndFilter extends CommonFilterImpl {

	@Override
	public boolean specificComputationForFilter() throws IOException {
		ObjectInputStream ois = new ObjectInputStream(super.in);
		try {
			while(true) {
				Student student = (Student) ois.readObject();
				Utils.writeObjectToBuffer(student, out);
			}
		}
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		return false;
	}


}
