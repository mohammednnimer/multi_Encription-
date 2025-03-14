package Ass2Enc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class Q1 extends Application {

	final static 	int a = 50, b = 32;

	
	byte[] text;
	byte[] key;
	byte[] cipher ;
	String enc="";
	
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

		//C:\\Users\\HP\\Pictures\\Screenshots\\Screenshot 2024-07-17 214218.png
		
	

		b1.setOnAction(er -> {
			if (tt.getText().trim() == "") {
				Alert al = new Alert(AlertType.ERROR);
				al.setContentText("Please Enter Text");
				al.show();
				return;

			}

			String n = tt.getText();
			

			text=new byte[n.length()];
			
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
				enc+=(char)cipher[i];	
			}
			
		
			
			Label ciph=new Label("Cipher Text:");
			ciph.setLayoutX(10);
			ciph.setLayoutY(10);	
			ciph.setFont(new Font(25));
			TextArea ttt = new TextArea(enc);
			ttt.setLayoutX(10);
			ttt.setLayoutY(50);
			ttt.setPrefHeight(220);
			ttt.setPrefWidth(770);
			ttt.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
			ttt.setFont(new Font(20));
			ttt.setEditable(false);
			
			
			
			
			
			
			
			
			
			
			Pane p2 = new Pane();
			p2.setStyle("-fx-Background-color:rgb(187, 192, 209);");

			
			Button Decryption=new Button("Decryption");
			Decryption.setPrefWidth(200);
			Decryption.setPrefHeight(30);
			Decryption.setLayoutX(450);
			Decryption.setLayoutY(500);	
			Decryption.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");
			
			Button Showkey=new Button("Show Key");
			Showkey.setPrefWidth(200);
			Showkey.setPrefHeight(30);
			Showkey.setLayoutX(150);
			Showkey.setLayoutY(500);	
			Showkey.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");

			Decryption.setOnAction(etr->
			{
				
					
				String plan="";
				for(int i=0;i<key.length;i++)
				{
					
					plan+=(char)(key[i]^cipher[i]);
					
				}
				
				
				
		
		
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
			Showkey.setOnAction(ee->
			{
				
				String plan="";
				
				 for (int i = 0; i < key.length; i++) {
					   
					 int j = (int) key[i];
					 if(j<0)
					 {
						 
						 j=256+j;
					 }
					 
					 
					 int count = 0;
					 String sum = "";
					   while (count < 8) {
						   
						   if (j == 0) {
							   sum += "0";   
						   }else if (j % 2 == 0) {
							   sum += "0";
						   } else {
								sum += "1";
						   }
							j /= 2;
							count++;
						}
						String reversed = new StringBuilder(sum).reverse().toString();
						plan += reversed;		
				             }
				
				
				
		
		
				Stage s1=new Stage();
				TextArea lab=new TextArea(plan);
				lab.setLayoutX(10);
				lab.setLayoutY(50);
				lab.setPrefHeight(220);
				lab.setPrefWidth(770);
				lab.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
				lab.setFont(new Font(20));
				lab.setEditable(false);
	
				lab.setScrollTop(Double.MAX_VALUE);
				Pane pp=new Pane();
				pp.getChildren().add(lab);
				pp.setStyle("-fx-Background-color:rgb(187, 192, 209);");

				
				
				Scene sd=new Scene(pp,800,300);
				
				s1.setScene(sd);
				s1.show();
				
				
				
				
			});
			
			
			
			
			
			
			
			
			
			p2.getChildren().addAll(Decryption,Showkey,ttt,ciph);

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
		
			key[i]=(byte)((a * i + b) % 256);
            	
		}		
		return key;
		
	
	}
}
