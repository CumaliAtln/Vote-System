import java.io.*;
import java.util.Scanner;

public class dosya_okuma_yazma {

    public static void main(String[] args) {
        //createFile();
        //readFile();
        //writeFile();

    }

    public static void createFile() { //DOSYA OLUŞTURMA FONKSİYONU
        File file =new File("C:\\files\\kullanicilar.txt");
        try {
            if (file.createNewFile()){
                System.out.println("dosya oluşturuldu");
            }else{
                System.out.println("dosya zaten mevcut");
            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static void getFileInfo() { //DOSYA HAKKINDA BİLGİ EDİNEBİLME FONKSİYONU
        File file =new File("C:\\files\\students.txt");
        if(file.exists()){
            System.out.println("Dosya adı : " + file.getName());
            System.out.println("Dosya yolu : " + file.getAbsolutePath());
            System.out.println("Dosya yazılabilir mi : " + file.canWrite());
            System.out.println("Dosya okunabilir mi : " + file.canRead());
            System.out.println("Dosya boyutu : " + file.length() + " kbyte");
        }
    }

    public static void readFile(){ //DOSYA İÇERİĞİNİ OKUYABİLME FONKSİYONU
        File file =new File("C:\\files\\students.txt");

        try {
            Scanner reader =new Scanner(file);
            while(reader.hasNextLine()){
                String line =reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public static void writeFile(){ //DOSYA İÇERİSİNE YAZABİLME FONKSİYONU
        try {
            BufferedWriter writer =new BufferedWriter(new FileWriter("C:\\files\\students.txt", true));
            writer.newLine();
            writer.write("Yusuf");
            System.out.println("dosyaya yazıldı");
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }
}
