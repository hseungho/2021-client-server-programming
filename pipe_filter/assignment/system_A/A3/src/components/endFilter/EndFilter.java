package components.endFilter;

import framework.CommonFilterImpl;
import util.Utils;

import java.io.IOException;
import java.io.ObjectInputStream;

public class EndFilter extends CommonFilterImpl{

	@Override
	public boolean specificComputationForFilter() throws IOException {
		ObjectInputStream ois = new ObjectInputStream(super.in);
		try {
			while(true) {
				Utils.writeToOutput(ois, out);
			} 
		}
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		return false;
	}


}
