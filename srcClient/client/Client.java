package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Anja Tanovic
 */
public class Client extends javax.swing.JFrame {

    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private ReceiveMessageFromServer reciever;
    
    private String userName;
    private String role;
    
    private String [] students;
    private int numberOfStudents;
    
    private String [] courses;
    private int numberOfCourses;
    
    private final String users_txt = "D:\\Anja\\FAKULTET\\Master\\Razvoj softvera za embeded operativne sisteme\\NetBeans\\Zadaci\\2\\Java-Student-Service\\srcServer\\server\\users.txt";
    File users_file;
    
    public String getUserName() {
        return userName;
    }

    public String getRole() {
        return role;
    }
    
    public BufferedReader getBr() {
        return br;
    }
 
    /**
     * Creates new form ClientGUI
     */
    public Client() {
        initComponents();
        
        labelNoConnection.setVisible(false);
        labelRole.setVisible(false);
        labelUsername.setVisible(false);
        labelPassword.setVisible(false);
        comboRole.setVisible(false);
        textUsername.setVisible(false);
        textPassword.setVisible(false);
        buttonLogIn.setVisible(false);
        labelNotCorrect.setVisible(false);
        
        labelStudents.setVisible(false);
        labelCourses.setVisible(false);
        listStudents.setVisible(false);
        listCourses.setVisible(false);
        buttonAddStudent.setVisible(false);
        buttonAddCourse.setVisible(false);
        
        this.students = new String[100];
        this.numberOfStudents = 0;
        this.courses = new String[100];
        this.numberOfCourses = 0;
    }
    
    public void loginSuccessful(boolean ok) {
        if (ok) {
            labelRole.setVisible(false);
            labelUsername.setVisible(false);
            labelPassword.setVisible(false);
            comboRole.setVisible(false);
            textUsername.setVisible(false);
            textPassword.setVisible(false);
            buttonLogIn.setVisible(false);
            labelNotCorrect.setVisible(false);

            if (this.role.equalsIgnoreCase("admin")){
                labelStudents.setVisible(true);
                labelCourses.setVisible(true);
                listStudents.setVisible(true);
                listCourses.setVisible(true); 
                buttonAddStudent.setVisible(true);
                buttonAddCourse.setVisible(true);
            }
        }
        else
        {
            labelNotCorrect.setVisible(true);
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

        buttonSignIn = new javax.swing.JButton();
        labelWelcome = new javax.swing.JLabel();
        textPassword = new javax.swing.JPasswordField();
        textUsername = new javax.swing.JTextField();
        labelUsername = new javax.swing.JLabel();
        labelPassword = new javax.swing.JLabel();
        comboRole = new javax.swing.JComboBox<>();
        labelRole = new javax.swing.JLabel();
        buttonLogIn = new javax.swing.JButton();
        labelNoConnection = new javax.swing.JLabel();
        labelStudents = new javax.swing.JLabel();
        labelCourses = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listStudents = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        listCourses = new javax.swing.JList<>();
        buttonAddStudent = new javax.swing.JButton();
        buttonAddCourse = new javax.swing.JButton();
        labelNotCorrect = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonSignIn.setText("Sign in");
        buttonSignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSignInActionPerformed(evt);
            }
        });

        labelWelcome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelWelcome.setText("Welcome to Student Service!");

        textPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPasswordActionPerformed(evt);
            }
        });

        textUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textUsernameActionPerformed(evt);
            }
        });

        labelUsername.setText("Username:");

        labelPassword.setText("Password:");

        comboRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Student" }));
        comboRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRoleActionPerformed(evt);
            }
        });

        labelRole.setText("Role");

        buttonLogIn.setText("Log In");
        buttonLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogInActionPerformed(evt);
            }
        });

        labelNoConnection.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNoConnection.setText("Server is not available. Try again later.");

        labelStudents.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelStudents.setText("Students:");

        labelCourses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCourses.setText("Courses:");

        jScrollPane1.setViewportView(listStudents);

        jScrollPane2.setViewportView(listCourses);

        buttonAddStudent.setText("Add Student");
        buttonAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddStudentActionPerformed(evt);
            }
        });

        buttonAddCourse.setText("Add Course");
        buttonAddCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddCourseActionPerformed(evt);
            }
        });

        labelNotCorrect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNotCorrect.setText("Username and password are not correct. Please try again.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(84, 84, 84)
                                    .addComponent(buttonSignIn))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(labelPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textPassword))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(labelUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelRole, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textUsername)))
                                    .addComponent(buttonLogIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
                            .addComponent(labelWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelNoConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(labelStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonAddStudent, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(buttonAddCourse, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(labelCourses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 208, Short.MAX_VALUE)
                .addComponent(labelNotCorrect, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(labelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonSignIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNoConnection)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPassword))
                .addGap(18, 18, 18)
                .addComponent(buttonLogIn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNotCorrect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStudents)
                    .addComponent(labelCourses))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddStudent)
                    .addComponent(buttonAddCourse)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSignInActionPerformed

        try {
            //First connect to server
            this.socket = new Socket("127.0.0.1", 6001);
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
            
            //Create new thread to recieve messagers from server
            this.reciever = new ReceiveMessageFromServer(this);
            Thread thr = new Thread(reciever);
            thr.start();
            
            //enable next step
            buttonSignIn.setVisible(false);
            labelWelcome.setVisible(false);
            labelNoConnection.setVisible(false);

            labelRole.setVisible(true);
            labelUsername.setVisible(true);
            labelPassword.setVisible(true);
            comboRole.setVisible(true);
            textUsername.setVisible(true);
            textPassword.setVisible(true);
            buttonLogIn.setVisible(true);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            labelNoConnection.setVisible(true);
        }        
    }//GEN-LAST:event_buttonSignInActionPerformed

    private void textPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textPasswordActionPerformed

    private void comboRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboRoleActionPerformed

    private void textUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textUsernameActionPerformed

    private void buttonLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogInActionPerformed
        
        if (!this.textUsername.getText().equals("") && this.textPassword.getPassword().length != 0) {
            //create message role:user:password
            String loginMessage = this.textUsername.getText() + ":"
                    + String.valueOf(this.textPassword.getPassword()) +  ":" 
                    + this.comboRole.getSelectedItem().toString();		
            this.pw.println(loginMessage);
            
            this.role = this.comboRole.getSelectedItem().toString();
            this.userName = this.textUsername.getText();
        } else {
            JOptionPane.showMessageDialog(null, "Insert username and password!");
        }
    }//GEN-LAST:event_buttonLogInActionPerformed

    private void buttonAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddStudentActionPerformed
        String newStudentFirstName = JOptionPane.showInputDialog("Enter student's first name:");
        String newStudentLastName = JOptionPane.showInputDialog("Enter student's last name:");
        String newStudentIndex = JOptionPane.showInputDialog("Enter student's index:");
        String newStudentJMBG = JOptionPane.showInputDialog("Enter student's JMBG:");
        String newStudentUsername = JOptionPane.showInputDialog("Enter username for student's new account:");
        String newStudentPassword = JOptionPane.showInputDialog("Enter password for student's new account:");
       
        String newFileInfo = newStudentUsername + ":" + newStudentPassword + ":" + "student";
        try {
            PrintWriter usersPw = new PrintWriter(new FileWriter(users_txt,true));
            usersPw.println(newFileInfo);
            usersPw.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (numberOfStudents < 100) {
            students[numberOfStudents] = newStudentFirstName + " " + newStudentLastName;
            numberOfStudents++;
        }
        else {
            System.out.println("More than 100 students");
            System.exit(1);
        }
        
        listStudents.setModel(new DefaultComboBoxModel<>(students));
    }//GEN-LAST:event_buttonAddStudentActionPerformed

    private void buttonAddCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCourseActionPerformed
        String newCourseName = JOptionPane.showInputDialog("Enter course's name:");
       
        if (numberOfCourses < 100) {
            courses[numberOfCourses] = newCourseName;
            numberOfCourses++;
        }
        else {
            System.out.println("More than 100 courses");
            System.exit(1);
        }
        
        listCourses.setModel(new DefaultComboBoxModel<>(courses));
    }//GEN-LAST:event_buttonAddCourseActionPerformed

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
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Client().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddCourse;
    private javax.swing.JButton buttonAddStudent;
    private javax.swing.JButton buttonLogIn;
    private javax.swing.JButton buttonSignIn;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelCourses;
    private javax.swing.JLabel labelNoConnection;
    private javax.swing.JLabel labelNotCorrect;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelRole;
    private javax.swing.JLabel labelStudents;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JList<String> listCourses;
    private javax.swing.JList<String> listStudents;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
