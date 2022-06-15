package com.snhu.sslserver;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@RestController
class ServerController{
	public static String encryptThisString(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-384");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
  
    @RequestMapping("/hash")
    public String myHash(){
    	String data = "Hello Zachary Meisner!";
       String hash = encryptThisString(data);
        return "<p>data:"+data+"\n\nName of Cipher Algorithm Used : SHA-384 CheckSum Value:"+hash;
    }
}
