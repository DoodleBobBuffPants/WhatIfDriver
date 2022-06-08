package doodlebobbuffpants.whatif.driver;

import doodlebobbuffpants.whatif.driver.exception.WhatIfCloseWriterException;
import doodlebobbuffpants.whatif.driver.exception.WhatIfFlushWriterException;
import doodlebobbuffpants.whatif.driver.exception.WhatIfOpenWriterException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static doodlebobbuffpants.whatif.driver.exception.ExceptionUtils.append;

public class WhatIfWriter implements AutoCloseable {
    private final BufferedWriter writer;

    public WhatIfWriter(String filename) {
        try {
            this.writer = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            throw new WhatIfOpenWriterException(filename, e);
        }
    }

    public void writeLine(String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    @Override
    public void close() {
        RuntimeException toThrow = null;
        try {
            writer.flush();
        } catch (IOException e) {
            toThrow = new WhatIfFlushWriterException(e);
        }
        try {
            writer.close();
        } catch (IOException e) {
            toThrow = append(toThrow, new WhatIfCloseWriterException(e));
        }
        if (toThrow != null) throw toThrow;
    }
}
