![bird](https://github.com/awasicek/TweetFighter/blob/master/twitter_bird_xx.jpg?raw=true)
# Tweet Fighter

##### by Andrew Wasicek

-----------

## Vision Description

In today's world, Twitter has turned into a platform from which many users broadcast messages that others find seriously offensive.   These offensive Twitter users range from anonymous internet trolls all the way to world leaders.  In order to allow everyday humans to cope with the constant bombardment of uncivil and derogatory tweets, Tweet Fighter must be made.  Tweet Fighter is the application that will allow you, the user, to fight back against these tweets — literally.  

Harnessing the power of technologies such as JavaFX and [Twitter's own public API](https://developer.twitter.com/en/docs.html), Tweet Fighter allows a player to fight their Twitter enemy (represented by their Twitter profile image) in a battle to the death.  While the Twitter enemy is a formidable opponent, being able to hurl offensive tweets from their recent history at the player, they are not unstoppable.  With skill, the user can fight and destroy these outrageous social media outbursts and ultimately vanquish their Twitter enemy.  

## Requirements

(1) Multiple states: start, game, end.

(2) AJAX calls to Twitter API to fetch tweets and user profile for the chosen opponent

(3) Game engine (e.g., use Timer class and action listener to draw frames)

(4) Gameplay where the user "fights" the oncoming tweets

## Mockups

![intro screen](https://github.com/awasicek/TweetFighter/blob/master/Tweet%20Fighter%20Mockup%20-%20Intro%20Screen.png?raw=true)

![battle screen](https://github.com/awasicek/TweetFighter/blob/master/Tweet%20Fighter%20Mockup%20-%20Battle%20Screen.png?raw=true)

## Implementation Details/Plans (subject to change)

(1) Win condition: reduce the enemy's health to zero.  Failure/loss condition: player's health is reduced to zero.  The player will have a set amount of health.

(2) Tweets that the enemy uses against the player will be populated by a call to the [Twitter Standard Search endpoint](https://developer.twitter.com/en/docs/tweets/search/api-reference/get-search-tweets.html).  The enemy's health will be directly proportional to the number of tweets found.  Thus, an enemy will be harder depending on the number of tweets found for the particular Twitter handle (up to the limit set by the Standard Twitter API, and possibly further capped by Tweet Fighter).  Destroying a tweet will diminish the enemy's health by a corresponding amount (and remove it from the pool of tweets that the enemy uses). Tweets that are not destroyed (that hit the user) will go back into the pool of tweets that the enemy will keep using. Thus, defeating an enemy will require that all of their Tweets be destroyed eventually before the player's health is eliminated.

(3) On the introduction screen, after the user enters a twitter handle for their opponent and presses start, the next screen will likely be a loading/error screen where the call to the Twitter API will be made and if tweets are not received then an appropriate error message will be displayed and the user will be sent back to the start screen.  If the call(s) is/are successful, once the collections that contain the enemy's tweet arsenal are populated and the enemy's image data is loaded, the application will enter its next state: the game itself.

(4) The player's score will go up each time a tweet is destroyed. A bonus amount will be added to the score when the enemy is destroyed.  If time permits, data persistence will be implemented for scores (perhaps by using the Properties class). Higher scores will be achieved by eliminating more powerful enemies (primarily defined by the number of tweets that they have, which will increase their maximum health, which in turn should make the game more difficult).  A score multiplier could be added in the future for easy/regular/hard modes that could perhaps multiply the speed at which tweets fly toward the user (e.g., easy 80% of normal speed and points, normal 100% of normal speed and points, hard 120% of normal speed and points).

(5) The enemy avatar will be represented by the [Twitter user's profile image](https://developer.twitter.com/en/docs/accounts-and-users/user-profile-images-and-banners).  If the user has a default profile image, another image will be used (most likely the "slightly used, X X eyes" Twitter bird image at the top of this markdown).

(6) The player's ability to move will likely be controlled by WASD keyboard inputs and attacking with the spacebar or maybe mouse (possibly a kick on a cooldown or firing some projectile aimed with the mouse, also on a cooldown).

(7) Collision detection between tweets and the player will have to be implemented.  Implementing trajectories for the tweet projectiles that pose an enjoyable and challenging experience will also have to be implemented.  It is very likely that the tweets will have a homing element to their trajectory so that they will tend to head toward the player.  If the tweet projectile misses it will either be re-added to the enemy's remaining tweet "arsenal" or start the trajectory again from the opposite end of the screen from which it exited.  My many years of physics education should finally come in handy ;)

(8) The frequency with which the enemy "fires" tweets will likely escalate the longer the game goes on.  This will likely be implemented through the use of timestamps and applying speed multipliers after predetermined periods of elapsed time.

(9) The enemy will likely move in a small area so that the tweets originate from different absolute positions.  Relative to the enemy, the tweets will likely emanate from above enemy, below the enemy, and to the left of the enemy. The player's area of movement will not be nearly as constrained, although time permitting it may be warranted to add some limits (e.g., gradually reducing the speed by which the player may travel to the right the closer they get to the enemy -- verisimilitude could be preserved by adding a wind blowing animation as the background representing the enemy bellowing at the player).

(10) Upon defeating an enemy or the player being defeated, the last game state will be entered which will be a victory/defeat screen with score information.  After this victory/defeat screen, the game will go back to its first state: the introduction screen state.
