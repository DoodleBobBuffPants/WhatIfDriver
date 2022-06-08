package doodlebobbuffpants.whatif.driver;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;

import java.sql.*;

@RequiredArgsConstructor
public class WhatIfDatabaseMetaData implements DatabaseMetaData {
    @Delegate(types = DatabaseMetaData.class, excludes = DatabaseMetaDataDelegateExclusion.class)
    private final DatabaseMetaData wrappedDatabaseMetaData;
    private final Connection whatIfConnection;

    @Override
    public Connection getConnection() {
        return whatIfConnection;
    }

    @SuppressWarnings("unused")
    private interface DatabaseMetaDataDelegateExclusion {
        Connection getConnection() throws SQLException;
    }
}
