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
import java.util.ArrayList;;

public class Database {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ship_booking";
 
    private static final String USER = "maskedcarrot";
    private static final String PASS = "oshuoshu";

    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 


    public  List<Map<String, Object>> executeQuerry(String querry){
    
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        try{
            
            //Class.forName("com.mysql.jdbc.Driver");

            LOGGER.log(Level.INFO ,"Connecting to database..." );

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            LOGGER.log(Level.INFO ,"Creating statement..." );

            stmt = conn.createStatement();

            rs = stmt.executeQuery(querry);

            
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


            LOGGER.log(Level.INFO ,"Cleaning environment" );
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

            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return resultList;
    }

    }
