package DataStructures.Week01;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thiloshon on 16-Sep-16.
 */


public class Tree<T> {
    private Node<T> root;

    public Tree(T rootData) {
        root = new Node<T>();
        root.data = rootData;
        root.children = new ArrayList<Node<T>>();
    }

    public static class Node<T> {
        private T data;
        private Node<T> parent;
        private List<Node<T>> children;
    }


    public static void main(String[] args){
        Tree tr = new Tree(5);


    }
}




