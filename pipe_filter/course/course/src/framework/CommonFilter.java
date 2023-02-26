package framework;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public interface CommonFilter extends Runnable {
    void connectOutputTo(CommonFilter filter) throws IOException;
    void connectInputTo(CommonFilter filter) throws IOException;
    PipedInputStream getPipedInputStream();
    PipedOutputStream getPipedOutputStream();
}
