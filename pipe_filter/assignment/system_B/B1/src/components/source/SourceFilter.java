/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package components.source;

import constants.Constants;
import framework.CommonFilterImpl;
import util.function.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SourceFilter extends CommonFilterImpl{
    private final String src;
    
    public SourceFilter(int portCount, String src){
    	super(portCount);
        this.src = src;
    }
    @Override
    public boolean specificComputationForFilter() throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(src)));
        if(this.src.equals(Constants.STUDENT_DATA_FILE))
        	Utils.readFile(bis, vOut.get(Constants.PORT_1));
        else if(this.src.equals(Constants.COURSE_DATA_FILE))
        	Utils.readFile(bis, vOut.get(Constants.PORT_2));
        return false;
    }
	
}
