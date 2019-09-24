import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GirisEkrani extends JFrame {
	

    CalisanIslemleri islem=new CalisanIslemleri();
		
	
	private JPanel contentPane;
	private JTextField yazi_alani;
	private JPasswordField parola_alani;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisEkrani frame = new GirisEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GirisEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 419);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(65, 82, 78, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblParola = new JLabel("Parola:");
		lblParola.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblParola.setBounds(65, 135, 78, 14);
		contentPane.add(lblParola);
		
		yazi_alani = new JTextField();
		yazi_alani.setBounds(179, 81, 128, 20);
		contentPane.add(yazi_alani);
		yazi_alani.setColumns(10);
		
		parola_alani = new JPasswordField();
		parola_alani.setBounds(179, 134, 128, 20);
		contentPane.add(parola_alani);
		
		JLabel yazi_degeri = new JLabel("");
		yazi_degeri.setForeground(Color.RED);
		yazi_degeri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yazi_degeri.setBounds(65, 207, 285, 31);
		contentPane.add(yazi_degeri);
		
		JButton giris_butonu = new JButton("Giri\u015F");
		giris_butonu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				yazi_degeri.setText("");
				String kullanici_adi=yazi_alani.getText();
				String parola=new String(parola_alani.getPassword());
				boolean giris_degeri=islem.girisYap(kullanici_adi, parola);
				
				if(giris_degeri)
				{
					CalisanEkrani ekran=new CalisanEkrani();
					ekran.setVisible(true);
					setVisible(false);
					//System.exit(0);
					
				}
					
				
				
				else
				{
					yazi_degeri.setText("Hata var..");
				}
					
				
			}
		});
		giris_butonu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		giris_butonu.setBounds(134, 279, 134, 42);
		contentPane.add(giris_butonu);
	}
}
