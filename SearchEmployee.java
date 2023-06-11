
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchEmployee extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/school";
    public static final String dbUser = "root";
    public static final String dbPass = "";

    private Statement statement = null;
    private Connection connection = null;
    private ResultSet rs = null;

    PreparedStatement ps;

    JFrame jframe;
    JLabel label1, label2, label3, label4, label5, label6, label7;
    JTextField txt1, txt2, txt3, txt4, txt5, txt6, txt7;
    JButton button1, button2, button3, button4;
    Font f;

    DefaultTableModel model = new DefaultTableModel();
    JTable tabgrid = new JTable(model);
    JScrollPane scrollpane = new JScrollPane(tabgrid);

    private JPopupMenu popup;

    public SearchEmployee() {

        jframe = new JFrame();
        f = new Font("Times New Roman", Font.BOLD, 20);
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);

        label6 = new JLabel("Search Employee");
        label6.setFont(new Font("Times New Roman", Font.BOLD, 30));
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

        label5 = new JLabel("Date of Joining");
        label5.setFont(f);
        label5.setBounds(210, 280, 170, 25);
        jframe.add(label5);
        txt5 = new JTextField(20);
        txt5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt5.setBounds(358, 280, 200, 25);
        txt5.setToolTipText("Enter Date of joining");
        jframe.add(txt5);

        label6 = new JLabel("Semester");
        label6.setFont(f);
        label6.setBounds(210, 320, 170, 25);
        jframe.add(label6);

        txt6 = new JTextField(20);
        txt6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txt6.setBounds(358, 320, 200, 25);
        txt6.setToolTipText("Enter Semester");
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

        button1 = new JButton("Search", new ImageIcon("images//search.png"));
        button1.setBounds(212, 400, 110, 35);
        button1.setContentAreaFilled(false);
        button1.setFocusPainted(false);
        button1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button1.setToolTipText("Click To Search");
        jframe.add(button1);
        button1.addActionListener(this);

        button2 = new JButton("Clear", new ImageIcon("images//clear.png"));
        button2.setBounds(332, 400, 110, 35);
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        button2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button2.setToolTipText("Click To Clear");
        jframe.add(button2);
        button2.addActionListener(this);

        button3 = new JButton("All", new ImageIcon("images//all.png"));
        button3.setBounds(452, 400, 110, 35);
        button3.setContentAreaFilled(false);
        button3.setFocusPainted(false);
        button3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        button3.setToolTipText("Click To Get All Employee ");
        jframe.add(button3);
        button3.addActionListener(this);

        scrollpane.setBounds(0, 450, 807, 500);
        jframe.add(scrollpane);
        tabgrid.setFont(new Font("Times New Roman", 0, 15));

        model.addColumn("ID");
        model.addColumn("First_Name");
        model.addColumn("Middle_Name");
        model.addColumn("Last_Name");
        model.addColumn("Date_of_Joining");
        model.addColumn("Semester");
        model.addColumn("Contact_Number");

        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        removeItem.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        removeItem.setBackground(Color.YELLOW);
        popup.add(removeItem);
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabgrid.getSelectedRow();
                if (row >= 0) {
                    model.removeRow(row);
                }

            }
        });

        tabgrid.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int row = tabgrid.rowAtPoint(e.getPoint());
                tabgrid.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(tabgrid, e.getX(), e.getY());
                }

            }
        });

        jframe.setTitle("Search Employee");
        jframe.setLocation(270, 100);
        jframe.setSize(810, 800);
        jframe.setVisible(true);
        jframe.setResizable(false);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == button1) {
            try {

                if (((txt1.getText().equals("")))) {
                    JOptionPane.showMessageDialog(this, "Please Enter Employee ID ");

                } else {
                    int tmp = 0;
                    connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                    ps = connection.prepareStatement(" select * from employee where id='" + txt1.getText() + "'");
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
                    } else if (tmp == 1) {
                        JOptionPane.showMessageDialog(null, "Record is available");

                    }

                }

            } catch (SQLException ex) {
                Logger.getLogger(SearchEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (ae.getSource() == button2) {
            txt1.setText("");
            txt2.setText("");
            txt3.setText("");
            txt4.setText("");
            txt5.setText("");
            txt6.setText("");
            txt7.setText("");

        }

        if (ae.getSource() == button3) {
            int row = 0;
            try {
                connection = (Connection) DriverManager.getConnection(dbHost, dbUser, dbPass);
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = statement.executeQuery("select * from employee");
                while (rs.next()) {
                    model.insertRow(row++, new Object[] { rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) });

                }
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SearchEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (ae.getSource() == button4) {
            try {
                jframe.dispose();
                new MainMenu();
            } catch (IOException ex) {
                Logger.getLogger(SearchEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
