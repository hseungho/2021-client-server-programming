package framework;

import components.addFilter.CSAddCourseFilter;
import components.endFilter.EndFilter;
import components.setFilter.SetStudentFilter;
import components.sink.SinkFilter;
import components.source.SourceFilter;

import java.util.ArrayList;

public class LifeCycleManager {

    public static void main(String[] args) {
        ArrayList<CommonFilter> filters = new ArrayList<>();
        try {
            CommonFilter fSource = new SourceFilter("resource/Students.txt");
            CommonFilter fStudentSet = new SetStudentFilter();
            CommonFilter fCSAddCourse = new CSAddCourseFilter();
            CommonFilter fEnd = new EndFilter();
            CommonFilter fSink = new SinkFilter("resource/Output.txt");

            fSource.connectOutputTo(fStudentSet);
            fStudentSet.connectOutputTo(fCSAddCourse);
            fCSAddCourse.connectOutputTo(fEnd);
            fEnd.connectOutputTo(fSink);

            filters.add(fSource);
            filters.add(fStudentSet);
            filters.add(fCSAddCourse);
            filters.add(fEnd);
            filters.add(fSink);

            for(CommonFilter filter : filters) {
                Thread thread = new Thread(filter);
                thread.start();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
