/**
 *
 * @author NAVEENKUMAR G
 */
public class home extends javax.swing.JFrame {

    public home() {
        initComponents();
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        taskbar_panel = new javax.swing.JPanel();
        ownerdetail_btn = new javax.swing.JButton();
        carlist_btn = new javax.swing.JButton();
        soldcar_btn = new javax.swing.JButton();
        totprofit_btn = new javax.swing.JButton();
        cardetail_btn = new javax.swing.JButton();
        heading_panel = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        taskbar_panel.setBackground(new java.awt.Color(153, 255, 255));

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

        javax.swing.GroupLayout taskbar_panelLayout = new javax.swing.GroupLayout(taskbar_panel);
        taskbar_panel.setLayout(taskbar_panelLayout);
        taskbar_panelLayout.setHorizontalGroup(
            taskbar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, taskbar_panelLayout.createSequentialGroup()
                .addGroup(taskbar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(taskbar_panelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cardetail_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(taskbar_panelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(taskbar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totprofit_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(soldcar_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ownerdetail_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(carlist_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
        );
        taskbar_panelLayout.setVerticalGroup(
            taskbar_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskbar_panelLayout.createSequentialGroup()
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

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel32.setText("CAR MANAGEMENT  SYSTEM");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("java netbeans");

        javax.swing.GroupLayout heading_panelLayout = new javax.swing.GroupLayout(heading_panel);
        heading_panel.setLayout(heading_panelLayout);
        heading_panelLayout.setHorizontalGroup(
            heading_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, heading_panelLayout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(131, 131, 131))
            .addGroup(heading_panelLayout.createSequentialGroup()
                .addGap(322, 322, 322)
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        heading_panelLayout.setVerticalGroup(
            heading_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(heading_panelLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel33)
                .addContainerGap(372, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(taskbar_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heading_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(heading_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(taskbar_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cardetail_btn;
    private javax.swing.JButton carlist_btn;
    private javax.swing.JPanel heading_panel;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JButton ownerdetail_btn;
    private javax.swing.JButton soldcar_btn;
    private javax.swing.JPanel taskbar_panel;
    private javax.swing.JButton totprofit_btn;
    // End of variables declaration//GEN-END:variables
}
