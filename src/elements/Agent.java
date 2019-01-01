package elements;

public class Agent {
	private int pos[] = new int[2];

	public Agent(int setPosX, int setPosY) {
		pos[0] = setPosX;
		pos[1] = setPosY;
	}

	public void setPosition(int newPosX, int newPosY) {
		pos[0] = newPosX;
		pos[1] = newPosY;
	}

	public int getX() {
		return pos[0];
	}

	public int getY() {
		return pos[1];
	}

	public int[] getPosition() {
		return pos;
	}

}
