package components.source;

import framework.CommonFilterImpl;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class SourceFilter extends CommonFilterImpl {
    private final String src;

    public SourceFilter(String src) {
        this.src = src;
    }

    @Override
    public boolean specificComputationForFilter() throws IOException {
        int byte_read;
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(src));

        while(true) {
            byte_read = br.read();
            if (byte_read == -1) return true;
            out.write(byte_read);
        }
    }
}
