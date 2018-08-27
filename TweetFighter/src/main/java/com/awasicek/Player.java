package com.awasicek;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Player {
	private double xPos = 100;
	private double yPos = 100;
	private double playerSpeed = 5;
	private Image playerImg;
	private ImageView playerGfx;
	private double imageWidth;
	private double imageHeight;
	private Rectangle hitBox;
	private boolean isAlive = true;
	
	public Player() {
		playerImg = new Image(this.getClass().getResource("/twitter_bird_xx.jpg").toString());
		playerGfx = new ImageView();
		playerGfx.setImage(playerImg);
		playerGfx.setX(xPos);
		playerGfx.setY(yPos);
		imageWidth = playerImg.getWidth();
		imageHeight = playerImg.getHeight();
		System.out.println("image width : " + imageWidth);
		System.out.println("image height : " + imageHeight);
		hitBox = new Rectangle(xPos, yPos, imageWidth, imageHeight);
	}
	
	// Movement methods
	public void goRight()
	{
		xPos += playerSpeed;
	}
	
	public void goLeft()
	{
		xPos -= playerSpeed;
	}
	
	public void goDown()
	{
		yPos += playerSpeed;
	}
	
	public void goUp()
	{
		yPos -= playerSpeed;
	}
	
	// Getters
	public Image getImage()
	{
		return playerImg;
	}
	
	public double getX()
	{
		return xPos + (imageWidth / 2);
	}
	
	public double getY()
	{
		return yPos + (imageHeight / 2);
	}
	
	public ImageView getPlayerGfx()
	{
		return playerGfx;
	}
	
	public double getPlayerSpeed()
	{
		return playerSpeed;
	}
	
	public double getImageWidthOffset()
	{
		return imageWidth / 2;
	}
	
	public double getImageHeightOffset()
	{
		return imageHeight / 2;
	}
	
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	
	// Setters
	public void setX(double xPos)
	{
		this.xPos = xPos;
	}
	
	public void setY(double yPos)
	{
		this.yPos = yPos;
	}
	
	public void setPlayerSpeed(double speed)
	{
		playerSpeed = speed;
	}
	
	public void setIsAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}
	
	public void update()
	{
		playerGfx.setY(yPos);
		playerGfx.setX(xPos);
		hitBox.setY(yPos);
		hitBox.setX(xPos);
	}
	
}
