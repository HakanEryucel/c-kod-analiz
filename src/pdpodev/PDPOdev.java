package pdpodev;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class PDPOdev 
{
    static int operatorSay(File dosya)throws IOException
    {
        int sayi = 0;
        boolean tirnak=false;
        BufferedReader reader = new BufferedReader(new FileReader(dosya));
        String satir;
        satir=reader.readLine();
        while(satir!=null)
        {
        for(int i=0;i<satir.length();i++)
        {
            if(satir.charAt(i)=='"')
            {
                if(tirnak)
                {
                    tirnak=false;
                }
                else
                {
                    tirnak=true;
                }
            }
            if(!tirnak)
            {
                if((satir.charAt(i)=='+'&&satir.charAt(i+1)!='+')||(satir.charAt(i)=='-'&&satir.charAt(i+1)!='-')||satir.charAt(i)=='/'||satir.charAt(i)=='*'||(satir.charAt(i)=='&'&&satir.charAt(i+1)!='&')||(satir.charAt(i)=='='&&satir.charAt(i+1)!='=')||satir.charAt(i)=='<'||satir.charAt(i)=='>'||(satir.charAt(i)=='!'&&satir.charAt(i+1)=='=')||(satir.charAt(i)=='|'&&satir.charAt(i+1)=='|')) 
                {// +, -, /, *, &, ++, --, +=, -=, /=, *=, =, ==, <, >, <=, >=, !=.  &&, || 
                    sayi++;
                }
            }
        }
        satir=reader.readLine();
        }
        return sayi;
    }
    
    static boolean fonksiyonMu(String satir)
    {
    }
    static int fonksiyonSay(File dosya)throws IOException
    {
        int sayi=0;
        
        return sayi;
    }
    static int parametreSay(File dosya)throws IOException
    {
        int sayi=0;
        
        return sayi;
    }
    public static void main(String[] args) throws IOException 
    {
        File dosya = new File("Program.c");
        int operatorSayi=operatorSay(dosya);
        int fonksiyonSayi=fonksiyonSay(dosya);
        int parametreSayi=parametreSay(dosya);
        System.out.println("Toplam Operatör Sayısı: "+operatorSayi);
        System.out.println("Toplam Fonksiyon Sayısı: "+fonksiyonSayi);
        System.out.println("Toplam Parametre Sayısı: "+parametreSayi);
    }
}