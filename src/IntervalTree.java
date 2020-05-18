public class IntervalTree<T extends Comparable<T>> extends RB_Tree<T> {


    @Override
    public Node<Interval> insert(T interval){
        Node<Interval> insertedNode = super.insert(interval);

        updateMaxValue(insertedNode);

        return insertedNode;
    }

    @Override
    public Node<Interval> deleteNode(T interval){
        Node<Interval> successor = super.deleteNode(interval);

        updateMaxValue(successor);

        return successor;
    }

    public Interval intervalSearch(Interval interval){
        Node<Interval> x = (Node<Interval>) root;
        if(x==null)
            return null;
        while(x!=sentinel && !isOverlapInY(x.getData(),interval)){
            if(x.getLeft()!=sentinel && x.getLeft().getData().getMax() >= interval.getYLow())
                x = x.getLeft();
            else
                x = x.getRight();
        }
        return x.getData();
    }

    private boolean isOverlapInY(Interval i1, Interval i2){
        if(i1.getYLow() > i2.getYHigh() || i2.getYLow() > i1.getYHigh()){
            return false;
        }
        return true;
    }


    @Override
    public void leftRotate(Node<T> x){
        super.leftRotate(x);
        Node<Interval> tempX = (Node<Interval>) x;
        Node<Interval> y = (Node<Interval>) x.getRight();
        if(y!=sentinel) {
            y.getData().setMax(tempX.getData().getMax());
            tempX.getData().setMax(maxOfThree(tempX.getData().getYHigh(), tempX.getLeft().getData(), tempX.getRight().getData()));
        }
    }

    @Override
    public void rightRotate(Node<T> x){
        super.rightRotate(x);
        Node<Interval> tempX = (Node<Interval>) x;
        Node<Interval> y = (Node<Interval>) x.getLeft();
        if(y!=sentinel) {
            y.getData().setMax(tempX.getData().getMax());
            tempX.getData().setMax(maxOfThree(tempX.getData().getYHigh(), tempX.getLeft().getData(), tempX.getRight().getData()));
        }
    }

    private void updateMaxValue(Node<Interval> interval){
        //setting max value
        while(interval!=sentinel){
            interval.getData().setMax(maxOfThree(interval.getData().getYHigh(), interval.getLeft().getData(),interval.getRight().getData()));
            interval = interval.getParent();
        }
    }
    private double maxOfThree(double one, Interval i2, Interval i3){
        double two = getMaxValue(i2);
        double three = getMaxValue(i3);
        double max = Math.max(one, two);
        return Math.max(max, three);
    }

    private double getMaxValue(Interval interval){
        return interval == null ? 0.0 : interval.getMax();
    }
}
