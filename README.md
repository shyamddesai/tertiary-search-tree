# Tertiary Search Tree
The Tertiary Search Tree is similar to a binary search tree, but each node has three children: left, middle, and right. This data structure allows storing elements that implement the `Comparable` interface and provides efficient insertion, deletion, and search operations with an additional rebalancing feature for optimized performance.

## Key Features
- **Three-Way Node Structure**: Each node has three children, allowing for additional flexibility in data storage and retrieval compared to a binary search tree.
- **Dynamic Rebalancing**: The `rebalance` method rearranges nodes for optimized search and insertion times.
- **Comparable Elements**: The tree stores elements implementing the `Comparable` interface, ensuring ordered data handling.

## Project Structure
- `TST.java`: Contains the `TertiarySearchTree` class implementation, including methods for insertion, deletion, and rebalancing.
- `Main.java`: Provides an example usage of the Tertiary Search Tree.

---

## Usage
The `TertiarySearchTree` class supports operations like insertion, deletion, and rebalancing of nodes. Each node contains a value that must implement the `Comparable` interface to allow for proper ordering within the tree structure.

### Example
```java
TST<Integer> tree = new TST<Integer>();
tree.insert(5);
tree.insert(2);
tree.insert(7);
tree.insert(3);
tree.insert(1);
tree.insert(-1);
tree.insert(0);
tree.insert(8);

// Example of removing a node
tree.remove(1);

// Example of rebalancing the tree
tree.rebalance();
```

### Visual Representation
The example below demonstrates the structure of the tree after multiple insertions, deletions, and a rebalancing operation.
![image](https://user-images.githubusercontent.com/21160813/186788529-7ceb15fb-3919-4e92-98fb-8b895e2b1902.png)
![image](https://user-images.githubusercontent.com/21160813/186788550-e6e07f64-e7bb-41ab-b636-6727c1efd328.png)

---

## Setup
1. **Compile the Java Files**:
   Compile all Java files in the directory.
   ```bash
   javac *.java
   ```

2. **Run the Main Program**:
   ```bash
   java Main
   ```
