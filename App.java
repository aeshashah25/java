
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

import java.sql.CallableStatement;

public class App implements ActionListener {

    private JTextField tname;
    private JTextField tprice;
    private JTextField tauthor;
    private JTextField tQuantity;
    private JTextArea ta;
    JTextField tquantitytoorder;
    JTextField tdatepublication;
    JTextField tpublication, ttotalcost;
    JButton btn_submit, btn_insert, btn_delete, btn_search, btn_plsql, btn_clear;

    public App(JTextField tname, JTextField tprice, JTextField tauthor,
            JTextField tQuantity, JTextArea ta,
            JButton btn_submit, JButton btn_insert, JButton btn_delete, JButton btn_search, JButton btn_plsql,
            JButton btn_clear, JTextField tpublication, JTextField tdatepublication, JTextField tquantitytoorder,
            JTextField ttotalcost) {
        this.tname = tname;
        this.tprice = tprice;
        this.tauthor = tauthor;
        this.tQuantity = tQuantity;

        this.tpublication = tpublication;
        this.tdatepublication = tdatepublication;
        this.ttotalcost = ttotalcost;
        this.tquantitytoorder = tquantitytoorder;

        this.btn_submit = btn_submit;
        this.btn_insert = btn_insert;
        this.btn_delete = btn_delete;
        this.btn_search = btn_search;
        this.btn_plsql = btn_plsql;
        this.btn_clear = btn_clear;
        this.ta = ta;

    }

    public App(JTextField tname2, JTextField tprice2, JTextField tauthor2, JTextField tQuantity2,
            JTextField tpublication2, JTextField tdatepublication2, JTextField ttotalcost2,
            JTextField tquantitytoorder2, JTextArea ta2, JButton btn_submit2, JButton btn_clear2, JButton btn_delete2,
            JButton btn_search2, JButton btn_plsql2, JButton btn_below2) {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String url = "jdbc:mysql://localhost:3306/bookdb";

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, "root", "");
            java.sql.Statement statement = con.createStatement();
            String bookname = tname.getText();
            String bookprice = tprice.getText().toString();
            String bookauthor = tauthor.getText();
            // tpublication, tdatepublication, ttotalcost, tquantitytoorder
            String bookquantity = tQuantity.getText().toString();
            String publication = tpublication.getText();
            String datepublication = tdatepublication.getText().toString();
            String quantitytoorder = tquantitytoorder.getText().toString();
            // String totalcost = ttotalcost.toString();

            if (e.getSource() == btn_submit) {
                String columnname;
                int columnprice;
                String columnauthor;
                int columnquantity;
                // tpublication, tdatepublication, ttotalcost, tquantitytoorder

                String columnpublication;
                int columndatepublication;
                int columnquan_order;
                int columntotalcost;

                // String db = "CREATE DATABASE bookdb";
                // statement.executeUpdate(db);
                // System.out.println("Database created successfully...");

                // String tablec = "create table data_books( book_name varchar(10), book_price
                // varchar(10),book_author varchar(10),book_quantity
                // varchar(10),book_publication varchar(10),book_date_of_publication
                // varchar(10),book_quantityto_order varchar(10),book_totalcost varchar(10))";
                // statement.executeUpdate(tablec);
                // System.out.println("table created ");

                ResultSet rs = statement.executeQuery("select * from data_books");

                StringBuilder resultText = new StringBuilder();

                if (!rs.next()) {
                    System.out.println("No records found in the books table.");
                } else {
                    do {
                        columnname = rs.getString(1);
                        columnprice = rs.getInt(2);
                        columnauthor = rs.getString(3);
                        columnquantity = rs.getInt(4);
                        columnpublication = rs.getString(5);
                        columndatepublication = rs.getInt(6);
                        columnquan_order = rs.getInt(7);
                        columntotalcost = rs.getInt(8);

                        resultText.append(columnname)
                                .append(" ")
                                .append(columnprice)
                                .append(" ")
                                .append(columnauthor)
                                .append(" ")
                                .append(columnquantity)
                                .append(" ")
                                .append(columnpublication)
                                .append(" ")
                                .append(columndatepublication)
                                .append(" ")
                                .append(columnquan_order)
                                .append(" ")
                                .append(columntotalcost)
                                .append("\n");
                    } while (rs.next());
                }

                ta.setText(resultText.toString());
            }

            else if (e.getSource() == btn_insert) {

                double totalPrice = Double.parseDouble(bookprice);
                int quantity_order = Integer.parseInt(quantitytoorder);
                double totalCost = totalPrice * quantity_order;
                String insertdata = "insert into data_books values(?,?,?,?,?,?,?,?)";
                PreparedStatement pstmt = con.prepareStatement(insertdata);
                pstmt.setString(1, bookname);
                pstmt.setString(2, bookprice);
                pstmt.setString(3, bookauthor);
                pstmt.setString(4, bookquantity);
                pstmt.setString(5, publication);
                pstmt.setString(6, datepublication);
                pstmt.setInt(7, quantity_order);
                pstmt.setDouble(8, totalCost);

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record added Successfully");
            }

            else if (e.getSource() == btn_search) {
                System.out.println("updatee");

                double totalPrice = Double.parseDouble(bookprice);
                int quantity_order = Integer.parseInt(quantitytoorder);
                double totalCost = totalPrice * quantity_order;

                String sql2 = "UPDATE data_books SET book_name=?, book_price=?, book_author=?, book_quantity=? ,book_publication=?,book_date_of_publication=?, book_quantityto_order=?,book_totalcost=? WHERE book_name=? AND book_author=?";
                PreparedStatement pstmt = con.prepareStatement(sql2);

                pstmt.setString(1, bookname);
                pstmt.setString(2, bookprice);
                pstmt.setString(3, bookauthor);
                pstmt.setString(4, bookquantity);
                pstmt.setString(5, publication);
                pstmt.setString(6, datepublication);
                pstmt.setString(7, quantitytoorder);
                pstmt.setDouble(8, totalCost);

                pstmt.setString(9, bookname);
                pstmt.setString(10, bookauthor);

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record updated successfully");

            }

            else if (e.getSource() == btn_delete) {
                String deleted = "DELETE FROM data_books WHERE book_name=? AND book_author=?";
                PreparedStatement ps = con.prepareStatement(deleted);
                ps.setString(1, bookname);

                ps.setString(2, bookauthor);
                System.out.println("Book Name: " + bookname);
                System.out.println("Book Author: " + bookauthor);

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Record deleted successfully");

            }

            else if (e.getSource() == btn_plsql) {
                System.out.println("success plsql");

                String plsql = " CREATE PROCEDURE update_price()" +
                        "BEGIN" +
                        " DECLARE v_price INT DEFAULT 500;" +

                        "UPDATE data_books" +
                        " SET book_price = book_price * 1.1" +
                        "WHERE book_price > v_price;" +

                        "UPDATE data_books" +
                        " SET book_price = book_price * 1.05" +
                        "WHERE book_price <= v_price;" +

                        " COMMIT;" +
                        " SELECT 'Prices updated successfully.' AS message;" +
                        " END //";

                try (CallableStatement cstmt = con.prepareCall("{call update_price()}")) {
                    cstmt.execute();
                    JOptionPane.showMessageDialog(null, "Stored procedure executed successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error executing stored procedure");
                }

            } else if (e.getSource() == btn_clear) {
                tname.setText("");
                tprice.setText("");
                tauthor.setText("");
                tQuantity.setText("");
                tpublication.setText("");
                tdatepublication.setText("");
                tquantitytoorder.setText("");

            }

            statement.close();
            con.close();

        } catch (

        Exception ex) {
            ex.printStackTrace();
            // Handle exception appropriately
        }
    }

}
