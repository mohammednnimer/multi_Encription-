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

import Algorethem2.Main;
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

public class Encr extends Application {

	final static 	int a = 37, b = 42;
	int width;
	int height;
	int count=0;
	String bits="";
	int x;
	int y;
	String total;
	
	byte[] cipher;
	byte[] outputcipher;

	File file;
	String Path;
	BufferedImage image;
	
	public void Decr() throws IOException {
		
	
	File filecopy = new File("C:/Users/HP/Pictures/Screenshots/Screenshot 2024-07-17 21490822.png");

	    outputcipher=new byte[cipher.length];
	    
	    
	    
		BufferedImage imagecopy;
		
		try {	
			imagecopy = ImageIO.read(filecopy);
			width = imagecopy.getWidth();
			height = imagecopy.getHeight();	  
			count = 0;
			bits = "";
			for (int i = 0; count < total.length(); i++) {
				
			  int revTohight = total.length() - 1 - i;
			  
			  int x1 = generateRandomNumber(i, width);
			  int y1 = generateRandomNumber(revTohight, height);

				int pixel = imagecopy.getRGB(x1, y1);
				int red = (pixel >> 16) & 0xff;
				count++;
				bits += red % 2 + "";
				if (count > total.length() - 1)
					{
					break;
					}
 				int green = (pixel >> 8) & 0xff;
				count++;
				bits += green % 2 + "";
				if (count > total.length() - 1)
					break;
				int blue = pixel & 0xff;
				count++;
				bits += blue % 2 + "";
				if (count > total.length() - 1)
					break;
		}

			int sum = 0;

			
			String secret = "";
			count =0;
			for (int i = 0; i < bits.length();) {
				sum = 0;
				for (int j = 0; j < 8 && i < bits.length(); j++) {
					if (bits.charAt(i) != '0')
						sum += Math.pow(2, 8 - j - 1);
					i++;
				}
				outputcipher[count]=(byte)sum;
				count++;
			}

		
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  
		
		
		
		
		
		
		
	}
	
	public void encr() throws IOException {
		try {
			image = ImageIO.read(file);
			if(image == null)
			{
				Alert al=new Alert(AlertType.ERROR);
				al.setContentText("Please enter valied file");
				al.show();
				return;
				
			 	}
			 width = image.getWidth();
			 height = image.getHeight();
		
		
for (int i = 0; i < cipher.length; i++) {
	   
	int j=cipher[i];
	 
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
		total += reversed;		
             }
	 bits = "";
	 count = 0;
	total = total.substring(1);
	 total = total.substring(1);
	 total = total.substring(1);
	 total = total.substring(1);
	 
	 
	int newPixel = 0;
	
	for (int i = 0; count < total.length(); i++) {
		
		int revTohight = total.length() - 1 - i;
		 x = generateRandomNumber(i, width);
		 y = generateRandomNumber(revTohight, height);
		 
		int pixel = image.getRGB(x, y);
		int alpha = (pixel >> 24) & 0xff;
		int red =   (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue =   pixel & 0xff;
		bits += total.charAt(count) + "";
		
		if (red % 2 == 0 && count < total.length()) {
			if (Integer.parseInt(total.charAt(count) + "") == 1) {
				red++;
			}

			count++;
		} else if (red % 2 != 0 && count < total.length()) {
			if (Integer.parseInt(total.charAt(count) + "") == 0) {
				red--;
			}

			count++;
		}
		
		if (count > total.length() - 1) {

			newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
			image.setRGB(x, y, newPixel);
			break;
		
		}
		bits += total.charAt(count) + "";
		if (green % 2 == 0 && count < total.length()) {
			if (Integer.parseInt(total.charAt(count) + "") == 1) {
				green++;
			}

			count++;
		} else if (green % 2 != 0 && count < total.length()) {
			if (Integer.parseInt(total.charAt(count) + "") == 0) {
				green--;
			}

			count++;
		}

		
		if (count > total.length() - 1) {
			newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
			image.setRGB(x, y, newPixel);
			
			break;
		}
		bits += total.charAt(count) + "";
		
		if (blue % 2 == 0 && count < total.length()) {
			if (Integer.parseInt(total.charAt(count) + "") == 1) {
				blue++;
			}

			count++;
		} else if (blue % 2 != 0 && count < total.length()) {
			if (Integer.parseInt(total.charAt(count) + "") == 0) {
				blue--;
			}
			count++;
		}
		
		if (count > total.length() - 1) {
			newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
			image.setRGB(x, y, newPixel);
			
			break;
		}
		
		newPixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
		image.setRGB(x, y, newPixel);
		

	}

	int x = 0, y = 0;



File outputFile = new File("C:/Users/HP/Pictures/Screenshots/Screenshot 2024-07-17 21490822.png");
try {
	ImageIO.write(image, "png", outputFile);
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}	



		
		
}catch (Exception e) {
			// TODO: handle exception}
	System.out.println(e.getMessage());
}
		
		
		
		
	}
	
	
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {

	}

	public static void main(String[] args) {
		//launch(args);
	}

	
	public static int generateRandomNumber(int n, int length) {
	
		return (a * n + b) % length;
		
	
	}
}
