import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CumaliAtalan
 */

public class oylamaDosyaİşlemi {
    public static void main(String[] args) {
        
    }
    
    public static void createFile() { //DOSYA OLUŞTURMA FONKSİYONU
        
        File file =new File("C:\\Users\\tr\\Desktop\\kullanicilar.txt");
        //belirtilen adreste bir dosyayı file objesi olarak oluşturur.
        
        try {
            if (file.createNewFile()){
                System.out.println("dosya oluşturuldu");
                //eğer adreste belirtilen isimde bir dosya yoksa oluşturur.
                
            }else{
                System.out.println("dosya zaten mevcut");
                //dosya zaten mevcutsa devam eder.
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static void writeFile(String kimlik_no, String ad_soyad, String parola, String oy_verilen){ 
        //DOSYA İÇERİSİNE YAZABİLME FONKSİYONU       
        try {
            BufferedWriter writer =new BufferedWriter(new FileWriter("C:\\Users\\tr\\Desktop\\kullanicilar.txt", true));
            //write objesi Buffer'a yazmamızı sağlıyor.
            writer.newLine(); //newLine ile yeni satıra geçiyor.            
            writer.write("Kimlik Numarası: " + kimlik_no + " Ad Soyad: " + ad_soyad
                    + " Parola: " + parola + " Verilen oy: " + oy_verilen);
            //writer objesine write metodu ile beraber dosyaya yazma işlemini geröekleştiriyor.
            
            writer.close(); //dosyayı yazdıktan sonra kapatıyor.
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
    
    public static void clearTheFile() throws IOException {
        // clearTheFile() metodunun gerekliliği writeFile metodundan doğmuştur. 
        // sürekli üstüne yazmaktansa listeye sadece yeni üyeleri eklemek hedeflenir.
        FileWriter fwOb = new FileWriter("C:\\Users\\tr\\Desktop\\kullanicilar.txt", false); 
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
}