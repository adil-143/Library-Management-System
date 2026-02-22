package ui;
import javax.swing.*;
import dao.BookDAO;
import dao.TransactionDAO;

public class ReturnBookFrame extends JFrame {

    public ReturnBookFrame() {

        setTitle("Return Book");
        setSize(400, 250);
        setLayout(null);

        JLabel l1 = new JLabel("Transaction ID:");
        l1.setBounds(30, 40, 120, 30);
        add(l1);

        JTextField transactionField = new JTextField();
        transactionField.setBounds(170, 40, 150, 30);
        add(transactionField);

        JLabel l2 = new JLabel("Book ID:");
        l2.setBounds(30, 90, 120, 30);
        add(l2);

        JTextField bookField = new JTextField();
        bookField.setBounds(170, 90, 150, 30);
        add(bookField);

        JButton returnBtn = new JButton("Return Book");
        returnBtn.setBounds(120, 150, 150, 40);
        add(returnBtn);

        returnBtn.addActionListener(e -> {

            try {

                int transactionId = Integer.parseInt(transactionField.getText());
                int bookId = Integer.parseInt(bookField.getText());

                TransactionDAO transactionDAO = new TransactionDAO();
                double fine = transactionDAO.returnBook(transactionId, bookId);

                if (fine >= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Book Returned Successfully!\nFine: ₹" + fine);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Invalid Transaction ID or Already Returned!");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter valid numeric values!");
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}