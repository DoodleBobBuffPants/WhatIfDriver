package doodlebobbuffpants.whatif.driver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WhatIfPreparedStatementTest {
    private WhatIfConnection connection;
    private WhatIfPreparedStatement statement;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = new WhatIfConnection("jdbc:whatif:h2:mem:db", new Properties(), WhatIfDriver.FILENAME_PROPERTY);
        statement = (WhatIfPreparedStatement) connection.prepareStatement("SELECT ?, ?, ?");
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
        if (!statement.isClosed()) statement.close();
    }

    @Test
    public void insertsParameterInCorrectPosition() throws SQLException, IOException {
        statement.setInt(2, 2);
        statement.execute();
        statement.close();

        assertEquals("SELECT ?, 2, ?", readWhatIfFile());
    }

    @Test
    public void insertsIntAndString() throws SQLException, IOException {
        statement.setString(1, "3");
        statement.setInt(2, 2);
        statement.setString(3, "1");
        statement.execute();
        statement.close();

        assertEquals("SELECT \"3\", 2, \"1\"", readWhatIfFile());
    }

    private String readWhatIfFile() throws IOException {
        return String.join("\n", Files.readAllLines(Path.of(WhatIfDriver.FILENAME_PROPERTY)));
    }
}
