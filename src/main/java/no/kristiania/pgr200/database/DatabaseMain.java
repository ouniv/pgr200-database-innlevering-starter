package no.kristiania.pgr200.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseMain {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=1234Lolz");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS brukere " +
                                "(name TEXT, phone INTEGER, email TEXT)");

        } catch(SQLException e) {
            System.out.println("Noe gikk galt " + e.getMessage());

        }
    }
}
