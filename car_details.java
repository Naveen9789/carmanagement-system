import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NAVEENKUMAR G
 */
public class car_details extends javax.swing.JFrame {

    public car_details() {
        initComponents();
        Connect();
        cardetails_table();
        Autoid();
        ownerid();
        //To avoid exception before for entering the date
        update_btn.setEnabled(false);
    }
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    // Variable Declaration 
    private String carid, ownerid, carname, model, carnumber, location, totalowners;

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

    //Variable intialization write in method to avoid duplication of code
    private void intialization() {
        carid = carid_txt.getText();
        ownerid = (String) ownerid_combobox.getSelectedItem();
        carname = carname_txt.getText();
        model = model_txt.getText();
        carnumber = carnumber_txt.getText();
        location = location_txt.getText();
        totalowners = totalowners_txt.getText();
    }

    // Automatic id generation to avoid the duplications in primarykey 
    private void Autoid() {
        String query = "select max(carid) from cardetails";
        try {
            rs = pst.executeQuery(query);
            rs.next();
            String maxcarid = rs.getString(1);

            if (maxcarid == null) {
                carid_txt.setText("CR001"); //CR represents CAR
            } else {
                int id = Integer.parseInt(maxcarid.substring(2)) + 1;
                carid_txt.setText("CR" + String.format("%03d", id));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while generation of autoid");
            System.out.println(ex);
        }
    }

    //To load the ownerid in the combobox to avoid mismatch entries
    private void ownerid() {
        try {
            pst = con.prepareStatement("select ownerid from ownerdetails");
            rs = pst.executeQuery();
            ownerid_combobox.removeAllItems();

            while (rs.next()) {
                ownerid_combobox.addItem(rs.getString(1));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //To insert the values in the cardetails table
    private void insert() {
        int total_owners = Integer.parseInt(totalowners);
        String query = "insert into cardetails values(?,?,?,?,?,?,?)";

        try {
            pst = con.prepareStatement(query);
            pst.setString(1, carid);
            pst.setString(2, ownerid);
            pst.setString(3, carname);
            pst.setString(4, model);
            pst.setString(5, carnumber);
            pst.setString(6, location);
            pst.setInt(7, total_owners);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data inserted successfully");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error while insertion");
            System.out.println(ex);
        }

    }

    //To load the cardetails in the table
    public void cardetails_table() {
        try {
            pst = con.prepareStatement("select * from cardetails");
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int c = rsmd.getColumnCount();

            DefaultTableModel df = (DefaultTableModel) cardetails_table.getModel();
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
        carid_txt.setText("");
        carname_txt.setText("");
        model_txt.setText("");
        carnumber_txt.setText("");
        location_txt.setText("");
        totalowners_txt.setText("");
        ownerid_combobox.setSelectedIndex(0);
    }

    //To update the values in the cardetails table
    private void update() {
        try {
            String query = "update cardetails set ownerid=?,carname=?,model=?,carnumber=?,location=?,totalownerofthecar=? where carid=?";
            pst = con.prepareStatement(query);
            pst.setString(1, ownerid);
            pst.setString(2, carname);
            pst.setString(3, model);
            pst.setString(4, carnumber);
            pst.setString(5, location);
            pst.setString(6, totalowners);
            pst.setString(7, carid);
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

        taskbar_panel1 = new javax.swing.JPanel();
        ownerdetail_btn = new javax.swing.JButton();
        carlist_btn = new javax.swing.JButton();
        soldcar_btn = new javax.swing.JButton();
        totprofit_btn = new javax.swing.JButton();
        cardetail_btn = new javax.swing.JButton();
        cardetails_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        carid_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        carname_txt = new javax.swing.JTextField();
        model_txt = new javax.swing.JTextField();
        carnumber_txt = new javax.swing.JTextField();
        location_txt = new javax.swing.JTextField();
        totalowners_txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        cardetails_table = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        insert_btn = new javax.swing.JButton();
        update_btn = new javax.swing.JButton();
        ownerid_combobox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taskbar_panel1.setBackground(new java.awt.Color(153, 255, 255));

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
        totprofit_btn.setText("PROFIT");
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

        javax.swing.GroupLayout taskbar_panel1Layout = new javax.swing.GroupLayout(taskbar_panel1);
        taskbar_panel1.setLayout(taskbar_panel1Layout);
        taskbar_panel1Layout.setHorizontalGroup(
            taskbar_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskbar_panel1Layout.createSequentialGroup()
                .addGroup(taskbar_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(taskbar_panel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cardetail_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(taskbar_panel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(taskbar_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totprofit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(soldcar_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ownerdetail_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(carlist_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
        );
        taskbar_panel1Layout.setVerticalGroup(
            taskbar_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskbar_panel1Layout.createSequentialGroup()
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

        jLabel1.setText("car id");

        jLabel2.setText("Car name");

        jLabel3.setText("Owner id");

        jLabel4.setText("Model");

        jLabel5.setText("Car number");

        jLabel6.setText("Total owners of car");

        jLabel7.setText("Location");

        cardetails_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "car id", "owner id", "car name", "model", "car number", "location", "total car in office"
            }
        ));
        cardetails_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardetails_tableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cardetails_tableMousePressed(evt);
            }
        });
        cardetails_table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cardetails_tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(cardetails_table);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("CAR DETAILS");

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

        ownerid_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        javax.swing.GroupLayout cardetails_panelLayout = new javax.swing.GroupLayout(cardetails_panel);
        cardetails_panel.setLayout(cardetails_panelLayout);
        cardetails_panelLayout.setHorizontalGroup(
            cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardetails_panelLayout.createSequentialGroup()
                .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardetails_panelLayout.createSequentialGroup()
                        .addGap(291, 291, 291)
                        .addComponent(jLabel8))
                    .addGroup(cardetails_panelLayout.createSequentialGroup()
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(cardetails_panelLayout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(insert_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cardetails_panelLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(cardetails_panelLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(28, 28, 28)
                                        .addComponent(totalowners_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(cardetails_panelLayout.createSequentialGroup()
                                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardetails_panelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(carname_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(model_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(carnumber_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(location_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(cardetails_panelLayout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(carid_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                                    .addComponent(ownerid_combobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardetails_panelLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(update_btn))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        cardetails_panelLayout.setVerticalGroup(
            cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardetails_panelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardetails_panelLayout.createSequentialGroup()
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carid_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ownerid_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(model_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(carnumber_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(location_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalowners_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(cardetails_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insert_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(taskbar_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cardetails_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(taskbar_panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cardetails_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        cardetails_table();
    }//GEN-LAST:event_insert_btnActionPerformed

    private void update_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_btnActionPerformed
        intialization();
        update();
        clear();
        cardetails_table();
        insert_btn.setEnabled(true);
        update_btn.setEnabled(false);
    }//GEN-LAST:event_update_btnActionPerformed

    private void cardetails_tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardetails_tableKeyPressed

    }//GEN-LAST:event_cardetails_tableKeyPressed

    private void cardetails_tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardetails_tableMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cardetails_tableMousePressed

    private void cardetails_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardetails_tableMouseClicked
        //To load the data in the textbox when the table row is clicked
        DefaultTableModel d1 = (DefaultTableModel) cardetails_table.getModel();
        int index = cardetails_table.getSelectedRow();
        carid_txt.setText(d1.getValueAt(index, 0).toString());
        carname_txt.setText(d1.getValueAt(index, 2).toString());
        model_txt.setText(d1.getValueAt(index, 3).toString());
        carnumber_txt.setText(d1.getValueAt(index, 4).toString());
        location_txt.setText(d1.getValueAt(index, 5).toString());
        totalowners_txt.setText(d1.getValueAt(index, 6).toString());

        update_btn.setEnabled(true);
        insert_btn.setEnabled(false);
    }//GEN-LAST:event_cardetails_tableMouseClicked

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
            java.util.logging.Logger.getLogger(car_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(car_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(car_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(car_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new car_details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cardetail_btn;
    private javax.swing.JPanel cardetails_panel;
    private javax.swing.JTable cardetails_table;
    private javax.swing.JTextField carid_txt;
    private javax.swing.JButton carlist_btn;
    private javax.swing.JTextField carname_txt;
    private javax.swing.JTextField carnumber_txt;
    private javax.swing.JButton insert_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField location_txt;
    private javax.swing.JTextField model_txt;
    private javax.swing.JButton ownerdetail_btn;
    private javax.swing.JComboBox<String> ownerid_combobox;
    private javax.swing.JButton soldcar_btn;
    private javax.swing.JPanel taskbar_panel1;
    private javax.swing.JTextField totalowners_txt;
    private javax.swing.JButton totprofit_btn;
    private javax.swing.JButton update_btn;
    // End of variables declaration//GEN-END:variables
}
