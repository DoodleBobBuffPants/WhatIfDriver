package doodlebobbuffpants.whatif.driver.exception;

public class WhatIfSqlException extends WhatIfException {
    public WhatIfSqlException(String sql, Exception e) {
        super("Unable to write SQL:\n" + sql, e);
    }
}
