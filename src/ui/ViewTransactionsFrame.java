package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.TransactionDAO;

public class ViewTransactionsFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewTransactionsFrame() {

        setTitle("Transaction History");
        setSize(700, 400);
        setLayout(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Transaction ID");
        model.addColumn("Book ID");
        model.addColumn("Member ID");
        model.addColumn("Issue Date");
        model.addColumn("Return Date");
        model.addColumn("Fine");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 640, 300);
        add(scrollPane);

        loadTransactions();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadTransactions() {

        model.setRowCount(0);

        TransactionDAO dao = new TransactionDAO();
        java.util.List<Object[]> transactions = dao.getAllTransactions();

        for (Object[] row : transactions) {
            model.addRow(row);
        }
    }
}