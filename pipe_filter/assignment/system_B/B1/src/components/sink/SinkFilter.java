package components.sink;

import constants.Constants;
import framework.CommonFilterImpl;
import util.function.Utils;

import java.io.FileWriter;
import java.io.IOException;

public class SinkFilter extends CommonFilterImpl{
    private final String src;
    
    public SinkFilter(int portCount, String src) {
    	super(portCount);
        this.src = src;
    }
    @Override
    public boolean specificComputationForFilter() throws IOException {
        FileWriter fw = new FileWriter(this.src);
        if(this.src.equals(Constants.OUTPUT_1_DATA_FILE))
        	Utils.writeBufferToFile(fw, vIn.get(Constants.PORT_1));
        else if(this.src.equals(Constants.OUTPUT_2_DATA_FILE))
        	Utils.writeBufferToFile(fw, vIn.get(Constants.PORT_2));
		return false;
    }
}
