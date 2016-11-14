package rb.mobiles;

/**
 *
 * @author Tiko Huizinga - s4460898 - <t.huizinga@student.ru.nl>
 * @author Jasper Haasdijk - s4449754 - <j.haasdijk@student.ru.nl>
 */

public class Node {

    public Node left;
    public Node right;
    public String value;
    public int weight;
    public int leafNodes;

    public Node(String input){		
        if(input == "B" || input == "R")
            value = input;
        else{
            int split = inputSplitter(input);
            left = new Node(input.substring(0, split));
            right = new Node(input.substring(split+1, input.length()-1));
        }
    }

    public int solve(){
        // TODO Auto-generated method stub
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

}