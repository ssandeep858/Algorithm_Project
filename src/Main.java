
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        IntervalTree<Interval> rb_tree = new IntervalTree<>();
        Map<Integer, Interval> rectangles = new HashMap<>();
        List<IntervalShort> shortIntervals = new ArrayList<>();
        List<Integer> inserted = new ArrayList<>();


        BufferedReader br = new BufferedReader(new FileReader(new File("C:\\Users\\ssand\\Desktop\\algorithmProject\\src\\input.txt")));
        String rectangle = br.readLine();
        while(rectangle!=null) {
            String [] tokens = rectangle.split(" ");
            int id = Integer.parseInt(tokens[0]);
            double xLow = Double.parseDouble(tokens[1]);
            double yLow = Double.parseDouble(tokens[2]);
            double xHigh = Double.parseDouble(tokens[3]);
            double yHigh = Double.parseDouble(tokens[4]);
            rectangles.put(id,new Interval(id,xLow,yLow,xHigh,yHigh));
            shortIntervals.add(new IntervalShort(id,xLow));
            shortIntervals.add(new IntervalShort(id,xHigh));
            rectangle = br.readLine();
        }

        Collections.sort(shortIntervals,(i1, i2) ->{
            if(i1.getXValue() > i2.getXValue())
                return 1;
            else if(i1.getXValue() < i2.getXValue())
                return -1;
            else
                return 0;
        });
        boolean resultFound = false;
        for(IntervalShort intervalShort : shortIntervals){
            int id = intervalShort.getRectId();
            Interval interval = rectangles.get(id);
            if(!inserted.contains(id)) {
                Interval overlappingInterval = rb_tree.intervalSearch(interval);
                if(overlappingInterval!=null){
                    if(interval.getId() < overlappingInterval.getId())
                        System.out.println(interval.getId() + " " + overlappingInterval.getId()+" overlap");
                    else
                        System.out.println(overlappingInterval.getId()+ " " + interval.getId() + " overlap");
                    resultFound = true;
                    break;
                }
                rb_tree.insert(rectangles.get(id));
                inserted.add(id);
            } else {
                rb_tree.deleteNode(interval);
                inserted.remove(inserted.indexOf(id));
            }
        }

        if(!resultFound)
            System.out.println("no overlap");

    }
}
