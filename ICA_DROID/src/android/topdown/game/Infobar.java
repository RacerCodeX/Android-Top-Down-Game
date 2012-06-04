package android.topdown.game;

import android.gameengine.icadroids.objects.GameObject;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Infobar extends GameObject {

	private Paint pt = new Paint();
	private Paint paintLine = new Paint();
	private int viewportX, viewportY;
	private Player player;
	private int screenwidth, screenheight;

	public Infobar(Player player) {
		pt.setColor(Color.BLACK);
		pt.setTextSize(18);
		this.player = player;
		paintLine.setStrokeWidth(8);
	}

	@Override
	public void update() {
		super.update();
		if (player.getHp() > 100) {
			paintLine.setARGB(255, 255, 255, 255);
		} else {
			paintLine.setARGB(255, (int) Math.round(player.getHp()), (int) Math.round(player.getHp() * 2.55), (int) Math.round(player.getHp()));
		}
		// TODO mischien de kleuren hier nog wat afstellen
	}

	@Override
	public void drawGameObject(Canvas canvas) {
		super.drawGameObject(canvas);
		canvas.drawText("HP: ", (float) (viewportX + (screenwidth * .01)), (float) (viewportY + (screenheight * .25)), pt);

		canvas.drawLine((float) (viewportX + (screenwidth * .01) + (pt.getTextSize() * 2.0)), (float) (viewportY + (screenheight * .24)), (float) (viewportX + (screenwidth * .01) + (pt.getTextSize() * 2.0) + (player.getHp())), (float) (viewportY + (screenheight * .24)), paintLine);

		canvas.drawText("Pistol: " + player.getPistol().getAmmo() + "/" + player.getPistol().getAmmoMax(), (float) (viewportX + (screenwidth * .01)), (float) (viewportY + (screenheight * .25) + (pt.getTextSize() + 5)), pt);
		if (player.hasShotgun())
			canvas.drawText("Shotgun: " + player.getShotgun().getAmmo() + "/" + player.getShotgun().getAmmoMax(), (float) (viewportX + (screenwidth * .01)), (float) (viewportY + (screenheight * .25) + (pt.getTextSize() * 2 + 5)), pt);
	}

	public void setPort(int viewportX, int viewportY) {
		this.viewportX = viewportX;
		this.viewportY = viewportY;
	}

	public void setScreenSize(int height, int width) {
		this.screenheight = height;
		this.screenwidth = width;
	}
}
