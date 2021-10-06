import kareltherobot.*;
import java.util.Scanner;

public class AntiDiamond implements Directions{
    //moves draws a NW diagonal line with variable length (length)
    public static void upLeft(int length, Robot rob) {
        for (int i = 0; i < length - 1; i++) {
            rob.pickBeeper();
            rob.move();
            rob.turnLeft();
            rob.move();
            turnRight(rob);
        }
    }
    public static void fillWorld(int size) {
        Robot robert = new Robot(1,1,East,(size*size));
        int jack = size;
        int cap = jack%2;
        while (jack > 0) {
            robert.putBeeper();
            for (int i = 0; i < size-1; i++) {
                robert.move();
                robert.putBeeper();
                
            }
            
            if (jack%2 == cap) {
                robert.turnLeft();
                robert.move();
                robert.turnLeft();
            } else {
                turnRight(robert);
                robert.move();
                turnRight(robert);
            }
            jack--;
        }
    }
    //turns right
    public static void turnRight(Robot rob) {
        for (int i = 0; i < 3; i++) {
            rob.turnLeft();
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Diamond Size: ");
        int length = Integer.parseInt(scan.nextLine());
        scan.close();
        int size = 2*length - 1;
        World.setVisible(true);
        World.setSize(size,size);
        World.setDelay(0);
        Robot rob = new Robot(1,length,East,(size*size));
        fillWorld(size);
        while (length > 0) {
            for (int i = 0; i < 4; i++) {
                upLeft(length, rob);
                rob.turnLeft();
           }
           if (length == 1) {
               rob.pickBeeper();
           }
           rob.turnLeft();
           rob.move();
           turnRight(rob);
           length--;
        }
    }
}
