package doodlebobbuffpants.whatif.driver;

import org.junit.jupiter.api.Test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WhatIfDriverTest {
    @Test
    public void driveManagerGetsDriverAfterManualRegistration() throws SQLException {
        WhatIfDriver expectedDriver = new WhatIfDriver();
        DriverManager.registerDriver(expectedDriver);

        Driver actualDriver = DriverManager.getDriver("jdbc:whatif:example");
        assertEquals(expectedDriver.getClass().getCanonicalName(), actualDriver.getClass().getCanonicalName());

        DriverManager.deregisterDriver(expectedDriver);
    }

    @Test
    public void driverManagerGetsDriverAutomatically() throws SQLException {
        Driver actualDriver = DriverManager.getDriver("jdbc:whatif:example");
        assertEquals(WhatIfDriver.class.getCanonicalName(), actualDriver.getClass().getCanonicalName());
    }
}
