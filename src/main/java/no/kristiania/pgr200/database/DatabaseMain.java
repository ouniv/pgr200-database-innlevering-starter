package no.kristiania.pgr200.database;


import org.flywaydb.core.Flyway;

import java.sql.*;
import java.util.Scanner;
import java.util.UUID;


public class DatabaseMain {

    private ConferenceDao dao;


    public DatabaseMain() throws SQLException {

        this.dao = new ConferenceDao();
        this.dao.CreateTableifNotExists();

    }
    public static void main(String[] args) throws SQLException {
        new DatabaseMain().run(args);
    }

    public void run(String[] args) throws SQLException {

//        Scanner scanner = new Scanner(System.in);
////
////        String input = scanner.nextLine();
////
////        String title = input.substring(1, 2);
////        String description = input.substring(2, 3);

        if(args.length == 0) {
            System.out.println("Run boy xD");
            System.exit(1);
        }

        String command = args[0];



        if(command.equalsIgnoreCase("insert")) {
            insertTalk();
            System.out.println("Nice command");
        }

        else if(command.equalsIgnoreCase("list")) {
            listAll();
        }
        else if(command.equalsIgnoreCase("clear")) {
            clearAll();
        } else {
            System.err.println("Unknown command!");
        }
    }

    private void insertTalk() throws SQLException {
        dao.InsertTalk();
 }

    private void listAll() throws SQLException {

        dao.ListAll();

    }
    private void clearAll() {

        dao.clearDatabase();
    }

}