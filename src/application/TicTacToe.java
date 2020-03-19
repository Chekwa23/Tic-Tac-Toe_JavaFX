package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.sun.glass.ui.View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A game of TicTacToe
 * @author Lucas Onwuchekwa
 */
public class TicTacToe extends Application
{
	
	ArrayList<myRectangle> rectList;
	FileInputStream srcx;
	FileInputStream srco;
	Image oh;
	Image ex;
	Pane root;
	VBox vbox;
	HBox hbox;
	StackPane stackP;
	StackPane xstack;
	StackPane ostack;
	Pane layer1;
	ImagePattern view;
	Text text;
	int count;
	String draw;
	String playerx;
	String playero;
	Button restart;
	Rectangle dem;
	Rectangle xrect;
	Rectangle orect;
	Text xplay;
	Text oplay;
	
	/**
	 * A constructor initializing variables and throwing a FileNotFoundException 
	 * @throws FileNotFoundException
	 */
	public TicTacToe() throws FileNotFoundException
	{
		rectList = new ArrayList<>();
// 		srcx = new FileInputStream("C:\\Users\\onwuc\\Eclipse2019\\TicTacToe\\x.jpg"); 
// 		srco = new FileInputStream("C:\\Users\\onwuc\\Eclipse2019\\TicTacToe\\o.jpg"); 
		srcx = new FileInputStream("x.jpg"); 
		srco = new FileInputStream("o.jpg"); 
		oh = new Image(srco); 
		ex = new Image(srcx);
		root = new Pane();
		vbox = new VBox();
		hbox = new HBox();
		stackP = new StackPane();
		xstack = new StackPane();
		ostack = new StackPane();
		layer1 = new Pane();
		view = new ImagePattern(oh);
		dem = new Rectangle(200,30);
		xrect = new Rectangle(200,30);
		orect = new Rectangle(200,30);
		draw = "DRAW";
		playerx = "Congratulations, X win the game";
		playero = "Congratulations, O win the game";
		text = new Text();
		xplay = new Text("X is playing");
		oplay = new Text("O is playing");
		count = 0;
	}

	/**
	 * Overriding the default application start method.
	 */
	@Override
	public void start(Stage primary) throws Exception 
	{ 
		restart = new Button("Restart");
		restart.setPrefSize(80,30);
		restart.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent arg0) 
			{
				count = 0;
				text.setText("");
				for(int i = 0; i < rectList.size(); i++)
			    {
			    	rectList.get(i).setFill(Color.WHITE);
			    	rectList.get(i).setClick(false);
			    	rectList.get(i).setOwner("");
			    }
		    	view = new ImagePattern(oh);
		    	vbox.getChildren().removeAll(restart);
				ostack.getChildren().removeAll(oplay);
				xstack.getChildren().removeAll(xplay);
				xstack.getChildren().add(xplay);
			}
		});
		
		myRectangle rect = null;
	    for(int i = 0; i<600; i+=200)
	    {
	    	for(int j = 0; j<600; j+=200)
	    	{
	    		rect = new myRectangle(200,200);
				rect.setX(i);
				rect.setY(j);
				rect.setFill(Color.WHITE);
				rect.setStroke(Color.BLACK);
				layer1.getChildren().add(rect);
				rectList.add(rect);
	    	}
	    }
	    
	    dem.setFill(Color.BLUE); 
	    dem.setStroke(Color.BLACK);
	    xrect.setFill(Color.WHITE);
	    xrect.setStroke(Color.BLACK);
	    orect.setFill(Color.WHITE);
	    orect.setStroke(Color.BLACK);
	    text.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
	    text.setFill(Color.BLUE);
	    xplay.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
	    oplay.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
	    xstack.getChildren().addAll(xrect,xplay);
	    ostack.getChildren().addAll(orect);
	    hbox.getChildren().add(xstack);
	    hbox.getChildren().add(dem);
	    hbox.getChildren().add(ostack);
	    stackP.getChildren().add(layer1);
	    stackP.getChildren().add(text);
	    vbox.getChildren().add(hbox);
	    vbox.getChildren().add(stackP);
	    root.getChildren().add(vbox);
		
	    
	    for(int i = 0; i < rectList.size(); i++)
	    {
	    	final Integer innerI = Integer.valueOf(i);
	    	rectList.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event)
				{
					if(rectList.get(innerI).getClick() == false)
					{
						setView();
						rectList.get(innerI).setFill(view);
						String temp = imageString(view);;
						rectList.get(innerI).setOwner(temp);
						
						increaseCount();
					}
					else {}
					rectList.get(innerI).setClick(true);
				}
			});
	    }
	    
	    Scene scene  = new Scene(root, 600, 670);
	    primary.setTitle("Tic Tac Toe");
		primary.setScene(scene);
		primary.show();
	}
	
	/**
	 * An inner rectangle class called my rectangle which extends the normal java rectangle class
	 * @author Lucas Onwuchekwa
	 */
	private class myRectangle extends Rectangle
	{
		private Boolean clicked;
		private String owner;
		public myRectangle(int width, int height)
		{
			super(width, height);
			clicked = false;
			owner = "";
		}
	
		public void setOwner(String temp)
		{
			owner = temp;
		}
		
		public String getOwner()
		{
			return owner;
		}
		
		public void setClick(Boolean click)
		{
			clicked = click;
		}
		
		public boolean getClick()
		{
			return clicked;
		}
	}
	
	/**
	 * Sets the image to be used depending on whose turn it is.
	 */
	private void setView()
	{
		if(view.getImage() == ex)
		{
			view = new ImagePattern(oh);
			ostack.getChildren().removeAll(oplay);
			xstack.getChildren().add(xplay);
		}
		else
		{
			view = new ImagePattern(ex);
			xstack.getChildren().removeAll(xplay);
			ostack.getChildren().add(oplay);
		}
	}
	
	/**
	 * Increases the count variable and checks if game is over.
	 */
	private void increaseCount()
	{
		count++;
		checkGameOver();
	}
	
	/**
	 * Gets the type of image either ex or oh
	 * @param img
	 * @return The type of image as a specified string
	 */
	private String imageString(ImagePattern img)
	{
		String str = "";
		if(img.getImage() == ex)
		{
			str = "ex";
		}
		else if(img.getImage() == oh)
		{
			str = "oh";
		}
		return str;
	}
	
	/**
	 * Checks if the game is over
	 */
	private void checkGameOver() 
	{
		String temp = checkWin();
		if(temp == "")
		{
			if(count >= 9)
			{
				text.setText(draw);
				
				vbox.getChildren().add(restart);
			}
			else {}
		}
		else 
		{
			text.setText(temp);
			for(int i = 0; i < rectList.size(); i++)
		    {
		    	rectList.get(i).setClick(true);
		    }
			vbox.getChildren().add(restart);
		}
	}

	/**
	 * Checks who won the game.
	 * @return The string name o fthe image who won the game eitehr ex or oh.
	 */
	private String checkWin()
	{ 
		String win = "";
		String owner = "";
		if(((rectList.get(0).getOwner()).equals(rectList.get(1).getOwner()) && (rectList.get(0).getOwner()).equals(rectList.get(2).getOwner())) && rectList.get(0).getClick())
		{
			owner = rectList.get(0).getOwner();
		}
		else if(((rectList.get(3).getOwner()).equals(rectList.get(4).getOwner()) && (rectList.get(3).getOwner()).equals(rectList.get(5).getOwner())) && rectList.get(3).getClick())
		{
			owner = rectList.get(3).getOwner();
		}
		else if(((rectList.get(6).getOwner()).equals(rectList.get(7).getOwner()) && (rectList.get(6).getOwner()).equals(rectList.get(8).getOwner())) && rectList.get(6).getClick())
		{
			owner = rectList.get(6).getOwner();
		}
		else if(((rectList.get(0).getOwner()).equals(rectList.get(3).getOwner()) && (rectList.get(0).getOwner()).equals(rectList.get(6).getOwner())) && rectList.get(0).getClick())
		{
			owner = rectList.get(0).getOwner();
		}
		else if(((rectList.get(1).getOwner()).equals(rectList.get(4).getOwner()) && (rectList.get(1).getOwner()).equals(rectList.get(7).getOwner())) && rectList.get(1).getClick())
		{
			owner = rectList.get(1).getOwner();
		}
		else if(((rectList.get(2).getOwner()).equals(rectList.get(5).getOwner()) && (rectList.get(2).getOwner()).equals(rectList.get(8).getOwner())) && rectList.get(2).getClick())
		{
			owner = rectList.get(2).getOwner();
		}
		else if(((rectList.get(0).getOwner()).equals(rectList.get(4).getOwner()) && (rectList.get(0).getOwner()).equals(rectList.get(8).getOwner())) && rectList.get(0).getClick())
		{
			owner = rectList.get(0).getOwner();
		}
		else if(((rectList.get(2).getOwner()).equals(rectList.get(4).getOwner()) && (rectList.get(2).getOwner()).equals(rectList.get(6).getOwner())) && rectList.get(2).getClick())
		{
			owner = rectList.get(2).getOwner();
		}
		else {}
		
		if(owner.equals("oh"))
		{
			win = playero;
		}
		else if(owner.equals("ex"))
		{
			win = playerx;
		}
		return win;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
