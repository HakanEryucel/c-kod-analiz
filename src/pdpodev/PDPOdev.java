package pdpodev;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class PDPOdev 
{
    static int operatorSay(File dosya)throws IOException//Operatorleri sayıyor.
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
            if(i!=satir.length()-1)
            {
                if(satir.charAt(i)=='/'&&satir.charAt(i+1)=='/')//Yorum satiri geldiyse siradaki satira geçmesini sağlıyor.
                {
                    break;
                }
                if(satir.charAt(i)=='/'&&satir.charAt(i+1)=='*')// Çoklu  yorum açıldıysa kapatılana kadar işlem yapmıyor.
                {
                    cokluYorum=true;
                }
                if(satir.charAt(i)=='*'&&satir.charAt(i+1)=='/')//Çoklu yorum bittiğinde operatorleri saymaya devam ediyor.
                {
                    cokluYorum=false;
                }
                if(satir.charAt(i)=='"'||satir.charAt(i)=='\'')//Tirnak açıldıysa operator saymayı durduruyor. 
                {
                    tirnak = !tirnak;
                }
                if(!tirnak&&!cokluYorum)//Koşullar sağlandıysa operator sayıyor.
                {
                    if((satir.charAt(i)=='+'&&satir.charAt(i+1)!='+')||(satir.charAt(i)=='-'&&satir.charAt(i+1)!='-')||(satir.charAt(i)=='/'&&satir.charAt(i+1)!='*'&&(i!=0&&satir.charAt(i-1)!='*'))||(satir.charAt(i)=='*'&&i!=0&&satir.charAt(i-1)!='/'&&satir.charAt(i+1)!='/')||(satir.charAt(i)=='&'&&satir.charAt(i+1)!='&')||(satir.charAt(i)=='='&&satir.charAt(i+1)!='=')||(satir.charAt(i)=='<'&&satir.charAt(i+1)!='=')||(satir.charAt(i)=='>'&&satir.charAt(i+1)!='=')||(satir.charAt(i)=='|'&&satir.charAt(i+1)=='|')) 
                    {// +, -, /, *, &, ++, --, +=, -=, /=, *=, =, ==, <, >, <=, >=, !=.  &&, || 
                        sayi++;
                    }
                }
            }
        }
        satir=reader.readLine();
        }
        return sayi;
    }
    static String[] diziDonustur(File dosya)throws IOException//SablonDonustur fonksiyonu sadece fonksiyonların adı ve parametreleri bulunan string bir dizi döndürüyor.
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
            if(i!=satir.length()-1)// Eğer satırın son karakteri değil ise bu kısımdaki işlemler yapılıyor. Sonraki karaktere ulaşılabiliyor.
            {
                if(satir.charAt(i)=='{')//Açılan her süsşü parantez için suslu değişkenini bir arttırıyor.Süslü parantezlerin içini aktarmayacagımız için döngüden çıkıyor.
                {
                    suslu++;
                    if(suslu==1)
                    {
                        break;
                    }
                }
                if(satir.charAt(i)=='}')//Süslü parantez kapatıldığında süslü değişkenini bir azaltıyor.
                {
                    suslu--;
                }
                if((satir.charAt(i)=='/'&&satir.charAt(i+1)=='/')||suslu>0||satir.charAt(i)=='#')// Yorum satırı veya önişlemci komutu var ise diziye aktarmayacağımız için döngüden çıkıyor.
                {
                    break;
                }
                
                if(satir.charAt(i)=='/'&&satir.charAt(i+1)=='*')//Çoklu yorum açıldığında kapatılana kadar işlemleri durduruyor.
                {
                    cokluYorum=true;
                }
                if(satir.charAt(i)=='*'&&satir.charAt(i+1)=='/')//Süslü parantez kapatıldığında işlemlerin devam etmesini sağlıyor.
                {
                    cokluYorum=false;
                }
                if(satir.charAt(i)=='"')//Tırnak açıldığında kapatılana kadar işlemleri durduruyor.
                {
                    tirnak = !tirnak;
                }
                if(!tirnak&&!cokluYorum)//Koşullar sağlanıyor ise işlemler yapılıyor.
                {
                    //Eğer gelen karakter önemsiz bir karakter değil ise metin değişkenimize ekliyor.
                    if(satir.charAt(i)!='\n'&&satir.charAt(i)!='"'&&satir.charAt(i)!=' '&&satir.charAt(i)!='\r'&&satir.charAt(i)!='\t'&&satir.charAt(i)!='(')
                    {
                        metin+=satir.charAt(i);
                    }
                    //Metin değişkeni içinde arka arkaya boşluk olmaması için ve tek satır halinde olması için birden fazla boşlukları ve satır sonu karakterini siliyor. Yerine boşluk yazıyor.
                    else if(satir.charAt(i)=='\n'||satir.charAt(i)=='\r'||satir.charAt(i)=='\t'||satir.charAt(i)==' ')
                    {
                        if(!metin.endsWith(" "))
                        {
                            metin+=' ';
                        }
                    }
                    else if(satir.charAt(i)=='(')//Parantezden önce boşluk olmaması için parantez geldiğinde metin stringinin sonundaki boşlukları siliyor.
                    {
                        metin=metin.trim();
                        metin+='(';
                    }
                }
            }
            else// Yukarıdaki işlemlerin aynısı yapılıyor fakat satırın karakterine gelindiği için bir sonraki karaktere erişmeye çalışması engelleniyor.
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
                if(suslu>0||satir.charAt(i)=='#')
                {
                    break;
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
        }
        satir=reader.readLine();
        }
        String [] fonksiyonDizi=metin.split("}");//Metinimizin içindeki süslü parantezlere göre metini bölüyoruz.Süslü parantezden öncesi fonksiyon isimlerini ve parametrelerini barındıran kısım oluyor.
        return fonksiyonDizi;
    }
    static int fonksiyonSay(File dosya)throws IOException//Sablon dönüştür sadece fonksiyon adlarından oluşan bir dizi döndürüyor, bu dizinin uzunluğu fonksiyon sayısını veriyor.
    {
        String []fonksiyonDizi=diziDonustur(dosya);
        return fonksiyonDizi.length;
    }
    static int parametreSay(File dosya)throws IOException//SablonDonustur den dönen dizideki her string elemanın parantezler arasındaki paremetre sayısını buluyor.
    {
        int sayi=0;
        String[] fonksiyonDizi=diziDonustur(dosya);
        for (String tmp : fonksiyonDizi) {
            boolean parantez=false;
            for (int j = 0; j < tmp.length(); j++) {
                if (tmp.charAt(j) == '(' && !parantez) {
                    parantez=true;
                    if (tmp.length() - j <= 2) {//Boş ise çıkıyor.
                        break;
                    } else {
                        sayi++;//Boş değilse kesinlikle en az bir tane olduğu için bir arttırıyor ve sonra her virgül sayısı kadar arttıracak.
                    }
                }
                if (parantez && tmp.charAt(j) == ',') {//Virgül sayisi kadar arttırıyor.
                    sayi++;
                }
            }
        }
        return sayi;
    }
    static void yazdir(File dosya)throws IOException //sablonDonustur den gelen dizinin her string elemanını fonksiyon adi ve paremetrelerine bölüp yazdırıyor.
    {
        System.out.println("Fonksiyon İsimleri:");
        String[] fonksiyonDizi=diziDonustur(dosya);
        for(String tmp : fonksiyonDizi)
        {
            int boslukIndex=0,ilkParantezIndex=0,ikinciParantezIndex=0;
            for(int i=0;i<tmp.length();i++)
            {
                if(tmp.charAt(i)==' '&&ilkParantezIndex==0)//Parantezlerden önceki son boşluğun indexini buluyor. Bu indeksten parantezlere kadar olan kısım fonksiyon adı olmuş oluyor.
                {
                    boslukIndex=i;
                }
                if(tmp.charAt(i)=='(')//Parantezlerin indexlerini bulup arasındakilerden parametreleri buluyor.
                {
                    ilkParantezIndex=i;
                }
                if(tmp.charAt(i)==')')
                {
                    ikinciParantezIndex=i;
                    break;
                }
            }
            System.out.print(tmp.substring(boslukIndex+1, ilkParantezIndex).trim()+" - Parametreler: ");//Boşluk index ve parantez arasındakini yazdiriyor.Fonksiyon adi yazilmiş oluyor.
            String[] parametreDizi=tmp.substring(ilkParantezIndex+1,ikinciParantezIndex).split(",");//İki parantez arasını virgüllere göre ayırıp diziye atıyor.
            for(String tmp2:parametreDizi)
            {
                String[] parametre=tmp2.split(" ");//Parametre dizisini yazdırıyor.
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
        System.out.println("Toplam Operatör Sayısı: "+operatorSay(dosya));//Operator sayısını yazdırıyor.
        System.out.println("Toplam Fonksiyon Sayısı: "+fonksiyonSay(dosya));//Fonksiyon sayisini yazdiriyor.
        System.out.println("Toplam Parametre Sayısı: "+parametreSay(dosya));// Parametre sayısını yazdırıyor.
        yazdir(dosya);//Fonksiyonları ve parametreleri yazdırıyor.
    }
}