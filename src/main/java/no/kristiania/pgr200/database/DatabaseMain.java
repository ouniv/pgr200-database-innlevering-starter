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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the conference database program!\n");
        System.out.println("type in ´insert´, ´list´ or ´clear´ to work the program\n ");
        System.out.printf("type in 'exit´ to stop the program\n ");
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
            insertTalk();
            System.out.println("You have successfully added a conference!");
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