
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.SwingUtilities;

public class JDBC implements ActionListener {

    private JTextField tname;
    private JTextField tprice;
    private JTextField tauthor;
    private JTextField tQuantity;
    private JTextArea ta;
    private JButton btn_submit, btn_clear, btn_delete, btn_search;

    public JDBC(JTextField tname, JTextField tprice, JTextField tauthor,
            JTextField tQuantity, JTextArea ta,
            JButton btn_submit, JButton btn_clear, JButton btn_delete, JButton btn_search, JButton sm) {
        this.tname = tname;
        this.tprice = tprice;
        this.tauthor = tauthor;
        this.tQuantity = tQuantity;
        this.ta = ta;
        this.btn_submit = btn_submit;
        this.btn_clear = btn_clear;
        this.btn_delete = btn_delete;
        this.btn_search = btn_search;

        // Add action listeners to buttons
        // Add listeners for other buttons if needed
    }

    public void actionPerformed1(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {
            if (e.getSource() == btn_submit) {
                String url = "jdbc:mysql://localhost:3306/jdbcdemo";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url, "root", "");
                    java.sql.Statement statement = con.createStatement();
                    ResultSet rs = statement.executeQuery("select * from books");
                    if (!rs.next()) {
                        System.out.println("No records found in the books table.");
                    }

                    while (rs.next()) {

                        ta.setText(rs.getString(1) + "" + rs.getInt(1) + "" + rs.getString(3) + "" + rs.getInt(4));
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    // Handle exception appropriately
                }
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
