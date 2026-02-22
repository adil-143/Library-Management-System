package ui;
import javax.swing.*;
import dao.BookDAO;
import dao.TransactionDAO;

public class IssueBookFrame extends JFrame {

    public IssueBookFrame() {

        setTitle("Issue Book");
        setSize(400, 250);
        setLayout(null);

        JLabel l1 = new JLabel("Book ID:");
        l1.setBounds(30, 30, 100, 30);
        add(l1);

        JTextField bookField = new JTextField();
        bookField.setBounds(150, 30, 200, 30);
        add(bookField);

        JLabel l2 = new JLabel("Member ID:");
        l2.setBounds(30, 80, 100, 30);
        add(l2);

        JTextField memberField = new JTextField();
        memberField.setBounds(150, 80, 200, 30);
        add(memberField);

        JButton issueBtn = new JButton("Issue Book");
        issueBtn.setBounds(120, 140, 150, 40);
        add(issueBtn);

        issueBtn.addActionListener(e -> {

            int bookId = Integer.parseInt(bookField.getText());
            int memberId = Integer.parseInt(memberField.getText());

            BookDAO bookDAO = new BookDAO();

            if (bookDAO.reduceQuantity(bookId)) {

                TransactionDAO transactionDAO = new TransactionDAO();
                transactionDAO.issueBook(bookId, memberId);

                JOptionPane.showMessageDialog(this, "Book Issued Successfully!");

            } else {
                JOptionPane.showMessageDialog(this, "Book Not Available!");
            }

        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}