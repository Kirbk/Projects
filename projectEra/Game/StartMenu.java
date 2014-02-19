package projectEra.Game;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;


public class StartMenu {
	TrueTypeFont font;
	
	public StartMenu() {
		glBegin(GL_QUAD_STRIP);
		{
			glVertex2f(100, 100);
			glVertex2f(200, 100);
			glVertex2f(100, 200);
			glVertex2f(200, 200);
		}
		glEnd();
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		font = new TrueTypeFont(awtFont, true);
		
		
		for (int i = 0; i < 10; i++) {
			glClear(GL_COLOR_BUFFER_BIT);
			font.drawString(100, 100, Integer.toString(i));
		}
	}
}
