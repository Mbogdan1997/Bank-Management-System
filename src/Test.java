import static org.junit.jupiter.api.Assertions.*;

import java.util.Hashtable;
import java.util.Map;
import java.util.TreeSet;

class Test {
	private Bank bank;

	public Test() {
		bank = new Bank();
	}

	@org.junit.jupiter.api.Test
	void test_add_remove_update_generare() {
		Person person = new Person("Micle Bogdan", "1971104055666", "0753800607", "miclebogdanioan@yahoo.com");
		Person person1 = new Person("Istrate Alexandru", "1971104055666", "0744662619", "chelsea@yahoo.com");
		Account account = new SpendingAccount(1000, 3);

		bank.addPerson(person);
		assertTrue(bank.getBank().size() == 1);
		for (Map.Entry<Person, TreeSet<Account>> map : bank.getBank().entrySet()) {

			assertTrue(map.getKey().getCnp().equals("1971104055666"));
			assertTrue((map.getKey().getPersonName()).equals("Micle Bogdan"));

			bank.updatePerson(person1);
			assertTrue((map.getKey().getPersonName()).equals("Istrate Alexandru"));
			bank.addAccount(map.getKey(), account);
			for (Account account2 : map.getValue()) {

				assertTrue(account2.getDeposit() == 1000);
				bank.lodge(account2, 2000);

				assertTrue(account2.getDeposit() == 3000);
				bank.withdraw(account2, 1500);

				assertTrue(account2.getDeposit() == 1500);
			}

		}
		bank.removePerson(person);
		assertTrue(bank.getBank().size() == 0);

		bank.reportGenerators(bank.createMap());
		Hashtable<Person, TreeSet<Account>> hashtable = bank.reportGenerators1();

		bank.setBank(hashtable);
		assertTrue(hashtable.size() == 5);

		int i = 0;
		for (Map.Entry<Person, TreeSet<Account>> map : bank.getBank().entrySet()) {
			if (i == 0)
				map.getKey().getPersonName().equals("Cenan Codruta");
			else if (i == 1)
				map.getKey().getPersonName().equals("Sandor Paul");
			else if (i == 2)
				map.getKey().getPersonName().equals("Crestin Alexandru");
			else if (i == 3)
				map.getKey().getPersonName().equals("Istrate Alexandru");
			else if (i == 4)
				map.getKey().getPersonName().equals("Micle Bogdan");
			i++;
		}

		// fail("Not yet implemented");
	}

}
