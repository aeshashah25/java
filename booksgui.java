
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class booksgui extends JFrame implements ActionListener {

    private JTextField tname, tprice, tauthor, tQuantity, tpublication, tdatepublication, ttotalcost, tquantitytoorder;
    private JTextArea ta;

    JButton btn_submit;

    public booksgui() {

        JFrame frame = new JFrame("Books Management System");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.GRAY);
        JLabel headerLabel = new JLabel("BOOKS & DATA");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 40));
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new GridLayout(0, 2, 20, 10));
        dataPanel.setBackground(Color.lightGray);

        dataPanel.add(new JLabel("Book Name"));
        tname = new JTextField();
        dataPanel.add(tname);

        dataPanel.add(new JLabel("Book Price"));
        tprice = new JTextField();
        dataPanel.add(tprice);

        dataPanel.add(new JLabel("Book Author"));
        tauthor = new JTextField();
        dataPanel.add(tauthor);

        dataPanel.add(new JLabel("Book Quantity"));
        tQuantity = new JTextField();
        dataPanel.add(tQuantity);

        // tpublication, tdatepublication, ttotalcost, tquantitytoorder
        dataPanel.add(new JLabel("Book Publication"));
        tpublication = new JTextField();
        dataPanel.add(tpublication);

        dataPanel.add(new JLabel("Book Date_of_Publication"));
        tdatepublication = new JTextField();
        dataPanel.add(tdatepublication);

        dataPanel.add(new JLabel("Book Quantity_to_order"));
        tquantitytoorder = new JTextField();
        dataPanel.add(tquantitytoorder);

        dataPanel.add(new JLabel("Total Cost-click on Submit button to find Totalcost"));
        // JLabel note = new JLabel(" Click on submit button to find the total cost");
        // note.setForeground(Color.gray);
        // dataPanel.add(note);
        ttotalcost = new JTextField();
        dataPanel.add(ttotalcost);
        ttotalcost.setVisible(false);

        JButton btn_submit = new JButton("Submit");
        JButton btn_clear = new JButton("Clear");
        JButton btn_delete = new JButton("Delete");
        JButton btn_search = new JButton("Search");
        JButton btn_update = new JButton("Update");
        JButton btn_showall = new JButton("ShowAll");

        btn_submit.addActionListener(this);
        btn_clear.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_search.addActionListener(this);
        btn_update.addActionListener(this);
        btn_showall.addActionListener(this);

        dataPanel.add(btn_submit);
        dataPanel.add(btn_clear);
        dataPanel.add(btn_delete);
        dataPanel.add(btn_search);
        dataPanel.add(btn_update);
        dataPanel.add(btn_showall);

        // Text Area
        ta = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(ta);
        frame.add(dataPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);

        books_action actionHandler = new books_action(tname, tprice, tauthor, tQuantity, ta, btn_submit, btn_clear,
                btn_delete, btn_search, btn_update, btn_showall, tpublication, tdatepublication, ttotalcost,
                tquantitytoorder);
        btn_submit.addActionListener(actionHandler);
        btn_clear.addActionListener(actionHandler);

        btn_delete.addActionListener(actionHandler);
        btn_search.addActionListener(actionHandler);
        btn_search.addActionListener(actionHandler);
        btn_update.addActionListener(actionHandler);
        btn_showall.addActionListener(actionHandler);

        // JDBC jdbcpbj = new JDBC(tname, tprice, tauthor,
        // tQuantity, ta, btn_submit, btn_clear,
        // btn_delete, btn_search);
        // btn_submit.addActionListener(jdbcpbj);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new booksgui());

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
