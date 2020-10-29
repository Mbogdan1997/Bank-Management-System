import javax.swing.JOptionPane;

public class SavingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float interest;
	private boolean makeWithdraw;
	private boolean makeLodge;

	public SavingAccount(float deposit, int idAccount) {
		super();
		this.setDeposit(deposit);
		this.setIdAccount(idAccount);
		this.setType(true);
		interest = (float) 0.01;
		setMakeWithdraw(true);
		makeLodge = true;
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("deprecation")
	@Override
	public void lodge(float sum) {
		if(makeLodge) {
		if (sum > 10000) {
			this.setMakeWithdraw(true);
			this.makeLodge = false;
			if (sum <= 15000)
				;
			else if (sum > 15000 && sum <= 20000) {
				this.interest = (float) 0.02;
			} else if (sum > 20000 && sum <= 25000) {
				this.interest = (float) 0.03;
			} else if (sum > 25000 && sum <= 30000) {
				this.interest = (float) 0.04;
			} else {
				this.interest = (float) 0.05;
			}
			// String arg0="A fost depusa suma:"+sum+"iar valoarea totala a contului este:";
			this.setDeposit(this.getDeposit()+sum+sum * interest);
			String arg0 = "A fost depusa suma:" + sum + " iar valoarea totala a contului este:" + this.getDeposit();
			setChanged();
			notifyObservers(arg0);

		} else
			JOptionPane.showMessageDialog(null, "Suma prea mica", "Eroare", JOptionPane.WARNING_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null, "S-a facut deja o depunere", "Eroare", JOptionPane.WARNING_MESSAGE);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void withdraw(float sum) {
		// TODO Auto-generated method stub
		if (sum == this.getDeposit()) {
			if (makeWithdraw == true) {
				String arg0 = "A fost extrasa suma:" + this.getDeposit();
				this.setDeposit(0);
				makeLodge = false;
				makeWithdraw=false;
				setChanged();
				notifyObservers(arg0);
			} else
				JOptionPane.showMessageDialog(null, "Din acest cont nu se pot face extrageri", "Eroare",
						JOptionPane.ERROR_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Se pot extrage doar toti banii", "Eroare", JOptionPane.ERROR_MESSAGE);

	}

	public boolean isMakeWithdraw() {
		return makeWithdraw;
	}

	public void setMakeWithdraw(boolean makeWithdraw) {
		this.makeWithdraw = makeWithdraw;
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	public boolean isMakeLodge() {
		return makeLodge;
	}

	public void setMakeLodge(boolean makeLodge) {
		this.makeLodge = makeLodge;
	}

}
