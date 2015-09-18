package org.example.hsqldb.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.hsqldb.HSQLDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HSQLDBTest {
    
    HSQLDB hsqldb = HSQLDB.getDbCon();
    
    @Before
    public void setUp() throws SQLException {
        hsqldb.query("create table employee ( id INTEGER, first_name VARCHAR(50), last_name VARCHAR(50) )");  
        hsqldb.insert("insert into employee (id, first_name, last_name) values (1001, 'John', 'Doe')");
    }
    
    @After
    public void tearDown() throws SQLException {
        hsqldb.insert("SHUTDOWN");
    }
    
    @Test
    public void testHSQLDB() throws SQLException {
        String id = null;
        ResultSet rs = hsqldb.query("select id from employee where first_name='John'");
        if (rs.next()) {
            id = rs.getString(1);
        };
        assertTrue("Id for John should be 1001",id.equals("1001"));
    }
    
}
