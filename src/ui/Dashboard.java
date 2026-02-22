package ui;
import javax.swing.*;
import ui.ViewMembersFrame;
import ui.ViewTransactionsFrame;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Library Dashboard");
        setSize(400, 500);
        setLayout(new java.awt.GridLayout(0, 1, 10, 10));

        JButton addBookBtn = new JButton("Add Book");
        JButton viewBookBtn = new JButton("View Books");
        JButton issueBookBtn = new JButton("Issue Book");
        JButton returnBtn = new JButton("Return Book");
        JButton addMemberBtn = new JButton("Add Member");
        JButton viewMemberBtn = new JButton("View Members");
        JButton transactionBtn = new JButton("Transaction History");

        add(addBookBtn);
        add(viewBookBtn);
        add(issueBookBtn);
        add(returnBtn);
        add(addMemberBtn);
        add(viewMemberBtn);
        add(transactionBtn);

        addBookBtn.addActionListener(e -> new AddBookFrame());
        viewBookBtn.addActionListener(e -> new ViewBooksFrame());
        issueBookBtn.addActionListener(e -> new IssueBookFrame());
        returnBtn.addActionListener(e -> new ReturnBookFrame());
        addMemberBtn.addActionListener(e -> new AddMemberFrame());
        viewMemberBtn.addActionListener(e -> new ViewMembersFrame());
        transactionBtn.addActionListener(e -> new ViewTransactionsFrame());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}