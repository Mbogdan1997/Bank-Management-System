import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

@SuppressWarnings("deprecation")
public class Person implements Observer, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personName;
	private String email;
	private String telefon;
	private String cnp;

	public Person() {
		super();
	}

	public Person(String personName, String cnp, String telefon, String email) {
		super();
		this.personName = personName;
		this.cnp = cnp;
		this.telefon = telefon;
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Integer.parseInt(cnp.substring(9));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (cnp != other.cnp)
			return false;
		return true;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String toString() {

		String string = "Nume:" + this.personName + " Email:" + this.email + " Telefon:" + this.telefon + " CNP:"
				+ this.cnp;
		return string;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		System.out.println("Update");
		String string=this.getPersonName()+":"+arg1;
		JOptionPane.showMessageDialog(null, string, "Ceva", JOptionPane.WARNING_MESSAGE);
	}

}
