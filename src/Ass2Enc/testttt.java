//package Ass2Enc;
//
//public class testttt {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		SDEC s1=new SDEC();
//		String n="mohammad nemer";
//		n=s1.enc(n);
//		
//		System.out.println(n);
//	
//		
//		
//	}
//
//}
//
//package Ass2Enc;
//
//import java.io.File;
//import java.io.IOException;
//
//import javafx.application.Application;
//
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextArea;
//import javafx.scene.effect.Shadow;
//import javafx.scene.image.ImageView;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.RowConstraints;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.text.Font;
//import javafx.scene.text.Text;
//
//public class Main extends Application {
//
//	final static int a = 50, b = 32;
//
//	byte[] text;
//	byte[] key;
//	byte[] cipher;
//	byte[] outcipher;
//	String enc = "";
//
//	@Override
//	public void start(Stage primaryStage) throws IOException {
//		Pane p1 = new Pane();
//
//		p1.setStyle("-fx-Background-color:rgb(187, 192, 209);");
//
//		ImageView imageView = new ImageView("comp.png");
//		imageView.setFitWidth(800);
//		imageView.setFitHeight(600);
//		Label l1 = new Label("Please Enter Any Text:");
//		l1.setStyle("-fx-font-size:25; -fx-text-fill:rgb(187, 192, 209);");
//
//		l1.setLayoutX(10);
//
//		l1.setLayoutY(320);
//
//		TextArea tt = new TextArea();
//
//		tt.setLayoutX(10);
//		tt.setLayoutY(360);
//		tt.setPrefHeight(120);
//		tt.setPrefWidth(770);
//		tt.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
//		tt.setFont(new Font(20));
//
//		Button b1 = new Button("Run");
//		b1.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");
//
//		b1.setPrefWidth(100);
//		b1.setPrefHeight(30);
//		b1.setLayoutX(350);
//		b1.setLayoutY(500);
//
//		b1.setOnAction(er -> {
//			if (tt.getText().trim() == "") {
//				Alert al = new Alert(AlertType.ERROR);
//				al.setContentText("Please Enter Text");
//				al.show();
//				return;
//			}
//
//			FileChooser f1 = new FileChooser();
//			f1.setInitialDirectory(new java.io.File("C:\\Users\\HP\\Pictures\\Screenshots"));
//			f1.setTitle("Choose File");
//
//			File file = f1.showOpenDialog(primaryStage);
//			if (file == null) {
//				Alert al = new Alert(AlertType.ERROR);
//				al.setContentText("Please enter file");
//				al.show();
//				return;
//			}
//
//			String path = file.getPath();
//
//			String n = new String();
//
//			SDEC hill = new SDEC();
//
//			n = hill.enc(tt.getText());
//
//			
//			String afterDec = n;
//
//			System.out.println(n);
//			text = new byte[n.length()];
//
//			for (int i = 0; i < n.length(); i++) {
//			
//				System.out.println(i);
//				//	System.out.println(n.charAt(i));
//					text[i] =(byte) n.charAt(i);
//				//	System.out.println((char)text[i]+"   "+text[i]);
//						
//				
//				
//			}
//
//			key = generateRandomKey(n.length());
//
//			cipher = new byte[n.length()];
//
//			for (int i = 0; i < n.length(); i++) {
//
//				cipher[i] = (byte) (key[i] ^ text[i]);
//
//			}
//
//			for (int i = 0; i < n.length(); i++) {
//				enc += (char) cipher[i];
//			}
//
//			Encr newn = new Encr();
//			newn.file = file;
//			newn.cipher = cipher;
//
//			try {
//
//				newn.encr();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			Label ciph = new Label("Cipher Text after S-DEC:");
//			ciph.setLayoutX(10);
//			ciph.setLayoutY(10);
//			ciph.setFont(new Font(25));
//			// enc
//			TextArea ttt = new TextArea(afterDec);
//			ttt.setLayoutX(10);
//			ttt.setLayoutY(50);
//			ttt.setPrefHeight(80);
//			ttt.setPrefWidth(770);
//			ttt.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
//			ttt.setFont(new Font(20));
//			ttt.setEditable(false);
//
//			Label ciph2 = new Label("Cipher Text One Time Pad:");
//			ciph2.setLayoutX(10);
//			ciph2.setLayoutY(130);
//			ciph2.setFont(new Font(25));
//
//			TextArea ttt2 = new TextArea(enc);
//			ttt2.setLayoutX(10);
//			ttt2.setLayoutY(170);
//			ttt2.setPrefHeight(80);
//			ttt2.setPrefWidth(770);
//			ttt2.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
//			ttt2.setFont(new Font(20));
//			ttt2.setEditable(false);
//
//			Pane p2 = new Pane();
//			p2.setStyle("-fx-Background-color:rgb(187, 192, 209);");
//
//			Button Decryption = new Button("Decryption");
//			Decryption.setPrefWidth(200);
//			Decryption.setPrefHeight(30);
//			Decryption.setLayoutX(450);
//			Decryption.setLayoutY(600);
//			Decryption.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");
//
//			Button Showkey = new Button("Show Key");
//			Showkey.setPrefWidth(200);
//			Showkey.setPrefHeight(30);
//			Showkey.setLayoutX(150);
//			Showkey.setLayoutY(600);
//			Showkey.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");
//
//			Decryption.setOnAction(etr -> {
//				try {
//					newn.Decr();
//					outcipher = newn.outputcipher;
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				String plan = new String();
//
//				System.out.println("------------------------------------");
//
//				for (int i = 0; i < key.length; i++) {
//
//					plan += (char) (key[i] ^ outcipher[i]);
//					System.out.println(plan);
//					System.out.println((char) (key[i] ^ outcipher[i]));
//					System.out.println("------------------------------");
//
//				}
//
//				plan = hill.dec(plan);
//				Stage s1 = new Stage();
//				Label lab = new Label(plan);
//				lab.setFont(new Font(15));
//
//				Pane pp = new Pane();
//				pp.getChildren().add(lab);
//				pp.setStyle("-fx-Background-color:rgb(187, 192, 209);");
//
//				Scene sd = new Scene(pp, 800, 300);
//
//				s1.setScene(sd);
//				s1.show();
//
//			});
//
//			Label org = new Label("Original image:");
//
//			org.setStyle("-fx-font-size:25; -fx-text-fill:Black;");
//
//			org.setLayoutX(10);
//			org.setLayoutY(260);
//
//			Label copy = new Label("Copy image:");
//
//			copy.setStyle("-fx-font-size:25; -fx-text-fill:Black;");
//
//			copy.setLayoutX(410);
//			copy.setLayoutY(260);
//
//			HBox h1 = new HBox();
//
//			ImageView image1 = new ImageView("file:" + path);
//			ImageView image2 = new ImageView(
//					"file:C:/Users/HP/Pictures/Screenshots/Screenshot 2024-07-17 21490822.png");
//
//			image1.setFitWidth(395);
//			image2.setFitWidth(395);
//			image1.setFitHeight(300);
//			image2.setFitHeight(300);
//
//			h1.getChildren().addAll(image1, image2);
//			h1.setLayoutY(300);
//			h1.setSpacing(10);
//
//			p2.getChildren().addAll(Decryption, Showkey, ttt, ciph, ttt2, ciph2, h1, org, copy);
//
//			Scene s2 = new Scene(p2, 800, 700);
//			primaryStage.setScene(s2);
//
//		});
//
//		p1.getChildren().addAll(imageView, tt, l1, b1);
//
//		Scene scene = new Scene(p1, 800, 600);
//		primaryStage.setScene(scene);
//		primaryStage.show();
//
//	}
//
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	public static byte[] generateRandomKey(int length) {
//
//		byte[] key = new byte[length];
//
//		for (int i = 0; i < length; i++) {
//
//			key[i] = (byte) ((a * i + b) % 256);
//
//		}
//		return key;
//
//	}
//
//	public static String charToBinary8Bits(char character) {
//		int asciiValue = (int) character;
//		String binaryString = String.format("%8s", Integer.toBinaryString(asciiValue)).replace(' ', '0');
//		return binaryString;
//	}
//}

//
//package Ass2Enc;
//
//
//public class SDEC {
//static String key = "1010000010";
//static  String[] keys = generateKeys(key);
//    public static void main(String[] args) {
//    	
//    	
//    	String text = "mohammad";
//    	String enc = enc(text);
//    	System.out.println(enc);
//    	dec(enc);
//    }
//    
//    
//    public static String enc(String n)
//    {
//    	String ciphertext ="";
//    	byte[] newn=new byte[n.length()];
//    	for(int i=0;i<n.length();i++) {
//    		System.out.println(charToBinary8Bits(n.charAt(i)));
//    		ciphertext+=encrypt(charToBinary8Bits(n.charAt(i)),keys); 
//    		
//    	}
//    	System.out.println("--------------");
//    	return binaryToString(ciphertext);
//    	
//    }
//    
//    public static String dec(String ciphertext)
//    {  	
//    	StringBuilder decypted =new StringBuilder();
//    	String res="";
//    	for(int i=0;i<ciphertext.length();i++) {
//    		res+=charToBinary8Bits(ciphertext.charAt(i));
//    	}
//    	for (int i = 0; i < res.length(); ) {
//    	    String c = res.substring(i, i + 8);
//    	    String decryptedBinary = decrypt(c, keys);
//    	//    System.out.println(decryptedBinary);
//    	    decypted.append(decryptedBinary);
//    	    i += 8;
//    	}
//    	return binaryToString(String.valueOf(decypted));
//    	
//    }
//
//    
//    public static String charToBinary8Bits(char character) {
//    	int asciiValue = (int) character;
//        String binaryString = String.format("%8s", Integer.toBinaryString(asciiValue)).replace(' ', '0');
//        return binaryString;
//    }
//    
//    public static String binaryToString(String binary) {
//    	if (binary.length() % 8 != 0) {
//            throw new IllegalArgumentException("Invalid binary string length. It must be a multiple of 8.");
//        }
//        StringBuilder result = new StringBuilder();
//        for (int i = 0; i < binary.length(); i += 8) {
//            String byteString = binary.substring(i, i + 8);
//            int charCode = Integer.parseInt(byteString, 2);
//            result.append((char) charCode);
//        }
//        return result.toString();
//    }
//    public static String[] generateKeys(String key) {
//        byte[] p10 = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };
//        byte[] p8 = { 6, 3, 7, 4, 8, 5, 10, 9 };
//
//        String p10key = "";
//        for (int i = 0; i < p10.length; i++) {
//            p10key += key.charAt(p10[i] - 1);
//        }
//
//        String sl1 = p10key.substring(0, 5);
//        String sl2 = p10key.substring(5, 10);
//
//        String shiftP10 = sl1.substring(1, 5) + sl1.charAt(0) + sl2.substring(1, 5) + sl2.charAt(0);
//        String res = "";
//        for (int i = 0; i < p8.length; i++) {
//            res += shiftP10.charAt(p8[i] - 1);
//        }
//
//        String[] keys = new String[2];
//        keys[0] = res;
//
//        shiftP10 = sl1.substring(3, 5) + sl1.charAt(0) + sl1.charAt(1) + sl1.charAt(2) + sl2.substring(3, 5)
//                + sl2.charAt(0) + sl2.charAt(1) + sl2.charAt(2);
//        res = "";
//        for (int i = 0; i < p8.length; i++) {
//            res += shiftP10.charAt(p8[i] - 1);
//        }
//        keys[1] = res;
//
//        return keys;
//    }
//
//    public static String encrypt(String plain, String[] keys) {
//        byte[] IP = { 2, 6, 3, 1, 4, 8, 5, 7 };
//        byte[] IpInv = { 4, 1, 3, 5, 7, 2, 8, 6 };
//        byte[] EP = { 4, 1, 2, 3, 2, 3, 4, 1 };
//        String[][] S0 = { { "01", "00", "11", "10" }, { "11", "10", "01", "00" }, { "00", "10", "01", "11" },
//                { "11", "01", "11", "10" } };
//
//        String[][] S1 = { { "00", "01", "10", "11" }, 
//                { "10", "00", "01", "11" }, 
//                { "11", "00", "01", "00" }, 
//                { "10", "01", "00", "11" }
//        };
//        byte[] p4 = { 2, 4, 3, 1 };
//
//        String ip = "";
//        for (int i = 0; i < IP.length; i++) {
//            ip += plain.charAt(IP[i] - 1);
//        }
//
//        String L0 = ip.substring(0, 4), R0 = ip.substring(4, 8);
//
//        String ep = "", xor1 = "";
//        for (int i = 0; i < EP.length; i++) {
//            ep += R0.charAt(EP[i] - 1);
//            xor1 += (int) (R0.charAt(EP[i] - 1)) ^ (int) (keys[0].charAt(i));
//        }
//
//        String sp0 = xor1.substring(0, 4);
//        String sp1 = xor1.substring(4, 8);
//
//        int lefts0 = Integer.parseInt(sp0.charAt(0) + "" + sp0.charAt(3) + "", 2);
//        int right0 = Integer.parseInt(String.valueOf(sp0.charAt(1) + "" + sp0.charAt(2) + ""), 2);
//
//        int lefts1 = Integer.parseInt(String.valueOf(sp1.charAt(0) + "" + sp1.charAt(3)), 2);
//        int right1 = Integer.parseInt(String.valueOf(sp1.charAt(1) + "" + sp1.charAt(2)), 2);
//
//        String boxres = String.valueOf(S0[lefts0][right0]) + "" + String.valueOf(S1[lefts1][right1]);
//        String p4res = "";
//        for (int i = 0; i < p4.length; i++) {
//            p4res += boxres.charAt(p4[i] - 1);
//        }
//        String xorres = "";
//        for (int i = 0; i < p4.length; i++) {
//            xorres += (int) p4res.charAt(i) ^ (int) L0.charAt(i);
//        }
//
//        // Swap L1 and R1
//        String L1 = R0;
//        String R1 = xorres;
//
//        // Second round starts here
//        ep = "";
//        xor1 = "";
//        for (int i = 0; i < EP.length; i++) {
//            ep += R1.charAt(EP[i] - 1);
//            xor1 += (int) (R1.charAt(EP[i] - 1)) ^ (int) (keys[1].charAt(i));
//        }
//
//        sp0 = xor1.substring(0, 4);
//        sp1 = xor1.substring(4, 8);
//
//        lefts0 = Integer.parseInt(sp0.charAt(0) + "" + sp0.charAt(3) + "", 2);
//        right0 = Integer.parseInt(String.valueOf(sp0.charAt(1) + "" + sp0.charAt(2) + ""), 2);
//
//        lefts1 = Integer.parseInt(String.valueOf(sp1.charAt(0) + "" + sp1.charAt(3)), 2);
//        right1 = Integer.parseInt(String.valueOf(sp1.charAt(1) + "" + sp1.charAt(2)), 2);
//
//        boxres = String.valueOf(S0[lefts0][right0]) + "" + String.valueOf(S1[lefts1][right1]);
//        p4res = "";
//        for (int i = 0; i < p4.length; i++) {
//            p4res += boxres.charAt(p4[i] - 1);
//        }
//        xorres = "";
//        for (int i = 0; i < p4.length; i++) {
//            xorres += (int) p4res.charAt(i) ^ (int) L1.charAt(i);
//        }
//
//        // Combine the final result
//        String preOutput = xorres + R1;
//        String output = "";
//        for (int i = 0; i < IpInv.length; i++) {
//            output += preOutput.charAt(IpInv[i] - 1);
//        }
//
//        return output;
//    }
//
//    public static String decrypt(String cipher, String[] keys) {
//        String temp = keys[0];
//        String []keys2 = new String[2];
//        keys2[0] = keys[1];
//        keys2[1] = temp;
//
//        return encrypt(cipher, keys2);
//    }
//}