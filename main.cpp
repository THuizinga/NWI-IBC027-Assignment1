// @author Jasper Haasdijk // s4449754
// @author Tiko Huizinga // s4460898

#include <iostream>
#include <stdlib.h>

using namespace std;

// global variables
struct node{

    node *parent;
    node *left;
    node *right;
};

// helper-functions
node input_handler(string input){

    // TODO
}


int main(){

//    string input;
//    cin >> input;

    string input = "(BR)";

//    for(size_t i = 0; i < input.size(); i++){
//        cout << input[i];
//    }
//    cout << "END";

    return 0;
}


/// Input
/*
    B denotes a black, light-weight leave
    R denotes a red, heavy leave
    (m1m2) denotes a rod with two sub mobiles m1 and m2

    Number of rods is bounded by 1500
*/

/// Output
/*
    IF possible: A single integer
        The minimal number of swaps needed to make the mobile balanced
    ELSE: stdout
        The string: "discard"
*/
