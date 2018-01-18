import ethnicity.EthnicGroup;
import ethnicity.Ethnicity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matěj Kareš on 18.01.2018.
 */
public class GameHistory {

    private class EthnicGroupRecord {
        int wealth;
        HashMap<Ethnicity,Double> trustLevels;
    }

    private List<Integer> communityWealth = new ArrayList<>();
    private Map<EthnicGroup, List<EthnicGroupRecord>> ethnicGroupHistory = new HashMap<>();

    public void recordCommunityWealth(int wealth){
        communityWealth.add(wealth);
    }

    public void recordEthnicGroup(EthnicGroup group, int wealth){
        EthnicGroupRecord record = new EthnicGroupRecord();
        record.wealth = wealth;
        record.trustLevels = new HashMap<>(group.getTrustLevels());

        ethnicGroupHistory.get(group).add(record);
    }

    public String getHistoryJSON(){
        StringBuilder sb = new StringBuilder();

        sb.append("[\n");

        for (int i = 0; i < communityWealth.size(); i++) {
            sb.append("\t{");
            sb.append("\"community\": ").append(communityWealth.get(i));

            for(Map.Entry<EthnicGroup, List<EthnicGroupRecord>> entry : ethnicGroupHistory.entrySet()){

                sb.append(", \"").append(entry.getKey().getName())
                    .append("\": ").append(entry.getValue().get(i).wealth);
            }

            sb.append(",\t \"_trust\": { ");

            for(Map.Entry<EthnicGroup, List<EthnicGroupRecord>> entry : ethnicGroupHistory.entrySet()){

                EthnicGroup group = entry.getKey();
                EthnicGroupRecord record = entry.getValue().get(i);

                record.trustLevels.forEach((ethnicity, trust) -> {
                    sb.append("\"" + group.getName() + " -> " + ethnicity.getName() + "\": " + trust + ", ");
                });
            }

            sb.append("}");

            sb.append("},\n");
        }

        sb.append("]\n");

        return sb.toString();
    }

    public void addEthnicGroup(EthnicGroup ethnicGroup){
        ethnicGroupHistory.putIfAbsent(ethnicGroup, new ArrayList<>());
    }


}
