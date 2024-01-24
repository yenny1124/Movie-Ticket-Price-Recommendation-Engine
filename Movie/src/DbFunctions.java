import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions extends JFrame{
    Statement statement;        // Create a JTable component
    JTable table = new JTable();

    public Connection connect(String dbname, String user, String pass) {
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(conn!=null){
                System.out.println("Connection Established");
            }
            else{
                System.out.println("Connection Failed");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return conn;
    }

    public void createTable(Connection conn, String tableName){
        try{
            // Create a statement and execute a query
            String query="create table "+tableName+
                            "(id SERIAL, title varchar(100), description varchar(500), releaseDate varchar(50), removalDate varchar(50), isAvailable Boolean, isan varchar(33), primary key(id));";
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void insertRow(Connection conn,String tableName, String title, String description, String releaseDate, String removalDate, Boolean isAvailable, String isan){
        Statement statement;
        try {
            String query=String.format("insert into %s(title, description, releaseDate, removalDate, isAvailable, isan) values('%s','%s','%s','%s','%s','%s');",
                                        tableName, title, description, releaseDate, removalDate, isAvailable, isan);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");

            // Get the data from the table and display it in the JTable
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movie");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));

            // Add the JTable to the frame
            add(new JScrollPane(table));

        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void readData(Connection conn, String table_name){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from %s",table_name);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("Address")+" ");
            }

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void updateName(Connection conn,String table_name, String old_name,String new_name){
        Statement statement;
        try {
            String query=String.format("update %s set name='%s' where name='%s'",table_name,new_name,old_name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void searchByTitle(Connection conn, String table_name,String name){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from %s where name= '%s'",table_name,name);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void searchByReleaseDate(Connection conn, String table_name,int id){
        Statement statement;
        ResultSet rs=null;
        try {
            String query=String.format("select * from %s where empid= %s",table_name,id);
            statement=conn.createStatement();
            rs=statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));

            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

//    public void delete_row_by_name(Connection conn,String table_name, String name){
//        Statement statement;
//        try{
//            String query=String.format("delete from %s where name='%s'",table_name,name);
//            statement=conn.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Data Deleted");
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }
//
//    public void delete_row_by_id(Connection conn,String table_name, int id){
//        Statement statement;
//        try{
//            String query=String.format("delete from %s where empid= %s",table_name,id);
//            statement=conn.createStatement();
//            statement.executeUpdate(query);
//            System.out.println("Data Deleted");
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

    public void deleteTable(Connection conn, String table_name){
        Statement statement;
        try {
            String query= String.format("drop table %s",table_name);
            statement=conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Deleted");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
