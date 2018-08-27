package com.awasicek;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class FinalScene extends Scene {

	static final double WIDTH = 1200;
	static final double HEIGHT = 800;
	Label endLabel = new Label("GAME OVER");
	Button resetButton = new Button("Play Again");
	Button exitButton = new Button("Exit");

	public FinalScene(Pane root) {
		super(root, WIDTH, HEIGHT);
		
		// white background
		root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		// styling for components
		this.getStylesheets().add(this.getClass().getResource("/TweetFighter.css").toString());
		
		if(TweetFighter.victoryState) 
		{
			endLabel.setText("GAME OVER - YOU WIN!");
		} 
		else 
		{
			endLabel.setText("GAME OVER - YOU LOSE.");
		}
		endLabel.setLayoutX((WIDTH / 2) - 250);
		endLabel.setLayoutY((HEIGHT / 2) - 300);
		endLabel.setId("final-endLabel");
		
		resetButton.setLayoutX((WIDTH / 2) + 25);
		resetButton.setLayoutY(HEIGHT / 2);
		resetButton.setPrefHeight(26);
		resetButton.setId("final-resetButton");

		exitButton.setLayoutX((WIDTH / 2) - 75);
		exitButton.setLayoutY(HEIGHT / 2);
		exitButton.setPrefHeight(26);
		exitButton.setId("final-exitButton");
		
		// handle clicks to exit or restart the game
		EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Button buttonPressed = (Button) event.getSource();
				
				if(buttonPressed == resetButton) 
				{
					TweetFighter.window.setScene(new IntroScene(new Pane()));
				} 
				else if (buttonPressed == exitButton)
				{
			        System.out.println("Exiting Tweet Fighter v1.0");
			        System.exit(0);
				}
			}
		};
		resetButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
		root.getChildren().add(endLabel);
		root.getChildren().add(resetButton);
		root.getChildren().add(exitButton);
		
	}

}
