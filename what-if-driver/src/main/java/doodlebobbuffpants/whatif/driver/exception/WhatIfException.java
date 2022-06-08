package doodlebobbuffpants.whatif.driver.exception;

public class WhatIfException extends RuntimeException {
    public WhatIfException(String message, Exception e) {
        super(message, e);
    }
}
