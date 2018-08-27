package com.awasicek;

import java.util.ArrayList;
import java.util.List;

public class GameObjectManager {
	List<Tweet> tweetList = new ArrayList<Tweet>();
	List<NoteOfJustice> noteList = new ArrayList<NoteOfJustice>();
	double tweetTimer = 0;
	double tweetSpawnTime = 2000;
	Player player;
	GameScene scene;
	int tweetCount;
	
	public GameObjectManager(GameScene scene, Player player)
	{
		this.scene = scene;
		this.player = player;
	}
	
	public void addNote(NoteOfJustice n)
	{
		noteList.add(n);
		scene.getPane().getChildren().add(n.getMusicNote());
	}
	
	private void addTweet(Tweet t)
	{
		tweetList.add(t);
	}
	
	public void manageTweets()
	{
		System.out.println("Tweets remaining to be launched: " + Tweet.tweets.size());
		if (Tweet.tweets.size() > 0)
		{
			Tweet t = new Tweet();
			addTweet(t);
			scene.getPane().getChildren().add(t.getTextBubble());
		}
	}
	
	private void purgeObjects()
	{
		// purge dead objects from lists and TODO nodes
		for (int i = 0; i < tweetList.size(); i++)
		{
			if(!tweetList.get(i).getIsAlive())
			{
				scene.getPane().getChildren().remove(tweetList.get(i).getTextBubble());
				tweetList.remove(i);
			}
		}
		
		for (int i = 0; i < noteList.size(); i++)
		{
			if(!noteList.get(i).getIsAlive())
			{
				scene.getPane().getChildren().remove(noteList.get(i).getMusicNote());
				noteList.remove(i);
			}
		}
	}
	
	private void checkCollisions()
	{
		// check for collision between player and tweets
		for(Tweet t : tweetList)
		{
			if(player.getHitBox().intersects(t.getHitBox().getBoundsInLocal()))
			{
				player.setIsAlive(false);
				System.out.println("Tweet smushed player.");
			}
		}
		// check for collision between notes and tweets
		for(Tweet t : tweetList)
		{
			for(NoteOfJustice n : noteList)
			{
				if(t.getHitBox().intersects(n.getHitBox().getBoundsInLocal()))
				{
					t.setIsAlive(false);
					n.setIsAlive(false);
					System.out.println("Note just took out a tweet.");
				}
			}
		}
	}
		
	private void handleOutOfBounds()
	{
		for (int i = 0; i < tweetList.size(); i++)
		{
			Tweet t = tweetList.get(i);
			if (t.getX() < -200) // if tweets go out of bounds, reset them back on the other side and speed the up
			{
				t.resetX();
				t.resetY();
				t.increaseSpeed();
			}
		}
		for (int i = 0; i < noteList.size(); i++)
		{
			if(noteList.get(i).getX() > GameScene.WIDTH + 100)
			{
				noteList.get(i).setIsAlive(false);
				System.out.println("A note just went out of bounds and disintegrated...");
			}
		}
	}
	
	public void update()
	{
		checkCollisions();
		
		handleOutOfBounds();
		
		tweetCount = tweetList.size();

		for (NoteOfJustice n : noteList)
		{
			n.move();
			n.update();
		}
		
		for (Tweet t : tweetList)
		{
			t.move();
			t.update();
		}
		
		purgeObjects();
	}
	
	public int getTweetCount()
	{
		return tweetCount;
	}
}
