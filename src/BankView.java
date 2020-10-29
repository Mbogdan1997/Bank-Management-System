
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeSet;

import javax.swing.JButton;

@SuppressWarnings("deprecation")
public class BankView extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnConturi;
	private JButton btnPersoane;
	private JButton btnOperatii;
	private Controller controller;
	private TableOperation personsTable;
	private TableOperation accountsTable;
	private OperationView operationView;

	public BankView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnConturi = new JButton("Conturi");
		btnConturi.setBounds(23, 91, 251, 42);
		contentPane.add(btnConturi);

		controller = new Controller(this);

		btnPersoane = new JButton("Persoane");
		btnPersoane.setBounds(23, 24, 251, 42);
		contentPane.add(btnPersoane);

		btnOperatii = new JButton("Operatii Cont");
		btnOperatii.setBounds(23, 158, 251, 42);
		contentPane.add(btnOperatii);

		controller = new Controller(this);
		btnConturi.addActionListener(controller);
		btnPersoane.addActionListener(controller);

		JTable table = new JTable();
		JTable table1 = new JTable();
		personsTable = new TableOperation(table);
		accountsTable = new TableOperation(table1);
		operationView = new OperationView();

		personsTable.getBtnDelete().addActionListener(controller);
		personsTable.getBtnInsert().addActionListener(controller);
		personsTable.getBtnNewButton().addActionListener(controller);

		accountsTable.getBtnDelete().addActionListener(controller);
		accountsTable.getBtnInsert().addActionListener(controller);
		accountsTable.getBtnNewButton().addActionListener(controller);

		btnOperatii.addActionListener(controller);
		controller.getBank().addObserver(this);

	}

	public void createOperationView() {
		operationView.setVisible(false);
		operationView.getBtnDepunere().addActionListener(controller);
		operationView.getBtnNewButton().addActionListener(controller);
		operationView.setVisible(true);
	}

	public void createPersonTable(JTable table) {
		// if(personsTable!=null)
		personsTable.setVisible(false);
		personsTable = new TableOperation(table);
		personsTable.getBtnDelete().addActionListener(controller);
		personsTable.getBtnInsert().addActionListener(controller);
		personsTable.getBtnNewButton().addActionListener(controller);
		personsTable.getTable().addMouseListener(controller);
		personsTable.setVisible(true);

	}

	public void createAccountTable(JTable table) {
		// if(accountsTable!=null)
		accountsTable.setVisible(false);
		accountsTable = new TableOperation(table);
		accountsTable.getBtnDelete().addActionListener(controller);
		accountsTable.getBtnInsert().addActionListener(controller);
		accountsTable.getBtnNewButton().addActionListener(controller);
		accountsTable.getTable().addMouseListener(controller);
		accountsTable.setVisible(true);

	}

	public JButton getBtnOperatii() {
		return btnOperatii;
	}

	public void setBtnOperatii(JButton btnOperatii) {
		this.btnOperatii = btnOperatii;
	}

	public TableOperation getAccountsTable() {
		return accountsTable;
	}

	public void setAccountsTable(TableOperation accountsTable) {
		this.accountsTable = accountsTable;
	}

	public TableOperation getPersonsTable() {
		return personsTable;
	}

	public void setPersonsTable(TableOperation personsTable) {
		this.personsTable = personsTable;
	}

	public JButton getBtnConturi() {
		return btnConturi;
	}

	public void setBtnConturi(JButton btnConturi) {
		this.btnConturi = btnConturi;
	}

	public JButton getBtnPersoane() {
		return btnPersoane;
	}

	public void setBtnPersoane(JButton btnPersoane) {
		this.btnPersoane = btnPersoane;
	}

	public OperationView getOperationView() {
		return operationView;
	}

	public void setOperationView(OperationView operationView) {
		this.operationView = operationView;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

		this.getAccountsTable().setVisible(false);
		this.getPersonsTable().setVisible(false);
		controller.getBank().reportGenerators((Hashtable<Person, TreeSet<Account>>) controller.getBank().getBank());
		if (arg1.equals("cont"))
			this.createAccountTable(this.controller.createJTableAccounts());
		else if (arg1.equals("persoana"))
			this.createPersonTable(this.controller.createJTablePersoane());

	}
}
