package doodlebobbuffpants.whatif.driver;

import lombok.experimental.Delegate;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WhatIfCallableStatement extends WhatIfPreparedStatement implements CallableStatement {
    @Delegate(types = CallableStatement.class, excludes = PreparedStatement.class)
    private final CallableStatement wrappedStatement;

    public WhatIfCallableStatement(WhatIfWriter writer, WhatIfConnection connection, String sql) throws SQLException {
        super(writer, connection, sql);
        this.wrappedStatement = connection.getWrappedConnection().prepareCall(sql);
    }

    @Override
    public void close() throws SQLException {
        super.close();
        wrappedStatement.close();
    }
}
