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
                if((satir.charAt(i)=='+'&&satir.charAt(i+1)!='+')||satir.charAt(i)=='-'||satir.charAt(i)=='/'||satir.charAt(i)=='*'||satir.charAt(i)=='&'||satir.charAt(i)=='='||satir.charAt(i)=='<'||satir.charAt(i)=='>'||(satir.charAt(i)=='!'&&satir.charAt(i+1)=='=')||(satir.charAt(i)=='|'&&satir.charAt(i+1)=='|')) 
                {// +, -, /, *, &, ++, --, +=, -=, /=, *=, =, ==, <, >, <=, >=, !=.  &&, || 
                    sayi++;
                }
            }
        }
        satir=reader.readLine();
        }
        return sayi;
    }
    public static void main(String[] args) throws IOException 
    {
        File dosya = new File("Program.c");
        int operatorSayi;
        operatorSayi=operatorSay(dosya);
        System.out.println(operatorSayi);
    }
}