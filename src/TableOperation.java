
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class TableOperation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JButton btnDelete;
	private JButton btnInsert;

	public TableOperation(JTable table) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1211, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 1045, 533);
		contentPane.add(scrollPane);

		this.setTable(table);
		scrollPane.setViewportView(table);

		btnNewButton = new JButton("UPDATE");
		btnNewButton.setBounds(1065, 22, 120, 32);
		contentPane.add(btnNewButton);

		btnDelete = new JButton("DELETE");
		btnDelete.setBounds(1065, 82, 120, 32);
		contentPane.add(btnDelete);

		btnInsert = new JButton("INSERT");
		btnInsert.setBounds(1065, 140, 120, 32);
		contentPane.add(btnInsert);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnInsert() {
		return btnInsert;
	}

	public void setBtnInsert(JButton btnInsert) {
		this.btnInsert = btnInsert;
	}

}
