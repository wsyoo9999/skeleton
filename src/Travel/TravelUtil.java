package Travel;

import java.sql.Connection;
import java.sql.DriverManager;

public class TravelUtil {
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/skel";
    private final String user = "root";
    private final String pass = "1234";
    private static TravelUtil util = new TravelUtil();

    private TravelUtil() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static TravelUtil getInstance() {
        return util;
    }

    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
     return conn;
    }

    public void close(AutoCloseable... closeables) {
        for (AutoCloseable c : closeables) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
