import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class MyFrame extends JFrame {
    private JTextField searchField;

    public MyFrame() {
        // Set up the frame
        setTitle("Postgres Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Create a JTable component
        JTable table = new JTable();

        // Create a search field
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        // Connect to Postgres
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviedb", "postgres", "Mm1008mm!!");

            // Create a statement and execute a query
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE mytable (id SERIAL PRIMARY KEY, name VARCHAR(50))");

            // Insert values into the table
            statement.executeUpdate("INSERT INTO mytable (name) VALUES ('John Doe')");
            statement.executeUpdate("INSERT INTO mytable (name) VALUES ('Jane Doe')");

            // Set up the JTable with data from the table
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Name");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM mytable");
            while (resultSet.next()) {
                model.addRow(new Object[]{resultSet.getInt("id"), resultSet.getString("name")});
            }
            table.setModel(model);

            // Add a listener to the search button to search for data
            searchButton.addActionListener(e -> {
                String searchValue = searchField.getText();
                try {
                    DefaultTableModel searchModel = new DefaultTableModel();
                    searchModel.addColumn("ID");
                    searchModel.addColumn("Name");
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM mytable WHERE name LIKE ?");
                    preparedStatement.setString(1, "%" + searchValue + "%");
                    ResultSet searchResultSet = preparedStatement.executeQuery();
                    while (searchResultSet.next()) {
                        searchModel.addRow(new Object[]{searchResultSet.getInt("id"), searchResultSet.getString("name")});
                    }
                    table.setModel(searchModel);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });

            // Add the JTable to the frame
            add(new JScrollPane(table));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Show the frame
        setVisible(true);
    }

    public static void main(String[] args) {
        new MyFrame();
    }
}
