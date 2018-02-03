package ua.com.dagget.aqlconsole;

import org.junit.Before;
import org.junit.Test;
import ua.com.dagget.sqlconsole.DatabaseManager;

import java.sql.Connection;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * 1. Make it work. 2. Make it right. 3. Make it fast
 */
public class DatabaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = new DatabaseManager();
        manager.connect("sqlcmd", "postgres", "postgres" );
    }

    @Test
    public void testGetAllTableNames() {
        String[] tableNames = manager.getTableNames();
        assertEquals("[contact, user]", Arrays.toString(tableNames));
    }
}
