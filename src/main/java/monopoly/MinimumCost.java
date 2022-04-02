package monopoly;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MinimumCost implements Cost {

    private Double amount;
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

    public String toString(){
        return String.format("MinimumCost(%f or %f %% of total)", amount, percentage * 100);
    }

}