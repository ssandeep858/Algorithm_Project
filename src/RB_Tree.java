import java.util.LinkedList;
import java.util.Queue;

public class RB_Tree<T extends Comparable<T>> {
    Node<T> root;
    Node<T> sentinel;

    public RB_Tree(){
        sentinel = new Node<T>(null,null,null,null,'B');
        root = sentinel;
    }

    public Node insert(T val) {
        Node newNode = new Node<T>(val, sentinel, sentinel, null, 'R');

        if (root == sentinel) {
            newNode.setParent(sentinel);
            root = newNode;
        } else {
            Node temp = root;
            Node parent = null;
            while (temp != sentinel) {
                parent = temp;
                if (val.compareTo((T) temp.getData()) < 0)
                    temp = temp.getLeft();
                else
                    temp = temp.getRight();
            }
            if (val.compareTo((T) parent.getData()) < 0)
                parent.setLeft(newNode);
            else
                parent.setRight(newNode);
            newNode.setParent(parent);
        }
        fixup(newNode);
        return newNode;
    }

    public Node nodePosition(T val,Node temp) {

        if (root == null) {
            System.out.println("empty tree ");
            return null;
        } else {
            while (temp != sentinel && val.compareTo((T) temp.getData())!=0) {

                if (val.compareTo((T) temp.getData()) < 0)
                    temp =  temp.getLeft();
                else
                    temp = temp.getRight();
                }
            return temp;
            }
        }

    public Node treeSuccessor(Node node){
        while(node.getLeft()!=sentinel)
        {
            node=node.getLeft();
        }
        return node;
    }

    private void transplant(Node destination, Node initial)
    {
        if(destination.getParent()==sentinel)
        {
            root=initial;
        }
        else if(destination==destination.getParent().getLeft())
        {
            destination.getParent().setLeft(initial);
        }
        else
            destination.getParent().setRight(initial);
            initial.setParent(destination.getParent());

    }
    public Node deleteNode(T val){
        Node z = root;
        z = nodePosition(val,z);
        if(z==sentinel) {
           System.out.println("Error! value doesn't exist");
           return sentinel;
        }
        else {
           Node<T> x;
           Node<T> y = z;
           char yOriginalColor = y.getColor();
           // less than two children condition left side
           if(z.getLeft() == sentinel){
               x = z.getRight();
               transplant(z,z.getRight());
           }
           // less than two children condition right side
           else if(z.getRight() == sentinel) {
               x = z.getLeft();
               transplant(z, z.getLeft());
           }
           else {
                y = treeSuccessor(z.getRight());
                yOriginalColor = y.getColor();
                x = y.getRight();

                if(y.getParent() == z)
                    x.setParent(y);

                else {
                    transplant(y,y.getRight());
                    y.setRight(z.getRight());
                    y.getRight().setParent(y);
                }
                transplant(z,y);
                y.setLeft(z.getLeft());
                y.getLeft().setParent(y);
                y.setColor(z.getColor());
           }
           if (yOriginalColor == 'B'){
               deleteFixup(x);
            }
           return x;
        }
    }

    private void deleteFixup(Node node) {
        while (node != root && node.getColor() == 'B') {
            if (node == node.getParent().getLeft()) {
                Node sibling = node.getParent().getRight();
                if (sibling.getColor() == 'R') {                       //case 1 for left side
                    sibling.setColor('B');
                    node.getParent().setColor('R');
                    leftRotate(node.getParent());
                    sibling = node.getParent().getRight();
                }
                if (sibling.getLeft().getColor() == 'B' && sibling.getRight().getColor() == 'B') {
                    sibling.setColor('R');                              //case 2 for left side
                    node = node.getParent();
                    continue;
                }

                else if (sibling.getRight().getColor() == 'B') {
                    sibling.getLeft().setColor('B');                   //case 3 for left side
                    sibling.setColor('R');
                    rightRotate(sibling);
                    sibling = node.getParent().getRight();
                }

                sibling.setColor(node.getParent().getColor());      //case 4 for left side
                node.getParent().setColor('B');
                sibling.getRight().setColor('B');
                leftRotate(node.getParent());
                node = root;
               }
            else {
                Node sibling = node.getParent().getLeft();
                if (sibling.getColor() == 'R') {                       //case 1 for right side
                    sibling.setColor('B');
                    node.getParent().setColor('R');
                    rightRotate(node.getParent());
                    sibling = node.getParent().getLeft();
                }
                if (sibling.getRight().getColor() == 'B' && sibling.getLeft().getColor() == 'B') {
                    sibling.setColor('R');                              //case 2 for left side
                    node = node.getParent();
                    continue;
                }

                else if (sibling.getLeft().getColor() == 'B') {
                    sibling.getRight().setColor('B');                   //case 3 for left side
                    sibling.setColor('R');
                    leftRotate(sibling);
                    sibling = node.getParent().getLeft();
                }

                sibling.setColor(node.getParent().getColor());      //case 4 for left side
                node.getParent().setColor('B');
                sibling.getLeft().setColor('B');
                rightRotate(node.getParent());
                node = root;
               }
           }
        node.setColor('B');
       }

           //At this point insertion is done, but we need fixup
           private void fixup(Node node)
           {
               while (node.getParent().getColor() == 'R') {
                   if (node.getParent().getParent().getLeft() == node.getParent()) { //if this node's parent lies on the left side of grant parent
                       Node uncle = node.getParent().getParent().getRight();

                       //Case 01 Left Side
                       if (uncle.getColor() == 'R') {
                           node = case1FixUp(node, uncle);
                       }

                       //Case 02 Left side
                       else if (node == node.getParent().getRight()) {
                           node = node.getParent();
                           leftRotate(node);
                           node.getParent().setColor('B');
                           node.getParent().getParent().setColor('R');
                           rightRotate(node.getParent().getParent());
                       }

                       //Case 03 Left Side
                       else {
                           node.getParent().setColor('B');
                           node.getParent().getParent().setColor('R');
                           rightRotate(node.getParent().getParent());
                       }

                   } else { //node's parent lies on the right side of grandparent
                       Node uncle = node.getParent().getParent().getLeft();

                       //Case 01 Right Side
                       if (uncle.getColor() == 'R') {
                           node = case1FixUp(node, uncle);
                       }

                       //Case 02 Right side
                       else if (node == node.getParent().getLeft()) {
                           node = node.getParent();
                           rightRotate(node);
                           node.getParent().setColor('B');
                           node.getParent().getParent().setColor('R');
                           leftRotate(node.getParent().getParent());
                       }

                       //Case 03 Right Side
                       else {
                           node.getParent().setColor('B');
                           node.getParent().getParent().setColor('R');
                           leftRotate(node.getParent().getParent());
                       }
                   }
               }
               root.setColor('B');
           }


        public void leftRotate(Node<T> x){
            Node y = x.getRight();
            x.setRight(y.getLeft());
            if(y.getLeft()!=sentinel)
                y.getLeft().setParent(x);
            y.setParent(x.getParent());
            if(x.getParent() == sentinel)
                root = y;
            else if(x==x.getParent().getLeft())
                x.getParent().setLeft(y);
            else
                x.getParent().setRight(y);
            y.setLeft(x);
            x.setParent(y);
        }
        public void rightRotate(Node<T> x){
            Node y = x.getLeft();
            x.setLeft(y.getRight());
            if(y.getRight()!=sentinel)
                y.getRight().setParent(x);
            y.setParent(x.getParent());
            if(x.getParent() == sentinel)
                root = y;
            else if(x==x.getParent().getRight())
                x.getParent().setRight(y);
            else
                x.getParent().setLeft(y);
            y.setRight(x);
            x.setParent(y);
        }
        public void inOrderTraversal(){
            traversalHelper(root);
        }
        private void traversalHelper(Node<T> node){
            if(node!=sentinel){
                traversalHelper(node.getLeft());
                System.out.println(node.getData()+" "+node.getColor());
                traversalHelper(node.getRight());
            }
        }

        private Node case1FixUp(Node node, Node uncle){
            node.getParent().setColor('B'); //parent's color is set to black
            uncle.setColor('B'); //uncle's color is set to black
            node = node.getParent().getParent();
            node.setColor('R'); //grand parent's color is set to red
            return node;
        }

        public void bfs(){
            Queue<Node> frontier = new LinkedList<>();
            frontier.add(root);
            while(!frontier.isEmpty()){
                Node node = frontier.remove();
                System.out.println(node.getData()+" "+node.getColor());
                if(node.getLeft()!=sentinel)
                frontier.add(node.getLeft());
                if(node.getRight()!=sentinel)
                frontier.add(node.getRight());
            }
        }

}
