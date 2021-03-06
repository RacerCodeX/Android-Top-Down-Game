package android.topdown.game;

public class GunPickup extends Pickup {

	public static final int TYPE_PISTOL = 0;
	public static final int TYPE_SHOTGUN = 1;
	private static final String PISTOL_SPRITE = "pistolpickup";
	private static final String SHOTGUN_SPRITE = "shotgunpickup";

	/**
	 * @param x
	 *            x location for the pickup to spawn in pixels
	 * @param y
	 *            y location for the pickup to spawn in pixels
	 * @param type
	 *            the gun type that the ammo is for
	 * @param amount
	 *            the amount of ammo
	 * @param respawnrate
	 *            the amount of time it wil take for the object to respawn
	 */
	public GunPickup(int x, int y, int type, int amount, int respawnrate) {
		super(x, y, gunSprite(type), respawnrate,type, amount);
	}

	private static String gunSprite(int type) {
		if (type == TYPE_PISTOL)
			return PISTOL_SPRITE;
		else if (type == TYPE_SHOTGUN)
			return SHOTGUN_SPRITE;
		return "missingimage";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.topdown.game.Pickup#pickupEvent(android.topdown.game.Player)
	 */
	@Override
	public void pickupEvent(Player player) {
		player.giveGun(giveGun());
		super.pickupEvent(player);
	}

	/**
	 * Resolve the gun type that we wil give the player
	 * 
	 * @return the gun
	 */
	private Gun giveGun() {
		if (getType() == TYPE_PISTOL) {
			return new Pistol(getAmount());
		} else if (getType() == TYPE_SHOTGUN) {
			return new Shotgun(getAmount());
		} else {// als de type niet bestaat geef dan een lege pistol om
				// problemen te voorkomen
			return new Pistol(0);
		}
	}

}
