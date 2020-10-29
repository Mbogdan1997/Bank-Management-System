import java.io.Serializable;
import java.util.Observable;

@SuppressWarnings("deprecation")
public abstract class Account extends Observable implements Comparable<Account>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float deposit;
	private boolean type;
	private int idAccount;

	public Account() {
		super();

	}

	public float getDeposit() {
		return deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public abstract void withdraw(float sum);

	public abstract void lodge(float sum);

	@Override
	public int compareTo(Account arg0) {
		// TODO Auto-generated method stub

		return this.idAccount - arg0.idAccount;
	}

	public String toString() {
		String string = "";
		string += "Id:" + this.idAccount + " Deposit:" + this.deposit + " Type:" + this.type;

		return string;
	}

}
