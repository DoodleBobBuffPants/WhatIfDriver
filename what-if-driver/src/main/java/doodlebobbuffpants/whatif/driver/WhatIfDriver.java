package doodlebobbuffpants.whatif.driver;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class WhatIfDriver implements Driver {
    public static final String FILENAME_PROPERTY = "whatif.txt";

    public WhatIfDriver() {
        try {
            DriverManager.registerDriver(this);
        } catch (SQLException ignored) {
        }
    }

    @Override
    public Connection connect(String url, Properties info) {
        return acceptsURL(url) ? new WhatIfConnection(url, info, FILENAME_PROPERTY) : null;
    }

    @Override
    public boolean acceptsURL(String url) {
        return url.startsWith("jdbc:whatif:");
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return false;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }
}
