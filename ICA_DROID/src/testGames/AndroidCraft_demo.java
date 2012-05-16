package testGames;

import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.engine.GameFPSCounter;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.renderer.GameView;
import android.gameengine.icadroids.renderer.Viewport;
import android.gameengine.icadroids.tiles.GameTiles;
import android.graphics.Color;

/**
 * Deze test test de functionaliteiten van de viewport, de gametiles, de
 * GameEngine, onscreenButtons en de Sprite.
 * 
 * @author Edward van Raak
 */
public class AndroidCraft_demo extends GameEngine {

	MoveableGameObject player;

	public AndroidCraft_demo() {
		super();
		player = new Player();
		addPlayer(player, 150, 150);
		player.setSprite("tile2");
		Viewport.useViewport = true;
		GameFPSCounter.USE_FPS_COUNTER = true;
	}

	@Override
	public void update() {

		super.update();

		/*
		 * if (TouchInput.onPress) { player.setSpeed(3);
		 * player.moveTowardsAPoint(TouchInput.xPos, TouchInput.yPos); }
		 */
	}

	@Override
	public void initialize() {
		super.initialize();

		setBackground("bg");
		OnScreenButtons.use = true;
		TouchInput.use = false;

		OnScreenButtons.feedback = true;
		// OnScreenButtons.opacity = 195;
		GameView.BACKGROUND_COLOR = Color.BLACK;

		String[] tileResources = { "tile1", "tile4", "tile5", "tile6" };
		int[][] Map = {
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, 3, 3, -1, -1, -1, -1, 3, 3, -1, -1, -1, -1,
						3, 3, 3, 3, 3, -1, -1, -1, 3, -1, 3, -1, -1, 2, 2, 2,
						2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, 3, -1, 3, -1, -1, -1, 3, -1, 3, -1, -1, -1,
						3, -1, -1, -1, 3, -1, -1, 3, -1, -1, -1, 3, -1, 2, 2,
						2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, 3, -1, -1, 3, -1, -1, 3, -1, -1, 3, -1, -1,
						3, -1, -1, -1, 3, -1, -1, 3, -1, -1, -1, 3, -1, 2, 2,
						2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, 3, -1, -1, 3, -1, -1, 3, -1, -1, 3, -1, -1,
						3, -1, -1, -1, 3, -1, -1, 3, 3, 3, 3, 3, -1, 2, 2, 2,
						2, 2, 2, 2, 2, 2 },
				{ 2, -1, -1, -1, 3, -1, -1, 3, -1, -1, 3, -1, -1, 3, -1, -1, 3,
						-1, -1, -1, 3, -1, -1, 3, -1, -1, -1, 3, -1, 2, 2, 2,
						2, 2, 2, 2, 2, 2 },
				{ 2, -1, -1, -1, 3, -1, 3, -1, -1, -1, 3, -1, 3, -1, -1, -1, 3,
						-1, -1, -1, 3, -1, -1, 3, -1, -1, -1, 3, -1, 2, 2, 2,
						2, 2, 2, 2, 2, 2 },
				{ 2, -1, -1, -1, 3, 3, -1, -1, -1, -1, 3, 3, -1, -1, -1, -1, 3,
						3, 3, 3, 3, -1, -1, 3, -1, -1, -1, 3, -1, 2, 2, 2, 2,
						2, 2, 2, 2, 2 },
				{ 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
						2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
				{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
						2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, };
		GameTiles gt = new GameTiles(tileResources, Map, 64);
		setTileMap(gt);
	}

}
