package android.topdown.game;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;

public abstract class Gun implements IAlarm {
	private int ammoMax, damage, rate, ammo;
	private boolean canShoot;

	public Gun(String sprite, int ammo, int ammoMax, int damage, int rate) {
		this.ammo = ammo;
		this.ammoMax = ammoMax;
		this.damage = damage;
		this.rate = rate;
		canShoot = true;
	}

	public int getAmmo() {
		return ammo;
	}

	public int getAmmoMax() {
		return ammoMax;
	}

	public int getDamage() {
		return damage;
	}

	public void addAmmo(int ammo) {
		this.ammo += ammo;
		if (this.ammo > ammoMax)
			this.ammo = ammoMax;
	}

	public boolean isEmpty(){
		if(ammo<=0){
			SoundLib.play(SoundLib.SFX_GUNCLICK);
			new Alarm(1, 10, this);
			canShoot=false;
			return true;
		}
		return false;
	}
	
	public boolean canShoot(){
		return canShoot;
	}
	
	public void shot(){
		ammo--;
		canShoot = false;
		new Alarm(1, rate, this);
	}

	public boolean alarmsActiveForThisObject() {
		return !canShoot;
	}

	public void triggerAlarm(int alarmID) {
		System.out.println("alarm triggered");
		canShoot = true;
	}

	public abstract void shoot(double x, double y, int rotation);
}
