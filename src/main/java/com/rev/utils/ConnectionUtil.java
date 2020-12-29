package com.rev.utils;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class ConnectionUtil {
    protected String user;
    protected String password;
    protected String url;
    public abstract Connection newConnection() throws SQLException;

}