package doodlebobbuffpants.whatif.driver.exception;

public class WhatIfCloseWriterException extends WhatIfWriterException {
    public WhatIfCloseWriterException(Exception e) {
        super("Unable to close writer", e);
    }
}
