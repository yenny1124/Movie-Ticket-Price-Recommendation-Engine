import javax.swing.*;
import java.sql.Connection;

public class Main extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton button1;

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        // Set up the frame
        setTitle("Postgres Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        DbFunctions db=new DbFunctions();
        Connection conn=db.connect("moviedb","postgres","Mm1008mm!!");
//        db.createTable(conn,"movie");
        db.insertRow(conn,"movie","Twilight","A Story of Vampire", "03/16/2023", "05/16/2023",false,"0000-0004-0C99-0017-Z-0000-0000-9");
//        db.update_name(conn,"employee","Rahul","Raj");
//        db.search_by_name(conn,"employee","abhishek");
//        db.delete_row_by_name(conn,"employee","abhishek");
//        db.delete_row_by_id(conn,"employee",4);
//        db.read_data(conn,"employee");
//        db.deleteTable(conn,"movie");
        // write your code here

        // Show the frame
        setVisible(true);
    }
}
