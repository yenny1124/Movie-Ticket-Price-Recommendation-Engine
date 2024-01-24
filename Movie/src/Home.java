import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Home extends JFrame{
    private JPanel Main;
    private JTable table1;

    public static void main(String[] args) {
//        DbFunctions db=new DbFunctions();
//        Connection conn=db.connect("moviedb","postgres","Mm1008mm!!");
        new Home();
    }

    public Home(){
        // Set up the frame
        setTitle("Postgres Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Create a JTable component
        JTable table = new JTable();

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviedb", "postgres", "Mm1008mm!!");

            // Create a statement and execute a query
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE movie (id SERIAL PRIMARY KEY, name VARCHAR(50))");

            // Get the data from the table and display it in the JTable
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movie");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Add the JTable to the frame
            add(new JScrollPane(table));


            if (connection != null) {
                System.out.println("Connection Established!");
            } else {
                System.out.println("Connection failed");
            }

        } catch (Exception err) {
            System.out.println(err);
        }

    }

//    public void insertRow(Connection conn,String tableName, String title, String description, String releaseDate, String removalDate, Boolean isAvailable, String isan){
//        Statement statement;
//        try {
//            String query=String.format("insert into %s(title, description, releaseDate, removalDate, isAvailable, isan) values('%s','%s','%s','%s','%s','%s');",
//                    tableName, title, description, releaseDate, removalDate, isAvailable, isan);
//            statement=conn.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Row Inserted");
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

}
