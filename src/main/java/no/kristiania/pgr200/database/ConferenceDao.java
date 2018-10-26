package no.kristiania.pgr200.database;

import org.flywaydb.core.Flyway;

import java.sql.*;

public class ConferenceDao {


    public void CreateTableifNotExists() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=1234Lolz")) {
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS CONFERENCE_TALK " +
                    "(Title varchar primary key, DESCRIPTION text)");

        }
    }

    public void InsertTalk(String title, String description) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=1234Lolz")) {
            String sql = "insert into CONFERENCE_TALK(TITLE, DESCRIPTION) values (?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, title);
                statement.setString(2, description);

                statement.executeUpdate();
            }
        }

    }

    public void ListAll() throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=1234Lolz")) {

            String sql = "SELECT * FROM conference_talk";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

//            ResultSetMetaData metaData = resultSet.getMetaData();
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                System.out.println(metaData.getColumnName(i));
//            }
            System.out.println("List of tables: ");
            while(resultSet.next()) {
                System.out.println(
                        "       " + resultSet.getString("title")
                       + ", " + resultSet.getString("description"));

            }
        }
    }

    public void clearDatabase() {
        Flyway flyway = new Flyway();
        flyway.clean();
        flyway.migrate();
        System.out.println("You have cleared the database");
    }
}