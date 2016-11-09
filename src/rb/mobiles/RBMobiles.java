package rb.mobiles;

/**
 *
 * @author Tiko Huizinga - s4460898 <t.huizinga@student.ru.nl>
 */
public class RBMobiles {

    /**
     * @param args the command line arguments
     */
	public static void main(String[] args) {
		String input = "(BR)";
		Node n = new Node(input);
		System.out.println(n.solve());
	}
    
}
