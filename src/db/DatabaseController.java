package db;

import java.sql.*;

public class DatabaseController {
    // АААА КОСТЫЛЬ!!!11!!!1
    static private final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static private final String USER = "postgres";
    static private final String PASS = "postgres";
    Connection connection;
    public DatabaseController(){
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }
    public void init(Boolean is_first_run) throws SQLException {
        if(is_first_run){
            Statement st = connection.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS genres(genre_id SERIAL PRIMARY KEY, genre_name VARCHAR(50) NOT NULL);");
        }
    }
}
