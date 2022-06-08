package doodlebobbuffpants.whatif.driver;

import doodlebobbuffpants.whatif.driver.exception.WhatIfConnectionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class WhatIfConnectionTest {
    private WhatIfConnection connection;

    @AfterEach
    public void tearDown() {
        if (connection != null && !connection.isClosed()) connection.close();
    }

    @Test
    public void failsOnInvalidUrl() {
        assertThrows(WhatIfConnectionException.class, () -> connection = new WhatIfConnection("jdbc:whatif:example", new Properties(), WhatIfDriver.FILENAME_PROPERTY));
    }

    @Test
    public void succeedsOnValidUrl() throws SQLException {
        connection = createConnection();
        assertEquals("DB", connection.getCatalog());
    }

    @Test
    public void closesConnection() throws SQLException {
        connection = createConnection();
        assertTrue(connection.isValid(0));
        connection.close();
        assertTrue(connection.isClosed());
        assertFalse(connection.isValid(0));
    }

    @Test
    public void interceptsSchemaCommand() throws SQLException, IOException {
        connection = createConnection();
        assertEquals("PUBLIC", connection.getSchema());

        connection.setSchema("test");
        assertEquals("test", connection.getSchema());

        connection.close();

        assertEquals("-- SETTING SCHEMA TO 'test'", readWhatIfFile());
    }

    private WhatIfConnection createConnection() {
        return new WhatIfConnection("jdbc:whatif:h2:mem:db", new Properties(), WhatIfDriver.FILENAME_PROPERTY);
    }

    private String readWhatIfFile() throws IOException {
        return String.join("\n", Files.readAllLines(Path.of(WhatIfDriver.FILENAME_PROPERTY)));
    }
}
