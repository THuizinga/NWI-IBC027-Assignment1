package rb.mobiles;

/**
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */

/*
 * TODO
 * 1. In the constructor initialise the Node variables (except updateWeight)
 * 2. Add recursive solve() function
 */
public class Node{

    private Node left;                      // left node of this tree
    private Node right;                     // right node of this tree 
    private int weight = -1;		    // total weight of this tree
    private int desiredWeight = -1;         // weight to be added to this tree
    private boolean isLeafNode;             // shows whether the node is a leaf node

    public Node(String input){
        if("R".equals(input) || "B".equals(input)){
            if("R".equals(input)){
                weight = 1;
            }
            else{
                weight = 0;
            }
            isLeafNode = true;
        }
        else{
            int split = inputSplitter(input.substring(1, input.length() - 1));
            left = new Node(input.substring(1, split + 1));                         // string for building left child's tree
            right = new Node(input.substring(split + 1, input.length() - 1));       // string for building right child's tree
        }
    }

    /**
     * This function calculates the minimum number of swaps needed to make the
     * tree balanced. It works recursively, just like the tree itself. The swaps
     * are counted by counting the number of red nodes changing in to black
     * nodes. The total number of nodes stay the same because all the desired
     * weights are based on the total weight of the tree at the start. So if n
     * nodes change from red to black, also n nodes will change from black to
     * red.
     *
     * @return The minimum number of swaps needed to make the tree balanced.
     * -1 if not possible.
     */
    public int calcMinimumSwaps(){
        if(desiredWeight == -1){
            desiredWeight = weight;
        }
        getWeight();
        //Base-case: if this is a leaf node, return 1 if a red node is swapped
        // to a black node. Return -1 if a leaf node has to be heavyer than 1
        // or lighter than 0. Return 0 if it stays the same or black -> red.
        if(isLeafNode){
            if(weight == 1 && desiredWeight == 0){
                return 1;
            }
            else if(desiredWeight > 1 || desiredWeight < 0){
                return -1;
            }
            else{
                return 0;
            }
        }
        //If it is not a leaf, it has to have a left and a right node. If the 
        // weight of this node is even, the desired weight of the left node 
        // and the desired weight of the right node will be the desired weight
        // of this node devided by two.
        else if((desiredWeight & 1) == 0){
            left.setDesiredWeight(desiredWeight / 2);
            right.setDesiredWeight(desiredWeight / 2);
            return conditionalSum(left.calcMinimumSwaps(), right.calcMinimumSwaps());
        }

        //If the desired weight of this node is odd, it cannot be easily divided
        // by 2. One of the desired weights will be bigger than the other.
        // To make sure we get the best possible answer, we will split it both 
        // ways and choose the smallest outcome of the two answers to go with.
        else{
            int leftHigh;
            int rightHigh;

            left.setDesiredWeight((desiredWeight / 2) + 1);
            right.setDesiredWeight((desiredWeight / 2));
            leftHigh = conditionalSum(left.calcMinimumSwaps(), right.calcMinimumSwaps());

            left.setDesiredWeight((desiredWeight / 2));
            right.setDesiredWeight((desiredWeight / 2) + 1);
            rightHigh = conditionalSum(left.calcMinimumSwaps(), right.calcMinimumSwaps());

            return conditionalMin(leftHigh, rightHigh);
        }

    }

    /**
     * This will add two integers as long as their value is not -1. It will
     * return -1 otherwise.
     *
     * @param a
     * @param b
     *
     * @return a+b if both integers are not -1.
     * @return -1 otherwise.
     */
    private int conditionalSum(int a, int b){
        if(a == -1 || b == -1){
            return -1;
        }
        else{
            return a + b;
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
     * @return the weight of the node
     */
    public int getWeight(){
        if(weight == -1){
            weight = left.getWeight() + right.getWeight();
        }
        return weight;
    }

    public void setDesiredWeight(int w){
        this.desiredWeight = w;
    }

    /**
     * @return the desiredWeight to be added to this tree
     */
    public int getDesiredWeight(){
        return desiredWeight;
    }

    /**
     * Calculates the minimum value of two integers that are not -1.
     * If one of them is -1, it returns the other one.
     *
     * @param a first integer to be minimized
     * @param b second integer to be minimized
     *
     * @return smallest value of a and b, if they are both not -1.
     * @return b if a is -1
     * @return a if b is -1
     */
    private int conditionalMin(int a, int b){
        if(a == -1){
            return b;
        }
        else if(b == -1){
            return a;
        }
        else{
            return Math.min(a, b);
        }
    }
}
