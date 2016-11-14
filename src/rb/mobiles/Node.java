package rb.mobiles;

/**
 *
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */

public class Node{

    public Node left;
    public Node right;
    public String value;    // value appended to a leaf node ['R' || 'B']
    public int weight;
    public int leafNodes;

    public Node(String input){		
        if("R".equals(input) || "B".equals(input)){
            value = input;      // we have reached a leaf node of the tree
        }
        else{
            int split = inputSplitter(input.substring(1, input.length()-1));
            left = new Node(input.substring(1, split+1));                     // string for building left child's tree
            right = new Node(input.substring(split+1, input.length()-1));   // string for building right child's tree
        }
    }

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
}