import java.util.List;

public class Room {

    private int length, width;
    private Robot robot1, robot2;
    private List<Furniture> furnitures;
    private List<Dust> dusts;
    private List<Dustpan> dustpans;

    public static final int ROBOT1 = 1;
    public static final int ROBOT2 = 2;

    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    public Room(int length, int width, Robot robot1, Robot robot2, 
            List<Furniture> furnitures, List<Dust> dusts, List<Dustpan> dustpans) {
        this.length = length;
        this.width = width;
        this.robot1 = robot1;
        this.robot2 = robot2;
        this.furnitures = furnitures;
        this.dusts = dusts;
        this.dustpans = dustpans;
    }

    public void move(int robot, int direction) {
        Robot r = null;
        if (robot == ROBOT1) {
            r = robot1;
        } else if (robot == ROBOT2) {
            r = robot2;
        } else {
            throw new IllegalArgumentException("Unknown robot!");
        }

        switch (direction) {
        case LEFT:
            if (r.y > 1 && !furnitureExistIn(r.x, r.y-1) && !dustExistIn(r.x, r.y-1) && !dustpanExistIn(r.x, r.y-1))
                r.moveLeft();
            break;
        case RIGHT:
            if (r.y < width && !furnitureExistIn(r.x, r.y+1) && !dustExistIn(r.x, r.y+1) && !dustpanExistIn(r.x, r.y+1))
                r.moveRight();
            break;
        case UP:
            if (r.x > 1 && !furnitureExistIn(r.x-1, r.y) && !dustExistIn(r.x-1, r.y-1) && !dustpanExistIn(r.x-1, r.y))
                r.moveUp();
            break;
        case DOWN:
            if (r.x < length && !furnitureExistIn(r.x+1, r.y) && !dustExistIn(r.x+1, r.y) && !dustpanExistIn(r.x+1, r.y))
                r.moveDown();
            break;
        default:
            throw new IllegalArgumentException("Unknown direction!");
        }
    }
    
    public void sweep(int robot, int where, int to) {
        try {
            if (robot == ROBOT1)
                robot1.sweepDust(this, where, to);
            else if (robot == ROBOT2)
                robot2.sweepDust(this, where, to);
            else
                throw new IllegalArgumentException("Unknown robot!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= width; j++) {
                if (i == robot1.x && j == robot1.y && i == robot2.x && j == robot2.y) {
                    sb.append("2r ");
                } else if (i == robot1.x && j == robot1.y) {
                    sb.append(robot1.toString() + ROBOT1 + " ");
                } else if (i == robot2.x && j == robot2.y) {
                    sb.append(robot2.toString() + ROBOT2 + " ");
                } else if (furnitureExistIn(i, j)) {
                    sb.append("fu ");
                } else if (dustExistIn(i, j)) {
                    sb.append("du ");
                } else if (dustpanExistIn(i, j)) {
                    sb.append("dp ");
                } else {
                    sb.append("-- ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
    
    public Dust getDustAt(int x, int y) {
        for (Dust d : dusts) {
            if (x == d.x && y == d.y) {
                return d;
            }
        }
        return null;
    }
    
    public Furniture getFurnitureAt(int x, int y) {
        for (Furniture f : furnitures) {
            if (x == f.x && y == f.y) {
                return f;
            }
        }
        return null;
    }
    
    public Dustpan getDustpanAt(int x, int y) {
        for (Dustpan d : dustpans) {
            if (x == d.x && y == d.y) {
                return d;
            }
        }
        return null;
    }
    
    private boolean furnitureExistIn(int x, int y) {
        return getFurnitureAt(x, y) != null;
    }
    
    private boolean dustExistIn(int x, int y) {
        return getDustAt(x, y) != null;
    }
    
    private boolean dustpanExistIn(int x, int y) {
        return getDustpanAt(x, y) != null;
    }

}