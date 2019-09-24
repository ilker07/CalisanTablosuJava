import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CalisanIslemleri {

	private java.sql.Statement state;
	private Connection con=null;
	
	private PreparedStatement ps=null;
	
	
	public CalisanIslemleri()
	{
      String url="jdbc:mysql://"+Database.host+":"+Database.port+"/"+Database.db_ismi+"?useUnicode=true&characterEncoding=utf8";
		
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Bulunamadý....");
		}
		
		try {
			con= DriverManager.getConnection(url,Database. kullanici_adi, Database.parola);
			System.out.println("Baðlandý...");
		} catch (SQLException e) {

			System.out.println("Baðlantý olmadý...");
		
		}
	}
	public boolean girisYap(String k_adi,String parola)
	{
		
		String sorgu="Select * from adminler where username= ? and password=?";
		
		try {
			ps=con.prepareStatement(sorgu);
			ps.setString(1, k_adi);
			ps.setString(2, parola);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()==false)
				return false;
			else
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;

		}
		
		
		
	}
	
	public ArrayList<Calisan> calisanGetir()
	{
		
		ArrayList<Calisan> cikti=new ArrayList<Calisan>();
		try {
			state=con.createStatement();
			String sorgu="Select * from calisanlar";
			ResultSet rs=state.executeQuery(sorgu);
			
			
			while(rs.next())
			{
				int id=rs.getInt("id");
				String ad=rs.getString("ad");
				String soyad=rs.getString("soyad");
				String departman=rs.getString("departman");
				String maas=rs.getString("maas");

	           cikti.add(new Calisan(id, ad, soyad, departman, maas));
	          
				
			}
			
			 return cikti;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
    
}
