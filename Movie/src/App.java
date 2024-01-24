import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;


public class App extends JFrame {
    public static void main(String[] args) throws Exception {
        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviedb", "postgres", "Mm1008mm!!");

            if (connection != null) {
                System.out.println("Connection Established!");
            } else {
                System.out.println("Connection failed");
            }

        } catch (Exception err) {
            System.out.println(err);
        }
    }
}

