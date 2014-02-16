package projectEra.Game;

import static org.lwjgl.opengl.GL11.*;

public class StartMenu {
	public StartMenu() {
		glBegin(GL_QUAD_STRIP);
		{
			glVertex2f(100, 100);
			glVertex2f(200, 100);
			glVertex2f(100, 200);
			glVertex2f(200, 200);
		}
		glEnd();
	}
}
