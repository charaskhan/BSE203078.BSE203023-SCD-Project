import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentList extends JFrame implements ActionListener {

    private static final String dbHost = "jdbc:mysql://localhost:3306/school";
    private static final String dbUser = "root";
    private static final String dbPass = "";

    JFrame jframe = new JFrame();
    JLabel label;
    JButton button1;
    Connection connection;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

    private JPopupMenu popup;

    public StudentList() {

        jframe.setLayout(null);
        jframe.getContentPane().setBackground(Color.LIGHT_GRAY);

        label = new JLabel("List Of Student");
        label.setFont(new Font("Times New Roman", Font.BOLD, 25));
        label.setBounds(300, 30, 350, 25);
        jframe.add(label);

        scrlPane.setBounds(0, 150, 807, 500);
        jframe.add(scrlPane);
        tabGrid.setFont(new Font("Times New Roman", 0, 15));
        model.addColumn("ID");
        model.addColumn("First_Name");
        model.addColumn("Middle_Name");
        model.addColumn("Last_Name");
        model.addColumn("CNIC");
        model.addColumn("Semester");
        model.addColumn("Contact_Number");

        popup = new JPopupMenu();
        JMenuItem removeItem = new JMenuItem("Delete row");
        removeItem.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        removeItem.setBackground(Color.YELLOW);
        popup.add(removeItem);
        removeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = tabGrid.getSelectedRow();
                if (row >= 0) {

                    model.removeRow(row);
                }
            }
        });
        tabGrid.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                int row = tabGrid.rowAtPoint(e.getPoint());
                tabGrid.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popup.show(tabGrid, e.getX(), e.getY());
                }
            }
        });

        int r = 0;
        try {

            connection = DriverManager.getConnection(dbHost, dbUser, dbPass);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = statement.executeQuery("select * from student");
            while (rs.next()) {
                model.insertRow(r++, new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7) });

            }

            connection.close();
        } catch (SQLException se) {
            System.out.println(se);
            JOptionPane.showMessageDialog(null, "SQL Error:" + se);
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error:" + e);
        }

        jframe.setTitle("Student List");
        jframe.setSize(810, 600);
        jframe.setLocation(270, 100);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            try {
                jframe.dispose();
                new MainMenu();
            } catch (IOException ex) {
                Logger.getLogger(StudentList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
