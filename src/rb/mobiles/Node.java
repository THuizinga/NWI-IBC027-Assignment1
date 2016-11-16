package rb.mobiles;

/**
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */

public class Node{

    private Node left;		    // left node of this tree
    private Node right;		    // right node of this tree 
    private String value;	    // 'R' if Red leaf. 'B' if black leaf
    private int weight = -1;		    // total weight of this tree
    private int leafNodes;	    // number of leafnodes in this tree
    private int updateWeight = 0;       // weight to be added to this tree
    private boolean isLeafNode;     // shows whether the node is a leaf node
    private int desiredWeight = 0;  // the weight you want the tree to have

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
            int split = inputSplitter(input.substring(1, input.length() - 1));
            left = new Node(input.substring(1, split + 1));                       // string for building left child's tree
            right = new Node(input.substring(split + 1, input.length() - 1));       // string for building right child's tree
        }
    }

    /**
     * Checks recursively if the tree is already balanced or not
     *
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
     *
     * @return false iff the left weight differs more than 1 from the
     * right weight
     */
    public Boolean isBalanced(){
        return Math.abs(left.getWeight() - right.getWeight()) <= 1;
    }
    
    /**
     * calc function using desiredWeight instead of updateWeight
     * @return 
     */
    public int calcSwaps(){
        // CASE 1
        if(isLeafNode){
            if(getWeight() == 1 && desiredWeight == 0){
                return 1;
            }
            else{
                return 0;
            }
        }

        // CASE 2
        else if (getWeight() % 2 == 0){
            left.setDesiredWeight(weight/2);
            right.setDesiredWeight(weight/2);

            return left.calcSwaps() + right.calcSwaps();
        }

        // CASE 3
        else{
            int leftHigh;
            int rightHigh;

            left.setDesiredWeight(weight/2);
            right.setDesiredWeight((weight/2) + 1);
            leftHigh = left.calcSwaps() + right.calcSwaps();

            left.setDesiredWeight((weight/2) + 1);
            right.setDesiredWeight(weight/2);
            rightHigh = left.calcSwaps() + right.calcSwaps();

            return Math.min(leftHigh, rightHigh);
        }
    }

    /**
     * Calculate the value of the index where the input string should be
     * splitted in to the left and the right string by counting the brackets.
     *
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
        while (brackets > 0 || index == 0){
            char cur = s.charAt(index);

            switch (cur){
                case '(':
                    brackets++;
                    index++;
                    break;
                case ')':
                    brackets--;
                    index++;
                    break;
                default:
                    index++;
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
        if(weight == -1){
            weight = left.getWeight() + right.getWeight();
        }
        return weight;
    }

    /**
     * @return the number of leafNodes in the tree
     */
    public int getLeafNodes(){
        return leafNodes;
    }

    /**
     * update the updateWeight
     *
     * @param w the integer added to updateWeight
     */
    public void updateUpdateWeight(int w){
        this.updateWeight += w;
    }

    public void setUpdateWeight(int w){
        this.updateWeight = w;
    }

    /**
     * @return the weight to be added to this tree
     */
    public int getUpdateWeight(){
        return updateWeight;
    }

    /**
     * @return true iff the node
     * is a leafnode in the tree
     */
    public Boolean getIsLeafNode(){
        return isLeafNode;
    }
    
    public int getDesiredWeight(){
        return desiredWeight;
    }
    
    public void setDesiredWeight(int w){
        this.desiredWeight = w;
    }
}
