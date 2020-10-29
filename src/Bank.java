import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;
import java.util.Observable;
import java.util.TreeSet;

@SuppressWarnings("deprecation")
public class Bank extends Observable implements BankProc, Serializable {

	public Map<Person, TreeSet<Account>> getBank() {
		return bank;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Person, TreeSet<Account>> bank;

	public Bank() {
		bank = new Hashtable<Person, TreeSet<Account>>();
	}

	@Override
	public void withdraw(Account account, float suma) {
		// TODO Auto-generated method stub
		System.out.println("aici");
		assert account != null : "Contul e null";
		assert suma >= 0 : "Nu se folosesc sume negative";
		if (account instanceof SpendingAccount) {
			System.out.println("aici");

			System.out.println("aici");
			account.addObserver(this.findPersonForAccount(account.getIdAccount()));
			account.withdraw(suma);

		}

		if (account instanceof SavingAccount) {

			account.addObserver(this.findPersonForAccount(account.getIdAccount()));
			account.withdraw(suma);

		}
		setChanged();
		notifyObservers("cont");

	}

	@Override
	public void lodge(Account account, float suma) {
		// TODO Auto-generated method stub
		assert account != null : "Contul e null";
		float sumaActuala = account.getDeposit();
		account.lodge(suma);
		account.addObserver(findPersonForAccount(account.getIdAccount()));
		setChanged();
		notifyObservers("cont");
		assert sumaActuala != account.getDeposit() + suma : "Ceva clar nu e bine";

	}

	@Override
	public void addPerson(Person p) {
		// TODO Auto-generated method stub
		assert !bank.containsKey(p) : "Aceasta persoana exista deja";
		assert p.getEmail() != "" : "Mail Gresit";
		assert p.getTelefon() != "" : "Telefon Gresit";
		assert p.getCnp() != null : "Cnp gresit";
		assert p.getCnp().length() == 13 : "Cnp gresit";
		int size = bank.size();
		TreeSet<Account> accounts = new TreeSet<Account>();
		assert wellFormed() : "Invariantul nu e bun";
		bank.put(p, accounts);
		// String arg0 = "person";
		setChanged();
		notifyObservers("persoana");
		assert (size + 1) == bank.size() : "Persoana nu a fost adaugata cu succes";

	}

	@Override
	public void removePerson(Person p) {
		assert bank.containsKey(p) : "Nu puteti sterge ceva ce nu exista";
		int size = bank.size();
		assert wellFormed() : "Invariantul nu e bun";
		bank.remove(p);
		System.out.println("Aici");
		// String arg0 = "person";
		setChanged();
		notifyObservers("persoana");
		assert size - 1 == bank.size() : "Stergerea nu a fost executata cu succes";
		// TODO Auto-generated method stub

	}

	@Override
	public void writeAccount(Account a) {
		// TODO Auto-generated method stub
		assert a != null : "Cont null";
		assert a.getDeposit() >= 0 : "Nu puteti face un cont cu sume negative";
		Account account = findAccount(a.getIdAccount());
		assert account != null : "Cont inexistent";
		// assert wellFormed() : "Invariantul nu e bun";
		assert a.isType() == account.isType() : "Nu se poate modifica tipul contului";
		account.setDeposit(a.getDeposit());
		setChanged();
		notifyObservers("cont");
		assert wellFormed() : "Invariantul nu e bun";

	}

	@Override
	public void reportGenerators(Hashtable<Person, TreeSet<Account>> map) {
		// TODO Auto-generated method stub
		assert map != null : "Mapul e null";
		try {
			FileOutputStream fileOut = new FileOutputStream("ceva.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(map);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /tmp/employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

	public String toString() {

		String string = "";
		for (Map.Entry<Person, TreeSet<Account>> ceva : bank.entrySet()) {
			string += "Person:" + ceva.getKey();
			string += "\n" + "Conturi:";
			for (Account ac : ceva.getValue())
				string += " " + ac;

			string += "\n";

		}
		assert wellFormed() : "Invariantul nu e bun";

		return string;
	}

	@Override
	public void addAccount(Person p, Account ac) {
		assert bank.containsKey(p) : "Aceasta persoana nu exista";
		assert ac.getDeposit() > 0 : "Nu exista cont cu sume negative";
		assert this.findAccount(ac.getIdAccount()) == null : "Contul Exista";
		int size = bank.get(p).size();
		bank.get(p).add(ac);
		assert wellFormed() : "Invariantul nu e bun";
		assert (size + 1) == (bank.get(p).size()) : "Persoana nu a fost adaugata cu succes";
		setChanged();
		notifyObservers("cont");

		// TODO Auto-generated method stub

	}

	@Override
	public Account findAccount(int idAccount) {
		assert idAccount >= 0 : "Idurile conturilor sunt pozitive";
		assert wellFormed() : "Invariantul nu e bun";
		for (Map.Entry<Person, TreeSet<Account>> map : bank.entrySet())
			for (Account ac : map.getValue())
				if (ac.getIdAccount() == idAccount)
					return ac;
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person findPersonForAccount(int idAccount) {
		// TODO Auto-generated method stub
		assert idAccount >= 0 : "Idurile conturilor sunt pozitive";
		assert wellFormed() : "Invariantul nu e bun";
		for (Map.Entry<Person, TreeSet<Account>> map : bank.entrySet())
			for (Account ac : map.getValue())
				if (ac.getIdAccount() == idAccount)
					return map.getKey();
		return null;
	}

	public void displayAccounts(TreeSet<Account> treeSet) {
		// assert treeSet != null : "Nu avem conturi";
		if (treeSet != null)
			for (Account account : treeSet) {
				System.out.println(account);
			}
		assert wellFormed() : "Invariantul nu e bun";

	}

	@Override
	public TreeSet<Account> findAccountsForPerson(Person p) {
		// TODO Auto-generated method stub
		assert p != null : "Persoana este nula";
		assert wellFormed() : "Invariantul nu e bun";
		for (Map.Entry<Person, TreeSet<Account>> map : bank.entrySet())
			if (map.getKey().equals(p))
				return map.getValue();

		return null;
	}

	public boolean wellFormed() {
		// if(bank==null)
		// return false;

		for (Map.Entry<Person, TreeSet<Account>> map : bank.entrySet()) {
			if (map.getKey().getCnp().length() != 13)
				return false;
			for (Account account : map.getValue())
				if (account.getDeposit() < 0)
					return false;
		}
		return true;
	}

	@Override
	public Hashtable<Person, TreeSet<Account>> createMap() {
		// TODO Auto-generated method stub
		Hashtable<Person, TreeSet<Account>> map = new Hashtable<Person, TreeSet<Account>>();
		Person persoana1 = new Person("Micle Bogdan", "1978901006687", "0753800607", "miclebogdanioan@yahoo.com");
		Person persoana2 = new Person("Istrate Alexandru", "1976661006687", "0753709607", "istrateAlexandru@yahoo.com");
		Person persoana3 = new Person("Crestin Alexandru", "1988901006687", "0753909607", "crestinAlexandru@yahoo.com");
		Person persoana4 = new Person("Sandor Paul", "1977701016687", "0753802608", "paulLari@yahoo.com");
		Person persoana5 = new Person("Cenan Codruta", "1988931006687", "0753890608", "muieVerestiuc@yahoo.com");
		TreeSet<Account> accounts1 = new TreeSet<Account>();
		TreeSet<Account> accounts2 = new TreeSet<Account>();
		TreeSet<Account> accounts3 = new TreeSet<Account>();
		TreeSet<Account> accounts4 = new TreeSet<Account>();
		TreeSet<Account> accounts5 = new TreeSet<Account>();
		Account account11 = new SpendingAccount(3024, 1234);
		Account account12 = new SavingAccount(9024, 1434);
		Account account13 = new SpendingAccount(100024, 9234);
		accounts1.add(account11);
		accounts1.add(account12);
		accounts1.add(account13);

		Account account2 = new SavingAccount(329024, 9232);
		accounts2.add(account2);

		Account account3 = new SavingAccount(30224, 10234);
		accounts3.add(account3);

		Account account4 = new SavingAccount(13024, 11234);
		accounts4.add(account4);

		Account account5 = new SpendingAccount(23024, 12340);
		accounts5.add(account5);

		map.put(persoana1, accounts1);
		map.put(persoana2, accounts2);
		map.put(persoana3, accounts3);
		map.put(persoana4, accounts4);
		map.put(persoana5, accounts5);

		assert wellFormed() : "Nu e bun invariantul";

		return map;
	}

	@Override
	public Hashtable<Person, TreeSet<Account>> reportGenerators1() {
		Hashtable<Person, TreeSet<Account>> readObject = null;
		assert wellFormed() : "Nu e bun invariantul";
		try {
			FileInputStream fileIn = new FileInputStream("ceva.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			@SuppressWarnings("unchecked")
			Hashtable<Person, TreeSet<Account>> map = (Hashtable<Person, TreeSet<Account>>) in.readObject();
			readObject = map;
			// System.out.println(map);
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return readObject;
		} catch (ClassNotFoundException c) {
			System.out.println(" class not found");
			c.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return readObject;
	}

	@Override
	public void deleteAccount(Account ac) {
		// TODO Auto-generated method stub
		assert ac != null : "Contul e null";
		for (Map.Entry<Person, TreeSet<Account>> map : bank.entrySet()) {
			for (Account account : map.getValue()) {
				if (ac.getIdAccount() == account.getIdAccount())
					map.getValue().remove(account);
			}
		}
		setChanged();
		notifyObservers("cont");
		assert wellFormed() : "Nu e bun invariantul";

	}

	public void setBank(Hashtable<Person, TreeSet<Account>> bank) {
		this.bank = bank;
		assert wellFormed() : "Nu e bun invariantul";
	}

	@Override
	public void updatePerson(Person p) {
		// TODO Auto-generated method stub
		assert p != null : "Nu e ceva bine";
		assert bank.containsKey(p) : "Aceasta persoana nu exista ";
		assert p.getEmail() != "" : "Mail Gresit";
		assert p.getTelefon() != "" : "Telefon Gresit";
		assert p.getCnp() != null : "Cnp gresit";
		assert p.getCnp().length() == 13 : "Cnp gresit";
		// int size = bank.size();
		// TreeSet<Account> accounts = new TreeSet<Account>();
		for (Map.Entry<Person, TreeSet<Account>> map : bank.entrySet()) {
			if (map.getKey().equals(p)) {
				map.getKey().setCnp(p.getCnp());
				map.getKey().setPersonName(p.getPersonName());
				map.getKey().setTelefon(p.getTelefon());
				map.getKey().setEmail(p.getEmail());
				// String arg0 = "person";
				setChanged();
				notifyObservers("persoana");
			}
		}

		assert wellFormed() : "Invariantul nu e bun";

		// bank.put(p, accounts);
		// assert (size + 1) == bank.size() : "Persoana nu a fost adaugata cu succes";

	}

	@Override
	public Person findPerson(String cnp) {
		// TODO Auto-generated method stub
		assert cnp != null : "Cnp null";
		for (Map.Entry<Person, TreeSet<Account>> map : bank.entrySet())
			if (map.getKey().getCnp().equalsIgnoreCase(cnp))
				return map.getKey();
		assert wellFormed() : "Invariantul nu e bun";
		return null;

	}

	/*
	 * public static void main(String[] arg) { Bank bank=new Bank();
	 * bank.reportGenerators(bank.createMap());
	 * 
	 * }
	 */

}
