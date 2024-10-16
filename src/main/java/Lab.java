

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;

/**
 * JDBC stands for Java DataBase Connectivity.  It is utilized to connect our java code with a database.
 * JDBC will allow us to execute SQL statements from java and retrieve the result set of that query to be utilized in java
 *
 * JDBC datatypes to know:
 *  - Connection: Creates an active connection to the database.
 *  - Statement: An object that represents a SQL statement to be executed.
 *  - ResultSet: An object that represents the virtual table return from a query (Only needed for executing DQL statements)
 *
 * Background:
 * Assume we have the following table:
 *      songs table
 *      |   id  |      title        |        artist         |
 *      -----------------------------------------------------
 *      |1      |'Let it be'        |'Beatles'              |
 *      |2      |'Hotel California' |'Eagles'               |
 *      |3      |'Kashmir'          |'Led Zeppelin'         |
 *
 * Assignment: Write JDBC logic in the methods below to achieve the following
 *                  - create a new song in our songs database table
 *                  - retrieve all songs from our database table
 *
 * If this is your first time working with JDBC, I recommend reading through the JDBCWalkthrough file that displays how to use JDBC for a similar scenario.
 */
public class Lab {

    public void createSong(Song song)  {
        try (Connection connection = ConnectionUtil.getConnection()) {
            
            // Create SQL INSERT statement
            String sql = "INSERT INTO songs (title, artist) VALUES (?, ?)";

            // Use PreparedStatement to prevent SQL injection
            PreparedStatement ps = connection.prepareStatement(sql);

            // Set the values for the title and artist fields
            ps.setString(1, song.gettitle());
            ps.setString(2, song.getArtist());

            // Execute the update
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //write jdbc code here
    }

    public List<Song> getAllSongs(){
        List<Song> songs = new ArrayList<>();

        //write jdbc code here
        try (Connection connection = ConnectionUtil.getConnection()) {

            // Create SQL SELECT statement
            String sql = "SELECT * FROM songs";

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query and get the result set
            ResultSet rs = statement.executeQuery(sql);

            // Loop through the result set
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");

                // Create a Song object and add it to the list
                Song song = new Song(id, title, artist);
                songs.add(song);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return songs;
    }
}
