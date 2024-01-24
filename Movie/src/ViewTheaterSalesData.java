/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author jamiechoi
 */
public class ViewTheaterSalesData extends javax.swing.JFrame {

    /**
     * Creates new form ViewTheaterSalesData
     */
    DefaultTableModel model;
    
    public ViewTheaterSalesData() {
        initComponents();
        jTable_SalesByAvailability.setRowHeight(40);
        jTable_SalesByAvailability.setShowGrid(true);
        jTable_SalesByAvailability.setGridColor(Color.yellow);
        jTable_SalesByAvailability.setSelectionBackground(Color.black);
        setMoviesToTable1();
        
        jTable_SalesByADayOfTheWeek.setRowHeight(40);
        jTable_SalesByADayOfTheWeek.setShowGrid(true);
        jTable_SalesByADayOfTheWeek.setGridColor(Color.yellow);
        jTable_SalesByADayOfTheWeek.setSelectionBackground(Color.black);
        
        setMoviesToTable2();
        
    }
    
            public void setMoviesToTable1(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviedb", "postgres", "Mm1008mm!!");
            
            if (con != null) {
                System.out.println("Connection Established!");
            } else {
                System.out.println("Connection failed");
            }

            String sql = "Select movie.title, movie.isavailable, COALESCE(x.totalsale,0) AS totalticketsales\n" +
                    "from movie \n" +
                    "LEFT JOIN (SELECT movie.title, movie.isavailable, count(*) as totalsale\n" +
                    "		From movie\n" +
                    "		JOIN showtime ON(movie.id = showtime.movieid)\n" +
                    "		JOIN ticketsale ON (showtime.id = ticketsale.showtimeid)\n" +
                    "		Group By movie.title, isavailable\n" +
                    "	  )as x on movie.title = x.title	\n" +
                    "Order By totalticketsales";
            
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                var title = rs.getString("title");
                Boolean isAvailable = rs.getBoolean("isAvailable");
                Integer totalTicketSales = rs.getInt("totalticketsales");
                
                Object[] obj = {title, isAvailable, totalTicketSales};
                model = (DefaultTableModel) jTable_SalesByAvailability.getModel();
                model.addRow(obj);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }


     public void setMoviesToTable2(){
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/moviedb", "postgres", "Mm1008mm!!");
            
            if (con != null) {
                System.out.println("Connection Established!");
            } else {
                System.out.println("Connection failed");
            }
            
            String sql = "SELECT to_char(date, 'Day') AS DayofTheWeek , count(*) as totalticketsales\n" +
"From showtime\n" +
"JOIN movie ON(movie.id = showtime.movieid)\n" +
"Group By DayofTheWeek";
            
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                var dayoftheweek = rs.getString("DayofTheWeek");
                Integer totalTicketSales = rs.getInt("totalticketsales");
                
                Object[] obj = {dayoftheweek, totalTicketSales};
                model = (DefaultTableModel) jTable_SalesByADayOfTheWeek.getModel();
                model.addRow(obj);
            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    private void txt_SearchByADayOfTheWeekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SearchByADayOfTheWeekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchByADayOfTheWeekActionPerformed

    private void txt_SearchByADayOfTheWeekKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SearchByADayOfTheWeekKeyReleased
        String searchString = txt_SearchByADayOfTheWeek.getText();
        search2(searchString);
    }//GEN-LAST:event_txt_SearchByADayOfTheWeekKeyReleased

    
        public void search2(String str) {
        model = (DefaultTableModel) jTable_SalesByADayOfTheWeek.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable_SalesByADayOfTheWeek.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel_ViewTheaterSalesData = new javax.swing.JLabel();
        txt_SearchByADayOfTheWeek = new javax.swing.JTextField();
        jLabel_SearchByADayofTheWeek = new javax.swing.JLabel();
        txt_SearchByAvailability = new javax.swing.JTextField();
        jLabel_SearchByAvailability = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_SalesByAvailability = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_SalesByADayOfTheWeek = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_ViewTheaterSalesData.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel_ViewTheaterSalesData.setText("View Theater Sales Data");

        txt_SearchByADayOfTheWeek.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_SearchByADayOfTheWeek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SearchByADayOfTheWeekActionPerformed(evt);
            }
        });
        txt_SearchByADayOfTheWeek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_SearchByADayOfTheWeekKeyReleased(evt);
            }
        });

        jLabel_SearchByADayofTheWeek.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel_SearchByADayofTheWeek.setText("Search By Day of the Week: ");

        txt_SearchByAvailability.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_SearchByAvailability.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SearchByAvailabilityActionPerformed(evt);
            }
        });
        txt_SearchByAvailability.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_SearchByAvailabilityKeyReleased(evt);
            }
        });

        jLabel_SearchByAvailability.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel_SearchByAvailability.setText("Search By Availability: ");

        jTable_SalesByAvailability.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Title", "Is Available", "TotalTicketSales"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.Boolean.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable_SalesByAvailability);
        if (jTable_SalesByAvailability.getColumnModel().getColumnCount() > 0) {
            jTable_SalesByAvailability.getColumnModel().getColumn(1).setHeaderValue("Is Available");
        }

        jTable_SalesByADayOfTheWeek.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Day of the Week", "TotalTicketSales"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable_SalesByADayOfTheWeek);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(376, 376, 376)
                                .addComponent(jLabel_ViewTheaterSalesData, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel_SearchByAvailability)
                                                .addGap(18, 18, 18)
                                                .addComponent(txt_SearchByAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel_SearchByADayofTheWeek)
                                                .addGap(18, 18, 18)
                                                .addComponent(txt_SearchByADayOfTheWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(36, 36, 36)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(162, 162, 162))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel_ViewTheaterSalesData, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel_SearchByAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_SearchByAvailability, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel_SearchByADayofTheWeek, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_SearchByADayOfTheWeek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(197, Short.MAX_VALUE))
        );

        setBounds(0, 0, 1285, 775);
    }// </editor-fold>
        
    private void txt_SearchByAvailabilityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SearchByAvailabilityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SearchByAvailabilityActionPerformed

    private void txt_SearchByAvailabilityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SearchByAvailabilityKeyReleased
        String searchString = txt_SearchByAvailability.getText();
        search1(searchString);
    }//GEN-LAST:event_txt_SearchByAvailabilityKeyReleased

        public void search1(String str) {
        model = (DefaultTableModel) jTable_SalesByAvailability.getModel();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        jTable_SalesByAvailability.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(str));
        }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewTheaterSalesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewTheaterSalesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewTheaterSalesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewTheaterSalesData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTheaterSalesData().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel_SearchByADayofTheWeek;
    private javax.swing.JLabel jLabel_SearchByAvailability;
    private javax.swing.JLabel jLabel_ViewTheaterSalesData;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable_SalesByADayOfTheWeek;
    private javax.swing.JTable jTable_SalesByAvailability;
    private javax.swing.JTextField txt_SearchByADayOfTheWeek;
    private javax.swing.JTextField txt_SearchByAvailability;
    // End of variables declaration//GEN-END:variables
}