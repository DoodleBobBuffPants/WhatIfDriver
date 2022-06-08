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

public class WhatIfStatementTest {
    private WhatIfConnection connection;
    private WhatIfStatement statement;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = new WhatIfConnection("jdbc:whatif:h2:mem:db", new Properties(), WhatIfDriver.FILENAME_PROPERTY);
        statement = (WhatIfStatement) connection.createStatement();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.close();
        if (!statement.isClosed()) statement.close();
    }

    @Test
    public void interceptsQuery() throws SQLException, IOException {
        String sql = "SELECT 1;";
        statement.executeQuery(sql);
        statement.close();

        assertEquals(sql, readWhatIfFile());
    }

    private String readWhatIfFile() throws IOException {
        return String.join("\n", Files.readAllLines(Path.of(WhatIfDriver.FILENAME_PROPERTY)));
    }
}
