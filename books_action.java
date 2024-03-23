import javax.swing.*;
import java.lang.Double;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class books_action implements ActionListener {

    private JTextField tname;
    private JTextField tprice;
    private JTextField tauthor;
    private JTextField tQuantity;
    private JTextArea ta;
    private JButton btn_submit, btn_clear, btn_delete, btn_search, btn_update, btn_showall;
    JTextField tdatepublication;
    JTextField tpublication, ttotalcost, tquantitytoorder;

    public books_action(JTextField tname, JTextField tprice, JTextField tauthor, JTextField tQuantity, JTextArea ta, JButton btn_submit, JButton btn_clear, JButton btn_delete, JButton btn_search, JButton btn_update, JButton btn_showall, JTextField tpublication, JTextField tdatepublication, JTextField ttotalcost,  JTextField tquantitytoorder) {
        this.tname = tname;
        this.tprice = tprice;
        this.tauthor = tauthor;
        this.tQuantity = tQuantity;
        this.tpublication = tpublication;
        this.tdatepublication = tdatepublication;
        this.ttotalcost = ttotalcost;
        this.tquantitytoorder = tquantitytoorder;
        this.ta = ta;
        this.btn_submit = btn_submit;
        this.btn_clear = btn_clear;
        this.btn_delete = btn_delete;
        this.btn_search = btn_search;
        this.btn_update = btn_update;
        this.btn_showall = btn_showall;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(() -> {

            if (e.getSource() == btn_submit) {
                if (validate()) {
                    submit();
                } else {
                    ta.setText("Invalid input. Please check your data.");
                }
            } else if (e.getSource() == btn_showall) {
                show();
            } else if (e.getSource() == btn_update) {
                System.out.println("update");
                updateData();
            } else if (e.getSource() == btn_clear) {
                // Handle clear action
                tname.setText("");
                tprice.setText("");
                tauthor.setText("");
                tQuantity.setText("");
                ta.setText("");
                // tpublication, tdatepublication, ttotalcost, tquantitytoorder
                tpublication.setText("");
                tdatepublication.setText("");
                ttotalcost.setText("");
                tquantitytoorder.setText("");

            } else if (e.getSource() == btn_delete) {
                delete();
            } else if (e.getSource() == btn_search) {
                search();
            }

        });

    }

    private void show() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data1.txt"))) {
            StringBuilder data = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                // Append each line to the StringBuilder
                data.append(line).append("\n");
            }

            // Set the text of the JTextArea to the accumulated data
            ta.setText(data.toString());
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    private void updateData() {
        String name = tname.getText();
        String author = tauthor.getText();

        ArrayList<String> temparray = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader("data1.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lineArr = line.split(",");

                if (lineArr.length >= 3 && lineArr[0].equals(name) && lineArr[2].equals(author)) {
                    // Update the existing data
                    String bookName = tname.getText();
                    String bookPriceStr = tprice.getText();
                    String quantityToOrderStr = tquantitytoorder.getText();

                    int price = Integer.parseInt(bookPriceStr);
                    int quantityToOrder = Integer.parseInt(quantityToOrderStr);

                    int totalCost = price * quantityToOrder;

                    String updatedData = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                            bookName, bookPriceStr, tauthor.getText(), tQuantity.getText(),
                            tpublication.getText(), tdatepublication.getText(),
                            String.valueOf(totalCost), quantityToOrderStr);

                    ttotalcost.setText(String.valueOf(totalCost));

                    temparray.add(updatedData);
                    ta.setText("Data updated: \n" + updatedData);
                } else {
                    // Keep the existing data
                    temparray.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        try (PrintWriter writer = new PrintWriter("data1.txt")) {
            temparray.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }

    public void search() {
        System.out.println("Data search");

        try {
            FileReader fileobj = new FileReader("data1.txt");
            BufferedReader buobj = new BufferedReader(fileobj);
            StringBuilder data = new StringBuilder();
            // while ((data = buobj.readLine()) != null) { ---- to prin5t whole page

            String line;
            // for one data of same name
            // while ((line = buobj.readLine()) != null) {
            // data.append(line).append("\n");
            // if (line.startsWith(tname.getText())) {
            // ta.setText(line.toString());

            // return;
            // }

            // }
            List<String> matchingBooks = new ArrayList<>();

            String bookname = tname.getText().trim();
            String bookauthor = tauthor.getText().trim();

            while ((line = buobj.readLine()) != null) {
                String[] bookData = line.split(",");
                if (bookData.length >= 3 && bookData[0].trim().equals(bookname)
                        && bookData[2].trim().equals(bookauthor)) {

                    /*
                     * The -equalsIgnoreCase() method compares two strings, ignoring lower case
                     * and
                     * upper case differences.
                     * // This method returns true if the strings are equal, and false if not.
                     */

                    matchingBooks.add(line);
                }
            }
            buobj.close();

            // System.out.println("Search Name: " + searchName);
            // System.out.println("Matching Books: " + matchingBooks);

            if (!matchingBooks.isEmpty()) {
                ta.setText(String.join("\n", matchingBooks));
            } else {
                ta.setText("No books found with the given name.");
            }

        } catch (IOException ioe) {
            // TODO: handle exception
        }

    }

    public void delete() {
        try {
            File inputFile = new File("data1.txt");
            File tempFile = new File("tempData.txt");

            BufferedReader buobj = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String searchName = tname.getText().trim();
            String searchAuthor = tauthor.getText().trim();
            String currentLine;

            while ((currentLine = buobj.readLine()) != null) {
                String[] bookData = currentLine.split(",");
                if (bookData.length >= 3 && bookData[0].trim().equals(searchName)
                        && bookData[2].trim().equals(searchAuthor)) {
                    // This condition checks if the array has at least three elements and then
                    // compares the first element (bookData[0]) with searchName and the third
                    // element (bookData[2]) with searchAuthor. If both conditions are true, it
                    // means you found a matching line, and it will be skipped.
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            buobj.close();
            writer.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Error deleting the original file");
                return;
            }

            // Rename the temporary file to the original file
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Error renaming the temporary file");
                return;
            }

            System.out.println("Search Name: " + searchName);
            System.out.println("Search Author: " + searchAuthor);
            ta.setText("Data Deleted");
        } catch (IOException ioe) {
            ioe.printStackTrace(); // Handle the exception, at least print the stack
        }
    }

    public void submit() {
        ttotalcost.setVisible(true);

        try (FileOutputStream fout = new FileOutputStream("data1.txt", true);
                BufferedOutputStream writer = new BufferedOutputStream(fout)) {

            // writer.write(("Book Name:" + tname.getText() + "\n").getBytes());
            // writer.write(("Book Price:" + tprice.getText() + "\n").getBytes());
            // writer.write(("Book Author:" + tauthor.getText() + "\n").getBytes());
            // writer.write(("Book Quantity:" + tQuantity.getText() + "\n").getBytes());

            // writer.write(
            // ("" + tname.getText() + "," + tprice.getText()
            // + "," + tauthor.getText() + "," + tQuantity.getText() + "," +
            // tpublication.getText() + ","
            // + tdatepublication.getText() + "," + ttotalcost.getText() + ","
            // + tquantitytoorder.getText()).getBytes());
            String lineSeparator = System.getProperty("line.separator");
            if (validate()) {
                String bookName = tname.getText();
                String bookPriceStr = tprice.getText();
                String quantityToOrderStr = tquantitytoorder.getText();

                int price = Integer.parseInt(bookPriceStr);
                int quantityToOrder = Integer.parseInt(quantityToOrderStr);

                int totalCost = price * quantityToOrder;

                String data = String.format("%s,%s,%s,%s,%s,%s,%s,%s",
                        bookName, bookPriceStr, tauthor.getText(), tQuantity.getText(),
                        tpublication.getText(), tdatepublication.getText(), quantityToOrderStr,
                        String.valueOf(totalCost));
                ttotalcost.setText(String.valueOf(totalCost));

                writer.write((data + System.getProperty("line.separator")).getBytes());

                ta.setText(data);

                System.out.println("Data written to file successfully.");
            } else {
                ta.setText("Invalid input. Please check your data.");
            }

        } catch (IOException | NumberFormatException ex) {
            System.err.println("Error writing to file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public boolean validate() {
        if (tname.getText().isEmpty() || !tname.getText().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Book name is not writen or Book name only contains letters");
            return false;
        }

        if (tprice.getText().isEmpty() || !tprice.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Book price is not writen or Book price only contains number");
            return false;
        }

        if (tauthor.getText().isEmpty() || !tauthor.getText().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null, "Book author is not writen or Book author only contains letters");
            return false;
        }

        if (tQuantity.getText().isEmpty() || !tQuantity.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Book quantity is not writen or Book quantity only contains number");
            return false;
        }
        // tpublication, tdatepublication, ttotalcost, tquantitytoorder
        if (tpublication.getText().isEmpty() || !tpublication.getText().matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(null,
                    "Book publication is not writen or Book publication only contains letters");
            return false;
        }
        if (tdatepublication.getText().isEmpty() || !tdatepublication.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null,
                    "Book date of publication is not writen or Book date of publication  only contains number");
            return false;
        }
        // if (ttotalcost.getText().isEmpty() ||
        // !ttotalcost.getText().matches("[0-9]+")) {
        // JOptionPane.showMessageDialog(null,
        // "Book total cost is not writen or Book total cost only contains number");
        // return false;
        // }

        if (tquantitytoorder.getText().isEmpty() || !tquantitytoorder.getText().matches("[0-9]+")) {
            JOptionPane.showMessageDialog(null,
                    "Book quantity to order is not writen or Book quantity to order only contains number");
            return false;
        }

        return true;
    }

}
