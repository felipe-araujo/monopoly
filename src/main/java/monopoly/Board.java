package monopoly;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import lombok.Getter;

@Getter
public class Board {

    private static final List<Square> squares = new ArrayList<>();

    static {
        try {
            initializeSquares();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void initializeSquares() throws URISyntaxException, IOException {
        URI uri = Board.class.getClassLoader().getResource("squares.csv").toURI();
        FileSystems.newFileSystem(uri, Collections.emptyMap());

        try (Stream<String> stream = Files
                .lines(Paths.get(uri))) {
            stream.forEach(line -> {
                String[] args = line.split(",");
                String name = args[0];
                Square.Type type = Square.Type.valueOf(args[1].toUpperCase());
                Cost cost = CostFactory.costOf(args[2]);
                Board.squares.add(new Square(name, type, cost));
            });
        }
    }

    public static void printSquares() {
        squares.stream().forEach(square -> System.out.println(square.toString()));
    }

    private Player player;
    private Integer turns = 0;
    private Integer currentPosition = 0;

    public Board(Player player) {
        this.player = player;
    }

    public void run() {

        for (int i = 0; i < 1500; i++) {
            turns++;
            Integer increment = player.rollDice();
            if ((currentPosition + increment) >= squares.size()) {
                player.credit(200D);
                currentPosition = (currentPosition + increment) % squares.size();
            } else {
                currentPosition = currentPosition + increment;
            }
            Square square = squares.get(currentPosition);
            if (square.isProperty() && player.hasProperty(square)) {
                continue;
            }
            if (square.getCost().canAfford(player)) {
                square.getCost().charge(player);
                if (square.isProperty()) {
                    player.addProperty(square);
                }
            } else {
                break;
            }
        }
        printReport();
    }

    private void printReport() {
        System.out.println(String.format("Player finhshed with %f in cash and %d properties", player.getCash(),
                player.getProperties().size()));
        System.out.println(String.format("Game finished after %d turns", turns));
    }

}