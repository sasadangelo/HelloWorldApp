package org.androidforfun.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
    InputStream readFile(String fileName) throws IOException;
    OutputStream writeFile(String fileName) throws IOException;
}
