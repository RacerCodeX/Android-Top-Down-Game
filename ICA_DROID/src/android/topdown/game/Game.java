package android.topdown.game;

import android.content.Context;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.input.OnScreenButtons;
import android.gameengine.icadroids.renderer.GameView;
import android.gameengine.icadroids.renderer.Viewport;
import android.gameengine.icadroids.tiles.Tile;
import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class Game extends GameEngine {

	public static final int MAX_ZOMBIES = 80;
	
	private Player player;
	private Level level;
	private Infobar info;
	private Viewport port;
	Thread soundLoad;

	private int numZom;

	public Game() {
		super();
		level = new Level("map");
		player = new Player();
		info = new Infobar(player);
		addPlayer(player, 0, 0);
		addGameObject(info);
		Viewport.useViewport = true;
		if(SoundLib.loading())
			SoundLib.startLoad();
	}

	@Override
	public void initialize() {
		super.initialize();

		info.isVisible = true;

		player.setPosition(super.getScreenWidth() / 2 - player.getSprite().getFrameWidth() / 2, super.getScreenHeight() / 2 - player.getSprite().getFrameHeight() / 2);
		player.isVisible = true;

		OnScreenButtons.use = true;
		OnScreenButtons.feedback = true;
		OnScreenButtons.opacity = 100;

		GameView.BACKGROUND_COLOR = Color.BLACK;

		level.initialize();

		setTileMap(level.getGameTiles());

		port = Viewport.getInstance();
		port.setBounds(0, 0, Level.TILE_SIZE * level.getGameTiles().getMapWidth(), Level.TILE_SIZE * level.getGameTiles().getMapHeigth());
		port.setPlayer(player);
		port.setPlayerPositionTolerance(0.0, 0.0);
		port.setPlayerPositionOnScreen(Viewport.PLAYER_VCENTER | Viewport.PLAYER_HCENTER);
		Display display = ((WindowManager) GameEngine.getAppContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		info.setScreenSize(display.getHeight(), display.getWidth());
	}

	@Override
	public void update() {
		super.update();
		if (Math.round(Math.random()) == 0 && numZom < MAX_ZOMBIES)
			spawnZombie();

		if (OnScreenButtons.start)
			player.setPosition(player.getFullX() + 64, player.getFullY() + 64);
		if (OnScreenButtons.select)
			player.setPosition(player.getFullX() - 64, player.getFullY() - 64);

		info.setPort(port.getViewportX(), port.getViewportY());
		info.setPosition(port.getViewportX() + 200, port.getViewportY() + 200);
	}

	private void spawnZombie() {

		Tile[][] tile = level.getGameTiles().getTileArray();
		int x = (int) (Math.random() * (tile.length*Level.TILE_SIZE));
		int y = (int) (Math.random() * (tile[0].length*Level.TILE_SIZE));

		if (notInWall(x,y)&&outSideViewport(x,y)) {
			addGameObject(new Zombie(100, 1, 5, player), x, y);
			numZom++;
			int xx = x/(Level.TILE_SIZE*Level.TILE_SIZE);
			int yy = y/(Level.TILE_SIZE*Level.TILE_SIZE);
			Log.d("ZombieSpawn", "spawned at: ("+x+","+y+") or ["+xx+"]["+yy+"] on ID: " + level.getGameTiles().getTileArray()[xx][yy].getTileType());
			Log.d("PlayerLoc", "player at at: (" + (int) player.getFullX() / Level.TILE_SIZE + "," + (int) player.getFullY() / Level.TILE_SIZE + ") on ID: " + level.getGameTiles().getTileArray()[(int) player.getFullX() / Level.TILE_SIZE][(int) player.getFullY() / Level.TILE_SIZE].getTileType());
		} else {
			Log.d("ZombieSpawn","failed");
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return true if outside viewport else false
	 */
	private boolean outSideViewport(int x, int y) {
		if((x > port.getViewportX()||(x+Level.TILE_SIZE) < port.getViewportX() + getScreenWidth())&& 
				(y > port.getViewportY()||(y+Level.TILE_SIZE) < port.getViewportY() + getScreenHeight())){
			return true;
		}
		return false;
	}

	private boolean notInWall(int x, int y) {
		Tile[][] tile = level.getGameTiles().getTileArray();
		int xx = x/Level.TILE_SIZE;
		int yy = y/Level.TILE_SIZE;
		if(xx+1>=tile.length||yy+1>=tile.length){
			Log.d("ZombieSpawn","attempting spawn at ("+x+","+y+") or ["+xx+"]["+yy+"]");
			return false;
		}
		int top = tile[xx][yy].getTileType();
		int right = tile[xx+1][yy].getTileType();
		int bottom = tile[xx+1][yy+1].getTileType();
		int left = tile[xx][yy+1].getTileType();

		Log.d("ZombieSpawn","attempting spawn at ("+x+","+y+") or ["+xx+"]["+yy+"] on ID: " + level.getGameTiles().getTileArray()[xx][yy].getTileType());
		return top!=Level.ID_WALL&&right!=Level.ID_WALL&&bottom!=Level.ID_WALL&&left!=Level.ID_WALL;
	}
}