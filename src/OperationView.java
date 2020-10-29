
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

public class OperationView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton;
	private JButton btnDepunere;

	public OperationView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 35, 219, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 95, 219, 27);
		contentPane.add(textField_1);

		btnNewButton = new JButton("Retragere");
		btnNewButton.setBounds(263, 35, 147, 27);
		contentPane.add(btnNewButton);

		btnDepunere = new JButton("Depunere");
		btnDepunere.setBounds(263, 95, 147, 27);
		contentPane.add(btnDepunere);

		JLabel lblNewLabel = new JLabel("Id Cont:");
		lblNewLabel.setBounds(10, 10, 111, 14);
		contentPane.add(lblNewLabel);

		JLabel lblSuma = new JLabel("Suma:");
		lblSuma.setBounds(10, 73, 111, 14);
		contentPane.add(lblSuma);
	}

	public JButton getBtnNewButton() {
		return btnNewButton;
	}

	public void setBtnNewButton(JButton btnNewButton) {
		this.btnNewButton = btnNewButton;
	}

	public JButton getBtnDepunere() {
		return btnDepunere;
	}

	public void setBtnDepunere(JButton btnDepunere) {
		this.btnDepunere = btnDepunere;
	}

	public JTextField getTextField() {
		return textField;
	}

	public void setTextField(String textField) {
		this.textField.setText(textField);
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(String textField_1) {
		this.textField_1.setText(textField_1);
	}
}
