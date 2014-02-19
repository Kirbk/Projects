package projectEra.Core;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import projectEra.Game.StartMenu;
import projectEra.Input.KeyboardListener;

public class Start {
	GameStates cgs = GameStates.startMenu;
	
	static boolean failed;
	static boolean antiAlias = true;
	
    
	//Initiate the whole program
	public static void main(String args[]) {
		lastFrame = getTime();
		
		initDisplay();
		initGraphics();
		new Start().gameLoop();
		cleanUp();
	}
	
	//Initialize graphics
	public static void initGraphics() {
		glEnable(GL_TEXTURE_2D);
		glShadeModel(GL_SMOOTH);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_LIGHTING);
		
		glClearDepth(1);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glMatrixMode(GL_MODELVIEW);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	//Initiate inputs
	public void getInput() {
		switch (cgs) {
		case startMenu:
			new KeyboardListener().startMenu();
		default:
			break;
		}
	}
	
	//Initiate display
	public static void initDisplay() {
			DisplayMode[] modes = null;
			try {
				modes = Display.getAvailableDisplayModes();
			} catch (LWJGLException e1) {
				e1.printStackTrace();
			}
			
			
			DisplayMode current = null;
			String s = null;
			for (int i=0;i<modes.length;i++) {
				current = modes[i];
				System.out.println(current.getWidth() + "x" + current.getHeight() + "x" + current.getBitsPerPixel() + " " + current.getFrequency() + "Hz");
				s = s + current.getWidth() + "x" + current.getHeight();
			}
			
			if (!s.contains("1366x768")) {
				resolutionError();
				
				failed = true;
			}else {
				try{
					Display.setTitle("Project Era Version 0.0.1 Indev");
					Display.setInitialBackground(0.0f, 0.4f, 0.4f);
					Display.setFullscreen(true);
					Display.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//Game loop
	public void gameLoop() {
		if (!failed) {
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
		}else {
			while (!Display.isCloseRequested()) {
				Display.update();
			}
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
	
	public static void resolutionError() {
		JFrame f = new JFrame("Failure");
		JTextField t = new JTextField();
		JPanel p = new JPanel();
		t.setText("Failed to start, incompatible resolution");
		t.setEditable(false);
		f.setSize(new Dimension(250, 60));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setResizable(false);
		p.add(t);
		f.add(p);
	}
}
