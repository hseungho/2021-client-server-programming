package framework;

import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public abstract class CommonFilterImpl implements CommonFilter {

    protected PipedInputStream in = new PipedInputStream();
    protected PipedOutputStream out = new PipedOutputStream();

    public abstract boolean specificComputationForFilter() throws IOException;

    @Override
    public void connectOutputTo(CommonFilter filter) throws IOException {
        out.connect(filter.getPipedInputStream());
    }

    @Override
    public void connectInputTo(CommonFilter filter) throws IOException {
        in.connect(filter.getPipedOutputStream());
    }

    @Override
    public PipedInputStream getPipedInputStream() {
        return in;
    }

    @Override
    public PipedOutputStream getPipedOutputStream() {
        return out;
    }

    @Override
    public void run() {
        try {
            specificComputationForFilter();
        } catch (IOException e) {
            if(e instanceof EOFException) return;
            e.printStackTrace();
        } finally {
            close();
        }
    }

    private void close() {
        try {
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
