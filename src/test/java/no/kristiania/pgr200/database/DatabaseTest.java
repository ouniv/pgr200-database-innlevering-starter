package no.kristiania.pgr200.database;

import org.flywaydb.core.Flyway;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;


public class DatabaseTest {


    @Test
    public void shouldInsertConferenceTalks() throws SQLException, IOException {
        ConferenceDao dao = new ConferenceDao();
        dao.CreateTableifNotExists();
        dao.CreateTableifNotExists();
        dao.InsertTalk("A random talk", "with a random description" );

    }
    @Test
    public void shouldListAllTables() throws SQLException, IOException {
        ConferenceDao dao = new ConferenceDao();
        dao.ListAll();
        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234Lolz");
        flyway.clean();
        //       flyway.migrate();

    }

//    @Test
//    public void shouldClearDatabase() {
//        Flyway flyway = new Flyway();
//        flyway.setDataSource("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234Lolz");
//        flyway.clean();
//        flyway.migrate();
//
//    }


}
