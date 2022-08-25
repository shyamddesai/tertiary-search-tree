// add your imports here
import java.util.ArrayList;
import java.util.Iterator;

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
    // TODO: implement the iterator class here

	//Instance Variables
	private ArrayList<T> list = new ArrayList<>();
	private TSTNode<T> root;
	int index;
	
	//Constructor
    public TSTIterator(TSTNode<T> root) {
    	this.root = root;
    	inOrderTraversal(this.root); //Create an ordered list of all elements i.e. in-order traversal
    	//System.out.println("Constructor: \t" + list);
	}

    /*In-Order traversal of a sorted tree with three child nodes works by recursively
     * first visiting the left and middle subtrees, then visit the node, then the right subtree
     * i.e. Left-Middle-Root-Right traversal
     */
    private void inOrderTraversal(TSTNode<T> node) {
    	if(node != null) {
	    	inOrderTraversal(node.left);
	    	inOrderTraversal(node.mid);
	    	list.add(node.element);
	    	//System.out.println(list);
	    	inOrderTraversal(node.right);
    	}
    }
    
    public ArrayList<T>list() {
		return list;    	
    }

	/**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next}
     * would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
    	return (index<list.size() && list.get(index) != null);
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     *
     * @throws NoSuchElementException
     *         if the iteration has no more elements
     */
    @Override
    public T next() {
    	if(hasNext()) {
    		T temp = list.get(index++);
    		return temp;
    	} else return null;
    	//throw new NoSuchElementException("The tree has no more elements!");
    }
}