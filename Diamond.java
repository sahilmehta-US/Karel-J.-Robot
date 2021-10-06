import kareltherobot.*;
import java.util.Scanner;

public class Diamond implements Directions{
    //moves draws a NW diagonal line with variable length (length)
    public static void upLeft(int length, Robot rob) {
        for (int i = 0; i < length - 1; i++) {
            rob.putBeeper();
            rob.move();
            rob.turnLeft();
            rob.move();
            turnRight(rob);
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
        Robot rob = new Robot(1,length,East,(length*length+(length-1)*(length-1)));
        while (length > 0) {
            for (int i = 0; i < 4; i++) {
                upLeft(length, rob);
                rob.turnLeft();
           }
           if (length == 1) {
               rob.putBeeper();
           }
           rob.turnLeft();
           rob.move();
           turnRight(rob);
           length--;
        }
    }
}
