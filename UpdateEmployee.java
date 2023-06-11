
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class UpdateEmployee extends JFrame implements ActionListener {

    private static final String dbHost = "jdbc:mysql://localhost:3306/school";
    private static final String dbUser = "root";
    private static final String dbPass = "";

    private Connection connection = null;
    private Statement statement = null;
    ResultSet rs = null;
    PreparedStatement ps;

    JFrame jframe;
    JLabel label1, label2, label3, label4, label5, label6, label7;
    JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7;
    JButton button1, button2, button3, button4, button5;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Font f;

    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
    private JPopupMenu popup;

    UpdateEmployee() {

        jframe = new JFrame();
        // Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);
        jframe.setLayout(null);

        label6 = new JLabel("Update Employee");
        label6.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label6.setBounds(300, 50, 300, 40);
        jframe.add(label6);

        label1 = new JLabel("Employee ID ");
        label1.setFont(f);
        label1.setBounds(210, 120, 135, 25);
        jframe.add(label1);
        txt1 = new JTextField(20);
        txt1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt1.setBounds(358, 120, 200, 25);
        txt1.setToolTipText("Enter ID");
        jframe.add(txt1);

        label2 = new JLabel(" First Name:");
        label2.setFont(f);
        label2.setBounds(210, 160, 135, 25);
        jframe.add(label2);

        txt2 = new JTextField(20);
        txt2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt2.setBounds(358, 160, 200, 25);
        txt2.setToolTipText("Enter First Name");
        jframe.add(txt2);

        label3 = new JLabel(" Middle Name:");
        label3.setFont(f);
        label3.setBounds(210, 200, 170, 25);
        jframe.add(label3);

        txt3 = new JTextField(20);
        txt3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt3.setBounds(358, 200, 200, 25);
        txt3.setToolTipText("Enter Middle Name");
        jframe.add(txt3);

        label4 = new JLabel("Last Name:");
        label4.setFont(f);
        label4.setBounds(210, 240, 170, 25);
        jframe.add(label4);

        txt4 = new JTextField(20);
        txt4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt4.setBounds(358, 240, 200, 25);
        txt4.setToolTipText("Enter Last Name");
        jframe.add(txt4);

        label5 = new JLabel("Cnic No:");
        label5.setFont(f);
        label5.setBounds(210, 280, 170, 25);
        jframe.add(label5);
        txt5 = new JTextField(20);
        txt5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt5.setBounds(358, 280, 200, 25);
        txt5.setToolTipText("Enter CNIC Number");
        jframe.add(txt5);

        label6 = new JLabel("Semester");
        label6.setFont(f);
        label6.setBounds(210, 320, 170, 25);
        jframe.add(label6);

        txt6 = new JTextField(20);
        txt6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt6.setBounds(358, 320, 200, 25);
        txt6.setToolTipText("Enter semester");
        jframe.add(txt6);

        label7 = new JLabel("Contact No:");
        label7.setFont(f);
        label7.setBounds(210, 360, 170, 25);
        jframe.add(label7);
        txt7 = new JTextField(20);
        txt7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt7.setBounds(358, 360, 200, 25);
        txt7.setToolTipText("Enter Contact Number");
        jframe.add(txt7);

        button1 = new JButton("Open", new ImageIcon("images//open.png"));
        button1.setBounds(180, 400, 110, 35);
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button1.setToolTipText("click to open details");
        jframe.add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Update", new ImageIcon("images//update.png"));
        button2.setBounds(295, 400, 110, 35);
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button2.setToolTipText("click to update details");
        jframe.add(button2);
        button2.addActionListener(this);

        button3 = new JButton("Clear", new ImageIcon("images//clear.png"));
        button3.setBounds(410, 400, 110, 35);
        button3.setContentAreaFilled(false);
        button3.setFocusPainted(false);
        button3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button3.setToolTipText("click to clear all textfilds");
        jframe.add(button3);
        button3.addActionListener(this);

        button4 = new JButton("All", new ImageIcon("images//all.png"));
        button4.setBounds(525, 400, 110, 35);
        button4.setContentAreaFilled(false);
        button4.setFocusPainted(false);
        button4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button4.setToolTipText("click to view all details");
        jframe.add(button4);
        button4.addActionListener(this);

        scrlPane.setBounds(0, 450, 807, 500);
        jframe.add(scrlPane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));

        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        removeItem.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        removeItem.setBackground(Color.YELLOW);
        popup.add(removeItem);

        tabGrid.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = tabGrid.rowAtPoint(e.getPoint());
                tabGrid.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(tabGrid, e.getX(), e.getY());
                }

            }
        });
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabGrid.getSelectedRow();
                if (row >= 0) {
                    model.removeRow(row);
                }

            }
        });

        model.addColumn("ID");
        model.addColumn("First_Name");
        model.addColumn("Middle_Name");
        model.addColumn("Last_Name");
        model.addColumn("CNIC");
        model.addColumn("Semester");
        model.addColumn("Contact_Number");

        jframe.setTitle("Update Employee");
        jframe.setSize(810, 800);
        jframe.setLocation(270, 100);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button1) {
            if (((txt1.getText()).equals(""))) {
                JOptionPane.showMessageDialog(this, "Please Enter employee id");
            } else {

                try {
                    int tmp = 0;

                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = connection.prepareStatement("select * from employee where id='" + txt1.getText() + "'");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        txt1.setText(rs.getString(1));
                        txt2.setText(rs.getString(2));
                        txt3.setText(rs.getString(3));
                        txt4.setText(rs.getString(4));
                        txt5.setText(rs.getString(5));
                        txt6.setText(rs.getString(6));
                        txt7.setText(rs.getString(7));
                        tmp = 1;
                    }
                    if (tmp == 0) {
                        JOptionPane.showMessageDialog(null, "Record is not available");
                        txt1.setText("");
                        txt2.setText("");
                        txt3.setText("");
                        txt4.setText("");
                        txt5.setText("");
                        txt6.setText("");
                        txt7.setText("");
                    }
                    connection.close();
                } catch (SQLException se) {
                    System.out.println(se);
                    JOptionPane.showMessageDialog(null, "SQL Error:" + se);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error:" + e);
                }
            }
        } else if (ae.getSource() == button2) {

            if (((txt1.getText()).equals(""))) {

                JOptionPane.showMessageDialog(this, "Please Enter employe Id ");
            } else if (((txt2.getText()).equals("")) || ((txt3.getText()).equals("")) || ((txt4.getText()).equals(""))
                    || ((txt5.getText()).equals("")) || ((txt6.getText()).equals(""))
                    || ((txt7.getText()).equals(""))) {

                JOptionPane.showMessageDialog(this, "Detail are Missing !", "Warning!!!", JOptionPane.ERROR_MESSAGE);
            }

            else {
                try {

                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    statement = connection.createStatement();
                    String sql = "UPDATE employee SET id='" + txt1.getText() + "',fname='" + txt2.getText()
                            + "',mname='" + txt3.getText() + "',lname='" + txt4.getText() + "',dateOfJoining='"
                            + txt5.getText() + "' ,semester='" + txt6.getText() + "' ,contactNum='" + txt7.getText()
                            + "' where id='" + txt1.getText() + "' ";
                    statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Record is Updated");
                    txt1.setText("");
                    txt2.setText("");
                    txt3.setText("");
                    txt4.setText("");
                    txt5.setText("");
                    txt6.setText("");
                    txt7.setText("");
                    connection.close();
                } catch (SQLException se) {
                    System.out.println(se);
                    JOptionPane.showMessageDialog(null, "SQL Error:" + se);
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "Error:" + e);
                }
            }
        } else if (ae.getSource() == button3) {// clear
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");
            txt7.setText("");
        } else if (ae.getSource() == button4) {
            int r = 0;
            try {
                connection = DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = statement.executeQuery("SELECT * from employee");
                while (rs.next()) {
                    model.insertRow(r++, new Object[] { rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) });
                }
                connection.close();
            } catch (SQLException se) {
                System.out.println(se);
                JOptionPane.showMessageDialog(null, "SQL Error" + se);
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "Error:" + e);
            }
        } else if (ae.getSource() == button5) {
            try {
                jframe.dispose();
                new MainMenu();
            } catch (IOException ex) {
                Logger.getLogger(UpdateEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
