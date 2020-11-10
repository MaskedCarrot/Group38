package apoorv;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

class Database {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ship_booking";
    
    //User Credentials
    private static final String USER = "maskedcarrot";
    private static final String PASS = "oshuoshu";

    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 


    protected  List<Map<String, Object>> executeQuery(String Query){
    
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            //LOGGER.log(Level.INFO ,"Connecting to database..." );
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //LOGGER.log(Level.INFO ,"Creating statement..." );
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);

        
            Map<String, Object> row = null;
            java.sql.ResultSetMetaData metaData = rs.getMetaData();
            Integer columnCount = metaData.getColumnCount();

            while (rs.next()) {
                row = new HashMap<String, Object>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                resultList.add(row);
            }


            //LOGGER.log(Level.INFO ,"Cleaning environment" );
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            LOGGER.log(Level.WARNING ,"errors for Class.forName");
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
                LOGGER.log(Level.WARNING , "SQL EXCEPTION");
                se2.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                LOGGER.log(Level.WARNING , "SQL EXCEPTION");
                se.printStackTrace();
            }
        }
        return resultList;
    }

    protected  int executeUpdate(String Query){
    
        Connection conn = null;
        Statement stmt = null;
        int rs = 0;

        // List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");

            //LOGGER.log(Level.INFO ,"Connecting to database..." );
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //LOGGER.log(Level.INFO ,"Creating statement..." );
            stmt = conn.createStatement();
            rs = stmt.executeUpdate(Query);


            //LOGGER.log(Level.INFO ,"Cleaning environment" );
            stmt.close();
            conn.close();
        }catch(Exception e){
            LOGGER.log(Level.WARNING ,"errors for Class.forName");
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
                LOGGER.log(Level.WARNING , "SQL EXCEPTION");
                se2.printStackTrace();
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                LOGGER.log(Level.WARNING , "SQL EXCEPTION");
                se.printStackTrace();
            }
        }
        return rs;
    }

    protected int convertObjectToInt(Object object){
        return Integer.parseInt(object.toString());
    }

    protected String convertObjectToString(Object object){
        return object.toString();
    }

    protected Long convertObjectToLong(Object object){
        return Long.parseLong(object.toString());
    }

}
