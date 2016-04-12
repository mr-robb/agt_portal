package mx.com.ebs.inter.util.file;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public final class ObjectOuputStreamAppender extends ObjectOutputStream {

    public ObjectOuputStreamAppender(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }

}