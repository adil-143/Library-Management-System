package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import dao.BookDAO;

public class ViewBooksFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewBooksFrame() {

        setTitle("View Books");
        setSize(800, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 🔹 Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel searchLabel = new JLabel("Search:");
        JTextField searchField = new JTextField(20);
        JButton searchBtn = new JButton("Search");
        JButton deleteBtn = new JButton("Delete Selected");
        JButton updateBtn = new JButton("Update Selected");

        topPanel.add(searchLabel);
        topPanel.add(searchField);
        topPanel.add(searchBtn);
        topPanel.add(deleteBtn);
        topPanel.add(updateBtn);

        add(topPanel, BorderLayout.NORTH);

        // 🔹 Table
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Publisher");
        model.addColumn("Quantity");

        add(new JScrollPane(table), BorderLayout.CENTER);

        loadBooks();

        // 🔍 Search
        searchBtn.addActionListener(e -> {

            String keyword = searchField.getText().trim();
            model.setRowCount(0);

            BookDAO dao = new BookDAO();
            java.util.List<Object[]> books;

            if (keyword.isEmpty()) {
                books = dao.getAllBooks();
            } else {
                books = dao.searchBooks(keyword);
            }

            for (Object[] row : books) {
                model.addRow(row);
            }
        });

        // 🗑 Delete
        deleteBtn.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a book first!");
                return;
            }

            int bookId = (int) model.getValueAt(selectedRow, 0);

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                BookDAO dao = new BookDAO();
                boolean deleted = dao.deleteBook(bookId);

                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Deleted!");
                    loadBooks();
                } else {
                    JOptionPane.showMessageDialog(this, "Delete Failed!");
                }
            }
        });

        // ✏ Update
        updateBtn.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Select a book first!");
                return;
            }

            int bookId = (int) model.getValueAt(selectedRow, 0);
            String title = (String) model.getValueAt(selectedRow, 1);
            String author = (String) model.getValueAt(selectedRow, 2);
            String publisher = (String) model.getValueAt(selectedRow, 3);
            int quantity = (int) model.getValueAt(selectedRow, 4);

            JTextField titleField = new JTextField(title);
            JTextField authorField = new JTextField(author);
            JTextField publisherField = new JTextField(publisher);
            JTextField quantityField = new JTextField(String.valueOf(quantity));

            JPanel panel = new JPanel(new GridLayout(4, 2));
            panel.add(new JLabel("Title:"));
            panel.add(titleField);
            panel.add(new JLabel("Author:"));
            panel.add(authorField);
            panel.add(new JLabel("Publisher:"));
            panel.add(publisherField);
            panel.add(new JLabel("Quantity:"));
            panel.add(quantityField);

            int result = JOptionPane.showConfirmDialog(
                    this,
                    panel,
                    "Update Book",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (result == JOptionPane.OK_OPTION) {

                try {
                    int newQuantity = Integer.parseInt(quantityField.getText().trim());

                    BookDAO dao = new BookDAO();
                    boolean updated = dao.updateBook(
                            bookId,
                            titleField.getText().trim(),
                            authorField.getText().trim(),
                            publisherField.getText().trim(),
                            newQuantity
                    );

                    if (updated) {
                        JOptionPane.showMessageDialog(this, "Updated!");
                        loadBooks();
                    } else {
                        JOptionPane.showMessageDialog(this, "Update Failed!");
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Quantity must be a number!");
                }
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadBooks() {

        model.setRowCount(0);

        BookDAO dao = new BookDAO();
        java.util.List<Object[]> books = dao.getAllBooks();

        for (Object[] row : books) {
            model.addRow(row);
        }
    }
}