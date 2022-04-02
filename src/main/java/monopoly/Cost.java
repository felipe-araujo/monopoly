package monopoly;

/**
 * Represents some penalty or cost the player has to pay once it enters a square
 * in the game.
 * If a player fails to afford this cost, the game ends.
 * 
 */
public interface Cost {
    public void charge(Player player);

    public Boolean canAfford(Player player);
}