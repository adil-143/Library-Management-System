package ui;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import dao.MemberDAO;

public class ViewMembersFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    public ViewMembersFrame() {

        setTitle("View Members");
        setSize(600, 400);
        setLayout(null);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Member ID");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 540, 300);
        add(scrollPane);

        loadMembers();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void loadMembers() {

        model.setRowCount(0);

        MemberDAO dao = new MemberDAO();
        java.util.List<Object[]> members = dao.getAllMembers();

        for (Object[] row : members) {
            model.addRow(row);
        }
    }
}