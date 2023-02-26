package framework;

import components.middle.MiddleFilter;
import components.sink.SinkFilter;
import components.source.SourceFilter;

public class LifeCycleManager {

    public static void main(String[] args) {
        try {
            CommonFilter filterSource = new SourceFilter("resource/Students.txt");
            CommonFilter filterSink = new SinkFilter("resource/Output.txt");
            CommonFilter filterMid = new MiddleFilter();

            filterSource.connectOutputTo(filterMid);
            filterMid.connectOutputTo(filterSink);

            Thread threadFilterSource = new Thread(filterSource);
            Thread threadFilterSink = new Thread(filterSink);
            Thread threadFilterMid = new Thread(filterMid);

            threadFilterSource.start();
            threadFilterSink.start();
            threadFilterMid.start();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
