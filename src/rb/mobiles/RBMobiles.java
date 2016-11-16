package rb.mobiles;

import java.util.Scanner;

/**
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */
public class RBMobiles{

    /**
     * The main function will get the string from standard input, create a new 
     * node, pass it to the node and orders it to calculate the minimum number
     * of swaps needed to balance the tree. If this is possible, it will output
     * this number. If it is not possible, it will output 'discard'.
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Node n = new Node(input);
        n.getWeight();
        int answer1 = n.calcMinimumSwaps();
        if(answer1 != -1)
            System.out.println(answer1);
        
        else{
            System.out.println("discard");
        }
    }
}
