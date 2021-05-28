package AnalizadorLexico.InterfazAnaLex;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Creación extends JFrame {

	private JPanel contentPane;
	private JTextField tfEstados;
	private JTextField tfAlfabeto;
	private int Estados;
	private int Alfabeto;

	/**
	 * Create the frame.
	 */
	public Creación(AnalizadorLexicoInterfaz ali) {
		
		setBackground(Color.WHITE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 428, 376);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("¿Cuántos estados componen su autómata?");
		lblNewLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblNewLabel.setBounds(21, 11, 424, 63);
		contentPane.add(lblNewLabel);
		
		tfEstados = new JTextField();
		tfEstados.setFont(new Font("Consolas", Font.PLAIN, 15));
		tfEstados.setBounds(21, 74, 374, 38);
		contentPane.add(tfEstados);
		tfEstados.setColumns(10);
		
		JLabel lblIndiqueQue = new JLabel("¿Cuántos símbolos componen su alfabeto?:");
		lblIndiqueQue.setFont(new Font("Consolas", Font.PLAIN, 18));
		lblIndiqueQue.setBounds(21, 134, 424, 63);
		contentPane.add(lblIndiqueQue);
		
		tfAlfabeto = new JTextField();
		tfAlfabeto.setFont(new Font("Consolas", Font.PLAIN, 15));
		tfAlfabeto.setBounds(21, 199, 374, 38);
		contentPane.add(tfAlfabeto);
		tfAlfabeto.setColumns(10);
		
		JButton btnNewButton = new JButton("Siguiente");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Consolas", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(102, 153, 204));
		btnNewButton.setBounds(100, 270, 214, 45);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tfAlfabeto.getText().isEmpty() && tfEstados.getText().isEmpty()) {
					showMessageDialog(null, "Rellene los campos");
				}else {
					try {
						
						Estados = Integer.parseInt(tfEstados.getText());
						
						Alfabeto = Integer.parseInt(tfAlfabeto.getText());
						
						Analizador an = new Analizador(Estados, Alfabeto, ali, true);
						dispose();
						an.setVisible(true);
					}catch(NumberFormatException ex) {
						showMessageDialog(null, "El número de estados debe ser un entero");
					}
				}
			}
		});
		contentPane.add(btnNewButton);
		
		
	}
	
	
	
	
	
}
