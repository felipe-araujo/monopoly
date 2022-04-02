package monopoly;

import lombok.AllArgsConstructor;

/**
 * A kind of cost that consists on a percentage of the cash owned by the user OR
 * a minimum fixed value, whichever is greater.
 * 
 * 
 */
@AllArgsConstructor
public class MinimumCost implements Cost {

    /**
     * @return Minimum value charged
     * 
     */
    private Double amount;
    /**
     * @return Percentage of user cash due.
     * 
     */
    private Double percentage;

    @Override
    public void charge(Player player) {
        if (canAfford(player)) {
            player.charge(finalAmount(player));
        } else {
            throw new RuntimeException("Player can't afford this cost!");
        }
    }

    private double finalAmount(Player player) {
        return Math.max(player.getCash() * percentage, amount);
    }

    @Override
    public Boolean canAfford(Player player) {
        return player.getCash() >= finalAmount(player);
    }

    public String toString() {
        return String.format("MinimumCost(%.2f or %.2f %% of total)", amount, percentage * 100);
    }

}