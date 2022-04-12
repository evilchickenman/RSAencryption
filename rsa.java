import java.util.Scanner;
import java.io.*;
import java.math.*;

public class RSA {
	
    private static final BigInteger [] publicKey = new BigInteger [2];
    private static final BigInteger [] privateKey = new BigInteger [2];
    private static BigInteger d;
	
	
	public static void main(String[] args) throws FileNotFoundException {

		File in = new File("input.txt");
        try (Scanner s = new Scanner(in)) {
			String c = s.nextLine(); 
			String a = s.nextLine(); 
			String b = s.nextLine(); 
			String message = s.nextLine(); 
			String cipher = s.nextLine();
			
			BigInteger pInt = new BigInteger(c, 16);    
			BigInteger qInt = new BigInteger(a, 16);       
			BigInteger eInt = new BigInteger(b, 16);       
			BigInteger mInt = new BigInteger(message, 16);     
			BigInteger cInt = new BigInteger(cipher, 16);
			
			keyGen(pInt, qInt, eInt);  
			
			try (PrintStream Out = new PrintStream("output.txt")) {
				Out.println(d.toString(16));                    
				Out.println(encryption(mInt).toString(16));    
				Out.println(decryption(cInt).toString(16));
			}
		}
        
    }
    public static void keyGen(BigInteger p, BigInteger q, BigInteger e)
    {
        BigInteger n = p.multiply(q);
        BigInteger x = new BigInteger("1");
        BigInteger phi = (p.subtract(x)).multiply(q.subtract(x));
        d = e.modInverse(phi);
        
        publicKey[0] = e;
        publicKey[1] = n;
        
        privateKey[0] = d;
        privateKey[1] = n;
        
    }
    public static BigInteger encryption (BigInteger message)
    {
        BigInteger C = message.modPow(publicKey[0],publicKey[1]);
        return C;
    }
     public static BigInteger decryption(BigInteger cipher)
    {
        BigInteger M = cipher.modPow(privateKey[0], privateKey[1]);
        return M;
    }
     


	}
