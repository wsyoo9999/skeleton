package Travel;

import java.sql.*;
import java.util.ArrayList;


public class TravelDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/skel";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";
    private PreparedStatement ps = null;
    private Connection conn = null;

    TravelDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connected to database successfully");
    }

    ArrayList<TravelVo> executeQuery(String query) {
        ResultSet rs = null;
        ArrayList<TravelVo> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
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

    @Override
    protected void finalize() throws Throwable {
        conn.close();
        super.finalize();
    }
}
