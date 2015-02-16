package Part1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Restaurant {
    private List<Edible> items;
    
    public Restaurant() {
        items = new ArrayList<Edible>();
    }
    
    public void addEdibleItem(Edible edibleItem) {
        items.add(edibleItem);
    }
    
    public void orderByCalories() {
        Collections.sort(items, new CalorieComparator());
    }
    
    public List<Edible> getItems() {
        return items;
    }
    
    public String getMenu() {
        StringBuffer buffer = new StringBuffer();
        for (Edible ed : items) {
            buffer.append(ed.toString());
            buffer.append('\n');
        }
        return buffer.toString();
    }
    
    
}
