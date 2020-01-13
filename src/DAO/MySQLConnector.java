package DAO;
/**
 *
 * @author Thomas Faure
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is to create the connection with the database
 */
public class MySQLConnector {
    private String JDBC_DRIVER;  
    private String DB_URL;
    private String USER;
    private String PASS;
    private Connection conn;
    private static MySQLConnector instance= null;
    private MySQLConnector(){
        JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        DB_URL = "jdbc:mysql://mysql-thomasfaure05.alwaysdata.net:3306/thomasfaure05_projetoose?useUnicode=yes&characterEncoding=UTF-8&useTimezone=true&serverTimezone=UTC";
        USER = "197687_oose";
        PASS = "";
        conn = null;
    }

    /**Method to get the connection of the database
     * @return the connection
     */
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

    /**
     * Method to open the connection with the database
     */
    private void openConnection(){
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to close the connection with the database
     */
    public void closeConnection(){
        try{
            if(conn!=null)
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    /**Method wich return the connection of the databse
     * @return a connection
     */
    private Connection getConnection(){
        return conn;
    }

}