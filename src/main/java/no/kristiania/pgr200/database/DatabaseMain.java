package no.kristiania.pgr200.database;


import java.sql.*;
import java.util.UUID;

public class DatabaseMain {

    private ConferenceDao dao;

    public DatabaseMain() throws SQLException {

        this.dao = new ConferenceDao();
        this.dao.CreateTableifNotExists();

    }
    public static void main(String[] args) throws SQLException {
        new DatabaseMain();
    }

    private void InsertTalk() throws SQLException {
        dao.InsertTalk("A new talk called" + UUID.randomUUID(), "This is a very nice description");

    }

    private void ListAll() throws SQLException {
        dao.ListAll();

    }
}