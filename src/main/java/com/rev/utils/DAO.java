package com.rev.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class DAO {

    protected ConnectionUtil util;
    protected Properties dbProps;

    /**
     * Constructor that sets the properties file and establishes connection
     * @throws SQLException
     * @throws IOException
     */
    public DAO() throws SQLException, IOException {
        this.dbProps = null;
        dbProps = new Properties();
        dbProps.load(new FileReader("src\\main\\resources\\db.properties"));
        this.util = new PostgresConnectionUtil(this.dbProps);
    }

    /**
     * returns a resultset of a given sql string
     * @param SqlString
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public ResultSet selectDAO(String SqlString) throws SQLException, IOException {
        ResultSet res = null;
        Connection c = this.util.newConnection();
        if (c == null) {
            System.out.println("Could not connect...");
        }
        PreparedStatement ps = c.prepareStatement(SqlString);
        res = ps.executeQuery();
        c.close();

        return res;
    }

    /**
     * attempts to login with the email and pw paramaters and returns a boolean
     * @param email
     * @param pw
     * @return returns a boolean, true if successful
     * @throws SQLException
     */
    public Boolean login(String email, String pw) throws SQLException {
        ResultSet res = null;
        Connection c = util.newConnection();
        if (c == null) {
            System.out.println("Could not connect...");
        }
        try {
            PreparedStatement ps = c.prepareStatement("select hash from project1.workers where email=?");
            ps.setString(1, email);
            res = ps.executeQuery();

            res.next();
            if (pw.equals(res.getString("hash"))) {
                c.close();
                return true;
            }
            c.close();
        } catch (Exception e){
            return false;
        }
        return false;
    }

    /**
     *  makes a request to the database for the amound of funds requested
     * @param email
     * @param amt
     * @return a boolean to determine if it was possible to make request
     * @throws SQLException
     */
    public Boolean makeReimbRequest(String email, Double amt) throws SQLException {
        ResultSet res = null;
        Connection c = util.newConnection();
        if (c == null) {
            System.out.println("Could not connect...");
        }
        c.setAutoCommit(false);
        try {
            PreparedStatement ps = c.prepareStatement("insert into project1.employee (current_employee, request) values (?,?)");
            ps.setString(1, email);
            ps.setString(2,"Amount of $" + amt + " requested");
            ps.executeUpdate();
            c.commit();
            c.close();
            return true;
        } catch (Exception e){
            c.close();
            return false;
        }
    }

    /** gets all the pending requests that a user submitted
     *
     * @param email
     * @return returns a ResultSet that can be iterated through as needed
     * @throws SQLException
     */
    public ResultSet viewRequests(String email) throws SQLException {
        ResultSet res = null;
        Connection c = util.newConnection();
        if (c == null) {
            System.out.println("Could not connect...");
        }
        c.setAutoCommit(false);
        try {
            PreparedStatement ps = c.prepareStatement("select * from project1.employee where email=?");
            ps.setString(1,email);
            res = ps.executeQuery();

            c.close();
            return res;
        } catch (Exception e) {
            c.close();
            return res;
        }
    }

}
