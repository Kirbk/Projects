package projectEra.Core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

import projectEra.Game.StartMenu;
import projectEra.Input.KeyboardListener;

public class Start {
	GameStates cgs = GameStates.startMenu;
	
	//Initiate the whole program
	public static void main(String args[]) {
		lastFrame = getTime();
		
		initGraphics();
		initDisplay();
		new Start().gameLoop();
		cleanUp();
	}
	
	//Initialize graphics
	public static void initGraphics() {
		
	}
	
	//Initiate inputs
	public void getInput() {
		switch (cgs) {
		case startMenu:
			new KeyboardListener().startMenu();
			System.out.println("Doesn't Work");
		default:
			System.out.println("Works");
			break;
		}
	}
	
	//Initiate display
	public static void initDisplay() {
		try {
			Display.setTitle("Project Era Version 0.0.1 Indev");
			Display.setInitialBackground(0.0f, 0.4f, 0.4f);
			Display.setFullscreen(true);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	//Game loop
	public void gameLoop() {
		while (!Display.isCloseRequested()) {
			new Start().getInput();
			
			glClear(GL_COLOR_BUFFER_BIT);  
			glMatrixMode(GL_PROJECTION);
		    glLoadIdentity();
		    glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
			switch(cgs) {
			case startMenu:
				new StartMenu();
				break;
			default:
				break;
			}
			
			Display.update();
		}
	}
	
	//Closes the program
	public static void cleanUp() {
		Display.destroy();
	}
	
	//Start movement delta handler
	private static long lastFrame;
	
	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private static double getDelta() {
		long currentTime = getTime();
		double delta = (double) currentTime - (double) lastFrame;
		lastFrame = getTime();
		return delta;
	}
	
	//End movement delta handler
}
