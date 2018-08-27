package com.awasicek;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/*
 * Major TODO list:
 * 1. Implement rest of game features and improve look and feel.
 * 2. Refactor and polish passes of code.
 * 3. Restructure code around established design patterns -- e.g., controllers for scenes.
 * 4. Clean up and improve error handling.  Also sanitize user inputs -- especially input of enemy Twitter username if no Tweets found 
 * 	  and based on possible error code received from Twitter.
 * 5. Additional commenting, especially in JavaFX sections.
 * 6. Put API/secret/tokens in more appropriate and secure location.
 * 7. Use full oAuth to access Twitter API endpoints that require more than just a bearer token.
 */

public class TweetFighter extends Application
{
	static boolean victoryState = false; // store game's victory/failure status
	static Stage window;
	
    public static void main(String[] args)
    {
        launch(args);
    }

    // main stage that will house all scenes
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		window.setTitle("Tweet Fighter v1.0");
		window.setScene(new IntroScene(new Pane()));
		window.show();
		window.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override public void handle(WindowEvent t) {
		        System.out.println("Exiting Tweet Fighter v1.0");
		        System.exit(0);
		    }
		});
	}
}