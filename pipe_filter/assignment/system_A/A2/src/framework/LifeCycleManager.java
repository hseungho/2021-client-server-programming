package framework;

import components.addFilter.EEAddCourseFilter;
import components.endFilter.EndFilter;
import components.setFilter.SetStudentFilter;
import components.sink.SinkFilter;
import components.source.SourceFilter;

import java.util.ArrayList;

public class LifeCycleManager {

    public static void main(String[] args) {
        ArrayList<CommonFilter> filters = new ArrayList<>();
        try {
            CommonFilter fSource = new SourceFilter("resource/Students.txt"); filters.add(fSource);
            CommonFilter fStudentSet = new SetStudentFilter(); filters.add(fStudentSet);
            CommonFilter fEEAddCourse = new EEAddCourseFilter(); filters.add(fEEAddCourse);
            CommonFilter fEnd = new EndFilter(); filters.add(fEnd);
            CommonFilter fSink = new SinkFilter("resource/Output.txt");filters.add(fSink);

            fSource.connectOutputTo(fStudentSet);
            fStudentSet.connectOutputTo(fEEAddCourse);
            fEEAddCourse.connectOutputTo(fEnd);
            fEnd.connectOutputTo(fSink);

            for(CommonFilter filter : filters) {
                Thread thread = new Thread(filter);
                thread.start();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
