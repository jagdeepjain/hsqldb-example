package org.example.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HSQLDB {
    
    public Connection conn;
    private Statement statement;
    public static HSQLDB db;
    
    private HSQLDB() {
        String url = "jdbc:hsqldb:mem:";
        String dbName = "hr";
        String driver = "org.hsqldb.jdbc.JDBCDriver";
        String userName = "sa";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection) DriverManager.getConnection(url + dbName,
                    userName, password);
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    
    public static synchronized HSQLDB getDbCon() {
        if (db == null) {
            db = new HSQLDB();
        }
        return db;
        
    }
    
    public ResultSet query(String query) throws SQLException {
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
        
    }
}
