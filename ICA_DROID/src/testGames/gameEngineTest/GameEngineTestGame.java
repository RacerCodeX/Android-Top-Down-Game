package testGames.gameEngineTest;

import java.util.Random;

import android.gameengine.icadroids.alarms.Alarm;
import android.gameengine.icadroids.alarms.IAlarm;
import android.gameengine.icadroids.forms.IFormInput;
import android.gameengine.icadroids.input.TouchInput;
import android.view.View;

/**
 * Deze test 'game' test alarms, forms, gameObject, MoveableGameObject,
 * gameEngine en TouchInput.
 * 
 * @author Bas
 * 
 */
public class GameEngineTestGame extends DebugEngine implements IAlarm,
		IFormInput {
	TestGameObject testObject = new TestGameObject();
	Alarm am = new Alarm(1, 60, this);
	Alarm am2 = new Alarm(2, 20, this);
	randomObject ro = new randomObject();

	public GameEngineTestGame() {
		System.out.println("Hello tests!");

		addPlayer(testObject, 10, 10);
		addGameObject(ro, 10, 10);
		TouchInput.use = true;

	}

	@Override
	protected void initialize() {
		super.initialize();

		startGame();
	}

	@Override
	public void update() {
		super.update();
		if (TouchInput.onPress) {
			testObject.moveTowardsAPoint(TouchInput.xPos, TouchInput.yPos);
		}
	}

	public void triggerAlarm(int alarmID) {
		super.triggerAlarm(alarmID);
		if (alarmID == 1) {
			randomObject ro = new randomObject();
			Random r = new Random();
			addGameObject(ro, r.nextInt(getScreenHeight()),
					r.nextInt(getScreenWidth()));
			am.restartAlarm();
		}

	}

	public void formElementClicked(View touchedElement) {
		super.formElementClicked(touchedElement);
		System.out.println("geklikt!");
	}
}
