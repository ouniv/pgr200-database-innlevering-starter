package no.kristiania.pgr200.database;

import org.flywaydb.core.Flyway;
import org.junit.Test;

import java.sql.SQLException;


public class DatabaseTest {


    @Test
    public void shouldInsertConferenceTalks() throws SQLException {
        ConferenceDao dao = new ConferenceDao();
        dao.CreateTableifNotExists();
        dao.CreateTableifNotExists();
        dao.InsertTalk("My ssdf Title", "A longer description of the talk");

    }
    @Test
    public void shouldListAllTables() throws SQLException {
        ConferenceDao dao = new ConferenceDao();
        dao.ListAll();

    }

    @Test
    public void shouldClearDatabase() {
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234Lolz");
        flyway.clean();
        flyway.migrate();

    }


}
