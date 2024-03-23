
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class books extends JFrame implements ActionListener {

    private JTextField tname, tprice, tauthor, tQuantity, tpublication, tdatepublication, ttotalcost, tquantitytoorder;
    private JTextArea ta;

    public books() {
    
        JFrame frame = new JFrame("Books Management System");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.GRAY);
        JLabel headerLabel = new JLabel("BOOKS & DATA");
        headerLabel.setFont(new Font("Serif", Font.BOLD, 30));
        headerPanel.add(headerLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        JPanel dataPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 0 rows, 2 columns
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


        dataPanel.add(new JLabel("Book Publication"));
        tpublication = new JTextField();
        dataPanel.add(tpublication);

        dataPanel.add(new JLabel("Book   Date of Publication "));
        tdatepublication = new JTextField();
        dataPanel.add(tdatepublication);

        dataPanel.add(new JLabel("Book Quantity to order"));
        tquantitytoorder = new JTextField();
        dataPanel.add(tquantitytoorder);

      

        JButton btn_submit = new JButton("Submit");
        JButton btn_insert = new JButton("Insert");
        JButton btn_delete = new JButton("Delete");
        JButton btn_search = new JButton("Update");
        JButton btn_plsql = new JButton("plsql_above");
        JButton btn_clear = new JButton("Clear");

        btn_submit.addActionListener(this);
        btn_insert.addActionListener(this);
        btn_delete.addActionListener(this);
        btn_search.addActionListener(this);
        btn_plsql.addActionListener(this);
        btn_clear.addActionListener(this);

        dataPanel.add(btn_submit);
        dataPanel.add(btn_insert);
        dataPanel.add(btn_delete);
        dataPanel.add(btn_search);
        dataPanel.add(btn_plsql);
        dataPanel.add(btn_clear);

        ta = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(ta);
        frame.add(dataPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        frame.setVisible(true);

        // books_action actionHandler = new books_action(tname, tprice, tauthor,
        // tQuantity, ta, btn_submit, btn_clear,
        // btn_delete, btn_search);
        // btn_submit.addActionListener(actionHandler);
        // btn_clear.addActionListener(actionHandler);
        // btn_delete.addActionListener(actionHandler);
        // btn_search.addActionListener(actionHandler);

        App jdbcpbj = new App(tname, tprice, tauthor,
                tQuantity, ta, btn_submit, btn_insert,
                btn_delete, btn_search, btn_plsql, btn_clear, tpublication, tdatepublication, tquantitytoorder,ttotalcost);
        btn_submit.addActionListener(jdbcpbj);
        btn_insert.addActionListener(jdbcpbj);
        btn_delete.addActionListener(jdbcpbj);
        btn_search.addActionListener(jdbcpbj);
        btn_plsql.addActionListener(jdbcpbj);
        btn_clear.addActionListener(jdbcpbj);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new booksgui());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle actions based on the button clicked

    }
}
