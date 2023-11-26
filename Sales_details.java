import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NAVEENKUMAR G
 */
public class Sales_details extends javax.swing.JFrame {

    public Sales_details() {
        initComponents();
        Connect();
        carsalestable();
        Autoid();
        carid();
        //To avoid exception before for entering the date
        update_btn.setEnabled(false);
    }
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    //Variables declaration
    String salesid, carid, salesdate, salesstatus, deliverystatus, sellingprice;

    //Variable intialization write in method to avoid duplication of code
    private void intialization() {
        salesid = salesid_txt.getText();
        carid = (String) carid_combobox.getSelectedItem();
        SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
        salesdate = d1.format(solddate.getDate());
        salesstatus = salesstatus_txt.getText();
        deliverystatus = deliverystatus_txt.getText();
        sellingprice = sellingprice_txt.getText();
    }

    // For database connection 
    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/carmanagement_system", "root", "");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Check the Database Connection");
            System.out.println(ex);
        }
    }

    // Automatic id generation to avoid the duplications in primarykey 
    private void Autoid() {
        String query = "select max(salesid) from carsales";
        try {
            rs = pst.executeQuery(query);
            rs.next();
            String maxcarid = rs.getString(1);

            if (maxcarid == null) {
                salesid_txt.setText("CS001"); //CS represents CARSALES
            } else {
                int id = Integer.parseInt(maxcarid.substring(2)) + 1;
                salesid_txt.setText("CS" + String.format("%03d", id));
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
        String query = "insert into carsales values(?,?,?,?,?,?)";

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, salesid);
            pst.setString(2, carid);
            pst.setString(3, salesdate);
            pst.setString(4, salesstatus);
            pst.setString(5, deliverystatus);
            pst.setString(6, sellingprice);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data inserted successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while insertion");
            System.out.println(ex);
        }
    }

    //To load the cardetails in the table   
    public void carsalestable() {
        try {
            pst = con.prepareStatement("select * from carsales");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();

            DefaultTableModel df = (DefaultTableModel) carsales_table.getModel();
            df.setRowCount(0);

            while (rs.next()) {
                ArrayList<String> rowData = new ArrayList<>();

                for (int i = 1; i <= c; i++) {
                    rowData.add(rs.getString(i));
                }

                df.addRow(rowData.toArray());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while loding cardetails table");
            System.out.println(ex);
        }
    }

    //To clear the textbox after the insertion process
    private void clear() {
        salesid_txt.setText("");
        salesstatus_txt.setText("");
        deliverystatus_txt.setText("");
        sellingprice_txt.setText("");
        solddate.setDate(null);
        carid_combobox.setSelectedIndex(0);
    }

    //To update the values in the cardetails table
    private void update() {
        try {
            String query = "update carsales set carid=?,solddate=?,salesstatus=?,deliverystatus=?,sellingprice=? where salesid=?";
            pst = con.prepareStatement(query);
            pst.setString(1, carid);
            pst.setString(2, salesdate);
            pst.setString(3, salesstatus);
            pst.setString(4, deliverystatus);
            pst.setString(5, sellingprice);
            pst.setString(6, salesid);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data updated successfully");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error while updation");
            System.out.println(ex);
        }
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
        soldcars_panel = new javax.swing.JPanel();
        salesstatus_txt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        salesid_txt = new javax.swing.JTextField();
        insert_btn = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        update_btn = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        carsales_table = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        deliverystatus_txt = new javax.swing.JTextField();
        sellingprice_txt = new javax.swing.JTextField();
        solddate = new com.toedter.calendar.JDateChooser();
        carid_combobox = new javax.swing.JComboBox<>();

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

        jLabel17.setText("sold date");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setText("SOLD CARS");

        jLabel22.setText("sales id");

        insert_btn.setBackground(new java.awt.Color(204, 255, 204));
        insert_btn.setText("INSERT");
        insert_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insert_btnActionPerformed(evt);
            }
        });

        jLabel23.setText("car id");

        update_btn.setBackground(new java.awt.Color(204, 255, 204));
        update_btn.setText("UPDATE");
        update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_btnActionPerformed(evt);
            }
        });

        jLabel24.setText("sales status");

        carsales_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "sales id", "car id", "sold date", "sales status", "delivery status", "selling price"
            }
        ));
        carsales_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carsales_tableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(carsales_table);

        jLabel25.setText("delivery status");

        jLabel26.setText("selling price");

        carid_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout soldcars_panelLayout = new javax.swing.GroupLayout(soldcars_panel);
        soldcars_panel.setLayout(soldcars_panelLayout);
        soldcars_panelLayout.setHorizontalGroup(
            soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(soldcars_panelLayout.createSequentialGroup()
                .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(soldcars_panelLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel12))
                    .addGroup(soldcars_panelLayout.createSequentialGroup()
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(soldcars_panelLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(insert_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, soldcars_panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(soldcars_panelLayout.createSequentialGroup()
                                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(36, 36, 36)
                                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(salesstatus_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(solddate, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(salesid_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(soldcars_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(carid_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, soldcars_panelLayout.createSequentialGroup()
                                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(sellingprice_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                            .addComponent(deliverystatus_txt))))
                                .addGap(35, 35, 35)))
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(soldcars_panelLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(update_btn))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        soldcars_panelLayout.setVerticalGroup(
            soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(soldcars_panelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(soldcars_panelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(insert_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(soldcars_panelLayout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salesid_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carid_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(solddate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salesstatus_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deliverystatus_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(soldcars_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sellingprice_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(taskbar_panel5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soldcars_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskbar_panel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(soldcars_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void insert_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insert_btnActionPerformed
        intialization();
        insert();
        clear();
        Autoid();
        carsalestable();
    }//GEN-LAST:event_insert_btnActionPerformed

    private void update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnActionPerformed
        intialization();
        update();
        clear();
        carsalestable();
        Autoid();
        insert_btn.setEnabled(true);
        update_btn.setEnabled(false);
    }//GEN-LAST:event_update_btnActionPerformed

    private void carsales_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carsales_tableMouseClicked
        //To load the data in the textbox when the table row is clicked
        DefaultTableModel d1 = (DefaultTableModel) carsales_table.getModel();
        int index = carsales_table.getSelectedRow();
        salesid_txt.setText(d1.getValueAt(index, 0).toString());
        salesstatus_txt.setText(d1.getValueAt(index, 3).toString());
        deliverystatus_txt.setText(d1.getValueAt(index, 4).toString());
        sellingprice_txt.setText(d1.getValueAt(index, 5).toString());
        carid_combobox.setSelectedItem(d1.getValueAt(index, 1).toString());

        update_btn.setEnabled(true);
        insert_btn.setEnabled(false);
    }//GEN-LAST:event_carsales_tableMouseClicked

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
            java.util.logging.Logger.getLogger(Sales_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sales_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sales_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sales_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sales_details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cardetail_btn;
    private javax.swing.JComboBox<String> carid_combobox;
    private javax.swing.JButton carlist_btn;
    private javax.swing.JTable carsales_table;
    private javax.swing.JTextField deliverystatus_txt;
    private javax.swing.JButton insert_btn;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton ownerdetail_btn;
    private javax.swing.JTextField salesid_txt;
    private javax.swing.JTextField salesstatus_txt;
    private javax.swing.JTextField sellingprice_txt;
    private javax.swing.JButton soldcar_btn;
    private javax.swing.JPanel soldcars_panel;
    private com.toedter.calendar.JDateChooser solddate;
    private javax.swing.JPanel taskbar_panel5;
    private javax.swing.JButton totprofit_btn;
    private javax.swing.JButton update_btn;
    // End of variables declaration//GEN-END:variables
}
