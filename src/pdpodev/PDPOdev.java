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
        boolean cokluYorum=false;
        BufferedReader reader = new BufferedReader(new FileReader(dosya));
        String satir;
        satir=reader.readLine();
        while(satir!=null)
        {
        for(int i=0;i<satir.length();i++)
        {
            if(satir.charAt(i)=='/'&&satir.charAt(i+1)=='/')
            {
                break;
            }
            if(satir.charAt(i)=='/'&&satir.charAt(i+1)=='*')
            {
                cokluYorum=true;
            }
            if(satir.charAt(i)=='*'&&satir.charAt(i+1)=='/')
            {
                cokluYorum=false;
            }
            if(satir.charAt(i)=='"')
            {
                tirnak = !tirnak;
            }
            if(!tirnak&&!cokluYorum)
            {
                if((satir.charAt(i)=='+'&&satir.charAt(i+1)!='+')||(satir.charAt(i)=='-'&&satir.charAt(i+1)!='-')||(satir.charAt(i)=='/'&&satir.charAt(i+1)!='/'&&satir.charAt(i-1)!='/'&&satir.charAt(i+1)!='*')||(satir.charAt(i)=='*'&&satir.charAt(i-1)!='/')||(satir.charAt(i)=='&'&&satir.charAt(i+1)!='&')||(satir.charAt(i)=='='&&satir.charAt(i+1)!='=')||satir.charAt(i)=='<'||satir.charAt(i)=='>'||(satir.charAt(i)=='!'&&satir.charAt(i+1)=='=')||(satir.charAt(i)=='|'&&satir.charAt(i+1)=='|')) 
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
        boolean anahtar=false,parantez=false,esittir=false;
        int tmp=0;
        if(satir.contains("void")||satir.contains("char")||satir.contains("int")||satir.contains("short")||satir.contains("long")||satir.contains("float")||satir.contains("double"))
        {
            anahtar=true;
        }
        if(satir.contains("(")&&satir.contains(")"))
        {
            parantez=true;
        }
        for(int i=0;i<satir.length();i++)
        {
            if(satir.charAt(i)=='(')
            {
                tmp=i;
                break;
            }
        }
        if(tmp!=0)
        {
            satir=satir.substring(1, tmp-1);
        }
        if(satir.contains("="))
        {
            esittir=true;
        }
        return (anahtar&&parantez)&&(!esittir);
    }
    static int fonksiyonSay(File dosya)throws IOException
    {
        int sayi=0;
        BufferedReader reader = new BufferedReader(new FileReader(dosya));
        String satir=reader.readLine();
        while(satir!=null)
        {
            /*if(satir.contains("void")||satir.contains("return"))
            {
                sayi++;
            }
            */
            if(fonksiyonMu(satir))
            {
                sayi++;
            }
            satir=reader.readLine();
        }
        return sayi;
    }
    static void deneme(File dosya)throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(dosya));
        String metin="";
        int karakter;
        while((karakter= reader.read())!=-1)
        {
            char tmp=(char)karakter;
            if(tmp!='\n'&&tmp!='\r')
            {
                metin+=tmp;
            }
            else
            {
                metin+=' ';
            }
        }
        System.out.println(metin);
    }
    static int parametreSay(File dosya)throws IOException
    {
        int sayi=0;
        BufferedReader reader = new BufferedReader(new FileReader(dosya));
        String satir=reader.readLine();
        while(satir!=null)
        {
            if(satir.contains("void"))
            {
                int ilk=0,son=0;
                for(int i=0;i<satir.length();i++)
                {
                    if(satir.charAt(i)=='(')
                    {
                        ilk=i;
                    }
                    if(satir.charAt(i)==')')
                    {
                        son=i;
                    }
                }
                if(son-ilk!=1)
                {
                    satir=satir.substring(ilk+1, son);
                    int tmp=0;
                    for(int i=0;i<satir.length();i++)
                    {
                        if(satir.charAt(i)==',')
                        {
                            tmp++;
                        }
                    }
                    sayi+=tmp+1;
                }
            }
            satir=reader.readLine();
        }
        return sayi;
    }
    public static void main(String[] args) throws IOException 
    {
        File dosya = new File("Program.c");
        int operatorSayi=operatorSay(dosya);
        /*int fonksiyonSayi=fonksiyonSay(dosya);
        int parametreSayi=parametreSay(dosya);*/
        System.out.println("Toplam Operatör Sayısı: "+operatorSayi);
        deneme(dosya);
       /* System.out.println("Toplam Fonksiyon Sayısı: "+fonksiyonSayi);
        System.out.println("Toplam Parametre Sayısı: "+parametreSayi);*/
    }
}