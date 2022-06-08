package doodlebobbuffpants.whatif.driver.exception;

public class WhatIfFlushWriterException extends WhatIfWriterException {
    public WhatIfFlushWriterException(Exception e) {
        super("Unable to write", e);
    }
}
