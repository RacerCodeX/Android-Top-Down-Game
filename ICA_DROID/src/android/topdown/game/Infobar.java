package android.topdown.game;

import android.gameengine.icadroids.objects.GameObject;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Infobar extends GameObject {

	private Paint pt, hudPaint, paintLine;
	private Rect hudRect, hudSelectRect;
	private int viewportX, viewportY;
	private Player player;
	private int screenwidth, screenheight;
	private int score;

	public Infobar(Player player) {
		hudPaint = new Paint();
		paintLine = new Paint();
		pt = new Paint();
		pt.setColor(Color.BLACK);
		pt.setTextSize(18);
		this.player = player;
		paintLine.setStrokeWidth(8);
		hudPaint.setARGB(67, 255, 255, 255);
		hudSelectRect = new Rect();
		hudRect = new Rect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.gameengine.icadroids.objects.GameObject#update()
	 */
	@Override
	public void update() {
		super.update();
		if (player.getHp() > 100) {
			paintLine.setARGB(255, 255, 255, 255);
		} else {
			paintLine.setARGB(255, (int) Math.round(player.getHp()), (int) Math.round(player.getHp() * 2.55), (int) Math.round(player.getHp()));
		}

		hudRect.left = (int) (viewportX + (screenwidth * .01));
		hudRect.top = (int) (viewportY + (screenheight * .20) - (pt.getTextSize() + 5));
		hudRect.right = (int) (viewportX + (screenwidth * .01) + (pt.getTextSize() * 2.0) + 101);
		if (player.hasShotgun()) {
			hudRect.bottom = (int) (viewportY + (screenheight * .25) + (pt.getTextSize() * 2.0 + 10));
		} else {
			hudRect.bottom = (int) (viewportY + (screenheight * .25) + (pt.getTextSize() + 8));
		}
		if (player.getCurrentGun() instanceof Pistol) {
			hudSelectRect.top = (int) (viewportY + (screenheight * .25) + 5);
			hudSelectRect.bottom = (int) (viewportY + (screenheight * .25) + (pt.getTextSize() + 7));
		} else if (player.getCurrentGun() instanceof Shotgun) {
			hudSelectRect.top = (int) (viewportY + (screenheight * .25) + (pt.getTextSize() + 7));
			hudSelectRect.bottom = hudRect.bottom;
		}
		hudSelectRect.left = hudRect.left;
		hudSelectRect.right = hudRect.right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.gameengine.icadroids.objects.GameObject#drawGameObject(android
	 * .graphics.Canvas)
	 */
	@Override
	public void drawGameObject(Canvas canvas) {
		super.drawGameObject(canvas);
		canvas.drawRect(hudRect, hudPaint);// draw hud background
		canvas.drawRect(hudSelectRect, hudPaint);// show witch gun is selected
		canvas.drawText("Score: " + score, (float) (viewportX + (screenwidth * .01)), (float) (viewportY + (screenheight * .20)), pt);
		canvas.drawText("HP: ", (float) (viewportX + (screenwidth * .01)), (float) (viewportY + (screenheight * .25)), pt);

		canvas.drawLine((float) (viewportX + (screenwidth * .01) + (pt.getTextSize() * 2.0)), (float) (viewportY + (screenheight * .24)), (float) (viewportX + (screenwidth * .01) + (pt.getTextSize() * 2.0) + (player.getHp())), (float) (viewportY + (screenheight * .24)), paintLine);

		canvas.drawText("Pistol: " + player.getPistol().getAmmo() + "/" + player.getPistol().getAmmoMax(), (float) (viewportX + (screenwidth * .01)), (float) (viewportY + (screenheight * .25) + (pt.getTextSize() + 5)), pt);
		if (player.hasShotgun())
			canvas.drawText("Shotgun: " + player.getShotgun().getAmmo() + "/" + player.getShotgun().getAmmoMax(), (float) (viewportX + (screenwidth * .01)), (float) (viewportY + (screenheight * .25) + (pt.getTextSize() * 2 + 5)), pt);
	}

	/**
	 * parse through the current viewport data which the infobar needs
	 * 
	 * @param viewportX
	 *            the current viewportx
	 * @param viewportY
	 *            the current viewporty
	 */
	public void setPort(int viewportX, int viewportY) {
		this.viewportX = viewportX;
		this.viewportY = viewportY;
	}

	/**
	 * let infobar know what the current screensize is
	 * 
	 * @param height
	 *            the screenheight
	 * @param width
	 *            the screenwidth
	 */
	public void setScreenSize(int height, int width) {
		this.screenheight = height;
		this.screenwidth = width;
	}

	/**
	 * let the infobar know what the current score is
	 * 
	 * @param score
	 *            the current score
	 */
	public void setScore(int score) {
		this.score = score;
	}
}