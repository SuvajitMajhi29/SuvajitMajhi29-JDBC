package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Util {
    public static void generateTables(){
        

            String sql = "create table songs(\n" +
                            "id serial primary key,\n" +
                            "name varchar(50) unique not null,\n" +
                            "artist varchar(50) not null\n" +
                         ");";
          try (
                Connection connection = ConnectionUtil.getConnection();
            
            PreparedStatement ps = connection.prepareStatement(sql))
            {

            ps.executeUpdate();
            System.out.println("created successfully");

        } 
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    public static void dropAllTables(){
        String sql = "DROP TABLE IF EXISTS songs;";
        try (
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql))
            {

            ps.executeUpdate();
            System.out.println("Table 'songs' dropped successfully.");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
