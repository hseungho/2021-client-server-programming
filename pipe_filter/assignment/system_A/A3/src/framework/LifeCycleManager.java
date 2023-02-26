/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University.
 */
package framework;

import components.endFilter.EndFilter;
import components.filter.DelCourseFilter;
import components.setFilter.SetStudentFilter;
import components.sink.SinkFilter;
import components.source.SourceFilter;

import java.util.ArrayList;

public class LifeCycleManager {
    public static void main(String[] args) {
    	ArrayList<CommonFilter> filters = new ArrayList<CommonFilter>();
        try {
        	 CommonFilter fSource = new SourceFilter("resource/Students.txt"); filters.add(fSource);
             CommonFilter fStudentSet = new SetStudentFilter(); filters.add(fStudentSet);
             CommonFilter fNotCSDelCourse = new DelCourseFilter(); filters.add(fNotCSDelCourse);
             CommonFilter fEnd = new EndFilter(); filters.add(fEnd);
             CommonFilter fSink = new SinkFilter("resource/Output.txt");filters.add(fSink);
             
             for(int i=0; i<filters.size(); i++) {
            	 if(i<filters.size()-1) filters.get(i).connectOutputTo(filters.get(i+1));
            	 Thread thread = new Thread(filters.get(i));
            	 thread.start();
             }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
