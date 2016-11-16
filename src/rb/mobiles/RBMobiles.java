package rb.mobiles;

import java.util.Scanner;

/**
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */
public class RBMobiles{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Node n = new Node(input);

        if(true){       // Some condition...
            n.getWeight();
            System.out.println(n.calc1());
        }
        else{
            System.out.println("discard");
        }
    }
}
