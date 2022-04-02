package monopoly;

/**
 * Entry point
 *
 */
public class App {

    static final Integer ITERATIONS = 1000;

    public static void main(String[] args) {
                
        Integer totalTurns = 0;
        Integer purchasedProperties = 0;
        Integer indianaAvenuePurchases = 0;
        
        Board.printSquares();
        for (int i = 0; i < ITERATIONS; i++) {
            Player player = new Player();
            Board board = new Board(player);
            board.run();
            totalTurns += board.getTurns();
            purchasedProperties += player.getNumberOfProperties();
            indianaAvenuePurchases += player.hasProperty("Indiana Avenue") ? 1 : 0;
        }

        /***
         * 
         * What is the average number of rolls (turns) in a game?
         * What is the average number of properties purchased in a game?
         * As a percentage, in how many games is Indiana Avenue purchased?
         * 
         */

         System.out.println(String.format("Average number of turns: %.2f", (double) totalTurns / ITERATIONS));
         System.out.println(String.format("Average number of properties purchased: %.2f", (double) purchasedProperties / ITERATIONS));
         System.out.println(String.format("%% of games where Indiana Avenue is purchased: %.2f%%", ((double) indianaAvenuePurchases / ITERATIONS) * 100) );

    }
}
