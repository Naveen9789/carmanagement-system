
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NAVEENKUMAR G
 */
public class carlist extends javax.swing.JFrame {

    public carlist() {
        initComponents();
        Connect();
        Autoid();
        carlist_table();
        carid();
    }

    Connection con;
    ResultSet rs;
    PreparedStatement pst;

    //variables declaration
    String listid, carid, entrydate, exitdate, exitstatus;

    //Database connection method
    private void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmanagement_system", "root", "");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Check the Database Connection");
            System.out.println(ex);
        }
    }

    //intialization
    private void initialization() {
        listid = listid_txt.getText();
        carid = (String) carid_combobox.getSelectedItem();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        entrydate = formater.format(Entrydate.getDate());
        exitdate = formater.format(Exitdate.getDate());
        exitstatus = (String) exitstatus_combobox.getSelectedItem();
    }

    // Automatic id generation to avoid the duplications in primarykey 
    private void Autoid() {
        try {
            pst = con.prepareStatement("select max(listid) from carlist");
            rs = pst.executeQuery();
            rs.next();
            String maxid = rs.getString(1);

            if (maxid == null) {
                listid_txt.setText("CL001"); //CL represents carlist
            } else {
                int id = Integer.parseInt(maxid.substring(2)) + 1;
                listid_txt.setText("CL" + String.format("%03d", id));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while generation of autoid");
            System.out.println(ex);
        }
    }

    //To load the carid in the combobox to avoid mismatch entries
    private void carid() {
        try {
            pst = con.prepareStatement("select carid from cardetails");
            rs = pst.executeQuery();
            carid_combobox.removeAllItems();

            while (rs.next()) {
                carid_combobox.addItem(rs.getString(1));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //To insert the values in the cardetails table
    private void insert() {
        String query = "insert into carlist values(?,?,?,?,?)";
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, listid);
            pst.setString(2, carid);
            pst.setString(3, entrydate);
            pst.setString(4, exitdate);
            pst.setString(5, exitstatus);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data inserted successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while insertion");
            System.out.println(ex);
        }
    }

    //To load the cardetails in the table
    private void carlist_table() {
        try {
            pst = con.prepareStatement("select * from carlist");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();

            DefaultTableModel df = (DefaultTableModel) carlist_table.getModel();
            df.setRowCount(0);

            while (rs.next()) {
                ArrayList<String> rowdata = new ArrayList<>();
                for (int i = 1; i <= c; i++) {
                    rowdata.add(rs.getString(i));
                }
                df.addRow(rowdata.toArray());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while loding cardetails table");
            System.out.println(ex);
        }
    }

    //To update the values in the cardetails table
    private void update() {
        try {
            pst = con.prepareStatement("update carlist set carid=?,entrydate=?,exitdate=?,exitstatus=? where listid=?");
            pst.setString(1, carid);
            pst.setString(2, entrydate);
            pst.setString(3, exitdate);
            pst.setString(4, exitstatus);
            pst.setString(5, listid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error while updation");
            System.out.println(ex);
        }
    }

    //To clear the textbox after the insertion process
    private void clear() {
        listid_txt.setText("");
        carid_combobox.setSelectedIndex(0);
        Entrydate.setDate(null);
        Exitdate.setDate(null);
        exitstatus_combobox.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskbar_panel5 = new javax.swing.JPanel();
        ownerdetail_btn = new javax.swing.JButton();
        carlist_btn = new javax.swing.JButton();
        soldcar_btn = new javax.swing.JButton();
        totprofit_btn = new javax.swing.JButton();
        cardetail_btn = new javax.swing.JButton();
        totalcar_panel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        insert_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        listid_txt = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        carlist_table = new javax.swing.JTable();
        Entrydate = new com.toedter.calendar.JDateChooser();
        Exitdate = new com.toedter.calendar.JDateChooser();
        carid_combobox = new javax.swing.JComboBox<>();
        exitstatus_combobox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taskbar_panel5.setBackground(new java.awt.Color(153, 255, 255));

        ownerdetail_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ownerdetail_btn.setText("OWNER DETAILS");
        ownerdetail_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ownerdetail_btnActionPerformed(evt);
            }
        });

        carlist_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        carlist_btn.setText("TOTAL CAR IN OFFICE");
        carlist_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carlist_btnActionPerformed(evt);
            }
        });

        soldcar_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        soldcar_btn.setText("SOLD CARS");
        soldcar_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soldcar_btnActionPerformed(evt);
            }
        });

        totprofit_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totprofit_btn.setText(" PROFIT");
        totprofit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totprofit_btnActionPerformed(evt);
            }
        });

        cardetail_btn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cardetail_btn.setText("CAR DETAILS");
        cardetail_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardetail_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskbar_panel5Layout = new javax.swing.GroupLayout(taskbar_panel5);
        taskbar_panel5.setLayout(taskbar_panel5Layout);
        taskbar_panel5Layout.setHorizontalGroup(
            taskbar_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskbar_panel5Layout.createSequentialGroup()
                .addGroup(taskbar_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(taskbar_panel5Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cardetail_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(taskbar_panel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(taskbar_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totprofit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(soldcar_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ownerdetail_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(carlist_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
        );
        taskbar_panel5Layout.setVerticalGroup(
            taskbar_panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskbar_panel5Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(cardetail_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(ownerdetail_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(carlist_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(soldcar_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(totprofit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel9.setText("exit status");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setText("CAR LIST");

        insert_btn.setBackground(new java.awt.Color(204, 255, 204));
        insert_btn.setText("INSERT");
        insert_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insert_btnActionPerformed(evt);
            }
        });

        update_btn.setBackground(new java.awt.Color(204, 255, 204));
        update_btn.setText("UPDATE");
        update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnActionPerformed(evt);
            }
        });

        jLabel18.setText("entry date");

        jLabel19.setText("list id");

        jLabel20.setText("car id");

        jLabel21.setText("exit date");

        carlist_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "list id", "car id", "entry date", "exist date", "exist status"
            }
        ));
        carlist_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carlist_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(carlist_table);

        carid_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        exitstatus_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select items", "inside office", "outside office", " " }));

        javax.swing.GroupLayout totalcar_panelLayout = new javax.swing.GroupLayout(totalcar_panel);
        totalcar_panel.setLayout(totalcar_panelLayout);
        totalcar_panelLayout.setHorizontalGroup(
            totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalcar_panelLayout.createSequentialGroup()
                .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(totalcar_panelLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel11))
                    .addGroup(totalcar_panelLayout.createSequentialGroup()
                        .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, totalcar_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(listid_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                        .addComponent(carid_combobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(exitstatus_combobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(Exitdate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Entrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36))
                            .addGroup(totalcar_panelLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(insert_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(totalcar_panelLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(update_btn))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        totalcar_panelLayout.setVerticalGroup(
            totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalcar_panelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(totalcar_panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insert_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(totalcar_panelLayout.createSequentialGroup()
                        .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(totalcar_panelLayout.createSequentialGroup()
                                .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(listid_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(carid_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Entrydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Exitdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(totalcar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitstatus_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(taskbar_panel5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalcar_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskbar_panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(totalcar_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ownerdetail_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ownerdetail_btnActionPerformed
        Owner_details ownerdetail = new Owner_details();
        ownerdetail.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ownerdetail_btnActionPerformed

    private void carlist_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carlist_btnActionPerformed
        carlist list = new carlist();
        list.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_carlist_btnActionPerformed

    private void soldcar_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soldcar_btnActionPerformed
        Sales_details sales = new Sales_details();
        sales.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_soldcar_btnActionPerformed

    private void totprofit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totprofit_btnActionPerformed
        profit profit = new profit();
        profit.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_totprofit_btnActionPerformed

    private void cardetail_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardetail_btnActionPerformed
        car_details cardetail = new car_details();
        cardetail.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_cardetail_btnActionPerformed

    private void carlist_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carlist_tableMouseClicked
        DefaultTableModel d1 = (DefaultTableModel) carlist_table.getModel();
        int index = carlist_table.getSelectedRow();

        listid_txt.setText(d1.getValueAt(index, 0).toString());
        entrydate = d1.getValueAt(index, 2).toString();
        exitdate = d1.getValueAt(index, 3).toString();
        exitstatus = d1.getValueAt(index, 4).toString();

        update_btn.setEnabled(true);
        insert_btn.setEnabled(false);
    }//GEN-LAST:event_carlist_tableMouseClicked

    private void insert_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_btnActionPerformed
        initialization();
        insert();
        clear();
        Autoid();
        carlist_table();
    }//GEN-LAST:event_insert_btnActionPerformed

    private void update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnActionPerformed
        initialization();
        update();
        clear();
        Autoid();
        carlist_table();
        insert_btn.setEnabled(true);
        update_btn.setEnabled(false);
    }//GEN-LAST:event_update_btnActionPerformed

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
            java.util.logging.Logger.getLogger(carlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(carlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(carlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(carlist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new carlist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Entrydate;
    private com.toedter.calendar.JDateChooser Exitdate;
    private javax.swing.JButton cardetail_btn;
    private javax.swing.JComboBox<String> carid_combobox;
    private javax.swing.JButton carlist_btn;
    private javax.swing.JTable carlist_table;
    private javax.swing.JComboBox<String> exitstatus_combobox;
    private javax.swing.JButton insert_btn;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField listid_txt;
    private javax.swing.JButton ownerdetail_btn;
    private javax.swing.JButton soldcar_btn;
    private javax.swing.JPanel taskbar_panel5;
    private javax.swing.JPanel totalcar_panel;
    private javax.swing.JButton totprofit_btn;
    private javax.swing.JButton update_btn;
    // End of variables declaration//GEN-END:variables
}
