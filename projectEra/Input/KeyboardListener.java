package projectEra.Input;

import org.lwjgl.input.Keyboard;

public class KeyboardListener {
	public void startMenu() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			System.exit(0);
		}
	}
}
