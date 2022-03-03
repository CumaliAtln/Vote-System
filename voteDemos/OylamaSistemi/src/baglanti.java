import java.io.IOException;
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
    
    private String kullanici_adi = "root"; //veritabanımızın adı

    private String parola = ""; //veritabanımızın parolası

    private String db_ismi = "oylamasistemi";
    //veritabanı içerisinde üzerinde işlem yapacağımız db ismi
    
    private String host = "localhost";
    //veritabanımızın host bilgisi
    
    private int port = 3306;
    ////veritabanımızın port bilgisi
    
    private Connection con = null;
    //db'e bağlantı
    
    private Statement statement =null;
    //veritabanımızın bağlantılarda kullanacağı komut
    
    private String kimlikNo;
    private String adSoyad;
    private String pass;
    private String oyVerilen;
    //veritabanı işlemlerinde kullanılacak sütunlar tanımlanmıştır.
    
    
    public baglanti() {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi + "?useUnicode = true & characterEncoding = utf8";
        //veritabanının bilgileri ile bağlantı için url oluşturulur.
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
        } catch(ClassNotFoundException ex){
            
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);   
            
        }
        
        try {
            
            con = DriverManager.getConnection(url, kullanici_adi, parola);
        
        } catch (SQLException ex) {
        }
        
    }
    
    public void kullaniciEkle(String kimlikNo, String adSoyad, String pass) {
        try {
            statement = con.createStatement(); //yeni komut oluşturur.
            this.kimlikNo = kimlikNo;
            this.adSoyad = adSoyad;
            this.pass = pass;
            
            oyVerilen = "";
            String sorgu = "Insert Into kullanici (kimlikNo, adSoyad, parola, oyVerilen)VALUES (" +
            "'" + this.kimlikNo + "'," + "'" + this.adSoyad + "'," + "'" + this.pass + "'," + "'" + oyVerilen + "')";
            //db sorgusu ile dışarıdan gelen bilgileri db'e işler
            statement.execute(sorgu);
            
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean login(String kimlik_no, String pass){
        
        String sorgu = "Select * from kullanici where KimlikNo = " + kimlik_no + " and Parola = " + pass;
        //giriş yaparken kişiye ait bir kayıt olup olmadığını kontrol eder.
        int adet =0;
        
        try {
            statement = con.createStatement();
            
            ResultSet rs =statement.executeQuery(sorgu);
            
            while (rs.next()) {
                //eğer en az bir adet sonuç bulursa kayıt var demektir.
                adet++;
                kimlikNo = rs.getString("kimlikNo");
                parola = rs.getString("parola");
                oyVerilen = rs.getString("OyVerilen");         
                
            }
            if(adet != 0){
                
                return true;
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return false;
    }
    
  
    public void oyGuncelle(String kimlikNo, String oyVerilen) {
        
        try {
            
            statement = con.createStatement();
            this.kimlikNo = kimlikNo;
            this.oyVerilen = oyVerilen;
            
            String sorgu =  "update kullanici set oyVerilen = '" + this.oyVerilen + "'" + "where kimlikNo = '" + this.kimlikNo + "'";   
            //bu sorgu sayesinde bireyler oy kullanabiliyor.
            
            statement.executeUpdate(sorgu);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void kullanicilariGetir() throws IOException{
        
        oylamaDosyaİşlemi oyDosyaİşlemi =new oylamaDosyaİşlemi();
        String sorgu = "Select * from kullanici";
        oyDosyaİşlemi.clearTheFile();
        
        try {
            
            statement = con.createStatement();
            
            ResultSet rs =statement.executeQuery(sorgu);
            
            while (rs.next()) {
                
                kimlikNo = rs.getString("kimlikNo");
                adSoyad = rs.getString("adSoyad");
                parola = rs.getString("parola");
                oyVerilen = rs.getString("OyVerilen");
                
                oyDosyaİşlemi.writeFile(kimlikNo, adSoyad, parola, oyVerilen);
                            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int sayım1(){
        //sayım metodları adayların aldığı oyları saymayı hedefler
        String sorgu = "Select * from kullanici where OyVerilen = 'Aday 1'";
        int adet =0;
        
        try {
            statement = con.createStatement(); 
            ResultSet rs =statement.executeQuery(sorgu);
            
            while (rs.next()) {
                adet++;
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return adet;
    }    
    
    public int sayım2(){
        
        String sorgu = "Select * from kullanici where OyVerilen = 'Aday 2'";
        int adet =0;
        
        try {
            
            statement = con.createStatement(); 
            ResultSet rs =statement.executeQuery(sorgu);
            while (rs.next()) {
                adet++;
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return adet;
    }
    
    public int sayım3(){
        String sorgu = "Select * from kullanici where OyVerilen = 'Aday 3'";
        int adet =0;
        
        try {
            
            statement = con.createStatement(); 
            ResultSet rs =statement.executeQuery(sorgu);
            while (rs.next()) {
                
                adet++;
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return adet;
    }
    
    public int sayım4(){
        
        String sorgu = "Select * from kullanici where OyVerilen = 'Aday 4'";
        int adet =0;
        
        try {
            statement = con.createStatement(); 
            ResultSet rs =statement.executeQuery(sorgu);
            
            while (rs.next()) {
                adet++;
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return adet;
    }
    public static void main(String[] args){
    }

}