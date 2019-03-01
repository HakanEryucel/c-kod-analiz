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
        for(int i=0;i<satir.length()-1;i++)
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
                if((satir.charAt(i)=='+'&&satir.charAt(i+1)!='+')||(satir.charAt(i)=='-'&&satir.charAt(i+1)!='-')||(satir.charAt(i)=='/'&&satir.charAt(i+1)!='*'&&satir.charAt(i-1)!='*')||(satir.charAt(i)=='*'&&satir.charAt(i-1)!='/'&&satir.charAt(i+1)!='/')||(satir.charAt(i)=='&'&&satir.charAt(i+1)!='&')||(satir.charAt(i)=='='&&satir.charAt(i+1)!='=')||(satir.charAt(i)=='<'&&satir.charAt(i+1)!='=')||(satir.charAt(i)=='>'&&satir.charAt(i+1)!='=')||(satir.charAt(i)=='|'&&satir.charAt(i+1)=='|')) 
                {// +, -, /, *, &, ++, --, +=, -=, /=, *=, =, ==, <, >, <=, >=, !=.  &&, || 
                    sayi++;
                }
            }
        }
        satir=reader.readLine();
        }
        return sayi;
    }
    static String[] sablonDonustur(File dosya)throws IOException
    {
        int suslu=0;
        boolean tirnak=false;
        boolean cokluYorum=false;
        BufferedReader reader = new BufferedReader(new FileReader(dosya));
        String satir,metin="";
        satir=reader.readLine();
        while(satir!=null)
        {
        for(int i=0;i<satir.length();i++)
        {
            if(satir.charAt(i)=='{')
            {
                suslu++;
                if(suslu==1)
                {
                    //metin+='{';
                    break;
                }
            }
            if(satir.charAt(i)=='}')
            {
                suslu--;
            }
            if((satir.charAt(i)=='/'&&satir.charAt(i+1)=='/')||suslu>0||satir.charAt(i)=='#')
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
                if(satir.charAt(i)!='\n'&&satir.charAt(i)!='"'&&satir.charAt(i)!=' '&&satir.charAt(i)!='\r'&&satir.charAt(i)!='\t'&&satir.charAt(i)!='(')
                {
                    metin+=satir.charAt(i);
                }
                else if(satir.charAt(i)=='\n'||satir.charAt(i)=='\r'||satir.charAt(i)=='\t'||satir.charAt(i)==' ')
                {
                    if(!metin.endsWith(" "))
                    {
                        metin+=' ';
                    }
                }
                else if(satir.charAt(i)=='(')
                {
                    metin=metin.trim();
                    metin+='(';
                }
            }
        }
        satir=reader.readLine();
        }
        String [] fonksiyonDizi=metin.split("}");
        return fonksiyonDizi;
    }
    static int fonksiyonSay(File dosya)throws IOException
    {
        String []fonksiyonDizi=sablonDonustur(dosya);
        //int ayni=0;
       // for(int i=0;i<fonksiyonDizi.length;i++)
       // {
           // for(int j=0;j<fonksiyonDizi.length;j++)
           // {
             //   if(i!=j&&fonksiyonDizi[i].equals(fonksiyonDizi[j]))
              //  {
              //      ayni++;
               // }
           // }
        //}
        return fonksiyonDizi.length;//-ayni;
    }
    static int parametreSay(File dosya)throws IOException
    {
        int sayi=0;
        String[] fonksiyonDizi=sablonDonustur(dosya);
        for (String tmp : fonksiyonDizi) {
            boolean parantez=false;
            for (int j = 0; j < tmp.length(); j++) {
                if (tmp.charAt(j) == '(' && !parantez) {
                    parantez=true;
                    if (tmp.length() - j <= 2) {
                        break;
                    } else {
                        sayi++;
                    }
                }
                if (parantez && tmp.charAt(j) == ',') {
                    sayi++;
                }
            }
        }
        return sayi;
    }
    static void fonksiyonYazdir(File dosya)throws IOException
    {
        System.out.println("Fonksiyon İsimleri:");
        String[] fonksiyonDizi=sablonDonustur(dosya);
        for(String tmp : fonksiyonDizi)
        {
            int boslukIndex=0,ilkParantezIndex=0,ikinciParantezIndex=0;
            for(int i=0;i<tmp.length();i++)
            {
                if(tmp.charAt(i)==' '&&ilkParantezIndex==0)
                {
                    boslukIndex=i;
                }
                if(tmp.charAt(i)=='(')
                {
                    ilkParantezIndex=i;
                }
                if(tmp.charAt(i)==')')
                {
                    ikinciParantezIndex=i;
                    break;
                }
            }
            System.out.print(tmp.substring(boslukIndex+1, ilkParantezIndex).trim()+" - Parametreler: ");
            String[] parametreDizi=tmp.substring(ilkParantezIndex+1,ikinciParantezIndex).split(",");
            for(String tmp2:parametreDizi)
            {
                String[] parametre=tmp2.split(" ");
                System.out.print(parametre[parametre.length-1]);
                if(!tmp2.equals(parametreDizi[parametreDizi.length-1]))
                {
                    System.out.print(',');
                }
            }
            System.out.print("\n");
        }
    }
    public static void main(String[] args) throws IOException 
    {
        File dosya = new File("Program.c");
        System.out.println("Toplam Operatör Sayısı: "+operatorSay(dosya));
        System.out.println("Toplam Fonksiyon Sayısı: "+fonksiyonSay(dosya));
        System.out.println("Toplam Parametre Sayısı: "+parametreSay(dosya));
        fonksiyonYazdir(dosya);
    }
}