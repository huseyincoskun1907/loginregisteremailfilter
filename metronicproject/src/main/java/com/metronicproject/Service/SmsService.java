package com.metronicproject.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.springframework.stereotype.Service;



@Service("smsService")
public class SmsService {
	
	 public String smsSenderFunction(String phone) throws Exception {
	        /**
	         * SMS gönderimi için gerekli
	         * değişken tanımları
	         * */

	        // kullanıcı adı
	     
	        // kullanıcı şifresi
	        
	        // mesaj başlığı
	      
	        
	        Random r = new Random();
	        int Low = 100000;
	        int High = 999999;
	        int i = r.nextInt(High-Low) + Low;
	      
	       String tokensms = Integer.toString( i );
	      
	        // gönderilecek mesaj
	        String msg = URLEncoder.encode("Doğrulama Kodu:"+i, StandardCharsets.UTF_8.toString());

//	        // mesajın gönderileceği telefon numaraları
//	        String gsm = "5435011995";

	        // Get ile sorgulanacak Netgsm servisi

	        // URL obje tanımı
	        URL obj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	        // bağlantı türü tanımı
	        con.setRequestMethod("GET");

	        // bağlantı için header tanımı
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");

	        // servise bağlanılıyor ve sonuç alınıyor
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();

	        // sms gönderme sonrası oluşan raporu görüntüleme
	        System.out.println("rapor durum = "+response.toString());
			return tokensms;
	

	        // alınan örnek cevap
	        // 00 363457739
	    }

}
