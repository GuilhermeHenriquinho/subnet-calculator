package calculadora.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Calculadora extends JFrame {

	private JFrame frmCalculadoraDeSubredes;
	private JTextField txtIp1;
	private JTextField txtMascara1;
	private JTextField txtCidr;
	private JTextField txtIp2;
	private JTextField txtIp3;
	private JTextField txtMascara2;
	private JTextField txtMascara3;
	private JTextField txtSr;
	private JTextField txtHosts;
	private final int min = 0;
	private final int max = 256;
	private JTextField txtIp4;
	private JTextField txtMascara4;
	private JButton btnCalcular;
	private String classe = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculadora window = new Calculadora();
					window.frmCalculadoraDeSubredes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Calculadora() {
		initialize();
	}

	private void initialize() {
		frmCalculadoraDeSubredes = new JFrame();
		frmCalculadoraDeSubredes.setTitle("Calculadora");
		frmCalculadoraDeSubredes.getContentPane().setBackground(SystemColor.window);
		frmCalculadoraDeSubredes.setBounds(100, 100, 287, 348);
		frmCalculadoraDeSubredes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCalculadoraDeSubredes.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(30, 144, 255));
		panel.setBounds(0, 0, 276, 62);
		frmCalculadoraDeSubredes.getContentPane().add(panel);
		panel.setLayout(new BorderLayout());

		JLabel lblNewLabel = new JLabel("Cálculo de SubRedes");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(lblNewLabel, BorderLayout.CENTER);

		txtIp1 = new JTextField();
		txtIp1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				valid(txtIp1);
			}
		});
		txtIp1.setBounds(10, 87, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtIp1);
		txtIp1.setColumns(10);

		txtMascara1 = new JTextField();
		txtMascara1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				valid(txtMascara1);
			}
		});
		txtMascara1.setColumns(10);
		txtMascara1.setBounds(10, 150, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtMascara1);

		txtCidr = new JTextField();
		txtCidr.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int cidr = Integer.parseInt(txtCidr.getText());
					if (cidr > 0 && cidr <= 30) {
						//faz o inverso do calculo de cidr
						int mascara = 0xFFFFFFFF << (32 - cidr);

						String mascaraFormatada = String.format("%d.%d.%d.%d", (mascara >> 24) & 0xFF,
								(mascara >> 16) & 0xFF, (mascara >> 8) & 0xFF, mascara & 0xFF);

						String[] octetos = mascaraFormatada.split("\\.");

						String mascara1 = octetos[0];
						String mascara2 = octetos[1];
						String mascara3 = octetos[2];
						String mascara4 = octetos[3];

						txtMascara1.setText(mascara1);
						txtMascara2.setText(mascara2);
						txtMascara3.setText(mascara3);
						txtMascara4.setText(mascara4);
						
						calculaCampos();
					} else {
						messageInvalid();
					}
				}
			}
		});
		txtCidr.setColumns(10);
		txtCidr.setBounds(38, 215, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtCidr);

		txtIp2 = new JTextField();
		txtIp2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				valid(txtIp2);
			}
		});
		txtIp2.setColumns(10);
		txtIp2.setBounds(76, 87, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtIp2);

		txtIp3 = new JTextField();
		txtIp3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		txtIp3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				valid(txtIp3);
			}
		});
		txtIp3.setColumns(10);
		txtIp3.setBounds(142, 87, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtIp3);

		txtMascara2 = new JTextField();
		txtMascara2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				valid(txtMascara2);
			}
		});
		txtMascara2.setColumns(10);
		txtMascara2.setBounds(76, 150, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtMascara2);

		txtMascara3 = new JTextField();
		txtMascara3.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				valid(txtMascara3);
			}
		});
		txtMascara3.setColumns(10);
		txtMascara3.setBounds(142, 150, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtMascara3);

		txtSr = new JTextField();
		txtSr.setColumns(10);
		txtSr.setBounds(104, 215, 56, 36);
		txtSr.setEditable(false);
		frmCalculadoraDeSubredes.getContentPane().add(txtSr);

		txtHosts = new JTextField();
		txtHosts.setColumns(10);
		txtHosts.setBounds(170, 215, 56, 36);
		txtHosts.setEditable(false);
		frmCalculadoraDeSubredes.getContentPane().add(txtHosts);

		JLabel lblNewLabel_1 = new JLabel("IP");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 71, 188, 14);
		frmCalculadoraDeSubredes.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("M\u00E1scara N.D.P");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 134, 188, 14);
		frmCalculadoraDeSubredes.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("CIDR");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(38, 197, 56, 14);
		frmCalculadoraDeSubredes.getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("S.R.");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(104, 197, 56, 14);
		frmCalculadoraDeSubredes.getContentPane().add(lblNewLabel_1_2_1);

		JLabel lblNewLabel_1_2_1_1 = new JLabel("Hosts");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1_1.setBounds(170, 197, 56, 14);
		frmCalculadoraDeSubredes.getContentPane().add(lblNewLabel_1_2_1_1);

		btnCalcular = new JButton("Calcular");
		btnCalcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				calculaCampos();
			}
		});
		btnCalcular.setBounds(142, 262, 89, 36);
		frmCalculadoraDeSubredes.getContentPane().add(btnCalcular);

		txtIp4 = new JTextField();
		txtIp4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					verificaClasse();
				}
			}
		});
		txtIp4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				valid(txtIp4);
			}
		});
		txtIp4.setColumns(10);
		txtIp4.setBounds(208, 87, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtIp4);

		txtMascara4 = new JTextField();
		txtMascara4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (verificaMascara()) {
						calculaCampos();
					}
				}
			}
		});
		txtMascara4.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				valid(txtMascara4);
			}
		});
		txtMascara4.setColumns(10);
		txtMascara4.setBounds(208, 150, 56, 36);
		frmCalculadoraDeSubredes.getContentPane().add(txtMascara4);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearFields();
			}
		});
		btnLimpar.setBounds(48, 262, 89, 36);
		frmCalculadoraDeSubredes.getContentPane().add(btnLimpar);

		nextField();
	}

	public boolean verificaMascara() {
		int m1 = Integer.parseInt(txtMascara1.getText());
		int m2 = Integer.parseInt(txtMascara2.getText());
		int m3 = Integer.parseInt(txtMascara3.getText());
		int m4 = Integer.parseInt(txtMascara4.getText());

		String binaryM1 = String.format("%8s", Integer.toBinaryString(m1)).replace(' ', '0');
		String binaryM2 = String.format("%8s", Integer.toBinaryString(m2)).replace(' ', '0');
		String binaryM3 = String.format("%8s", Integer.toBinaryString(m3)).replace(' ', '0');
		String binaryM4 = String.format("%8s", Integer.toBinaryString(m4)).replace(' ', '0');

		String maskBinary = binaryM1 + binaryM2 + binaryM3 + binaryM4;
		boolean onesFound = false;

		//verifico se nao tem 0 no meio de 1
		for (char bit : maskBinary.toCharArray()) {
			if (bit == '1') {
				if (onesFound) {
					return false;
				}
			} else if (bit == '0') {
				onesFound = true;
			}
		}

		return true;
	}

	public void verificaClasse() {
		String classe = "";
		int ip1 = Integer.parseInt(txtIp1.getText());
		if (ip1 >= 1 && ip1 <= 126) {
			classe = "A";
		} else if (ip1 >= 128 && ip1 <= 191) {
			classe = "B";
		} else if (ip1 >= 192 && ip1 <= 223) {
			classe = "C";
		}

		if (classe.equals("A")) {
			txtMascara1.setText("255");
			txtMascara1.setEditable(false);
			txtMascara2.setText("0");
			txtMascara2.selectAll();
			txtMascara2.requestFocus();
			txtMascara3.setText("0");
			txtMascara4.setText("0");
			this.classe = "A";
			calculaCampos();
		} else if (classe.equals("B")) {
			txtMascara1.setText("255");
			txtMascara1.setEditable(false);
			txtMascara2.setText("255");
			txtMascara2.setEditable(false);
			txtMascara3.setText("0");
			txtMascara3.selectAll();
			txtMascara3.requestFocus();
			txtMascara4.setText("0");
			this.classe = "B";
			calculaCampos();
		} else if (classe.equals("C")) {
			txtMascara1.setText("255");
			txtMascara1.setEditable(false);
			txtMascara2.setText("255");
			txtMascara2.setEditable(false);
			txtMascara3.setText("255");
			txtMascara3.setEditable(false);
			txtMascara4.setText("0");
			txtMascara4.selectAll();
			txtMascara4.requestFocus();
			this.classe = "C";
			calculaCampos();
		} else {
			JOptionPane.showMessageDialog(Calculadora.this, "Classe Inválida ou sem Suporte.");
		}
	}

	public void valid(JTextField txt) {
		try {
			int ip = Integer.parseInt(txt.getText());
			if (ip < min || ip > max || txtIp1.getText().equals("127")) {
				messageInvalid();
				txt.requestFocus();
				txt.selectAll();
			}
		} catch (Exception ex) {
			if (!txt.getText().isEmpty()) {
				messageInvalid();
				txt.requestFocus();
				txt.selectAll();
			}
		}
	}

	public void messageInvalid() {
		JOptionPane.showMessageDialog(Calculadora.this, "Número Inválido.");
	}

	private void nextField() {
		JComponent[] campos = { txtIp1, txtIp2, txtIp3, txtIp4, txtMascara1, txtMascara2, txtMascara3, txtMascara4,
				txtCidr, txtSr, txtHosts, btnCalcular };

		for (int i = 0; i < campos.length - 1; i++) {
			JComponent campoAtual = campos[i];
			JComponent proximoCampo = campos[i + 1];

			campoAtual.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						proximoCampo.requestFocus();
					}
				}
			});
		}
	}

	public void calculaCampos() {
		int octeto1 = Integer.parseInt(txtMascara1.getText());
		int octeto2 = Integer.parseInt(txtMascara2.getText());
		int octeto3 = Integer.parseInt(txtMascara3.getText());
		int octeto4 = Integer.parseInt(txtMascara4.getText());

		// Calcula a máscara de sub-rede combinando os valores dos octetos
		int mascara = (octeto1 << 24) + (octeto2 << 16) + (octeto3 << 8) + octeto4;

		int cidr = 0;

		// Verifica cada bit na máscara de sub-rede
		for (int i = 31; i >= 0; i--) {
		    int bit = (mascara >> i) & 1;

		    // Se o bit for 1, incrementa o contador de CIDR
		    if (bit == 1) {
		        cidr++;
		    } else {
		        // Se encontrar um bit 0, significa que a parte da rede terminou, então para o loop
		        break;
		    }
		}

		txtCidr.setText(String.valueOf(cidr));

		List<Integer> oct = new ArrayList<>();

		for (int i = 7; i >= 0; i--) {
		    int value = (int) Math.pow(2, i);
		    oct.add(value);
		}

		int num = 0;
		if (this.classe.equals("A")) {
			num = octeto2;
		} else if (this.classe.equals("B")) {
			num = octeto3;
		} else if (this.classe.equals("C")) {
			num = octeto4;
		}

		//vai somando o comparator com o numero, vai somando 128, 64, 32, etc ate dar o numero
		int comparator = 0;
		int cont = 0;
		for (int i = 0; i < oct.size(); i++) {
			if (comparator < num) {
				comparator += oct.get(i);
				//vai contando para depois fazer o calculo 2 elevado a cont
				cont++;
			} else {
				break;
			}
		}
		
		//subrede
		int subredesPossiveis = (int) Math.pow(2, cont);
		txtSr.setText(String.valueOf(subredesPossiveis));

		//cidr especifica o tamanho da rede, ex: /24 significa que os 24 primeiros bits (1) sao pra rede
		//como endereço ip tem 32 bits no total faz 32 - qtd de bits pra rede, o que resulta na qtd de bits q pode ser utilizado pra host
		//ai faz 2 elevado a qtd de bits de host e subtrai broadcast e end. de rede
		int hostsPossiveis = (int) Math.pow(2, 32 - cidr) - 2;
		txtHosts.setText(String.valueOf(hostsPossiveis));
	}

	public void clearFields() {
		JTextField[] campos = { txtIp1, txtIp2, txtIp3, txtIp4, txtMascara1, txtMascara2, txtMascara3, txtMascara4,
				txtCidr, txtSr, txtHosts };

		for (int i = 0; i < campos.length; i++) {
			campos[i].setEditable(true);
			campos[i].setText("");
		}
	}
}
