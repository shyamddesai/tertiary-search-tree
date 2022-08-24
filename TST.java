import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TST<T extends Comparable<T>> implements Iterable<T>{
    TSTNode<T> root;  // root node of the tree

    // constructor
    public TST() {
        this.root = null;
    }

    // TODO: implement the tree class here

    public void insert(T element){
        root = addNode(root, element);
    }

    public void remove(T element){
        root = removeNode(root, element);
    }

    public boolean contains(T element){
        if(root != null) return containsNode(root, element); 
        return false; //Edge case i.e. tree is empty
    }

	/*
	 * The method divides the list into two: list1 (nodes without duplicates) and list2 (duplicates)
	 *Iterate through list1 using a while loop(hasNext()) using element1 = iter.next();
	 *		-> If list1 contains element1 (means it's a duplicate), add element1 to list2
	 *Run rebalance on list1
	 *		-> Build a new tree (based on list1)
	 *Add the nodes of list2 one by one below the middle child (since it's always null)
	 */
    public void rebalance(){
    	TSTIterator<T> iter = (TSTIterator<T>) iterator();
    	ArrayList<T> list = new ArrayList<>(); //List without duplicates
    	ArrayList<T> listofDuplicates = new ArrayList<>(); //List of duplicates elements
    																	//(so we can find those elements in the tree and add it to the middle child)
    	//Split the sorted list into two, one containing the unique elements, and one containing the duplicates
    	while(iter.hasNext()) {
    		T sortedListElement = iter.next();
    		if(list.contains(sortedListElement)) { //Compare first list with elements in the sorted list
    			listofDuplicates.add(sortedListElement); //If the element already exists in the first list, then it's a duplicate so add it to the second list
    		} else list.add(sortedListElement); //The element is not a duplicate so add it to the first list
    	}
    	//System.out.println(list);
    	//System.out.println(listofDuplicates);

    	root = rebalanceTree(list);
    	//root = rebalanceTree(iter.list()); //Test case that works if I don't separate the list with duplicates
       	
    	while(listofDuplicates.size()>0) {	
    		T elementToFind =  listofDuplicates.get(0);
    		TSTNode<T> rootOfDuplicate = findNode(root, elementToFind);			
			
			while(rootOfDuplicate.mid != null) { //Handles the case of multiple duplicates and finds the right position to place it
				rootOfDuplicate = rootOfDuplicate.mid;
				//System.out.println("Operation Successful Bro!");
			}	
			rootOfDuplicate.mid = new TSTNode<T>(elementToFind);
			listofDuplicates.remove(0);
    	}
    }

    /*
     * Helper Methods
     								*/
    //Recursive helper method to insert a node 
    private TSTNode<T> addNode(TSTNode<T> root, T element) {
        if(root == null) { //Base case
            root = new TSTNode<T>(element); //When reached end of tree, create a new node and assign the element to it
        } else if(element.compareTo(root.element)<0) { //If element < root, search the left subtree
            root.left = addNode(root.left, element);
        } else if(element.compareTo(root.element)==0) { //If element = root, search the middle subtree
            root.mid = addNode(root.mid, element);
        } else if(element.compareTo(root.element)>0) { //If element > root, search the right subtree
            root.right = addNode(root.right, element);
        }
        return root; //Returns root
    }
    
    //Recursive helper method to check if the tree contains a node
    private boolean containsNode(TSTNode<T> root, T element) {
    	if(element.compareTo(root.element)==0) return true; //If the node is found, return true
    	    	
    	if(element.compareTo(root.element)<0 && root.left != null)	 return containsNode(root.left, element);
    	else if(element.compareTo(root.element)>0 && root.right != null) return containsNode(root.right, element);
    	//We don't need to look through the middle subtree, because if it exists means the node we're looking for already exists
    	
    	return false; //If the tree doesn't contain the node, return false
    } 
    
    //Recursive helper method to rebalance the tree
    private TSTNode<T> rebalanceTree(List<T> list) {
    	int mid = list.size()/2;
    	TSTNode<T> root2;
    	
    	if(list.size() == 0) return null; //Base case (i.e. checking if list is empty)
    	
    	root2 = new TSTNode<T>(list.get(mid));
    	root2.left = rebalanceTree(list.subList(0, mid)); //System.out.println("left: " + list.subList(0, mid));
    	root2.right = rebalanceTree(list.subList(mid+1, list.size())); //System.out.println("right: " + list.subList(mid+1, size));
		return root2;
   	}
    
    //Recursive helper method to find a node in the tree (used to find the position of the duplicates and add it to the tree after rebalancing)
    private TSTNode<T> findNode(TSTNode<T> root, T element) {
    	if(root==null) return null;
    	else if(element.compareTo(root.element)==0) return root;
    	else if(element.compareTo(root.element)<0) return findNode(root.left, element);
    	else return findNode(root.right, element);
    }
    
    //Recursive helper method to remove a node
    private TSTNode<T> removeNode(TSTNode<T> root, T element) {
    	if(root == null ) return null;
    	else if(element.compareTo(root.element)<0)
    		root.left = removeNode(root.left, element);
    	else if(element.compareTo(root.element)>0)
    		root.right = removeNode(root.right, element);
    	else if(root.mid != null) //Takes care of duplicate elements
   				root.mid = removeNode(root.mid, element);  
   		else if(root.left == null) //root has no left child; right child is the new node
    		return root.right;
   		else if (root.right == null) //root has no right child; left child is the new node 
    		return root.left;
   		else{ //root has both left and right children; largest element of the left subtree is the new node
   			root.element = root.left.findMax().element;
   			root.mid = root.left.findMax().mid; //If the maximum node in the left subtree has middle children, copy it over (if not, just copies null values)
   			root.left.findMax().mid = null; //Delete the node we just copied to remove it from the tree
   			root.left = removeNode(root.left, root.element);
   		}
   		return root;
    }
    
    /**
     * 
     * Calculate the height of the tree.
     * You need to implement the height() method in the TSTNode class.
     *
     * @return -1 if the tree is empty otherwise the height of the root node
     */
    public int height(){
        if (this.root == null)
            return -1;
        return this.root.height();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {
        // TODO: implement the iterator method here
    	return new TSTIterator<T>(root);
    }

    // --------------------PROVIDED METHODS--------------------
    // The code below is provided to you as a simple way to visualize the tree
    // This string representation of the tree mimics the 'tree' command in unix
    // with the first child being the left child, the second being the middle child, and the last being the right child.
    // The left child is connect by ~~, the middle child by -- and the right child by __.
    // e.g. consider the following tree
    //               5
    //            /  |  \
    //         2     5    9
    //                   /
    //                  8
    // the tree will be printed as
    // 5
    // |~~ 2
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |-- 5
    // |   |~~ null
    // |   |-- null
    // |   |__ null
    // |__ 9
    //     |~~ 8
    //     |   |~~ null
    //     |   |-- null
    //     |   |__ null
    //     |-- null
    //     |__ null
    @Override
    public String toString() {
        if (this.root == null)
            return "empty tree";
        // creates a buffer of 100 characters for the string representation
        StringBuilder buffer = new StringBuilder(100);
        // build the string
        stringfy(buffer, this.root,"", "");
        return buffer.toString();
    }

    /**
     * Build a string representation of the tertiary tree.
     * @param buffer String buffer
     * @param node Root node
     * @param nodePrefix The string prefix to add before the node's data (connection line from the parent)
     * @param childrenPrefix The string prefix for the children nodes (connection line to the children)
     */
    private void stringfy(StringBuilder buffer, TSTNode<T> node, String nodePrefix, String childrenPrefix) {
        buffer.append(nodePrefix);
        buffer.append(node.element);
        buffer.append('\n');
        if (node.left != null)
            stringfy(buffer, node.left,childrenPrefix + "|~~ ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|~~ null\n");
        if (node.mid != null)
            stringfy(buffer, node.mid,childrenPrefix + "|-- ", childrenPrefix + "|   ");
        else
            buffer.append(childrenPrefix + "|-- null\n");
        if (node.right != null)
            stringfy(buffer, node.right,childrenPrefix + "|__ ", childrenPrefix + "    ");
        else
            buffer.append(childrenPrefix + "|__ null\n");
    }

    /**
     * Print out the tree as a list using an enhanced for loop.
     * Since the Iterator performs an inorder traversal, the printed list will also be inorder.
     */
    public void inorderPrintAsList(){
        String buffer = "[";
        for (T element: this) {
            buffer += element + ", ";
        }
        int len = buffer.length();
        if (len > 1)
            buffer = buffer.substring(0,len-2);
        buffer += "]";
        System.out.println(buffer);
    }
}