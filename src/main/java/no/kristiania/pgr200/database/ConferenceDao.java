package no.kristiania.pgr200.database;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class ConferenceDao {

    public final static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public final static String DB_USER = "postgres";
    public final static String DB_PASS = "ina";


    public void CreateTableIfNotExists() throws SQLException, IOException {
        try (Connection conn = DatabaseMain.connection()) {
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS CONFERENCE_TALK " +
                    "(Title varchar primary key, DESCRIPTION text)");
        }
    }

    public void InsertTalk(String title, String description) throws SQLException, IOException {


        try (Connection conn = DatabaseMain.connection()) {
            String sql = "insert into CONFERENCE_TALK(TITLE, DESCRIPTION) values (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, title);
                statement.setString(2, description);


                statement.executeUpdate();
            }
        }

    }

    public void ListAll() throws SQLException, IOException {
        try (Connection conn = DatabaseMain.connection()) {


            String sql = "SELECT * FROM conference_talk";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

//            ResultSetMetaData metaData = resultSet.getMetaData();
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                System.out.println(metaData.getColumnName(i));
//            }
            System.out.println("Information of talks: ");
            while(resultSet.next()) {
                System.out.println(
                        "       " + resultSet.getString("title")
                       + ", " + resultSet.getString("description"));

            }
        }
    }

    public void clearDatabase() throws IOException {
        Flyway flyway = new Flyway();
        flyway.setDataSource(DB_URL, DB_USER, DB_PASS);
        flyway.clean();
        flyway.migrate();
        System.out.println("You have cleared the database");
    }
}