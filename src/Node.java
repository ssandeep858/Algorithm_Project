public class Node<T> {
    private T data;
    private Node left;
    private Node right;
    private Node parent;
    private char color;

    public Node(T data, Node left, Node right, Node parent, char color) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.color = color;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
