/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package client;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author anjat
 */
public class EnterCourse extends javax.swing.JFrame {

    Client client;
    /**
     * Creates new form EnterCourse
     * @param client
     */
    public EnterCourse(Client client) {
        initComponents();
        this.client = client;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        labelPoints.setVisible(false);
        labelMinPoints.setVisible(false);
        labelMinPointsSum.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textName = new javax.swing.JTextField();
        textCatNum = new javax.swing.JTextField();
        textCatNames = new javax.swing.JTextField();
        textCatPoints = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        textCatMinPoints = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        buttonOk = new javax.swing.JButton();
        labelPoints = new javax.swing.JLabel();
        labelMinPoints = new javax.swing.JLabel();
        labelMinPointsSum = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Course name:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Number of categories:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Names of categories (use following format: name1, name2, name3 ...) :");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Points of categories (use following format: points1, points2, points3 ...) :");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Minimum number of points to pass the course for each category (use following format: points1, points2, points3 ...) :");

        buttonOk.setText("OK");
        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });

        labelPoints.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelPoints.setForeground(new java.awt.Color(255, 51, 51));
        labelPoints.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPoints.setText("Sum of all points has to be 100!");

        labelMinPoints.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelMinPoints.setForeground(new java.awt.Color(255, 51, 51));
        labelMinPoints.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMinPoints.setText("Minimum number of points in each category has to be lower then points in corresponding category!");

        labelMinPointsSum.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        labelMinPointsSum.setForeground(new java.awt.Color(255, 51, 51));
        labelMinPointsSum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMinPointsSum.setText("Sum of minimum number of points in each category has to be higher then 50!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textCatMinPoints, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textCatNum)
                    .addComponent(textName)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textCatNames)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textCatPoints)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(labelPoints, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMinPoints, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMinPointsSum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCatNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCatNames, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCatPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(labelPoints)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textCatMinPoints, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(labelMinPoints)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMinPointsSum)
                .addGap(23, 23, 23)
                .addComponent(buttonOk)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOkActionPerformed
        client.finishedCourse();
    }//GEN-LAST:event_buttonOkActionPerformed

    public void tryAgain() {
        JOptionPane.showMessageDialog(null, "Course information is not in correct format! Try again.");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton buttonOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel labelMinPoints;
    public static javax.swing.JLabel labelMinPointsSum;
    public static javax.swing.JLabel labelPoints;
    public static javax.swing.JTextField textCatMinPoints;
    public static javax.swing.JTextField textCatNames;
    public static javax.swing.JTextField textCatNum;
    public static javax.swing.JTextField textCatPoints;
    public static javax.swing.JTextField textName;
    // End of variables declaration//GEN-END:variables
}