package ethnicity;

import java.util.HashMap;
import java.util.Map;


/**
 * @author ike
 */
public class EthnicGroup implements Ethnicity {

    private String name;
    public Map<Ethnicity, Double> trustLevels = new HashMap<>();

    public EthnicGroup(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
