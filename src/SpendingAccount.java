import javax.swing.JOptionPane;

public class SpendingAccount extends Account {

	public SpendingAccount(float deposit, int idAccount) {
		super();
		this.setDeposit(deposit);
		this.setIdAccount(idAccount);
		this.setType(false);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public void withdraw(float sum) {
		System.out.println("aici");
		if (this.getDeposit() >= sum) {
			this.setDeposit(this.getDeposit() - sum);
			String arg0 = "A fost retrasa suma:" + sum + " iar valoarea totala a contului este:" + this.getDeposit();
			setChanged();
			notifyObservers(arg0);
		} else
			JOptionPane.showMessageDialog(null, "Suma este prea mare", "Eroare", JOptionPane.ERROR_MESSAGE);

	}

	@SuppressWarnings("deprecation")
	public void lodge(float sum) {
		this.setDeposit(this.getDeposit() + sum);
		String arg0 = "A fost depusa suma:" + sum + " iar valoarea totala a contului este:" + this.getDeposit();
		setChanged();
		notifyObservers(arg0);
	}

}
