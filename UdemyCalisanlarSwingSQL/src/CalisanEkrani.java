import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CalisanEkrani extends JFrame {

	DefaultTableModel model=null;
	CalisanIslemleri islem=new CalisanIslemleri();
	private JPanel contentPane;
	private JTable calisanTablosu;
	private JScrollPane scrollPane;
	private JTextField yazi_alani;
    public void dinamikAra(String ara)
    {
    	TableRowSorter<DefaultTableModel> tr=new TableRowSorter<DefaultTableModel>(model);
    	calisanTablosu.setRowSorter(tr);
    	tr.setRowFilter(RowFilter.regexFilter(ara));
    	
    	
    }
	public void calisanGoruntule()
	{
		
		model.setRowCount(0);//hata çýkarabilir diye her seferinde tablo boþalt.
		ArrayList<Calisan> calisan_listesi=new ArrayList<Calisan>();
		
		calisan_listesi=islem.calisanGetir();
		
		if(calisan_listesi !=null)
		{
			
		   for (Calisan kisi:calisan_listesi)
		   {
			   Object [] eklenecek= {kisi.getId(),kisi.getAd(),kisi.getSoyad(),kisi.getDepartman(),kisi.getMaas()};
			   model.addRow(eklenecek);
			   
		   }
		}
		else
		{
			System.out.println("hata varrr....");
		}
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalisanEkrani frame = new CalisanEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public CalisanEkrani() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 37, 397, 305);
		contentPane.add(scrollPane);
		
		calisanTablosu = new JTable();
		scrollPane.setViewportView(calisanTablosu);
		calisanTablosu.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0130d", "Ad", "Soyad", "Departman", "Maa\u015F"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		calisanTablosu.getColumnModel().getColumn(0).setResizable(false);
		calisanTablosu.getColumnModel().getColumn(1).setResizable(false);
		calisanTablosu.getColumnModel().getColumn(2).setResizable(false);
		calisanTablosu.getColumnModel().getColumn(3).setResizable(false);
		calisanTablosu.getColumnModel().getColumn(4).setResizable(false);
		calisanTablosu.setVerifyInputWhenFocusTarget(false);
		calisanTablosu.setRowSelectionAllowed(false);
		calisanTablosu.setFocusable(false);
		
		yazi_alani = new JTextField();
		yazi_alani.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				String ara=yazi_alani.getText();
				dinamikAra(ara);
				
			}
		});
		yazi_alani.setBounds(27, 6, 397, 20);
		contentPane.add(yazi_alani);
		yazi_alani.setColumns(10);
		
		
		model=(DefaultTableModel) calisanTablosu.getModel();//Sürekli oluþturmak zorunda kalmayalým diye.
		calisanGoruntule();
	}
}
