import java.util.Scanner;
import javax.print.DocFlavor.INPUT_STREAM;
import javax.swing.*;
import kareltherobot.*;

public class RoomCleaner implements Directions {
	private Robot roomba; 
	private int greatestPileNum = 0;
	private int greatestPileBeepers = 0;
	private int roomLength = 0;
	private int roomWidth = 0;
	private int numPile = 0;
	private int totalNumBeepers = 0;
	private int greatestPileStreet = 0;
	private int greatestPileAvenue = 0;

	private int startStreet;
	private int startAvenue;
	public static void main(String[] args) {
		Driver d = new Driver();
		d.getInfo();
		d.cleanRoom();
		d.displayResults();
	}
	private void getInfo() {
		JFrame f = new JFrame();
		String wrldName = JOptionPane.showInputDialog(f, "Which world would you like?");
		JFrame f2 = new JFrame();
		startStreet = Integer.parseInt(JOptionPane.showInputDialog(f2, "Which street should the roomba be on?"));
		startAvenue = Integer.parseInt(JOptionPane.showInputDialog(f2, "Which avenue should the roomba be on?"));
		String direction = JOptionPane.showInputDialog(f2, "Which direction should the roomba face?");
		Direction theDirection = South;
		switch (direction) {
			case "East": 
				theDirection = East;
				break;
			case "West":
				theDirection = West;
				break;
			case "North":
				theDirection = North;
				break;
			case "South":
				theDirection = South;
				break;
		}
		roomba = new Robot(startStreet, startAvenue, theDirection, 0);
		World.setDelay(1);
		World.readWorld(wrldName);
		World.setVisible(true);

	}
	public void turnRight() {
		for (int i = 0; i < 3; i++) {
		roomba.turnLeft();
		}
	}
	private void moveToCorner() {
		while (!roomba.facingWest()) {
			roomba.turnLeft();
		}
		while (roomba.frontIsClear()) {
			roomba.move();
		}
		turnRight();
		while (roomba.frontIsClear()) {
			roomba.move();
		}
		for (int i = 0; i < 2; i++) {
			roomba.turnLeft();
		}
	}
	private void cleanRoom() {
		int greatestPile = 0;
		int numBeepers = 0;
		moveToCorner();
		for (int i = 0; i < 2; i++) {
			if (roomLength%2 == 0) {
				roomba.turnLeft();
			} else {
				turnRight();
			}
			for (int j = 0; j < 2; j++) {
				numBeepers = 0;
				while (roomba.nextToABeeper()) {
					roomba.pickBeeper();
					totalNumBeepers++;
					if (!roomba.nextToABeeper()) {
						greatestPile++;
						numPile++;
					}
					numBeepers++;
					if (numBeepers > greatestPileBeepers) {
						greatestPileBeepers = numBeepers;
						greatestPileNum = greatestPile;
						greatestPileStreet = roomba.street();
						greatestPileAvenue = roomba.avenue();
					}
				}
                if (j == 0) {
				    roomba.move();
                }
				if (roomLength == 0) {
					roomWidth++;
				}
                if (roomba.frontIsClear()) {
                    j--;
                }
			}
			if (roomLength%2 == 0) {
				turnRight();
			} else {
				roomba.turnLeft();
			}
            if (i == 0) {
			    roomba.move();
            }
			roomLength++;
            if (roomba.frontIsClear()) {
                i--;
            }
		}
	}
	private void displayResults() {
		JFrame f = new JFrame();
		JOptionPane.showMessageDialog(f, "The Area of the room was: " + (roomLength * roomWidth) + "\nThe greatest number of beepers in a pile was: " + greatestPileBeepers + "\nThe total number of beepers in the room was: " + totalNumBeepers + "\nThe total number of piles in the room was: " + numPile + "\nThe location relative to the largest pile at the beginning was: " + Math.abs((startStreet - greatestPileStreet)) + " Streets and " + Math.abs(startAvenue - greatestPileAvenue) + " avenues away" + "\nThe average number of beepers in a pile was: " + ((double) totalNumBeepers/numPile) + "\nThe percent of the room that was dirty was: " + (100 * (double) numPile/(roomLength * roomWidth)) + "%");
	}
}
