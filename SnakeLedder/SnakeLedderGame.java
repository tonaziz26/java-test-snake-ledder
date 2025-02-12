import java.io.*;
import java.util.*;

class Player {
    private String name;
    private int position = 0;

    public Player() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}


public class SnakeLedderGame {
    public static void main(String[] args) {

        List<Player> players = new ArrayList<>();

        Map<Integer, Integer> snakeMap = new HashMap<>();
        Map<Integer, Integer> ledderMap = new HashMap<>();

        try (Scanner scanner = new Scanner(new File("input.txt"))) {

            int numSnakes = scanner.nextInt();
            for (int i = 0; i < numSnakes; i++) {
                snakeMap.put(scanner.nextInt(), scanner.nextInt());
            }

            int numLedder = scanner.nextInt();
            for (int i = 0; i < numLedder; i++) {
                ledderMap.put(scanner.nextInt(), scanner.nextInt());
            }

            int numPlayer = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < numPlayer; i++) {
                Player player = new Player();
                player.setName(scanner.nextLine().trim());
                players.add(player);
            }

            List<Integer> dices = List.of(1,2,3,4,5,6);

            int index = 0;

            while(index < 100){
                for(Player p : players){
                    Random random = new Random();
                    int dice = dices.get(random.nextInt(dices.size()));

                    int oldPosition = p.getPosition();
                    int newPosition = p.getPosition() + dice;

                    String hit = "";

                    if (snakeMap.containsKey(newPosition)) {
                        newPosition = snakeMap.get(newPosition);
                        hit = " hit Snanke ";
                    }

                    if (ledderMap.containsKey(newPosition)) {
                        newPosition = ledderMap.get(newPosition);
                        hit = " hit Ledder ";
                    }

                    if(newPosition > 100) newPosition = oldPosition ;

                    p.setPosition(newPosition);
                    index = newPosition;

                    System.out.println(p.getName() + " rolled a " + dice + " and moved from "+ oldPosition +" to "+ p.getPosition() + hit);

                };
            }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}