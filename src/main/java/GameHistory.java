import ethnicity.EthnicGroup;
import ethnicity.Ethnicity;

import java.util.*;


/**
 * Recorder for game history.
 *
 * @author Matěj Kareš
 */
public class GameHistory {

    /**
     * Record for one ethnic group in one point in time.
     */
    public class EthnicGroupRecord {

        /**
         * Ethnic wealth.
         */
        int wealth;

        /**
         * Trust levels towards other ethnic groups.
         */
        Map<Ethnicity,Double> trustLevels;
    }

    /**
     * List of community wealth in time.
     */
    private List<Integer> communityWealth = new ArrayList<>();

    /**
     * Map of ethnic groups with lists of history records.
     */
    private Map<EthnicGroup, List<EthnicGroupRecord>> ethnicGroupHistory = new HashMap<>();


    /**
     * Records community wealth.
     * @param wealth Wealth.
     */
    public void recordCommunityWealth(int wealth){
        communityWealth.add(wealth);
    }

    /**
     * Records ethnic group state.
     * @param group Ethnic group.
     */
    public void recordEthnicGroup(EthnicGroup group){
        EthnicGroupRecord record = new EthnicGroupRecord();
        record.wealth = group.getScore();
        record.trustLevels = new HashMap<>(group.getTrustLevels());

        ethnicGroupHistory.get(group).add(record);
    }

    /**
     * Gets history as JSON string.
     * @return History.
     */
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

    /**
     * Adds ethnic group into recording.
     * @param ethnicGroup Ethnic group.
     */
    public void addEthnicGroup(EthnicGroup ethnicGroup){
        ethnicGroupHistory.putIfAbsent(ethnicGroup, new ArrayList<>());
    }

    /**
     * Gets development of community wealth.
     * @return List of community wealth during time.
     */
    public List<Integer> getCommunityWealth() {
        return communityWealth;
    }

    /**
     * Gets development of ethnic groups state (wealth and trustfulness).
     * @return List of history records for all ethnic groups.
     */
    public Map<EthnicGroup, List<EthnicGroupRecord>> getEthnicGroupHistory() {
        return ethnicGroupHistory;
    }
}
