package no.kristiania.pgr200.database;


import org.flywaydb.core.Flyway;

import java.sql.*;


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


        if(args.length == 0) {
            System.out.println("Run boy xD");
            listAll();
            System.exit(1);
        }

        String command = args[0];

        if(command.equals("insert")) {
            insertTalk();
            System.out.println("Nice command");
        } else {
            System.err.println("Unknown command!");
        }
        if(command.equals("list")) {
            listAll();
        } else {
            System.err.println("Unknown command!");
        }
        if(command.equals("clear")) {
            clearAll();
        } else {
            System.err.println("Unknown command!");
        }
    }

    private void insertTalk() throws SQLException {
        dao.InsertTalk("A new talk called", "This is a very nice description");

    }

    private void listAll() throws SQLException {

        dao.ListAll();

    }
    private void clearAll() {
        dao.clearDatabase();
    }
}