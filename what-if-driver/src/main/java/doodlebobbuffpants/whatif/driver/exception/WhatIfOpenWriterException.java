package doodlebobbuffpants.whatif.driver.exception;

public class WhatIfOpenWriterException extends WhatIfWriterException {
    public WhatIfOpenWriterException(String filename, Exception e) {
        super("Unable to open writer for " + filename, e);
    }
}
