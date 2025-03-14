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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
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

public class Q2 extends Application {

	final static int a = 37, b = 42;

	byte[] text;
	byte[] key;
	byte[] cipher;
	String enc = "";
	char[][] rail;
	

	
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

		Label l2 = new Label("Please Enter # rails:");
		l2.setStyle("-fx-font-size:25; -fx-text-fill:rgb(187, 192, 209);");

		l2.setLayoutX(10);
		l2.setLayoutY(270);

		Spinner<Integer> number = new Spinner<>();

		number.setLayoutX(230);
		
		number.setLayoutY(277);
		
		number.setPrefWidth(70);
		
		SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 100, 2, 1);
		
		number.setValueFactory(valueFactory);
		
		Button b1 = new Button("RailFence-Encr");
		
		b1.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");

		b1.setPrefWidth(200);
		
		b1.setPrefHeight(30);
		
		b1.setLayoutX(300);
		
		b1.setLayoutY(500);

		// C:\\Users\\HP\\Pictures\\Screenshots\\Screenshot 2024-07-17 214218.png

		b1.setOnAction(er -> {
			if (tt.getText().trim() == "") {
				Alert al = new Alert(AlertType.ERROR);
				al.setContentText("Please Enter Text");
				al.show();
				return;
			}

			String n = tt.getText();
			String nosp = tt.getText().replaceAll(" ", "");

			if(number.getValue()>=n.length())
			{
				Alert al = new Alert(AlertType.ERROR);
				al.setContentText("The real whoule less than length of text");
				al.show();
				return;
			
			}
			rail = new char[number.getValue()][nosp.length() + 1];

			int count = 0;
			int counter = 0;
			boolean up = true;

			for (int i = 0; counter < n.length();) {

				if (count == number.getValue()) {

					up = !up;
					count -= 2;
				}
				if (count < 0) {

					up = !up;
					count += 2;
				}
				System.out.println(count);

				if (n.charAt(counter) == ' ' || !Character.isLetterOrDigit(n.charAt(counter))) {

					rail[count][i + 1] = n.charAt(counter);
					counter++;
				}
				if (counter == n.length()) {
					break;
				}

				rail[count][i] = n.charAt(counter);

				counter++;
				i++;

				if (up) {
					count++;
				} else {
					count--;
				}

			}

			GridPane gp = new GridPane();
			gp.setHgap(10);
			gp.setVgap(10);
			Pane p2 = new Pane();

			String cipher = "";

			for (int i = 0; i < number.getValue(); i++) {

				for (int j = 0; j < nosp.length(); j++) {

					Label l3 = new Label();

					l3.setPrefSize(30, 30);
					l3.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: 1;");
					if (Character.isLetterOrDigit(rail[i][j])) {
						l3.setText(rail[i][j] + "");
						cipher += rail[i][j];
					}
					l3.setFont(new Font(25));
					l3.setAlignment(Pos.CENTER);
					gp.add(l3, j, i);

				}

			}

			Label ciph = new Label("Cipher Text:");
			ciph.setLayoutX(10);
			ciph.setLayoutY(10);
			ciph.setFont(new Font(25));
			TextArea ttt = new TextArea(cipher);
			ttt.setLayoutX(10);
			ttt.setLayoutY(50);
			ttt.setPrefHeight(220);
			ttt.setPrefWidth(770);
			ttt.setStyle("-fx-control-inner-background:rgb(208, 215, 238);");
			ttt.setFont(new Font(20));
			ttt.setEditable(false);

//			gp.setLayoutY(30);
//			System.out.println(800-nosp.length()*20);
//			gp.setLayoutX((800-nosp.length()*30)/2);

			p2.setStyle("-fx-Background-color:rgb(187, 192, 209);");

			Button Decryption = new Button("Decryption");
			Decryption.setPrefWidth(200);
			Decryption.setPrefHeight(30);
			Decryption.setLayoutX(450);
			Decryption.setLayoutY(500);
			Decryption.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");

			Button Showkey = new Button("Show Key distribution");
			Showkey.setPrefWidth(200);
			Showkey.setPrefHeight(30);
			Showkey.setLayoutX(150);
			Showkey.setLayoutY(500);
			Showkey.setStyle("-fx-background-radius: 50px; -fx-font-size: 20px;");

			Decryption.setOnAction(etr -> {

				boolean down = true;

				int count1 = 0;

				String plan = "";

				for (int i = 0; i < nosp.length();) {

					if (count1 == number.getValue()) {
						down = !down;
						count1 -= 2;
					}
					if (count1 < 0) {
						down = !down;
						count1 += 2;
					}

					if (rail[count1][i + 1] == ' ' || !Character.isLetterOrDigit(rail[count1][i + 1])) {
						plan += rail[count1][i + 1];
					}

					plan += rail[count1][i];
					if (down) {
						count1++;
					} else {
						count1--;
					}
					i++;

				}

				Stage s1 = new Stage();
				Label lab = new Label(plan);
				lab.setFont(new Font(15));

				Pane pp = new Pane();
				pp.getChildren().add(lab);
				pp.setStyle("-fx-Background-color:rgb(187, 192, 209);");

				Scene sd = new Scene(pp, 800, 300);

				s1.setScene(sd);
				s1.show();

			});
			Showkey.setOnAction(ee -> {

				Stage s1 = new Stage();

				Pane pp = new Pane();
				pp.getChildren().add(gp);
				pp.setStyle("-fx-Background-color:rgb(187, 192, 209);");

				Scene sd = new Scene(pp, 800, 300);

				s1.setScene(sd);
				s1.show();

			});

			p2.getChildren().addAll(Decryption, Showkey, ttt, ciph);

			Scene s2 = new Scene(p2, 800, 600);
			primaryStage.setScene(s2);

//			File outputFile = new File("C:/Users/HP/Pictures/Screenshots/Screenshot 2024-07-17 21490822.png");
//			try {
//				ImageIO.write(image, "png", outputFile);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

//			System.out.println("enter number");
//			int n1 = next.nextInt();
//			if (n1 == 1) {
//

//
//			
//			
//			
//			
//			

		});

		p1.getChildren().addAll(imageView, tt, l1, b1, number, l2);

		Scene scene = new Scene(p1, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static byte[] generateRandomKey(int length) {

		byte[] key = new byte[length];

		for (int i = 0; i < length; i++) {
			key[i] = (byte) ((a * i + b) % 256);
		}
		return key;

	}
}
