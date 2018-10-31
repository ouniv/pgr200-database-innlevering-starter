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


        return conn;
    }

    public void run(String[] args) throws SQLException, IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the conference database program!");
        System.out.println("type in 'insert', 'list' or 'clear' to work the program");
        System.out.println("type in 'exit' to stop the program");
        String command = scanner.nextLine();


//
//        if(args.length == 0) {
//            System.out.println("Run boy xD");
//            System.exit(1);
//        }
//
//        String command = args[0];
//


        if(command.equalsIgnoreCase("insert")) {
            System.out.println("type in the name and title separated with a ','");
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
        String command = scanner.nextLine();
        String[] getData = command.split(",", 2);
        String title = getData[0];
        String desc = getData[1];

        dao.InsertTalk(title, desc);

    }
    /*private void insertTalk() throws SQLException, IOException {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        int endOfTitle = command.indexOf(",");
        String title = command.substring(endOfTitle-1);
        String description = command.substring(endOfTitle+1);
        dao.InsertTalk(title, description);*/



    private void listAll() throws SQLException, IOException {

        dao.ListAll();

    }
    private void clearAll() throws IOException {

        dao.clearDatabase();
    }

}