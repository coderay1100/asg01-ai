
public class Robot extends Entity {
    
    public Robot(int x, int y) {
        super(x, y);
    }

    public void moveLeft() {
        this.y--;
    }
    
    public void moveRight() {
        this.y++;
    }
    
    public void moveUp() {
        this.x--;
    }
    
    public void moveDown() {
        this.x++;
    }
    
    public void sweepDust(Room r, int where, int to) throws Exception {
        Dust d = null;
        Dustpan dp = null;
        
        // check the target position relative to the robot 
        if (where == Room.LEFT || where == Room.RIGHT) {
            // if the target is on its left/right side
            
            d = where == Room.LEFT ? r.getDustAt(this.x, this.y-1) : r.getDustAt(this.x, this.y+1);
            
            // check whether or not the dust exist
            if (d != null) {
                // if it does exist
                
                // check where we want to sweep to
                if (to == Room.UP) {
                    //TODO MASUKIN DEBU KE PENGKI
                    dp = r.getDustpanAt(d.x-1, d.y);
                    if (dp != null) {
                        dp.addDust(d);
                        d = null;
                    } else {
                        d.x--;                        
                    }
                } else if (to == Room.DOWN) {
                    d.x++;
                } else if (to == where) {
                    if (to == Room.LEFT) {
                        d.y--;
                    } else {
                        d.y++;
                    }
                } else {
                    throw new IllegalArgumentException("Dust from left/right-side can only be swept to either up, down, or further");
                }                
            } else {
                // if the target dust does not exist
                throw new Exception("No dust there!");
            }
        } else if (where == Room.UP || where == Room.DOWN) {
            d = where == Room.UP ? r.getDustAt(this.x-1, this.y) : r.getDustAt(this.x+1, this.y);
            if (d != null) {
                if (to == Room.LEFT) {
                    d.y--;
                } else if (to == Room.RIGHT) {
                    d.y++;
                } else if (to == where) {
                    if (to == Room.UP) {
                        d.x--;
                    } else {
                        d.x++;
                    }
                } else {
                    throw new IllegalArgumentException("Dust from up/down-side can only be swept to either left, right, further");
                }                
            } else {
                throw new Exception("No dust there!");
            }
        } else {
            throw new IllegalArgumentException("Unknown dust position!");
        }
    }
    
    @Override
    public String toString() {
        return "r";
    }
    
}
