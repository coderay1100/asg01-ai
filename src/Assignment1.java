import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Assignment1 
{
    public static void main(String[] args)
    { 	
        if (args.length < 3) {
            System.out.println("Usage: java Assignment1 <strategy> <inputfile> <outputfile>");
            return;
        }

        // The number of lines for each input file
        final int NUMBER_OF_LINES = 6;
        // The index args which points to the input file
        final int INPUT_FILE = 1;

        try {
            BufferedReader br = new BufferedReader(new FileReader(args[INPUT_FILE]));
            try {
                // Dimension of the room
                int m = 0, n = 0;
                // Position of the robots
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                // Number of dust pans
                int h = 0;
                // Number of dust piles
                int k = 0;
                // Number of furnitures
                int l = 0;
                // List of positions of dust pans
                ArrayList<Dustpan> dustPans = new ArrayList<Dustpan>();
                // List of positions of dust piles
                ArrayList<Dust> dustPiles = new ArrayList<Dust>();
                // List of positions of furnitures
                ArrayList<Furniture> furnitures = new ArrayList<Furniture>();

                String[] numbers = null;
                for (int i = 1; i <= NUMBER_OF_LINES; i++) {
                    String line = br.readLine();
                    if (line == null)
                        break;
                    numbers = line.split(" ");
                    
                    switch (i) {
                    case 1:
                        m = parse(numbers[0]);
                        n = parse(numbers[1]);
                        break;
                    case 2:
                        x1 = parse(numbers[0]);
                        y1 = parse(numbers[1]);
                        x2 = parse(numbers[2]);
                        y2 = parse(numbers[3]);
                        break;
                    case 3:
                        h = parse(numbers[0]);
                        k = parse(numbers[1]);
                        l = parse(numbers[2]);
                        break;
                    case 4:
                        for (int j = 0; j < h; j++) {
                            if (j != 0)
                                numbers = br.readLine().split(" ");
                            int x = parse(numbers[0]);
                            int y = parse(numbers[1]);
                            dustPans.add(new Dustpan(x, y));
                        }
                        break;
                    case 5:
                        for (int j = 0; j < k; j++) {
                            if (j != 0)
                                numbers = br.readLine().split(" ");
                            int x = parse(numbers[0]);
                            int y = parse(numbers[1]);
                            dustPiles.add(new Dust(x, y));
                        }
                        break;
                    case 6:
                        for (int j = 0; j < l; j++) {
                            if (j != 0)
                                numbers = br.readLine().split(" ");
                            int x = parse(numbers[0]);
                            int y = parse(numbers[1]);
                            furnitures.add(new Furniture(x, y));
                        }
                        break;
                    }
                }

                Room room = new Room(m, n, new Robot(x1, y1), new Robot(x2, y2), furnitures, dustPiles, dustPans);
                System.out.println(room);
                room.move(Room.ROBOT1, Room.UP);
                System.out.println(room);
                room.sweep(Room.ROBOT1, Room.RIGHT, Room.RIGHT);
                System.out.println(room);

            } finally {
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error when trying to read input file");
            System.out.println(e.getMessage());
            return;
        }
    }

    private static int parse(String number) {
        return Integer.parseInt(number);
    }

}
