package Travel;

import java.sql.*;
import java.util.ArrayList;


public class TravelDao {
    private final String driverName = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASSWORD;
    private PreparedStatement ps = null;


    TravelDao(String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<TravelVo> executeQuery(String query) {
        ArrayList<TravelVo> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             //Connection conn = TravelUtil.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)){
            while (rs.next()) {
                list.add(new TravelVo(  rs.getInt("no"),
                                        rs.getString("district"),
                                        rs.getString("title"),
                                        rs.getString("description"),
                                        rs.getString("address"),
                                        rs.getString("phone")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ArrayList<TravelVo> viewAll(){
        return  executeQuery("SELECT * FROM travel");
    }

    ArrayList<TravelVo> searchbyDistrict(String[] district) {
       StringBuilder query = new StringBuilder("SELECT * FROM travel WHERE district LIKE \"%").append(district[0]).append("\"%");
       for (int i = 1; i < district.length; i++) {
           query.append(" OR district LIKE \"%").append(district[0]).append("\"%");
       }
       query.append(" ORDER BY district ASC;");
        return  executeQuery(query.toString());
    }

    ArrayList<TravelVo> searchbyTitle(String[] Title) {
        StringBuilder query = new StringBuilder("SELECT * FROM travel WHERE Title LIKE \"%").append(Title[0]).append("\"%");
        for (int i = 1; i < Title.length; i++) {
            query.append(" OR Title LIKE \"%").append(Title[0]).append("\"%");
        }
        query.append(" ORDER BY Title ASC;");
        return  executeQuery(query.toString());
    }

    ArrayList<TravelVo> searchbyAddress(String[] Address) {
        StringBuilder query = new StringBuilder("SELECT * FROM travel WHERE Address LIKE \"%").append(Address[0]).append("\"%");
        for (int i = 1; i < Address.length; i++) {
            query.append(" OR Address LIKE \"%").append(Address[0]).append("\"%");
        }
        query.append(" ORDER BY Address ASC;");
        return  executeQuery(query.toString());
    }

}
