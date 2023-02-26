/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package components.sink;

import framework.CommonFilterImpl;

import java.io.FileWriter;
import java.io.IOException;

public class SinkFilter extends CommonFilterImpl{
    private final String src;
    
    public SinkFilter(String src) {
        this.src = src;
    }

    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read;
        FileWriter fw = new FileWriter(this.src);
        while(true) {
            byte_read = in.read(); 
            if (byte_read == -1) {
            	 fw.close();
                 System.out.print( "::Filtering is finished; Output file is created." );  
                 return true;
            }
            fw.write((char)byte_read);
        }   
    }
}
