package Ass2Enc;

public class SDEC {
static String key = "1010000010";
static  String[] keys = generateKeys(key);
    public static void main(String[] args) {
    	
       
        String ciphertext = "";
    	String plainText = "mohammad nader nemer";
    	String res ="";
    	for(int i=0;i<plainText.length();i++) {
    		res+=charToBinary8Bits(plainText.charAt(i));
    		ciphertext+=encrypt(charToBinary8Bits(plainText.charAt(i)),keys);
    	}
    	
    	StringBuilder decypted =new StringBuilder();

    	
    
    	for (int i = 0; i < ciphertext.length(); ) {
    	    String c = ciphertext.substring(i, i + 8);

    	    String decryptedBinary = decrypt(c, keys);
    	    decypted.append(decryptedBinary);
    	    i += 8;
    	}
    	System.out.println(res);
    	System.out.println((decypted));
    	System.out.println(binaryToString(String.valueOf(decypted)));
        
    }
    
    public static String enc(String n)
    {
    	String ciphertext ="";
    	for(int i=0;i<n.length();i++) {
    	
    		ciphertext+=encrypt(charToBinary8Bits(n.charAt(i)),keys);
    	
    	}
    	
    
    	return ciphertext;
    	
    }
    
    public static String dec(String ciphertext)
    {   StringBuilder decypted =new StringBuilder();
	
    	for (int i = 0; i < ciphertext.length(); ) {
    	    String c = ciphertext.substring(i, i + 8);
 
    	 
    	    String decryptedBinary = decrypt(c, keys);
    	    decypted.append(decryptedBinary);
    	    i += 8;
    	}
    
    	return binaryToString(String.valueOf(decypted));
    	
    }

    
    public static String charToBinary8Bits(char character) {
    	int asciiValue = (int) character;
        String binaryString = String.format("%8s", Integer.toBinaryString(asciiValue)).replace(' ', '0');
        return binaryString;
    }
    
    public static String binaryToString(String binary) {
    	if (binary.length() % 8 != 0) {
            throw new IllegalArgumentException("Invalid binary string length. It must be a multiple of 8.");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, i + 8);
            int charCode = Integer.parseInt(byteString, 2);
            result.append((char) charCode);
        }
        return result.toString();
    }
    public static String[] generateKeys(String key) {
        byte[] p10 = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };
        byte[] p8 = { 6, 3, 7, 4, 8, 5, 10, 9 };

        String p10key = "";
        for (int i = 0; i < p10.length; i++) {
            p10key += key.charAt(p10[i] - 1);
        }

        String sl1 = p10key.substring(0, 5);
        String sl2 = p10key.substring(5, 10);

        String shiftP10 = sl1.substring(1, 5) + sl1.charAt(0) + sl2.substring(1, 5) + sl2.charAt(0);
        String res = "";
        for (int i = 0; i < p8.length; i++) {
            res += shiftP10.charAt(p8[i] - 1);
        }

        String[] keys = new String[2];
        keys[0] = res;

        shiftP10 = sl1.substring(3, 5) + sl1.charAt(0) + sl1.charAt(1) + sl1.charAt(2) + sl2.substring(3, 5)
                + sl2.charAt(0) + sl2.charAt(1) + sl2.charAt(2);
        res = "";
        for (int i = 0; i < p8.length; i++) {
            res += shiftP10.charAt(p8[i] - 1);
        }
        keys[1] = res;

        return keys;
    }

    public static String encrypt(String plain, String[] keys) {
        byte[] IP = { 2, 6, 3, 1, 4, 8, 5, 7 };
        byte[] IpInv = { 4, 1, 3, 5, 7, 2, 8, 6 };
        byte[] EP = { 4, 1, 2, 3, 2, 3, 4, 1 };
        String[][] S0 = { { "01", "00", "11", "10" }, { "11", "10", "01", "00" }, { "00", "10", "01", "11" },
                { "11", "01", "11", "10" } };

        String[][] S1 = { { "00", "01", "10", "11" }, 
                { "10", "00", "01", "11" }, 
                { "11", "00", "01", "00" }, 
                { "10", "01", "00", "11" }
        };
        byte[] p4 = { 2, 4, 3, 1 };

        String ip = "";
        for (int i = 0; i < IP.length; i++) {
            ip += plain.charAt(IP[i] - 1);
        }

        String L0 = ip.substring(0, 4), R0 = ip.substring(4, 8);

        String ep = "", xor1 = "";
        for (int i = 0; i < EP.length; i++) {
            ep += R0.charAt(EP[i] - 1);
            xor1 += (int) (R0.charAt(EP[i] - 1)) ^ (int) (keys[0].charAt(i));
        }

        String sp0 = xor1.substring(0, 4);
        String sp1 = xor1.substring(4, 8);

        int lefts0 = Integer.parseInt(sp0.charAt(0) + "" + sp0.charAt(3) + "", 2);
        int right0 = Integer.parseInt(String.valueOf(sp0.charAt(1) + "" + sp0.charAt(2) + ""), 2);

        int lefts1 = Integer.parseInt(String.valueOf(sp1.charAt(0) + "" + sp1.charAt(3)), 2);
        int right1 = Integer.parseInt(String.valueOf(sp1.charAt(1) + "" + sp1.charAt(2)), 2);

        String boxres = String.valueOf(S0[lefts0][right0]) + "" + String.valueOf(S1[lefts1][right1]);
        String p4res = "";
        for (int i = 0; i < p4.length; i++) {
            p4res += boxres.charAt(p4[i] - 1);
        }
        String xorres = "";
        for (int i = 0; i < p4.length; i++) {
            xorres += (int) p4res.charAt(i) ^ (int) L0.charAt(i);
        }

        // Swap L1 and R1
        String L1 = R0;
        String R1 = xorres;

        // Second round starts here
        ep = "";
        xor1 = "";
        for (int i = 0; i < EP.length; i++) {
            ep += R1.charAt(EP[i] - 1);
            xor1 += (int) (R1.charAt(EP[i] - 1)) ^ (int) (keys[1].charAt(i));
        }

        sp0 = xor1.substring(0, 4);
        sp1 = xor1.substring(4, 8);

        lefts0 = Integer.parseInt(sp0.charAt(0) + "" + sp0.charAt(3) + "", 2);
        right0 = Integer.parseInt(String.valueOf(sp0.charAt(1) + "" + sp0.charAt(2) + ""), 2);

        lefts1 = Integer.parseInt(String.valueOf(sp1.charAt(0) + "" + sp1.charAt(3)), 2);
        right1 = Integer.parseInt(String.valueOf(sp1.charAt(1) + "" + sp1.charAt(2)), 2);

        boxres = String.valueOf(S0[lefts0][right0]) + "" + String.valueOf(S1[lefts1][right1]);
        p4res = "";
        for (int i = 0; i < p4.length; i++) {
        
        	p4res += boxres.charAt(p4[i] - 1);
        
        }
        xorres = "";
        for (int i = 0; i < p4.length; i++) {
            xorres += (int) p4res.charAt(i) ^ (int) L1.charAt(i);
        }

        // Combine the final result
        String preOutput = xorres + R1;
        String output = "";
        for (int i = 0; i < IpInv.length; i++) {
            output += preOutput.charAt(IpInv[i] - 1);
        }

        return output;
    }

    public static String decrypt(String cipher, String[] keys) {
        String temp = keys[0];
        String []keys2 = new String[2];
        keys2[0] = keys[1];
        keys2[1] = temp;

        return encrypt(cipher, keys2);
    }
}