
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeSet;

public interface BankProc {

	void addPerson(Person p);

	void removePerson(Person p);

	void updatePerson(Person p);

	Person findPerson(String cnp);

	void addAccount(Person p, Account ac);

	Account findAccount(int idAccount);

	void writeAccount(Account a);

	void withdraw(Account account, float suma);

	void lodge(Account account, float suma);

	void deleteAccount(Account ac);

	Person findPersonForAccount(int idAccount);

	TreeSet<Account> findAccountsForPerson(Person p);

	void reportGenerators(Hashtable<Person, TreeSet<Account>> map);

	Hashtable<Person, TreeSet<Account>> reportGenerators1();

	Map<Person, TreeSet<Account>> createMap();
}
