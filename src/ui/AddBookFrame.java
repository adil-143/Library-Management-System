package ui;
import javax.swing.*;
import dao.BookDAO;
import java.awt.event.*;

public class AddBookFrame extends JFrame {

    public AddBookFrame() {

        setTitle("Add Book");
        setSize(400, 300);
        setLayout(null);

        JLabel l1 = new JLabel("Title:");
        l1.setBounds(30, 30, 100, 30);
        add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(150, 30, 200, 30);
        add(t1);

        JLabel l2 = new JLabel("Author:");
        l2.setBounds(30, 70, 100, 30);
        add(l2);

        JTextField t2 = new JTextField();
        t2.setBounds(150, 70, 200, 30);
        add(t2);

        JLabel l3 = new JLabel("Publisher:");
        l3.setBounds(30, 110, 100, 30);
        add(l3);

        JTextField t3 = new JTextField();
        t3.setBounds(150, 110, 200, 30);
        add(t3);

        JLabel l4 = new JLabel("Quantity:");
        l4.setBounds(30, 150, 100, 30);
        add(l4);

        JTextField t4 = new JTextField();
        t4.setBounds(150, 150, 200, 30);
        add(t4);

        JButton btn = new JButton("Add Book");
        btn.setBounds(120, 200, 150, 40);
        add(btn);

        btn.addActionListener(e -> {

            String title = t1.getText().trim();
            String author = t2.getText().trim();
            String publisher = t3.getText().trim();
            String quantityText = t4.getText().trim();

            if (title.isEmpty() || author.isEmpty() || publisher.isEmpty() || quantityText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            int quantity;

            try {
                quantity = Integer.parseInt(quantityText);
                if (quantity < 0) {
                    JOptionPane.showMessageDialog(this, "Quantity cannot be negative!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity must be a number!");
                return;
            }

            BookDAO dao = new BookDAO();
            dao.addBook(title, author, publisher, quantity);

            JOptionPane.showMessageDialog(this, "Book Added Successfully!");
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}