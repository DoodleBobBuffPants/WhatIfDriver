package doodlebobbuffpants.whatif.driver.exception;

public class WhatIfSchemaException extends WhatIfException {
    public WhatIfSchemaException(Exception e) {
        super("Unable to intercept schema command", e);
    }
}
