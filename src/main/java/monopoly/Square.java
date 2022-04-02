package monopoly;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Square {
    private String name;
    private Type type;
    private Cost cost;
    // private Boolean isProperty;

    public Boolean isProperty(){
        return Type.PROPERTY.equals(type);
    }

    public String toString(){
        return String.format("Square[name = %s, type = %s, cost = %s", name, type, cost.toString());
    }

    enum Type {
        PROPERTY, PENALTY;
    }
}
