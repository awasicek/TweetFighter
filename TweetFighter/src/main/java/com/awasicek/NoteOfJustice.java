package com.awasicek;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class NoteOfJustice {
	private double x;
	private double y;
	private double noteSpeed = 15;
	private Image noteImg;
	private ImageView noteGfx;
	private double imageWidth;
	private double imageHeight;
	private Rectangle hitBox;
	private boolean isAlive = true;
	
	public NoteOfJustice(double x, double y)
	{
		this.x = x;
		this.y = y;
		noteImg = new Image(this.getClass().getResource("/music_note.jpg").toString());
		noteGfx = new ImageView();
		noteGfx.setImage(noteImg);
		imageWidth = noteImg.getWidth();
		imageHeight = noteImg.getHeight();
		hitBox = new Rectangle(x, y, imageWidth, imageHeight);
		
	}
	
	public void move()
	{
		x += noteSpeed; // notes move right
	}
	
	public void update()
	{
		noteGfx.setLayoutY(y);
		noteGfx.setLayoutX(x);
		hitBox.setY(y);
		hitBox.setX(x);
	}
	
	public double getX()
	{
		return x;
	}
	
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	
	public boolean getIsAlive()
	{
		return isAlive;
	}
	
	public void setIsAlive(boolean isAlive)
	{
		this.isAlive = isAlive;
	}
	
	public ImageView getMusicNote()
	{
		return noteGfx;
	}
	
	
}
