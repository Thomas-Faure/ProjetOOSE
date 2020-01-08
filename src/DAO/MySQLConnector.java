package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private String JDBC_DRIVER;  
    private String DB_URL;
    private String USER;
    private String PASS;
    private Connection conn;
    private static MySQLConnector instance= null;
    private MySQLConnector(){
        JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3308/projetoose?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC";
        USER = "root";
        PASS = "";
        conn = null;
    }
    public static Connection getSQLConnection() {
    	return getInstance().getConnection();
    }
    public static MySQLConnector getInstance() {
    	if(instance == null) {
    		instance = new MySQLConnector();
    		instance.openConnection();
    	}
    	return instance;
    }
    private void openConnection(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    private Connection getConnection(){
        return conn;
    }
    public static void main(String[] args) {
    	MySQLConnector sql = new MySQLConnector();
    	sql.openConnection();
    	sql.getConnection();
    }
}