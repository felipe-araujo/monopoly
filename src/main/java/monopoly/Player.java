package monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import lombok.Getter;

@Getter
public class Player {

    private Double cash;
    private List<Square> properties;

    public Player() {
        this.cash = 1500D;
        this.properties = new ArrayList<>();
    }

    public void charge(Double amount) {
        this.cash -= amount;
    }

    public void credit(Double amount) {
        this.cash += amount;
    }

    public void addProperty(Square property) {
        this.properties.add(property);
    }

    public Boolean hasProperty(Square property) {
        return properties.contains(property);
    }

    public Boolean hasProperty(String name) {
        return null != properties.stream().filter(property -> property.getName().equals(name)).findAny().orElse(null);
    }

    public Integer getNumberOfProperties() {
        return properties.size();
    }

    public Integer rollDice() {
        return roll() + roll();
    }

    private Integer roll() {
        Integer min = 1, max = 6;
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}