import java.util.ArrayList;
import java.util.List;


public class Dustpan extends Entity {
    
    private List<Dust> dusts;
    
    public Dustpan(int x, int y) {
        super(x, y);
        dusts = new ArrayList<Dust>();
    }
    
    public void addDust(Dust d) {
        dusts.add(d);
    }
    
}
