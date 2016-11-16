package rb.mobiles;

/**
 * TEST COMMIT
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */

public class Node{

    private Node left;		    // left node of this tree
    private Node right;		    // right node of this tree 
    private String value;	    // 'R' if Red leaf. 'B' if black leaf
    private int weight;		    // total weight of this tree
    private int leafNodes;	    // number of leafnodes in this tree
    private int updateWeight = 0;   // weight to be added to this tree
    private boolean isLeafNode;     // shows whether the node is a leaf node

    public Node(String input){		
        if("R".equals(input) || "B".equals(input)){
            if("R".equals(input)){
                weight = 1;
            }
            else{
                weight = 0;
            }
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
    public Boolean isFullyBalanced(){
        if(Math.abs(left.getWeight() - right.getWeight()) > 1){
            return false;
        }
        else if(isLeafNode){
            return true;
        }		
        else{
            return (left.isFullyBalanced() && right.isFullyBalanced());
        }
    }

    /**
     * Checks if the weight of the left and the right child have at most 
     * a difference of one.
     * @return false iff the left weight differs more than 1 from the
     * right weight
     */
    public Boolean isBalanced(){
        if(Math.abs(left.getWeight() - right.getWeight()) > 1){
            return false;
        }
        else{
            return true;
        }
    }


    /**
     * Calculates the minimal amount of swaps necessary for balancing the tree.
     * @return the amount of swaps; -1 if balancing is not possible
     */
    public int solve(){
        // If this is a leaf node, we count a step, and thus return 1 in the 
        // recursive function when a red child changes to a black child.
        if(isLeafNode){
            if(updateWeight == -1){
                return 1;
            }
            else{
                return 0;
            }
        }
        // If this is not a leaf node, the direct childs are balanced
        // and all the ancestors are balanced, the total amount of changes 
        // is the ammount of changes the subtrees have to make.
        // TODO Misschien ondanks dat dit gedeelte gebalanced is, dat we bij een oneven gewicht, toch andersom ook moeten proberen?
        else if(isBalanced() && updateWeight == 0){
            return left.solve() + right.solve();
        }

        // Het moeilijkste gedeelte :D
        else{
            Boolean even = true;
            left.updateUpdateWeight(left.getWeight());  // Incorrect
            return left.solve() + right.solve();
        }
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
     * @return left
     *  the left node
     */
    public Node getLeft(){
        return left;
    }

    /**
     * @return right
     *  the right node
     */
    public Node getRight(){
        return right;
    }

    /**
     * @return value
     *  the value of the node
     */
    public String getValue(){
        return value;
    }

    /**
     * @return weight
     *  the weight of the node
     */
    public int getWeight(){
        if(weight == -1){
            weight = left.getWeight() + right.getWeight();
        }
        return weight;
    }

    /**
     * @return leafNodes
     *  the number of leafNodes in the tree
     */
    public int getLeafNodes(){
        return leafNodes;
    }

    /**
     * update the updateWeight
     * @param w the integer added to updateWeight
     */
    public void updateUpdateWeight(int w){
        this.updateWeight += w;
    }

    /**
     * @return updateWeight
     *  the weight to be added to this tree
     */
    public int getUpdateWeight(){
        return updateWeight;
    }

    /**
     * @return true iff the node 
     *  is a leafnode in the tree
     */
    public Boolean getIsLeafNode(){
        return isLeafNode;
    }
}
