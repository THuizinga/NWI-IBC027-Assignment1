package rb.mobiles;

import java.time.Clock;

/**
 *
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */

public class Node {

    private Node left;		    // left node of this tree
    private Node right;		    // right node of this tree 
    private String value;	    // 'R' if Red leaf. 'B' If right leaf
    private int weight;		    // total weight of this tree
    private int leafNodes;	    // number of leafnodes in this tree
    private int differenceLeft;	    // weight to be added to left tree
    private int differenceRight;    // weight to be added to right tree

    public Node(String input){		
        if("B".equals(input) || "R".equals(input))
            value = input;
        else{
            int split = inputSplitter(input);
            left = new Node(input.substring(1, split));
            right = new Node(input.substring(split+1, input.length()-1));
        }
    }
    
    public Boolean isBalanced(){
	return false;
    }

    public int solve(){
        // TODO
        return 0;
    }

    /**
    * Calculate the value where the input string should be splitted  
    * in to the left and the right string by counting the brackets.
    * @return the index of the last character of the left string
    */
    private int inputSplitter(String s){
        int result = 0;
        int brackets = 0;
	System.out.println(s);
        while(brackets > 0 || result == 0){
            char cur = s.charAt(result);

            if(cur == '('){
                brackets ++;
                result ++;
            }
            else if(cur == ')'){
                brackets --;
                result ++;
            }
            else{
                result ++;
            }
        }
        return result--;
    }
    
    

    /**
     * @return the left
     */
    public Node getLeft() {
	return left;
    }

    /**
     * @return the right
     */
    public Node getRight() {
	return right;
    }

    /**
     * @return the value
     */
    public String getValue() {
	return value;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
	return weight;
    }

    /**
     * @return the leafNodes
     */
    public int getLeafNodes() {
	return leafNodes;
    }

    /**
     * @return the differenceLeft
     */
    public int getDifferenceLeft() {
	return differenceLeft;
    }

    /**
     * @return the differenceRight
     */
    public int getDifferenceRight() {
	return differenceRight;
    }

}
