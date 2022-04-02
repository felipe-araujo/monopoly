package monopoly;

public interface Cost {
    public void charge(Player player);
    public Boolean canAfford(Player player);
}