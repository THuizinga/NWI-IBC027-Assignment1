package rb.mobiles;

/**
 *
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */

/**
 * TODO
 * 1. In the constructor initialise the Node variables (except updateWeight)
 * 2. Add recursive solve() function
 */

public class Node{

    private Node left;		    // left node of this tree
    private Node right;		    // right node of this tree 
    private String value;	    // 'R' if Red leaf. 'B' if black leaf
    private int weight;		    // total weight of this tree
    private int leafNodes;	    // number of leafnodes in this tree
    private int updateWeight;       // weight to be added to this tree
    private boolean isLeafNode;     // shows whether the node is a leaf node

    public Node(String input){		
        if("R".equals(input) || "B".equals(input)){
            value = input;      // we have reached a leaf node of the tree
            isLeafNode = true;
        }
        else{
            int split = inputSplitter(input.substring(1, input.length()-1));
            left = new Node(input.substring(1, split+1));                       // string for building left child's tree
            right = new Node(input.substring(split+1, input.length()-1));       // string for building right child's tree
        }
    }

    /**
     * Checks recursively if the tree is already balanced or not
     * @return true iff the tree is already balanced.
     */
    public Boolean isBalanced(){
        if(Math.abs(left.getWeight() -  right.getWeight()) > 1){
            return false;
        }
        else if(isLeafNode){
            return true;
        }		
        else{
            return (left.isBalanced() && right.isBalanced());
        }
    }
    
	
	
    /**
     * Calculates the minimal amount of swaps necessary for balancing the tree.
     * @return the amount of swaps, -1 if balancing is not possible
     */
    public int solve(){
        // TODO
        return 0;
    }

    /**
     * Calculate the value of the index where the input string should be splitted  
     * in to the left and the right string by counting the brackets.
     * @return the index of the last character of the left string
     */
    private int inputSplitter(String s){
        int index = 0;
        int brackets = 0;
	
        // CASE (XX)
        if(s.charAt(0) == 'B' || s.charAt(0) == 'R'){
            return 1;
        }

        // CASE (XX)(XX)
        while(brackets > 0 || index == 0){
            char cur = s.charAt(index);

            switch(cur){
                case '(':
                    brackets ++;
                    index ++;
                    break;
                case ')':
                    brackets --;
                    index ++;
                    break;
                default:
                    index ++;
                    break;
            }
        }
        return index;
    }    
    

    /**
     * @return the left node
     */
    public Node getLeft(){
        return left;
    }

    /**
     * @return the right node
     */
    public Node getRight(){
        return right;
    }

    /**
     * @return the value of the node
     */
    public String getValue(){
        return value;
    }

    /**
     * @return the weight of the node
     */
    public int getWeight(){
        return weight;
    }

    /**
     * @return the number of leafNodes in the tree
     */
    public int getLeafNodes(){
        return leafNodes;
    }

    /**
     * @return the weight to be added to this tree
     */
    public int getUpdateWeight(){
        return updateWeight;
    }
    
    /**
     * 
     * @return true iff the node 
     *  is a leafnode in the tree
     */
    public Boolean getIsLeafNode(){
        return isLeafNode;
    }
}
