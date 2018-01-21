var groupBlue = factory.EthnicGroup("Blue (naive)");
var groupRed = factory.EthnicGroup("Red");

groupBlue.setBiasTowards(groupRed, 1.0); // blue are good to red at the beginning
groupRed.setBiasTowards(groupBlue, 0.0); // red hates blue

game.addPeopleToCommunity(1, factory.TrustComplexPerson(0.5, 0.02, 0.05, 0.1, 0.05), groupBlue);
game.addPeopleToCommunity(1, factory.TrustComplexPerson(0.5, 0.1, 0.05, 0.1, 0.05), groupRed);