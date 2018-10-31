package no.kristiania.pgr200.database;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;
import java.sql.SQLException;


public class DatabaseTest {


    @Test
    public void testConnection() throws SQLException, IOException{

        DatabaseMain.connection();
        assertTrue(DatabaseMain.connection()!= null);
    }
}
