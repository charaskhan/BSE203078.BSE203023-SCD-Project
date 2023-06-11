
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.plaf.basic.BasicMenuBarUI;

public class MainMenu extends JFrame implements ActionListener {

    public static final String dbHost = "jdbc:mysql://localhost:3306/school";
    public static final String dbUser = "root";
    public static final String dbPass = "root";

    PreparedStatement ps;

    static Container pane;
    static JFrame jframe, jframemain;
    JMenuBar jmenubar;
    JMenu menu1, menu2, menu3, menu4, menu5, menu6, menu7;
    JMenuItem m1_1, m1_2, m1_3, m1_4, m1_5, m2_1, m2_2, m2_3, m2_4, m2_5, m3_1, m3_2, m3_3, m3_4, m4_1, m5_1, m6_1,
            m6_2, m6_3, m6_4, m6_5, m7_1, m7_2, m7_3, m8_1;
    static JLabel labelb1, labelb2, labelb3, labelb4, labelb5, labelm1, label1, LogoCall, labelmonth, labelyear;
    static JButton signin, createaccount, buttonpreviuos, buttonnext;

    public MainMenu() throws IOException {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        jframe = new JFrame();
        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.GRAY);

        jmenubar = new JMenuBar();
        jmenubar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        jmenubar.setUI(new BasicMenuBarUI() {
            public void paint(Graphics g, JComponent c) {
                g.setColor(Color.getHSBColor(400, 300, 100));
                g.fillRect(0, 10, c.getWidth(), c.getHeight());
            }
        });
        jframe.setJMenuBar(jmenubar);
        menu7 = new JMenu("File");
        menu7.setFont(new Font("Times New Roman", Font.BOLD, 25));
        menu7.setForeground(Color.BLACK);
        menu7.setBorder(BorderFactory.createRaisedBevelBorder());
        jmenubar.add(menu7);

        m8_1 = new JMenuItem("Help", new ImageIcon("images//help.png"));
        m8_1.setBorder(BorderFactory.createRaisedBevelBorder());
        m8_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu7.add(m8_1);
        m8_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new Help();
            }
        });

        m5_1 = new JMenuItem("Exit", new ImageIcon("images//exit.png"));
        m5_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m5_1.setBorder(BorderFactory.createRaisedBevelBorder());
        menu7.add(m5_1);
        m5_1.addActionListener(this);

        menu1 = new JMenu("Student");
        menu1.setForeground(Color.BLACK);
        menu1.setBorder(BorderFactory.createRaisedBevelBorder());
        menu1.setFont(new Font("Times New Roman", Font.BOLD, 25));
        jmenubar.add(menu1);

        m1_1 = new JMenuItem("Add New Student", new ImageIcon("images//addnew.png"));
        m1_1.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_1);
        m1_1.addActionListener(this);

        m1_2 = new JMenuItem("Search Student", new ImageIcon("images//search.png"));
        m1_2.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_2);
        m1_2.addActionListener(this);

        m1_3 = new JMenuItem("Update Student", new ImageIcon("images//update.png"));
        m1_3.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_3);
        m1_3.addActionListener(this);

        m1_4 = new JMenuItem("Delete Student", new ImageIcon("images//delete.png"));
        m1_4.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_4);
        m1_4.addActionListener(this);

        m1_5 = new JMenuItem("List Of Student", new ImageIcon("images//all.png"));
        m1_5.setBorder(BorderFactory.createRaisedBevelBorder());
        m1_5.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu1.add(m1_5);
        m1_5.addActionListener(this);

        menu2 = new JMenu("Employee");
        menu2.setForeground(Color.BLACK);
        menu2.setBorder(BorderFactory.createRaisedBevelBorder());
        menu2.setFont(new Font("Times New Roman", Font.BOLD, 25));

        jmenubar.add(menu2);
   
        m2_1 = new JMenuItem("Add New Employee ", new ImageIcon("images//addnew.png"));
        m2_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m2_1.setBorder(BorderFactory.createRaisedBevelBorder());
        menu2.add(m2_1);
        m2_1.addActionListener(this);

        m2_2 = new JMenuItem("Search Employee", new ImageIcon("images//search.png"));
        m2_2.setBorder(BorderFactory.createRaisedBevelBorder());
        m2_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu2.add(m2_2);
        m2_2.addActionListener(this);

        m2_3 = new JMenuItem("Update Employee", new ImageIcon("images//update.png"));
        m2_3.setBorder(BorderFactory.createRaisedBevelBorder());
        m2_3.setFont(new Font("Times New Roman", Font.BOLD, 17));
        menu2.add(m2_3);
        m2_3.addActionListener(this);

        m2_4 = new JMenuItem("Delete Employee", new ImageIcon("images//delete.png"));
        m2_4.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m2_4.setBorder(BorderFactory.createRaisedBevelBorder());
        menu2.add(m2_4);
        m2_4.addActionListener(this);

        m2_5 = new JMenuItem("List of Employee", new ImageIcon("images//all.png"));
        m2_5.setFont(new Font("Times New Roman", Font.BOLD, 17));
        m2_5.setBorder(BorderFactory.createRaisedBevelBorder());
        menu2.add(m2_5);
        m2_5.addActionListener(this);

        jframe.setTitle("School Management System");
        jframe.setSize(1365, 763);
        jframe.setLocation(2, 2);
        jframe.setResizable(true);
        jframe.setVisible(true);
        jframe.setBackground(Color.BLUE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == m1_1) {
            new AddNewStudent();
        } else if (ae.getSource() == m1_2) {
            new SearchStudent();
        } else if (ae.getSource() == m1_3) {
            new UpdateStudent();
        } else if (ae.getSource() == m1_4) {
            new DeleteStudent();
        } else if (ae.getSource() == m1_5) {
            new StudentList();
        } else if (ae.getSource() == m2_1) {
            new AddNewEmployee();
        } else if (ae.getSource() == m2_2) {
            new SearchEmployee();
        } else if (ae.getSource() == m2_3) {
            new UpdateEmployee();
        } else if (ae.getSource() == m2_4) {
            new DeleteEmployee();
        } else if (ae.getSource() == m2_5) {
            new EmployeeList();
        } else if (ae.getSource() == m5_1) {
            System.exit(0);
        }
    }

    public static void main(String args[]) throws IOException {
        new MainMenu();
    }
}
