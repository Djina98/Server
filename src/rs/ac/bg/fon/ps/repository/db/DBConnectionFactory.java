/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import rs.ac.bg.fon.ps.util.PropertiesLoader;

/**
 *
 * @author Djina
 */
public class DBConnectionFactory {
    private Connection connection;
    private static DBConnectionFactory instance;

    private DBConnectionFactory() {
    }
    
    public static DBConnectionFactory getInstance(){
        if(instance == null){
            instance = new DBConnectionFactory();
        }
        
        return instance;
    }
    
    public Connection getConnection() throws SQLException{
        if(connection == null || connection.isClosed()){
           
            String url = PropertiesLoader.getInstance().getProperty("url");
            String username = PropertiesLoader.getInstance().getProperty("username");
            String password = PropertiesLoader.getInstance().getProperty("password");
            
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        
       }
        
        return connection;
    }
    
    public void closeConnection() throws SQLException {
        connection.close();
        instance = null;
    }
    
    
}
