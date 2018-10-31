package no.kristiania.pgr200.database;

//import org.flywaydb.core.Flyway;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class ConferenceDao {

    public final static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    public final static String DB_USER = "postgres";
    public final static String DB_PASS = "1234Lolz";


    public void CreateTableIfNotExists() throws SQLException, IOException {
        try (Connection conn = DatabaseMain.connection()) {
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS CONFERENCE_TALK " +
                    "(Title varchar primary key, DESCRIPTION varchar, TIMESLOT varchar, ROOM varchar, WHATDATE varchar)");
        }
    }

    public void InsertTalk(String title, String description, String timeslot, String room, String date) throws SQLException, IOException {


        try (Connection conn = DatabaseMain.connection()) {
            String sql = "insert into CONFERENCE_TALK(TITLE, DESCRIPTION, TIMESLOT, ROOM, WHATDATE) values (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, title);
                statement.setString(2, description);
                statement.setString(3, timeslot);
                statement.setString(4, room);
                statement.setString(5, date);


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
                        "       " + "Title: " + resultSet.getString("title")
                       + ", " + "Description: " + resultSet.getString("description")
                       + ", " + "Time: " + resultSet.getString("timeslot")
                        +", " + "Room: " + resultSet.getString("room")
                        +", " + "Date: " + resultSet.getString("whatdate"));

            }
        }
    }

    /*public void clearDatabase() throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream("connection.properties");
        properties.load(inputStream);
        Flyway flyway = new Flyway();
       // flyway.setDataSource(DB_URL, DB_USER, DB_PASS);
        flyway.setDataSource(properties.getProperty("DB_URL"), properties.getProperty("DB_USER"), properties.getProperty("DB_PASS"));
        flyway.clean();
        flyway.migrate();
        System.out.println("You have cleared the database");
    }*/
}