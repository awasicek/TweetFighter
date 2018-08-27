package com.awasicek;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

public class Tweet {
	public static List<String>tweets = new ArrayList<String>();
	
	private final static double OFFSET = 125;
	private double x;
	private double y;
	private double speed = 2;
	private Label textBubble;
	boolean isAlive = true;
	private Rectangle hitBox;
	private final double TWEET_WIDTH = 200;
	private final double TWEET_HEIGHT = 100;
	
	public Tweet()
	{
		x = GameScene.WIDTH;
		y = new Random().nextInt((int)(GameScene.HEIGHT - 2 * OFFSET)) + OFFSET;
		if (tweets.size() > 0) {
			textBubble = new Label(tweets.get(0));
			// TODO make the Tweet class itself extend Label
			textBubble.setStyle("-fx-border-color: black; -fx-padding: 5px; -fx-border-radius: 5px;");
			textBubble.setPrefSize(TWEET_WIDTH, TWEET_HEIGHT);
			textBubble.setWrapText(true);
			hitBox = new Rectangle(x, y, TWEET_WIDTH, TWEET_HEIGHT);
			tweets.remove(0);
			System.out.println("new tweet with text: " + textBubble.getText());
			System.out.println("new tweet's x position: " + x + " and y position: " + y);
		} else {
			System.out.println("no tweets left");
		}
	}
	
	public void move()
	{
		x -= speed; // tweets move left
	}
	
	public void update()
	{
		textBubble.setLayoutX(x);
		textBubble.setLayoutY(y);
		hitBox.setX(x);
		hitBox.setY(y);
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public Label getTextBubble()
	{
		return textBubble;
	}
	
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	public void resetX()
	{
		x = GameScene.WIDTH;
	}
	
	public void resetY()
	{
		y = new Random().nextInt((int)(GameScene.HEIGHT - 2 * OFFSET)) + OFFSET;
	}
	
	public void setIsAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}
	
	public void increaseSpeed()
	{
		speed += 1;
	}
	
}
