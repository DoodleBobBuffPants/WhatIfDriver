package doodlebobbuffpants.whatif.driver.exception;

public class WhatIfConnectionException extends WhatIfException {
    public WhatIfConnectionException(String url, Exception e) {
        super("Unable to create connection to " + url, e);
    }
}
