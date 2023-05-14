package lazyTrees;

import java.util.NoSuchElementException;
import java.util.*;

public class LazySearchTree<T extends Comparable<? super T>> {
    private LazySTNode root;

    private class LazySTNode {
        T data;
        LazySTNode left;
        LazySTNode right;
        boolean deleted;

        LazySTNode(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.deleted = false;
        }
    }
    public class FHs_treeNode<E extends Comparable< ? super E > >
    {
        // use public access so the tree or other classes can access members
        protected FHs_treeNode<E> lftChild, rtChild;
        protected E data;

        public FHs_treeNode( E d, FHs_treeNode<E> lft, FHs_treeNode<E> rt )
        {
            lftChild = lft;
            rtChild = rt;
            data = d;
        }

        public FHs_treeNode()
        {
            this(null, null, null);
        }
    }

    public boolean contains(T tmp) {
        LazySTNode node = findNode(root, tmp);
        return node != null && !node.deleted;
    }


    private LazySTNode findNode(LazySTNode node, T value) {
        if (node == null || (node.deleted && node.data.compareTo(value) == 0)) {
            return null;
        }

        int compare = value.compareTo(node.data);

        if (compare < 0) {
            return findNode(node.left, value);
        } else if (compare > 0) {
            return findNode(node.right, value);
        } else {
            return node;
        }
    }

    public void insert(T tmp) {
        root = insertNode(root, tmp);
    }

    private LazySTNode insertNode(LazySTNode node, T value) {
        if (node == null) {
            return new LazySTNode(value);
        }

        int compare = value.compareTo(node.data);

        if (compare < 0) {
            node.left = insertNode(node.left, value);
        } else if (compare > 0) {
            node.right = insertNode(node.right, value);
        } else {
            if (node.deleted) {
                node.deleted = false; // Restore node if it was previously marked as deleted
            }
        }

        return node;
    }

    public T find(T tmp) {
        LazySTNode node = findNode(root, tmp);
        return (node != null && !node.deleted) ? node.data : null;
    }

    public void remove(T tmp) {
    }
public T findMax() {
    LazySTNode maxNode = findMaxNode(root);
    return (maxNode != null && !maxNode.deleted) ? maxNode.data : null;
}

    private LazySTNode findMaxNode(LazySTNode node) {
        if (node == null || (node.deleted && node.right == null)) {
            return null;
        }

        if (node.right == null || node.right.deleted) {
            return node;
        }

        return findMaxNode(node.right);
    }

    public int sizeHard() {
        return countHardNodes(root);
    }

    public int size() {//T
        return countNodes(root);
    }

    private int countNodes(LazySTNode node) {
        if (node == null) {
            return 0;
        }
        int count = 1; // Count the current node
        count += countNodes(node.left); // Recursively count nodes in the left subtree
        count += countNodes(node.right); // Recursively count nodes in the right subtree
        return count;
    }


    private int countHardNodes(LazySTNode node) {
        if (node == null) {
            return 0;
        }
        int count = (node.deleted) ? 0 : 1; // Count the current node if it's not deleted
        count += countHardNodes(node.left); // Recursively count non-deleted nodes in the left subtree
        count += countHardNodes(node.right); // Recursively count non-deleted nodes in the right subtree
        return count;
    }
/*
    public void traverseHard(PrintObject<T> printObject) {
    }

    public void traverseSoft(PrintObject<T> printObject) {
    }*/

    public void traverseHard(PrintObject<T> printObject) {
        traverseHardNode(root, printObject);
    }

    private void traverseHardNode(LazySTNode node, PrintObject<T> printObject) {
        if (node == null) {
            return;
        }

        traverseHardNode(node.left, printObject);
        printObject.visit(node.data);
        traverseHardNode(node.right, printObject);
    }

    public void traverseSoft(PrintObject<T> printObject) {
        traverseSoftNode(root, printObject);
    }

    private void traverseSoftNode(LazySTNode node, PrintObject<T> printObject) {
        if (node == null) {
            return;
        }

        traverseSoftNode(node.left, printObject);
        if (!node.deleted) {
            printObject.visit(node.data);
        }
        traverseSoftNode(node.right, printObject);
    }

    public T findMin() {
        LazySTNode minNode = findMinNode(root);
        return (minNode != null && !minNode.deleted) ? minNode.data : null;
    }

    private LazySTNode findMinNode(LazySTNode node) {
        if (node == null || (node.deleted && node.left == null)) {
            return null;
        }

        if (node.left == null || node.left.deleted) {
            return node;
        }

        return findMinNode(node.left);
    }

   /* public void traverseSoft(PrintObject<T> printObject) {
        traverseSoftNode(root, printObject);
    }*/


}



