import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CumaliAtalan
 */

public class baglanti {
    
    private String kullanici_adi = "root";

    private String parola = "";

    private String db_ismi = "oylamasistemi";
    
    private String host = "localhost";
    
    private int port = 3306;
    
    private Connection con = null;
    
    private Statement statement =null;
    
    //public void calisanSil()
    
    
    public baglanti() {

        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi + "?useUnicode = true & characterEncoding = utf8";
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex){
            
            System.out.println("Driver bulunamadı");            
        }
        
        try {
            con = DriverManager.getConnection(url, kullanici_adi, parola);
            System.out.println("Bağlantı Başarılı");
        
        } catch (SQLException ex) {
            System.out.println("Bağlantı başarısız");
        }
        
    }
    
    public void kullaniciEkle() {
        
        try {
            statement = con.createStatement();
            String kimlikNo = "12345";
            
            String adSoyad = "Semih Uğurlu";

            String parola= "1234";

            String Sorgu = "insert into kullanici (kimlikNo, adSoyad, parola)VALUES (" + "'" + kimlikNo + "'," + "'" + adSoyad + "'," + "'" + parola + "')";

            statement.executeUpdate(Sorgu);
            
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
    public void oyGuncelle() {
        
        try {
            statement = con.createStatement();
        
            String sorgu = "update kullanici set  OyVerilen = 'mustafa murat' where AdSoyad = 'İsim Soyisim'";
            
            statement.executeUpdate(sorgu);
            
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void kullanicilariGetir(){
        String sorgu = "Select * from kullanici";
        
        try {
            statement = con.createStatement();
            
            ResultSet rs =statement.executeQuery(sorgu);
            
            while (rs.next()) {
                
                String kimlikNo = rs.getString("kimlikNo");
                String adSoyad = rs.getString("adSoyad");
                String parola = rs.getString("parola");
                String oyVerilen = rs.getString("OyVerilen");
                
                System.out.println("Kimlik No : " + kimlikNo + " Ad Soyad : " + adSoyad + " parola : " + parola + " Oy Verilen : " + oyVerilen);                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
/*    public void commitVerollback() {
        Scanner scanner = new Scanner(System.in);
        
        try {
            con.setAutoCommit(false);
            String sorgu1 = "Delete from calisanlar where id 2";
            String sorgu2 = "Update calisanlar set email = 'asdf' where id = 3";
           
            Statement statement =con.createStatement();
            
            statement.executeUpdate(sorgu1);
            statement.executeUpdate(sorgu2);
            
            System.out.println("dsafs");
            
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public static void main(String[] args){
        baglanti baglanti =new baglanti();

        //baglanti.kullaniciEkle();

        //baglanti.oyGuncelle();
        
        baglanti.kullanicilariGetir();
        
    }
}