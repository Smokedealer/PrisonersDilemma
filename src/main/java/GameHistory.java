import ethnicity.EthnicGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matěj Kareš on 18.01.2018.
 */
public class GameHistory {
    Map<EthnicGroup, ArrayList<Integer>> ethnicityWealth = new HashMap<>();

    ArrayList<Integer> communityWealth = new ArrayList<>();

    public void addCommunityWealth(int wealth){
        communityWealth.add(wealth);
    }

    public void addEthnicity(EthnicGroup ethnicGroup){
        ethnicityWealth.putIfAbsent(ethnicGroup, new ArrayList<>());
    }

    public void recordImmediateEthnicityWealth(EthnicGroup group, int wealth){
        ethnicityWealth.get(group).add(wealth);
    }

    public String getCommunityHistoryString(){
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for(int value : communityWealth){
            sb.append("{community: " + value + "}");
        }

        sb.append("}");

        return sb.toString();
    }

    public String getEthnicGroupsHistoryString(){
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for(EthnicGroup group : ethnicityWealth.keySet()){
            //TODO nevím v jakém formátu to potřebujeme
        }

        sb.append("}");

        return sb.toString();
    }
}
