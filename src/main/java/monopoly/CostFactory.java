package monopoly;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CostFactory {

    final static Pattern FIXED_AMOUNT_PATTERN = Pattern.compile("^(\\d{1,100}) or (\\d{1,2})\\% of total cash whichever is greater");

    public static Cost costOf(String constraints){
        if(constraints.trim().matches("^\\d{1,100}$")){
            return new FixedCost(Double.valueOf(constraints.trim()));
        }else{
            Matcher m = FIXED_AMOUNT_PATTERN.matcher(constraints);
            if(m.find()){
                Double amount = Double.valueOf(m.group(1));
                Double percentage = Double.valueOf(m.group(2)) / 100;
                return new MinimumCost(amount, percentage);
            }
        }
        return null;
    }
}