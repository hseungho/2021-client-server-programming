/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package framework;

import components.filter.CheckFilter;
import components.sink.SinkFilter;
import components.source.SourceFilter;
import constants.Constants;

import java.util.ArrayList;

public class LifeCycleManager {
	private static ArrayList<CommonFilter> vFilter = new ArrayList<CommonFilter>();
	
    public static void main(String[] args) {
        try {
        	
	    	CommonFilter fStudentSource = new SourceFilter(Constants.PORT_SIZE, Constants.STUDENT_DATA_FILE);
	    	CommonFilter fCourseSource = new SourceFilter(Constants.PORT_SIZE, Constants.COURSE_DATA_FILE); 		
	        CommonFilter fCheck = new CheckFilter(Constants.PORT_SIZE);
	        CommonFilter fTrueSink = new SinkFilter(Constants.PORT_SIZE, Constants.OUTPUT_1_DATA_FILE);
	        CommonFilter fFalseSink = new SinkFilter(Constants.PORT_SIZE, Constants.OUTPUT_2_DATA_FILE); 			
	        fStudentSource.connectOutputTo(Constants.PORT_1, fCheck);
	        fCourseSource.connectOutputTo(Constants.PORT_2, fCheck);
	        fCheck.connectOutputTo(Constants.PORT_1, fTrueSink);
	        fCheck.connectOutputTo(Constants.PORT_2, fFalseSink);
	        
	        vFilter.add(fStudentSource);
	        vFilter.add(fCourseSource);
	        vFilter.add(fCheck);
	        vFilter.add(fTrueSink);
	        vFilter.add(fFalseSink);
	        
	        for(CommonFilter filter : vFilter) {
	        	Thread thread = new Thread(filter);
	        	thread.start();
	        }
        } 
        catch(Exception e) { e.printStackTrace(); }
    }
}
