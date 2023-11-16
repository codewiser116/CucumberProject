package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class OracleDBConnection {

    private static final String JDBC_URL = Config.getProperty("jdbc_url");
    private static final String USERNAME = Config.getProperty("db_username");
    private static final String PASSWORD = Config.getProperty("db_password");
    private static final Logger logger = Logger.getLogger(OracleDBConnection.class.getName());
    private static Connection connection;

    public static Connection getConnection(){
        logger.info("Connecting to Database!");
        try{
            if(connection == null){
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("Creating a new connection to Database!");
                logger.info("Creating a new connection to Database!");
                return connection;
            }
        }
        catch(SQLException e){
            System.out.println("Failed to connect to DB!");
            logger.warning("Failed to connect to DB");
        }
        System.out.println("Returning existing connection!");
        logger.info("Returning existing connection!");
        return connection;
    }

}
