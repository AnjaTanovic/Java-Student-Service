package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    EnterCourse addCourse;
    EnterStudent addStudent;
    
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
        labelStudentInfo.setVisible(false);
        labelCourses.setVisible(false);
        comboStudent.setVisible(false);
        comboCourse.setVisible(false);
        textStudent.setVisible(false);
        textStudent.setEditable(false);
        textCourse.setVisible(false);
        textCourse.setEditable(false);
        jScrollPane3.setVisible(false);
        jScrollPane4.setVisible(false);
        buttonAddStudent.setVisible(false);
        buttonStudentCourse.setVisible(false);
        buttonGradeStudent.setVisible(false);
        buttonAddCourse.setVisible(false);
        buttonAddAdmin.setVisible(false); 
        buttonLogOut.setVisible(false);
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

            if (this.role.equals("Admin")){
                buttonAddStudent.setVisible(true);
                buttonStudentCourse.setVisible(true);
                buttonGradeStudent.setVisible(true);
                buttonAddCourse.setVisible(true);
                buttonAddAdmin.setVisible(true);
                labelStudents.setVisible(true);
                labelCourses.setVisible(true);
                comboStudent.setVisible(true);
                comboCourse.setVisible(true);
                textStudent.setVisible(true);
                textCourse.setVisible(true);
                jScrollPane3.setVisible(true);
                jScrollPane4.setVisible(true);
                buttonLogOut.setVisible(true);
            }
            else {
                labelStudentInfo.setVisible(true);
                textStudent.setVisible(true);
                jScrollPane3.setVisible(true);
                buttonLogOut.setVisible(true);
                
                this.pw.println("GetInfo:" + this.userName);
            }
            
        }
        else
        {
            labelNotCorrect.setVisible(true);
        }
    }

    public void updateStudents(String [] names) {
        comboStudent.removeAllItems();
        for (String name : names) {
            comboStudent.addItem(name);
        }
    }
    
    public void updateCourses(String [] names) {
        comboCourse.removeAllItems();
        for (String name : names) {
            comboCourse.addItem(name);
        }
    }
    
    public void writeStudentInfo(String info) {
        textStudent.setText(info);
    }
    
    public void writeCourseInfo(String info) {
        textCourse.setText(info);
    }
    
    public boolean checkCurrentStudentIndex(String index) {
        boolean eq;
        
        if (this.role.equals("Admin")) {
            String[] nameIndex = comboStudent.getSelectedItem().toString().split(",");
            if (index.equals(nameIndex[1].trim()))
                eq = true;
            else
                eq = false;
        }
        else {
            if (textStudent.getText().contains(index) || textStudent.getText().equals(""))
            eq = true;
        else
            eq = false;
        }
        
        return eq;
    }
    
    public boolean checkCurrentCourseName(String name) {
        boolean eq;
        if (name.equals(comboCourse.getSelectedItem().toString()))
            eq = true;
        else
            eq = false;
        
        return eq;
    }
    
    public void printMess(String mess) {
        JOptionPane.showMessageDialog(null, mess);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
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
        buttonAddStudent = new javax.swing.JButton();
        buttonAddCourse = new javax.swing.JButton();
        labelNotCorrect = new javax.swing.JLabel();
        buttonAddAdmin = new javax.swing.JButton();
        comboStudent = new javax.swing.JComboBox<>();
        comboCourse = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        textStudent = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        textCourse = new javax.swing.JTextArea();
        buttonStudentCourse = new javax.swing.JButton();
        buttonGradeStudent = new javax.swing.JButton();
        labelStudentInfo = new javax.swing.JLabel();
        buttonLogOut = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

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
        labelStudents.setText("Choose student:");

        labelCourses.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCourses.setText("Choose course:");

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

        buttonAddAdmin.setText("Add Admin");
        buttonAddAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddAdminActionPerformed(evt);
            }
        });

        comboStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboStudentActionPerformed(evt);
            }
        });

        comboCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCourseActionPerformed(evt);
            }
        });

        textStudent.setColumns(20);
        textStudent.setRows(5);
        jScrollPane3.setViewportView(textStudent);

        textCourse.setColumns(20);
        textCourse.setRows(5);
        jScrollPane4.setViewportView(textCourse);
        textCourse.getAccessibleContext().setAccessibleDescription("");

        buttonStudentCourse.setText("Add a new course to the student");
        buttonStudentCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStudentCourseActionPerformed(evt);
            }
        });

        buttonGradeStudent.setText("Grade Student");
        buttonGradeStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradeStudentActionPerformed(evt);
            }
        });

        labelStudentInfo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelStudentInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelStudentInfo.setText("Student Info:");

        buttonLogOut.setText("Log out");
        buttonLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelNotCorrect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelStudentInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelStudents, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboStudent, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonGradeStudent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addComponent(buttonStudentCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(comboCourse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddCourse, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelCourses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(labelNoConnection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                .addGap(31, 31, 31)
                .addComponent(labelStudentInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCourses, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelStudents))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddCourse)
                    .addComponent(buttonStudentCourse))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAddAdmin)
                    .addComponent(buttonGradeStudent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAddStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(buttonLogOut)
                .addContainerGap())
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
        } 
        else {
            JOptionPane.showMessageDialog(null, "Insert username and password!");
        }
    }//GEN-LAST:event_buttonLogInActionPerformed

    private void buttonAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddStudentActionPerformed
        //create new window
        addStudent = new EnterStudent(this);
        addStudent.setVisible(true);
    }//GEN-LAST:event_buttonAddStudentActionPerformed

    public void finishedStudent() {
        boolean ok = true;
        
        String newStudentFirstName = addStudent.textFirstName.getText().trim();
        String newStudentLastName = addStudent.textLastName.getText().trim();
        String newStudentIndex = addStudent.textIndex.getText().trim();
        String newStudentJMBG = addStudent.textJMGB.getText().trim();
        String newStudentUsername = addStudent.textUsername.getText().trim();
        String newStudentPassword = addStudent.textPassword.getText().trim();
       
        if (newStudentFirstName.equals("") || newStudentLastName.equals("") || newStudentIndex.equals("") 
                || newStudentJMBG.equals("") || newStudentUsername.equals("") || newStudentPassword.equals(""))
            ok = false;
        else {
            //check index format
            Pattern indexPattern = Pattern.compile("^\\s*(E|e)([1-3])([/-])20(0[0-9]|1[0-9]|2[0-3])\\s*$");
            Matcher matcher = indexPattern.matcher(newStudentIndex);
            boolean matchFound = matcher.find();
            if (!matchFound)
                ok = false;
            
            //check jmbg format
            Pattern jmbgPattern = Pattern.compile("^\\s*(0[1-9]|1[0-9]|2[0-9]|3[01])(0[1-9]|1[12])[0-9]{6}(9[0-9]{2}|0[01][0-9]|02[0-3])\\s*$");
            matcher = jmbgPattern.matcher(newStudentJMBG);
            matchFound = matcher.find();
            if (!matchFound)
                ok = false;
            else {
                String day = matcher.group(1);
                String month = matcher.group(2);
                if (Integer.parseInt(month) == 2 && Integer.parseInt(day) > 29) {
                    ok = false;
                }
                //months with 30 days
                if (Integer.parseInt(month) == 4 || Integer.parseInt(month) == 6 
                        || Integer.parseInt(month) == 9 || Integer.parseInt(month) == 11) {
                    if (Integer.parseInt(day) > 30)
                        ok = false;
                }
            }
        }
        
        if (ok) {
            addStudent.setVisible(false);
            
            String message1 = "New student";
            String message2 = newStudentFirstName + ":" + newStudentLastName + ":" + newStudentIndex + ":" 
                    + newStudentJMBG + ":" + newStudentUsername + ":" + newStudentPassword;
            this.pw.println(message1);
            this.pw.println(message2);
        }
        else {
            addStudent.tryAgain();
        }
        
    }
    
    private void buttonAddCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddCourseActionPerformed
        //create new window
        addCourse = new EnterCourse(this);
        addCourse.setVisible(true);
    }//GEN-LAST:event_buttonAddCourseActionPerformed

    public void finishedCourse() {
        boolean ok = true;
                
        String newCourseName = addCourse.textName.getText().trim();
        if (newCourseName.equals(""))
            ok = false;
        String newCourseNumber = addCourse.textCatNum.getText().trim();
        int courseNum = -1;
        try {
            courseNum = Integer.parseInt(newCourseNumber);
            if (courseNum < 1)
                ok = false;
        }
        catch (NumberFormatException nfe) {
            ok = false;
        }
        String[] categoriesN;
        String categoriesNames = addCourse.textCatNames.getText();
        categoriesN = categoriesNames.split(",");
        if (categoriesN.length != courseNum) {
            ok = false;
        }
        //check if categories have equal names
        for (int i = 0; i < categoriesN.length - 1; i++) {
            for (int j = i + 1; j < categoriesN.length; j++) {
                if (categoriesN[i].trim().equals(categoriesN[j].trim())) {
                    ok = false;
                    break;
                }
            }
        }
        String[] categoriesP;
        String categoriesPoints = addCourse.textCatPoints.getText();
        categoriesP = categoriesPoints.split(",");
        if (categoriesP.length != courseNum) {
            ok = false;
        }     
        String[] categoriesMP;
        String categoriesMinPoints = addCourse.textCatMinPoints.getText();
        categoriesMP = categoriesMinPoints.split(",");
        if (categoriesMP.length != courseNum) {
            ok = false;
        }   
        
        //check if sum is 100
        //check if min points are bigger than points
        //check if sum of min points is bigger then 50
        int sum = 0;
        int sumMin = 0;
        boolean bigger = false;
        
        for (int i = 0; i < categoriesP.length; i++) {
            try {
                int num = Integer.parseInt(categoriesP[i].trim());
                sum += num;
                
                if (categoriesP.length == categoriesMP.length) {
                    int numMin = Integer.parseInt(categoriesMP[i].trim());
                    if (numMin > num) {
                        ok = false;
                        bigger = true;
                    }
                    sumMin += numMin;
                }
                else 
                    bigger = true;
            }
            catch (NumberFormatException nfe) {
                ok = false;
                break;
            }
        }
        if (sum != 100) {
            ok = false;
            addCourse.labelPoints.setVisible(true);
        }
        else 
            addCourse.labelPoints.setVisible(false);
        if (bigger) {
            ok = false;
            addCourse.labelMinPoints.setVisible(true);
        }
        else
            addCourse.labelMinPoints.setVisible(false);
        if (sumMin < 51) {
            ok = false;
            addCourse.labelMinPointsSum.setVisible(true);
        }
        else 
            addCourse.labelMinPointsSum.setVisible(false);
        
        if (ok) {
            addCourse.setVisible(false);
            
            String message1 = "New course";
            String message2 = newCourseName + ":" + categoriesNames + ":" + categoriesPoints + ":" + categoriesMinPoints;  
            this.pw.println(message1);
            this.pw.println(message2);
        }
        else {
            addCourse.tryAgain();
        }
    }
    
    private void buttonAddAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddAdminActionPerformed
        
        String newAdminUsername = JOptionPane.showInputDialog("Enter username for admin's new account:");
        String newAdminPassword = JOptionPane.showInputDialog("Enter password for admin's new account:");
       
        if (!newAdminUsername.equals("") && !newAdminPassword.equals("")) {
            String message1 = "New admin";
            String message2 = newAdminUsername + ":" + newAdminPassword;
            this.pw.println(message1);
            this.pw.println(message2);
        }
        else {
            JOptionPane.showMessageDialog(null, "Admin information is not complete! Try again.");
        }
    }//GEN-LAST:event_buttonAddAdminActionPerformed
   
    private void comboStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboStudentActionPerformed
        //get info about chosen student
        if (comboStudent.getSelectedIndex() != -1) {
            String message = "ComboStudent:" + comboStudent.getSelectedItem().toString();
            this.pw.println(message);
        }
    }//GEN-LAST:event_comboStudentActionPerformed

    private void comboCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCourseActionPerformed
        //get info about chosen course
        if (comboCourse.getSelectedIndex() != -1) {
            String message = "ComboCourse:" + comboCourse.getSelectedItem().toString();
            this.pw.println(message);
        }
    }//GEN-LAST:event_comboCourseActionPerformed

    private void buttonStudentCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStudentCourseActionPerformed
        if (comboStudent.getSelectedIndex() != -1) {
            String newCourse = JOptionPane.showInputDialog("Enter full name of the new course for student:");

            boolean correctName = false;
            if (newCourse != null && !newCourse.equals("")) {
                for (int i = 0; i < comboCourse.getItemCount(); i++) {
                    if (newCourse.equals(comboCourse.getItemAt(i).toString())) {
                        correctName = true;
                        break;
                    }
                }
                if (correctName) {
                    String message = "Add new Course:" + newCourse + ":for student:" + comboStudent.getSelectedItem().toString();
                    this.pw.println(message);
                }
                else if (newCourse != null) {
                    JOptionPane.showMessageDialog(null, "Course \"" + newCourse + "\" does not exist! Try again.");
                }
            }
        }
    }//GEN-LAST:event_buttonStudentCourseActionPerformed

    private void buttonGradeStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradeStudentActionPerformed
        if (comboStudent.getSelectedIndex() != -1) {
            String courseToGrade = JOptionPane.showInputDialog("Enter full name of the course you want to grade the student on:");

            if (courseToGrade != null && !courseToGrade.equals("")) {
                boolean correctName = false;
                boolean studentHasCourse = false;
                for (int i = 0; i < comboCourse.getItemCount(); i++) {
                    if (courseToGrade.equals(comboCourse.getItemAt(i).toString())) {
                        correctName = true;
                        break;
                    }
                }
                //check if student is on that course
                String studentInfo = "* " + textStudent.getText() + " ->";
                if (studentInfo.contains(courseToGrade))
                    studentHasCourse = true;
                
                if (correctName && studentHasCourse) {
                    String categoryToGrade = JOptionPane.showInputDialog("Enter full name of the course category:");
                    
                    if (categoryToGrade != null && !categoryToGrade.equals("")) {
                        //client doesnt know is category correct, server will know
                        boolean pointsOk = false;
                        String studentPoints = JOptionPane.showInputDialog("Enter the number of points student got in that category:");
                        try {
                            int points = Integer.parseInt(studentPoints);
                            if (points < 1)
                                JOptionPane.showMessageDialog(null, "Wrong format for points! Try again.");
                            else pointsOk = true;
                        }
                        catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Wrong format for points! Try again.");
                        }
                            
                        if (pointsOk) {
                            //client doesent know if points are correct (more than maximum for category) server will know
                            String message = "Grade:" + comboStudent.getSelectedItem().toString() + ":" +
                                    courseToGrade + ":" + categoryToGrade + ":" + studentPoints;
                            this.pw.println(message);
                        }
                    }
                }
                else if (!correctName) {
                    JOptionPane.showMessageDialog(null, "Course \"" + courseToGrade + "\" does not exist! Try again.");
                }
                else {                    
                    JOptionPane.showMessageDialog(null, "Student is not on course \"" + courseToGrade + "\". Try again.");
                }
            }            
        }
    }//GEN-LAST:event_buttonGradeStudentActionPerformed

    private void buttonLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLogOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonLogOutActionPerformed

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
    private javax.swing.JButton buttonAddAdmin;
    public static javax.swing.JButton buttonAddCourse;
    private javax.swing.JButton buttonAddStudent;
    private javax.swing.JButton buttonGradeStudent;
    private javax.swing.JButton buttonLogIn;
    private javax.swing.JButton buttonLogOut;
    private javax.swing.JButton buttonSignIn;
    private javax.swing.JButton buttonStudentCourse;
    private javax.swing.JComboBox<String> comboCourse;
    private javax.swing.JComboBox<String> comboRole;
    private javax.swing.JComboBox<String> comboStudent;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel labelCourses;
    private javax.swing.JLabel labelNoConnection;
    private javax.swing.JLabel labelNotCorrect;
    private javax.swing.JLabel labelPassword;
    private javax.swing.JLabel labelRole;
    private javax.swing.JLabel labelStudentInfo;
    private javax.swing.JLabel labelStudents;
    private javax.swing.JLabel labelUsername;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JTextArea textCourse;
    private javax.swing.JPasswordField textPassword;
    private javax.swing.JTextArea textStudent;
    private javax.swing.JTextField textUsername;
    // End of variables declaration//GEN-END:variables
}
