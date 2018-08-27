package com.awasicek;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/*
 * Intro scene that begins game and takes user
 * input to determine the Twitter user to get tweets from
 * and then launches the main part of the game.
 */
public class IntroScene extends Scene {

	static final double WIDTH = 1200;
	static final double HEIGHT = 800;
	Label mainLabel = new Label("Tweet Fighter v1.0");
	Label infoLabel = new Label();
	TextField inputTwitterHandle = new TextField();
	Button submitHandle = new Button("Fight!");
	ImageView note = new ImageView();
	ImageView loadingGfx = new ImageView();
	
	public IntroScene(Pane root) {
		super(root, WIDTH, HEIGHT);
		
		// white background
		root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		// styling for components
		this.getStylesheets().add(this.getClass().getResource("/TweetFighter.css").toString());
		
		// TODO remove rest of magic numbers
		mainLabel.setLayoutX((WIDTH / 2) - 200);
		mainLabel.setLayoutY((HEIGHT / 2) - 300);
		mainLabel.setId("intro-mainLabel");
		
		inputTwitterHandle.setPromptText("Enter username of opponent...");
		inputTwitterHandle.setLayoutX((WIDTH / 2) - 125);
		inputTwitterHandle.setLayoutY(HEIGHT / 2);
		inputTwitterHandle.setPrefWidth(200);
		
		submitHandle.setLayoutX((WIDTH / 2) + 100);
		submitHandle.setLayoutY(HEIGHT / 2);
		submitHandle.setPrefHeight(26);
		submitHandle.setId("intro-submitButton");
		
		note.setImage(new Image(this.getClass().getResource("/music_note.jpg").toString()));
		note.setLayoutX((WIDTH / 2) - 250);
		note.setLayoutY(HEIGHT / 2);
		
		infoLabel.setId("intro-infoLabel");
		infoLabel.setPrefSize(400, 200);
		infoLabel.setLayoutX(((WIDTH / 2) - 200));
		infoLabel.setLayoutY((HEIGHT / 2) + 100);
		infoLabel.setText("Shoot the tweets with your notes of justice to win. Be careful not to get hit or you will lose. "
				+ "If a tweet gets by you, it will come again except even faster! Arrow keys for movement, spacebar to fire.");
		infoLabel.setWrapText(true);
		
		loadingGfx.setImage(new Image(this.getClass().getResource("/loading.gif").toString()));
		loadingGfx.setLayoutX((WIDTH / 2) - 125);
		loadingGfx.setLayoutY((HEIGHT / 2) - 150);
		
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				TwitterService.screenName = inputTwitterHandle.getText();
				// root.getChildren().removeAll(inputTwitterHandle, submitHandle);
				root.getChildren().clear();
				// TODO fix loading issue where the graphic is not displayed before/while waiting for Twitter response 
				// works if comment out TwitterService calls, so maybe thread issue??
				root.getChildren().add(loadingGfx);
				TwitterService ts = new TwitterService();
				ts.connect();
				TweetFighter.window.setScene(new GameScene(new Pane()));
			}
		};
		submitHandle.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		root.getChildren().add(inputTwitterHandle);
		root.getChildren().add(submitHandle);
		root.getChildren().add(mainLabel);
		root.getChildren().add(note);
		root.getChildren().add(infoLabel);
		submitHandle.requestFocus(); // set focus on button (something other than textfield so prompt text appears at first)

	}

}
