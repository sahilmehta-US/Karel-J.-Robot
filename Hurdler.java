import kareltherobot.*;
import java.util.Scanner;

public class Hurdler {
	Robot hurdler = new Robot(1,1,Directions.East, 0);

	public static void main(String[] args) {
		new JumpThoseHurdles().start();
	}

	//turns the robot right
	public static void turnRight(Robot rob) {
		for (int i = 0; i< 3; i++) {
			rob.turnLeft();
		}
	}

	//loads the world and moves the robot
	public void start() {
		loadWorld();
		int greatestHeight = Integer.MIN_VALUE;
		int smallestLength = Integer.MAX_VALUE;
		int count = 0;
		double avgHeight = 0.0;
		double avgLength = 0.0;
		int smallestHeight = Integer.MAX_VALUE;
		int greatestLength = Integer.MIN_VALUE;
		while (!hurdler.nextToABeeper()) {
			int w = findHurdle();
			int h = climbHurdle();
			clearHurdle();
			if (greatestHeight < h) {
				greatestHeight = h;
			} 
			if (smallestHeight > h) {
				smallestHeight = h;
			}
			if (smallestLength > w) {
				smallestLength = w;
			} 
			if (greatestLength < w) {
				greatestLength = w;
			}
			count++;
			avgHeight += h;
			avgLength += w;
		}

		System.out.println("Greatest hurdle height is: " + greatestHeight);
		System.out.println("Smallest hurdle height is: " + smallestHeight);
		System.out.println("Smallest distance between hurdle is: " + smallestLength);
		System.out.println("Greatest distance between hurdle is: " + greatestLength);
		System.out.println("Average hurdle height is: " + (avgHeight/count));
		System.out.println("Average distance between hurdle is: " + (avgLength/count));
	}

	//moves the robot to the next hurdle
	private int findHurdle() {
		World.setDelay(2);
		int count = 0;
		while (hurdler.frontIsClear()) {
			hurdler.move();
			count++;
		}
		return count;
	}

	//moves the robot above the hurdle
	private int climbHurdle() {
		World.setDelay(2);
		int count = 0;
		while (!hurdler.frontIsClear()) {
			hurdler.turnLeft();
			hurdler.move();
			turnRight(hurdler);
			count++;
		}
		return count;
	}

	//moves the robot over the hurdle and back to the ground.
	private void clearHurdle() {
		World.setDelay(2);
		hurdler.move();
		turnRight(hurdler);
		while (hurdler.frontIsClear()) {
			hurdler.move();
		}
		hurdler.turnLeft();
	}

	//generates the world
	private void loadWorld() {
		Scanner input = new Scanner(System.in);
		System.out.println("Which world? ");
		String worldChar = input.nextLine();
		input.close();
		String worldName = "world";
		worldName += worldChar;
		worldName += ".wld" ;
		World.readWorld(worldName);
		World.setVisible(true);
		World.setDelay(5);
	}
}

public class Main {
  public static void main(String[] args) {
    JumpThoseHurdles.main(args);
  }
}
