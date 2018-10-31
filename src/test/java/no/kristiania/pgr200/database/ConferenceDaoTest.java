package no.kristiania.pgr200.database;

import org.junit.Test;
import org.flywaydb.core.Flyway;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ConferenceDaoTest {

    @Test
    public void shouldInsertConferenceTalks() throws SQLException, IOException {
        ConferenceDao dao = new ConferenceDao();
        dao.CreateTableIfNotExists();
        dao.CreateTableIfNotExists();
        dao.daoInsertTalk("A random talk", "with a random description", "20:45 - 00:00", "205", "23.11" );

    }
    @Test
    public void shouldListAllInformation() throws SQLException, IOException {
        ConferenceDao dao = new ConferenceDao();
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("connection.properties");
        properties.load(inputStream);
        dao.CreateTableIfNotExists();
        dao.daoListAll();
        Flyway flyway = new Flyway();
        flyway.setDataSource(properties.getProperty("DB_URL"), properties.getProperty("DB_USER"), properties.getProperty("DB_PASS"));
        flyway.clean();
        flyway.migrate();

    }

    @Test
    public void shouldClearDatabase() throws SQLException, IOException{
        ConferenceDao dao = new ConferenceDao();
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("connection.properties");
        properties.load(inputStream);
        dao.CreateTableIfNotExists();
        dao.daoInsertTalk("heihei", "beskrivelse", "11:00- 23:00", "666", "06.05.2018");
        Flyway flyway = new Flyway();
        flyway.setDataSource(properties.getProperty("DB_URL"), properties.getProperty("DB_USER"), properties.getProperty("DB_PASS"));
        flyway.clean();
        flyway.migrate();

    }
}
