package Travel;

import java.sql.*;
import java.util.ArrayList;


public class TravelDao {

    private static TravelDao  tb = new TravelDao();

    public static TravelDao getTravelDao(){
        return tb;
    }

    private ArrayList<TravelVo> executeQuery(String query) {
        ArrayList<TravelVo> list = new ArrayList<>();
        try (Connection conn = TravelUtil.getInstance().getConnection();
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
       StringBuilder query = new StringBuilder("SELECT * FROM travel WHERE district LIKE \"%").append(district[0]).append("%\"");
       for (int i = 1; i < district.length; i++) {
           query.append(" OR district LIKE \"%").append(district[i]).append("%\"");
       }
       query.append(" ORDER BY No ASC;");
        return  executeQuery(query.toString());
    }

    ArrayList<TravelVo> searchbyTitle(String[] Title) {
        StringBuilder query = new StringBuilder("SELECT * FROM travel WHERE Title LIKE \"%").append(Title[0]).append("%\"");
        for (int i = 1; i < Title.length; i++) {
            query.append(" OR Title LIKE \"%").append(Title[i]).append("%\"");
        }
        query.append(" ORDER BY No ASC;");
        return  executeQuery(query.toString());
    }

    ArrayList<TravelVo> searchbyAddress(String[] Address) {
        StringBuilder query = new StringBuilder("SELECT * FROM travel WHERE Address LIKE \"%").append(Address[0]).append("%\"");
        for (int i = 1; i < Address.length; i++) {
            query.append(" OR Address LIKE \"%").append(Address[i]).append("%\"");
        }
        query.append(" ORDER BY No ASC;");
        return  executeQuery(query.toString());
    }

    ArrayList<String> getDistricts() {
        ArrayList<String> districts = new ArrayList<>();
        try (Connection conn = TravelUtil.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("select distinct district from travel;");) {
            while (rs.next()) {
                districts.add(rs.getString("district"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return districts;
    }

}
