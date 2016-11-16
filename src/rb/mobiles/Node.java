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

    private Node left;		    // left node of this tree
    private Node right;		    // right node of this tree 
    private int weight = -1;		    // total weight of this tree
    private int desiredWeight = -1;       // weight to be added to this tree
    private boolean isLeafNode;     // shows whether the node is a leaf node

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
            left = new Node(input.substring(1, split + 1));                       // string for building left child's tree
            right = new Node(input.substring(split + 1, input.length() - 1));       // string for building right child's tree
        }
    }

    public int calc1(){
        if(desiredWeight == -1){
            desiredWeight = weight;
        }
        getWeight();
        if(isLeafNode){
            if(weight == 1 && desiredWeight == 0){
                return 1;
            }
            else if (desiredWeight > 1 || desiredWeight < 0){
                return -1;
            }
            else{
                return 0;
            }
        }
        
        else if((desiredWeight & 1) == 0){
            left.setDesiredWeight(desiredWeight/2);
            right.setDesiredWeight(desiredWeight/2);
            return conditionalSum(left.calc1(), right.calc1());
        }
        
        else{
            int leftHigh;
            int rightHigh;
            
            left.setDesiredWeight((desiredWeight/2) + 1);
            right.setDesiredWeight((desiredWeight/2));
            leftHigh = conditionalSum(left.calc1(), right.calc1());
            
            left.setDesiredWeight((desiredWeight/2));
            right.setDesiredWeight((desiredWeight/2) + 1);
            rightHigh = conditionalSum(left.calc1(), right.calc1());
            
            return conditionalMin(leftHigh, rightHigh);
        }
        
    }
    
    public int calc2(){
        if(desiredWeight == -1){
            desiredWeight = weight;
        }
        getWeight();
        if(isLeafNode){
            if(weight == 0 && desiredWeight == 1){
                return 1;
            }
            else if (desiredWeight > 1 || desiredWeight < 0){
                return -1;
            }
            else{
                return 0;
            }
        }
        
        else if((desiredWeight & 1) == 0){
            left.setDesiredWeight(desiredWeight/2);
            right.setDesiredWeight(desiredWeight/2);
            return conditionalSum(left.calc2(), right.calc2());
        }
        
        else{

            int leftHigh;
            int rightHigh;
            
            left.setDesiredWeight((desiredWeight/2) + 1);
            right.setDesiredWeight((desiredWeight/2));
            leftHigh = conditionalSum(left.calc2(), right.calc2());
            
            left.setDesiredWeight((desiredWeight/2));
            right.setDesiredWeight((desiredWeight/2) + 1);
            rightHigh = conditionalSum(left.calc2(), right.calc2());
            
            return conditionalMin(leftHigh, rightHigh);
        }
        
    }

    private int conditionalSum(int a, int b){
        if(a == -1 || b == -1){
            return -1;
        }
        else{
            return a+b;
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

    private int conditionalMin(int leftHigh, int rightHigh){
        if(leftHigh == -1){
            return rightHigh;
        }
        else if(rightHigh == -1){
            return leftHigh;
        }
        else{
            return Math.min(leftHigh, rightHigh);
        }
    }


}
