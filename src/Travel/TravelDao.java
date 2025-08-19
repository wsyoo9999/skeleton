package Travel;

import java.sql.*;
import java.util.ArrayList;


public class TravelDao {
    private final String DB_URL;
    private final String DB_USER;
    private final String DB_PASSWORD;
    private PreparedStatement ps = null;


    TravelDao(String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    ArrayList<TravelVo> executeQuery(String query) {
        ArrayList<TravelVo> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
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
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
