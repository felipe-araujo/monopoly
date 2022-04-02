package monopoly;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FixedCost implements Cost {
    
    private Double amount;

    @Override
    public void charge(Player player) {
        if(canAfford(player)){
            player.charge(this.amount);
        }else{
            throw new RuntimeException("Player can't afford this cost!");
        }       
    }

    @Override
    public Boolean canAfford(Player player) {
        return player.getCash() >= this.amount;
    }

    public String toString(){
        return String.format("Fixed cost(%f)", amount);
    }
    
}