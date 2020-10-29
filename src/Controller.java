import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.Map;

import java.util.TreeSet;

import javax.swing.JTable;

public class Controller implements ActionListener, MouseListener {

	private BankView bankView;
	private Bank bank;

	public Controller(BankView bankView) {

		this.bankView = bankView;
		this.bank = new Bank();
		bank.setBank(bank.reportGenerators1());
		// bank.addObserver(this);

		System.out.println(bank.toString());

	}

	public void deletePerson() {
		JTable table = bankView.getPersonsTable().getTable();
		int i = table.getSelectedRow();
		String nume = (String) table.getValueAt(i, 0);
		String cnp = (String) table.getValueAt(i, 1);
		String telefon = (String) table.getValueAt(i, 2);
		String email = (String) table.getValueAt(i, 3);
		Person person2 = new Person(nume, cnp, telefon, email);
		bank.removePerson(person2);

	}

	public void deleteAccount() {
		JTable table = bankView.getAccountsTable().getTable();
		int i = table.getSelectedRow();
		int id = (int) table.getValueAt(i, 2);
		float deposit = (float) table.getValueAt(i, 4);
		boolean type = false;

		if (((String) table.getValueAt(i, 3)).equalsIgnoreCase("Saving Account"))
			type = true;
		else if (((String) table.getValueAt(i, 3)).equalsIgnoreCase("Spending Account"))
			type = false;
		else {
			return;
		}
		Account account;
		if (type == true)
			account = new SavingAccount(deposit, id);
		else
			account = new SpendingAccount(deposit, id);

		bank.deleteAccount(account);

		// Person person2=new Person(nume, cnp, telefon, email);
		// bank.removePerson(person2);

	}

	public void updatePerson() {
		JTable table = bankView.getPersonsTable().getTable();
		int i = table.getSelectedRow();
		String nume = (String) table.getValueAt(i, 0);
		String cnp = (String) table.getValueAt(i, 1);
		String telefon = (String) table.getValueAt(i, 2);
		String email = (String) table.getValueAt(i, 3);
		Person person2 = new Person(nume, cnp, telefon, email);
		bank.updatePerson(person2);

	}

	public void updateAccount() {
		JTable table = bankView.getAccountsTable().getTable();
		int i = table.getSelectedRow();
		int id = (int) table.getValueAt(i, 2);
		float deposit = Float.parseFloat((String) table.getValueAt(i, 4));
		boolean type = false;

		if (((String) table.getValueAt(i, 3)).equalsIgnoreCase("Saving Account"))
			type = true;
		else if (((String) table.getValueAt(i, 3)).equalsIgnoreCase("Spending Account"))
			type = false;
		else {
			return;
		}
		Account account;
		if (type == true)
			account = new SavingAccount(deposit, id);
		else
			account = new SpendingAccount(deposit, id);

		bank.writeAccount(account);
		;

	}

	public void insertAccount() {
		JTable table = bankView.getAccountsTable().getTable();
		int i = table.getRowCount() - 1;
		if (i == table.getSelectedRow()) {
			String cnp = (String) table.getValueAt(i, 1);
			int id = Integer.parseInt((String) table.getValueAt(i, 2));
			float deposit = Float.parseFloat((String) table.getValueAt(i, 4));
			boolean type = false;

			if (((String) table.getValueAt(i, 3)).equalsIgnoreCase("Saving Account"))
				type = true;
			else if (((String) table.getValueAt(i, 3)).equalsIgnoreCase("Spending Account"))
				type = false;
			else {
				return;
			}
			Account account;
			if (type == true)
				account = new SavingAccount(deposit, id);
			else
				account = new SpendingAccount(deposit, id);

			bank.addAccount(bank.findPerson(cnp), account);

		}

	}

	public void insertPerson() {
		JTable table = bankView.getPersonsTable().getTable();
		int i = table.getRowCount() - 1;
		if (i == table.getSelectedRow()) {
			String nume = (String) table.getValueAt(i, 0);
			String cnp = (String) table.getValueAt(i, 1);
			String telefon = (String) table.getValueAt(i, 2);
			String email = (String) table.getValueAt(i, 3);
			Person person2 = new Person(nume, cnp, telefon, email);
			bank.addPerson(person2);
		}

	}

	public JTable createJTableAccounts() {
		Map<Person, TreeSet<Account>> map = bank.getBank();
		int nrRows = 0;
		for (Map.Entry<Person, TreeSet<Account>> map1 : map.entrySet()) {
			// for(Account account:map1.getValue())
			nrRows += map1.getValue().size();

		}
		Object[][] objects = new Object[nrRows + 1][5];
		String[] strings = { "Nume Posesor", "CNP Posesor", "Id Cont", "Tip Cont", "Sold Cont" };
		int i = 0;
		for (Map.Entry<Person, TreeSet<Account>> map1 : map.entrySet())
			for (Account account : map1.getValue()) {
				objects[i][0] = map1.getKey().getPersonName();
				objects[i][1] = map1.getKey().getCnp();
				objects[i][2] = account.getIdAccount();
				if (account.isType())
					objects[i][3] = "Saving Account";
				else
					objects[i][3] = "Spending Account";
				objects[i][4] = account.getDeposit();
				i++;
			}
		objects[i][0] = "";
		objects[i][1] = "";
		objects[i][2] = 0;
		objects[i][3] = "Saving Account";
		objects[i][4] = 0;

		return new JTable(objects, strings);
	}

	public JTable createJTablePersoane() {
		Map<Person, TreeSet<Account>> map = bank.getBank();
		int nrRows = map.size();
		Object[][] objects = new Object[nrRows + 1][4];
		String[] strings = { "Nume", "CNP", "Telefon", "email" };

		int i = 0;
		for (Map.Entry<Person, TreeSet<Account>> map1 : map.entrySet()) {
			objects[i][0] = map1.getKey().getPersonName();
			objects[i][1] = map1.getKey().getCnp();
			objects[i][2] = map1.getKey().getTelefon();
			objects[i][3] = map1.getKey().getEmail();
			i++;
		}
		Person person = new Person();
		objects[i][0] = person.getPersonName();
		objects[i][1] = person.getCnp();
		objects[i][2] = person.getTelefon();
		objects[i][3] = person.getEmail();

		return new JTable(objects, strings);

	}

	public void depunere() {
		float suma = Float.parseFloat(bankView.getOperationView().getTextField_1().getText());
		int idCont = Integer.parseInt(bankView.getOperationView().getTextField().getText());

		bank.lodge(bank.findAccount(idCont), suma);
	}

	public void extragere() {
		float suma = Float.parseFloat(bankView.getOperationView().getTextField_1().getText());
		int idCont = Integer.parseInt(bankView.getOperationView().getTextField().getText());

		bank.withdraw(bank.findAccount(idCont), suma);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == bankView.getBtnPersoane())
			bankView.createPersonTable(this.createJTablePersoane());
		else if (arg0.getSource() == bankView.getBtnConturi())
			bankView.createAccountTable(this.createJTableAccounts());
		else if (arg0.getSource() == bankView.getPersonsTable().getBtnDelete())
			this.deletePerson();
		else if (arg0.getSource() == bankView.getPersonsTable().getBtnNewButton())
			this.updatePerson();
		else if (arg0.getSource() == bankView.getPersonsTable().getBtnInsert())
			this.insertPerson();
		else if (arg0.getSource() == bankView.getAccountsTable().getBtnDelete())
			this.deleteAccount();
		else if (arg0.getSource() == bankView.getAccountsTable().getBtnNewButton())
			this.updateAccount();
		else if (arg0.getSource() == bankView.getAccountsTable().getBtnInsert())
			this.insertAccount();
		else if (arg0.getSource() == bankView.getBtnOperatii())
			bankView.createOperationView();
		else if (arg0.getSource() == bankView.getOperationView().getBtnDepunere())
			this.depunere();
		else if (arg0.getSource() == bankView.getOperationView().getBtnNewButton())
			this.extragere();

	}

	/*
	 * @Override public void update(Observable arg0, Object arg1) { //
	 * bank.reportGenerators((Hashtable<Person, TreeSet<Account>>) bank.getBank());
	 * bankView.getAccountsTable().setVisible(false);
	 * bankView.getPersonsTable().setVisible(false);
	 * bank.reportGenerators((Hashtable<Person, TreeSet<Account>>) bank.getBank());
	 * if (arg1.equals("cont"))
	 * bankView.createAccountTable(this.createJTableAccounts()); else if
	 * (arg1.equals("persoana"))
	 * bankView.createPersonTable(this.createJTablePersoane()); // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == bankView.getAccountsTable().getTable()) {
			Point point = arg0.getPoint();
			JTable accountsTable = bankView.getAccountsTable().getTable();
			int column = accountsTable.columnAtPoint(point);
			int row = accountsTable.rowAtPoint(point);

			if (column == 2) {
				// System.out.println("aici");
				if (bankView.getOperationView().isVisible())
					bankView.getOperationView().setTextField(String.valueOf(accountsTable.getValueAt(row, column)));
			} else if (column == 0) {
				// System.out.print("aici");
				accountsTable.setValueAt(accountsTable.getValueAt(row, column), accountsTable.getRowCount() - 1, 0);
			} else if (column == 1) {
				// System.out.print("aici");
				accountsTable.setValueAt(accountsTable.getValueAt(row, column), accountsTable.getRowCount() - 1, 1);
			}

			else if (column == 3) {
				// System.out.print("aici");
				accountsTable.setValueAt(accountsTable.getValueAt(row, column), accountsTable.getRowCount() - 1, 3);
			} else if (column == 4) {
				// System.out.print("aici");
				accountsTable.setValueAt(accountsTable.getValueAt(row, column), accountsTable.getRowCount() - 1, 4);
			}
		} else if (arg0.getSource() == bankView.getPersonsTable().getTable()) {
			Point point = arg0.getPoint();
			JTable personsTable = bankView.getPersonsTable().getTable();
			int column = personsTable.columnAtPoint(point);
			int row = personsTable.rowAtPoint(point);

			if (column == 2) {
				// System.out.println("aici");
				personsTable.setValueAt(personsTable.getValueAt(row, column), personsTable.getRowCount() - 1, 2);
			} else if (column == 0) {
				// System.out.print("aici");
				personsTable.setValueAt(personsTable.getValueAt(row, column), personsTable.getRowCount() - 1, 0);
			}

			else if (column == 3) {
				// System.out.print("aici");
				personsTable.setValueAt(personsTable.getValueAt(row, column), personsTable.getRowCount() - 1, 3);
			}

			// JOptionPane.showMessageDialog(bankView.getAccountsTable().getTable(),
			// "Column header: " + column + " row: " + row + " is clicked");
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
