import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConversorMoeda extends JFrame {
	private JLabel valorLabel, deLabel, paraLabel, resultadoLabel;
	private JTextField valorField;
	private JComboBox<String> deComboBox, paraComboBox;
	private JButton conversorBotao;
	private DecimalFormat decimalFormato = new DecimalFormat("#,##0.00");

	private final String[] moedas = { "USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY", "INR" };
	private double[] valoresIntercambio = { 1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14 };// dolar como
																										// moeda
																										// principal

	public ConversorMoeda() {
		setTitle("Conversor de Moedas");
		setLayout(new GridLayout(4, 2));// linha x coluna

		valorLabel = new JLabel(" Valor:");
		add(valorLabel);

		valorField = new JTextField();
		add(valorField);

		deLabel = new JLabel(" De:");
		add(deLabel);

		deComboBox = new JComboBox<>(moedas);
		add(deComboBox);

		paraLabel = new JLabel(" Para:");
		add(paraLabel);

		paraComboBox = new JComboBox<>(moedas);
		add(paraComboBox);

		conversorBotao = new JButton("Converter:");
		add(conversorBotao);

		resultadoLabel = new JLabel();
		add(resultadoLabel);

		conversorBotao.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					double valor = Double.parseDouble(valorField.getText());
					String deMoeda = (String) deComboBox.getSelectedItem();
					String paraMoeda = (String) paraComboBox.getSelectedItem();
					double valorInercambio = valoresIntercambio[getIndex(paraMoeda)]
							/ valoresIntercambio[getIndex(deMoeda)];
					double resultado = valor * valorInercambio;
					resultadoLabel.setText(decimalFormato.format(resultado) + " " + paraMoeda);
				} catch (Exception ex) {
					resultadoLabel.setText("Valor Inválido");
				}

			}
		});
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private int getIndex(String moeda) {
		for (int i = 0; i < moedas.length; i++) {
			if (moeda.equals(moedas[i])) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		new ConversorMoeda();
	}
}
