package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.sun.glass.ui.View;

import javafx.application.Application;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TicTacToe extends Application{
	
	ArrayList<myRectangle> rectList;
	FileInputStream srcx;
	FileInputStream srco;
	Image oh;
	Image ex;
	Pane root;
	VBox vbox;
	StackPane stackP;
	Pane layer1;
	ImagePattern view;
	Text text;
	int count;
	String draw = "DRAW";
	String playerx = "Congratulations, X win the game";
	String playero = "Congratulations, O win the game";
	Button restart = new Button("Restart");
	
	public TicTacToe() throws FileNotFoundException
	{
		rectList = new ArrayList<>();
		srcx = new FileInputStream("C:\\Users\\onwuc\\Eclipse2019\\TicTacToe\\x.jpg"); 
		srco = new FileInputStream("C:\\Users\\onwuc\\Eclipse2019\\TicTacToe\\o.jpg"); 
		oh = new Image(srco); 
		ex = new Image(srcx);
		root = new Pane();
		vbox = new VBox();
		stackP = new StackPane();
		layer1 = new Pane();
		view = new ImagePattern(oh);
		text = new Text();
		count = 0;
	}

	
	
	@Override
	public void start(Stage primary) throws Exception { 
		
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
	    Button btn = new Button("Heyy");
	    
	    stackP.getChildren().add(layer1);
	    stackP.getChildren().add(text);
	    vbox.getChildren().add(btn);
	    vbox.getChildren().add(stackP);
//	    vbox.getChildren().add(restart);
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
	    
	    Scene scene  = new Scene(root, 600, 655);
	    primary.setTitle("Tic Tac Toe");
		primary.setScene(scene);
		primary.show();
	}
	
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
	
	public void setView()
	{
		if(view.getImage() == ex)
		{
			view = new ImagePattern(oh);
		}
		else
		{
			view = new ImagePattern(ex);
		}
	}
	
	public void increaseCount()
	{
		count++;
		checkGameOver();
	}
	
	public String imageString(ImagePattern img)
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
	
	public void checkGameOver() 
	{
//		String temp = "";
		if(count >= 9 )
		{
			text.setText(draw);
			text.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
			vbox.getChildren().add(restart);
		}
		else {}
		System.out.println("eyyy");
		
		text.setText(checkWin());
		text.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
//		vbox.getChildren().add(restart);
		
	}

	public String checkWin()
	{ 
		String win = "";
		String owner = "";
		System.out.println("eyyyyyyyyy");
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