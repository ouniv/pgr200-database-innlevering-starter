package no.kristiania.pgr200.database;


import org.flywaydb.core.Flyway;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;


public class DatabaseMain {

    private ConferenceDao dao;


    public DatabaseMain() throws SQLException, IOException {

        this.dao = new ConferenceDao();
        this.dao.CreateTableIfNotExists();

    }
    public static void main(String[] args) throws SQLException, IOException {
        new DatabaseMain().run(args);
    }

    public static Connection connection() throws IOException, SQLException {

        Properties properties = new Properties();
        InputStream inputStream = null;

        inputStream = new FileInputStream("connection.properties");
        properties.load(inputStream);

        String DB_URL= (String) properties.getProperty("DB_URL");
        String DB_USER= (String) properties.getProperty("DB_USER");
        String DB_PASS= (String) properties.getProperty("DB_PASS");

        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

        Flyway flyway = new Flyway();
         flyway.setDataSource(DB_URL, DB_USER, DB_PASS);
         flyway.migrate();

        return conn;
    }

    public void run(String[] args) throws SQLException, IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the conference database program!");
        System.out.println("type in 'insert', 'list' or 'clear' to work the program");
        System.out.println("type in 'exit' to stop the program");
        String command = scanner.nextLine();


        if(command.equalsIgnoreCase("insert")) {
            System.out.println("Enter the name of the seminar");
            insertTalk();
            System.out.println("You have successfully added a conference!");
        }

        else if(command.equalsIgnoreCase("list")) {
            listAll();
        }
        else if(command.equalsIgnoreCase("clear")) {
            clearAll();
        }
        else if(command.equalsIgnoreCase("exit")) {
                System.exit(0);
        } else {
            System.err.println("Unknown command!");
        }
    }


    private void insertTalk() throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        System.out.println("Enter a description for the seminar, press enter to skip");
        String desc = scanner.nextLine();
        System.out.println("Enter the timeslot for the seminar, press enter to skip");
        String timeslot = scanner.nextLine();
        System.out.println("In what room is the seminar held? press enter to skip");
        String room = scanner.nextLine();
        System.out.println("Enter the date of the seminar, press enter to skip");
        String date = scanner.nextLine();
        dao.daoInsertTalk(title, desc, timeslot, room, date);


    }


    private void listAll() throws SQLException, IOException {

        dao.daoListAll();

    }
    private void clearAll() throws IOException {

        dao.clearDatabase();
    }

}