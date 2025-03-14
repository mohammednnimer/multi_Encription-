package Ass2Enc;


import java.io.File;
import java.io.IOException;

import javafx.application.Application;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;




public class Main extends Application {

	final static 	int a = 50, b = 32;

	
	byte[] text;
	byte[] key;
	byte[] cipher ;
	byte[] outcipher ;
	String enc="";
	String onetime="";
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Pane p1 = new Pane();

		p1.setStyle("-fx-Background-color:rgb(187, 192, 209);");

		ImageView imageView = new ImageView("comp.png");
		imageView.setFitWidth(800);
		imageView.setFitHeight(600);
		Label l1 = new Label("Please Enter Any Text:");
		l1.setStyle("-fx-font-size:25; -fx-text-fill:rgb(187, 192, 209);");

		l1.setLayoutX(10);
		
		l1.setLayoutY(320);

		TextArea tt = new TextArea();
		
		tt.setLayoutX(10);
		tt.setLayoutY(360);
		tt.setPrefHeight(120);
		tt.setPrefWidth(770);
		tt.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
		tt.setFont(new Font(20));

		
		Button b1 = new Button("Run");
		b1.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");

		b1.setPrefWidth(100);
		b1.setPrefHeight(30);
		b1.setLayoutX(350);
		b1.setLayoutY(500);


		b1.setOnAction(er -> {
			
			
			if (tt.getText().trim() == "") {
				Alert al = new Alert(AlertType.ERROR);
				al.setContentText("Please Enter Text");
				al.show();
				return;
			}

			FileChooser f1 = new FileChooser();
			f1.setInitialDirectory(new java.io.File("C:\\Users\\HP\\Pictures\\Screenshots"));
			f1.setTitle("Choose File");	
			
			
			File file = f1.showOpenDialog(primaryStage);			
			if(file == null)
			{
				Alert al=new Alert(AlertType.ERROR);
				al.setContentText("Please enter file");
				al.show();
				return;	
			}
			
			String path=file.getPath();
			
			
			
			
			String n = new String();


			SDEC hill=new SDEC();
			
			
			
			n=hill.enc(tt.getText());
			
			
			
			String afterDec=n;
			
			
			
		   text  =   new byte[n.length()];
			
			
			for(int i=0;i<n.length();i++)
			{
				text[i]=(byte)n.charAt(i);
			}
			
			key=generateRandomKey(n.length());
			
			cipher=new byte[n.length()];
			
			for(int i=0;i<n.length();i++)
			{
				cipher[i]=(byte)(key[i]^text[i]);	
			}
		
			
			for(int i=0;i<n.length();i++)
			{
				onetime+= hill.charToBinary8Bits((char)key[i]);	
			}

			for(int i=0;i<n.length()/8;i++)
			{
				enc+=(char)cipher[i];	
			}
		
			
			Encr newn=new Encr();
			newn.file=file;
			
			newn.cipher=cipher;
		
			try {
				newn.encr();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Label ciph=new Label("Cipher Text after S-DEC:");
			ciph.setLayoutX(10);
			ciph.setLayoutY(10);	
			ciph.setFont(new Font(25));
			//enc
			TextArea ttt = new TextArea(afterDec);
			ttt.setLayoutX(10);
			ttt.setLayoutY(50);
			ttt.setPrefHeight(80);
			ttt.setPrefWidth(770);
			ttt.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
			ttt.setFont(new Font(20));
			ttt.setEditable(false);
			
			Label ciph2=new Label("Cipher Text One Time Pad:");
			ciph2.setLayoutX(10);
			ciph2.setLayoutY(130);	
			ciph2.setFont(new Font(25));
			
			TextArea ttt2 = new TextArea(enc);
			ttt2.setLayoutX(10);
			ttt2.setLayoutY(170);
			ttt2.setPrefHeight(80);
			ttt2.setPrefWidth(770);
			ttt2.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
			ttt2.setFont(new Font(20));
			ttt2.setEditable(false);
			
			
			
			
			Pane p2 = new Pane();
			p2.setStyle("-fx-Background-color:rgb(187, 192, 209);");
			
			Button Decryption=new Button("Decryption");
			Decryption.setPrefWidth(200);
			Decryption.setPrefHeight(30);
			Decryption.setLayoutX(500);
			Decryption.setLayoutY(530);	
			Decryption.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");
			
			Button Showkey=new Button("Show Steps");
			Showkey.setPrefWidth(200);
			Showkey.setPrefHeight(30);
			Showkey.setLayoutX(100);
			Showkey.setLayoutY(530);	
			Showkey.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");
			
			Showkey.setOnAction(e->
			{
				Stage s1=new Stage();	
				Label lab=new Label("This opartion will do Simple DEC :");
				lab.setFont(new Font(30));
				lab.setLayoutX(10);
				lab.setLayoutY(10);
				
				Label lab0=new Label("The Key :"+hill.key);
				lab0.setFont(new Font(30));
				lab0.setLayoutX(10);
				lab0.setLayoutY(60);
				
				
				Label lab1=new Label("The Key1 :"+hill.keys[0]);
				lab1.setFont(new Font(30));
				lab1.setLayoutX(10);
				lab1.setLayoutY(110);
				
				Label lab2=new Label("The key2 :"+hill.keys[1]);
				lab2.setFont(new Font(30));
				lab2.setLayoutX(10);
				lab2.setLayoutY(160);
				
				
				
				
				Label dect=new Label("The cipher text by bytes :");
				dect.setFont(new Font(30));
				dect.setLayoutX(10);
				dect.setLayoutY(220);
				
				
				
				
				
				Pane pp=new Pane();
				
				TextArea t = new TextArea(afterDec);
				t.setLayoutX(10);
				t.setLayoutY(50);
				t.setPrefHeight(80);
				t.setPrefWidth(770);
				t.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
				t.setFont(new Font(20));
				t.setEditable(false);
				t.setLayoutY(300);
				
				t.setPrefHeight(150);
				
				Button nextStep=new Button("Next Step ");
				nextStep.setPrefWidth(200);
				nextStep.setPrefHeight(30);
				nextStep.setLayoutX(300);
				nextStep.setLayoutY(500);	
				nextStep.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");
			
				
				
				
				pp.getChildren().addAll(lab,lab1,lab2,lab0,t,dect,nextStep);
				pp.setStyle("-fx-Background-color:rgb(187, 192, 209);");

				
				
				
				Scene sd=new Scene(pp,800,600);
				
				s1.setScene(sd);
				s1.show();
				
				
				
				
				nextStep.setOnAction(rt->
				{
					s1.close();
					
					
					Label step0=new Label("This Step will do One-time pad algorithem :");
				
					step0.setFont(new Font(30));
					step0.setLayoutX(10);
					step0.setLayoutY(10);
					
					Label step1=new Label("The Genrat random key is :");
					
					step1.setFont(new Font(30));
					step1.setLayoutX(10);
					step1.setLayoutY(70);
					
					
					TextArea tt2 = new TextArea(onetime);
					tt2.setLayoutX(10);
					tt2.setLayoutY(120);
					tt2.setPrefHeight(100);
					tt2.setPrefWidth(770);
					tt2.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
					tt2.setFont(new Font(20));
					tt2.setEditable(false);
				         
					
					Label step2=new Label("The cipher text :");
					step2.setFont(new Font(30));
					step2.setLayoutX(10);
					step2.setLayoutY(220);
					
				
					Pane pp1=new Pane();
					
					ttt2.setLayoutY(280);
					
					ttt2.setPrefHeight(150);
					
					
					Button nextStepp=new Button("Next Step ");
					nextStepp.setPrefWidth(200);
					nextStepp.setPrefHeight(30);
					nextStepp.setLayoutX(300);
					nextStepp.setLayoutY(500);	
					nextStepp.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");
					
					
				    pp1.getChildren().addAll(dect,nextStepp,step0,step1,step2,ttt2,tt2);
					pp1.setStyle("-fx-Background-color:rgb(187, 192, 209);");

					
					
					
					
					Scene sd2=new Scene(pp1,800,600);
					
					s1.setScene(sd2);
					s1.show();
					
					
					
					
					nextStepp.setOnAction(erre->{
						
						s1.close();
						
						Label org = new Label("Original image:");

						org.setStyle("-fx-font-size:25; -fx-text-fill:Black;");

						org.setLayoutX(10);
						org.setLayoutY(10);

						Label copy = new Label("Copy image:");

						copy.setStyle("-fx-font-size:25; -fx-text-fill:Black;");

						copy.setLayoutX(400);
						copy.setLayoutY(10);
						
						HBox h1=new HBox();
						
						ImageView image1=new ImageView("file:"+path);
						ImageView image2=new ImageView("file:C:/Users/HP/Pictures/Screenshots/Screenshot 2024-07-17 21490822.png");
						
						image1.setFitWidth(395);
						image2.setFitWidth(395);
						image1.setFitHeight(500);
						image2.setFitHeight(500);
					
						h1.getChildren().addAll(image1,image2);
						h1.setLayoutY(60);
						h1.setSpacing(10);
						
						
						Pane pp2=new Pane();
						
						pp2.getChildren().addAll(h1,org,copy);
						pp2.setStyle("-fx-Background-color:rgb(187, 192, 209);");

							
							
							
							
							Scene sd4=new Scene(pp2,800,600);
							
							s1.setScene(sd4);
							s1.show();
							
						
						
						
			        
					
					});
					
					
					
					
					
					
					
					
					
					
					
					
					
					                        
					
					
					
					
					
					
					
					
					
					
				});
				
				
				
				
				
				
				
				
				
			});
			
			
			
			
			
			
			

			Decryption.setOnAction(etr->
			{
				try {
					newn.Decr();
					outcipher=newn.outputcipher;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String plan="";
				
				outcipher=cipher;
				
				
				
				for(int i=0;i<key.length;i++)
				{
					
					plan+=(char)(key[i]^outcipher[i]);
					
				}
				
			
				plan=hill.dec(plan);
				Stage s1=new Stage();
				Label lab=new Label(plan);
				lab.setFont(new Font(15));
				Pane pp=new Pane();
				pp.getChildren().add(lab);
				pp.setStyle("-fx-Background-color:rgb(187, 192, 209);");
               	
				Scene sd=new Scene(pp,800,300);
               	
				s1.setScene(sd);
				
				s1.show();
				
			
				
				
				
			});
			
			
			

			HBox h1=new HBox();
			
			ImageView image1=new ImageView("file:"+path);
			ImageView image2=new ImageView("file:C:/Users/HP/Pictures/Screenshots/Screenshot 2024-07-17 21490822.png");
			
			image1.setFitWidth(395);
			image2.setFitWidth(395);
			image1.setFitHeight(400);
			image2.setFitHeight(400);
		
			h1.getChildren().addAll(image1,image2);
			h1.setLayoutY(60);
			h1.setSpacing(10);
			
			
			
			Label org = new Label("Original image:");

			org.setStyle("-fx-font-size:25; -fx-text-fill:Black;");

			org.setLayoutX(10);
			org.setLayoutY(10);

			Label copy = new Label("Copy image:");

			copy.setStyle("-fx-font-size:25; -fx-text-fill:Black;");

			copy.setLayoutX(400);
			copy.setLayoutY(10);
			
		
			

			//,ttt,ciph,ttt2,ciph2
			
			p2.getChildren().addAll(h1,copy,org,Decryption,Showkey);

			Scene s2 = new Scene(p2, 800, 600);
			primaryStage.setScene(s2);
			 
			 
	

		});

		p1.getChildren().addAll(imageView, tt, l1, b1);

		Scene scene = new Scene(p1, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
	

	
	
	
	
	
	public static byte[] generateRandomKey(int length) {
	
		byte[] key=new byte[length];
		
		for(int i=0;i<length;i++)
		{
               key[i]=(byte) ((a * i + b) % 256);	
		}		
		return key;
		
	
	}
    public static String charToBinary8Bits(char character) {
    	int asciiValue = (int) character;
        String binaryString = String.format("%8s", Integer.toBinaryString(asciiValue)).replace(' ', '0');
        return binaryString;
    }
}
