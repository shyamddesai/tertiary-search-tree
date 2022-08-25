// add your imports here

class TSTNode<T extends Comparable<T>>{
    T element;     	            // The data in the node
    TSTNode<T>  left;   		// left child
    TSTNode<T>  mid;   		    // middle child
    TSTNode<T>  right;  		// right child

    // TODO: implement the node class here
    
    TSTNode(T element){
    	this.element = element;
    }

    TSTNode<T> findMax(){
        return findMaxElement(this);
    }

    TSTNode<T> findMin(){
        return findMinElement(this);
    }

    int height(){
        return calculateHeight(this)-1; //The height of the tree minus the root
    }

    /*
     * Helper Methods
     								*/
    //Recursive Calculate Height Helper Method
    private int calculateHeight(TSTNode<T> root) {
    	 if(root == null) return 0; //The tree has no nodes i.e. height = 0
    	 
        //The height of the tree is the maximum height amongst the three subtrees 
        int leftHeight = calculateHeight(root.left);
        int middleHeight = calculateHeight(root.mid);
        int rightHeight = calculateHeight(root.right);
        
        return 1 + Math.max(leftHeight, Math.max(middleHeight, rightHeight));
    }
    
    //Recursive Minimum Element Finder Method
    private TSTNode<T> findMinElement(TSTNode<T> root) {
    	if (root == null) return null; //Only necessary for the first call
    	else if (root.left == null) return root; //We only need to consider the left subtree since the tree is sorted
    	else return findMinElement(root.left); //Returns the node containing the minimum element of the tree
    }
    
    //Recursive Maximum Element Finder Method
    private TSTNode<T> findMaxElement(TSTNode<T> root) {
    	if (root == null) return null; 
    	else if (root.right == null) return root;
    	else return findMaxElement(root.right);
    }
}

