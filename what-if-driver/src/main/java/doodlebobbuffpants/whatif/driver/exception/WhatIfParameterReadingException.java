package doodlebobbuffpants.whatif.driver.exception;

public class WhatIfParameterReadingException extends WhatIfException {
    public WhatIfParameterReadingException(Exception e) {
        super("Unable to read parameter", e);
    }
}
