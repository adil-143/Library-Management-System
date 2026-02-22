package ui;
import javax.swing.*;
import dao.MemberDAO;

public class AddMemberFrame extends JFrame {

    public AddMemberFrame() {

        setTitle("Add Member");
        setSize(400, 300);
        setLayout(null);

        JLabel l1 = new JLabel("Name:");
        l1.setBounds(30, 40, 100, 30);
        add(l1);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 40, 200, 30);
        add(nameField);

        JLabel l2 = new JLabel("Email:");
        l2.setBounds(30, 90, 100, 30);
        add(l2);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 90, 200, 30);
        add(emailField);

        JLabel l3 = new JLabel("Phone:");
        l3.setBounds(30, 140, 100, 30);
        add(l3);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 140, 200, 30);
        add(phoneField);

        JButton addBtn = new JButton("Add Member");
        addBtn.setBounds(120, 200, 150, 40);
        add(addBtn);

        addBtn.addActionListener(e -> {

            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!");
                return;
            }

            if (!email.contains("@")) {
                JOptionPane.showMessageDialog(this, "Invalid email format!");
                return;
            }

            MemberDAO dao = new MemberDAO();
            dao.addMember(name, email, phone);

            JOptionPane.showMessageDialog(this, "Member Added Successfully!");
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}