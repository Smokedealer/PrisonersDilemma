/*
Syntax:

	game.setIterations(numberOfIterations);

		@param numberOfIterations - how many rounds of trials should be run (each round = N/2 trials, N is size of community)


	game.addPeopleToCommunity(numberOfPersons, personFactory, [ethnicGroup]);

		@desc  Adds N people into community.
		@param numberOfPersons        - How many people of this particular personality should be generated
		@param personFactory          - Factory for generating those people
		@param ethnicGroup [optional] - Ethnic group shared across generated people; automatically generated if not provided



	factory.Kavka(), factory.<Persona>(), factory.<Persona>(params...)
	factory.TrustRobotPerson(defaultTrust, trustIncrement, trustDecrement)

		@desc  Factory for generating people. Can have additional parameters (in case of TrustRobotPerson and TrustComplexPerson).
		@param defaultTrust   - Default trust level towards strangers  <0,1>
		@param trustIncrement - Increment in trust when opponent cooperates  <0,1>
		@param trustDecrement - Decrement in trust when opponent doesn't cooperate  <0,1>


	factory.EthnicGroup(name)

		@desc  Factory for creating new ethnic group
		@param name - Name of the ethnic group


	group.setBiasTowards(otherGroup, trust);

		@desc  Sets bias (trust level) of ethnic group towards other ethnic group
		@param otherGroup - Other group, to which we are setting bias
		@param trust      - Initial trust level  <0,1>



We create 2 ethnic groups, Blue and Red. Blue fully trust Red, Red fully distrust Blue.
We add 2 TrustComplexPerson with slightly different parameters.
Result of simulation differs each run because of randomness. Most of the times Red starts to trust Blue.
*/


var groupBlue = factory.EthnicGroup("Blue (naive)");
var groupRed = factory.EthnicGroup("Red");

groupBlue.setBiasTowards(groupRed, 1.0); // blue are good to red at the beginning
groupRed.setBiasTowards(groupBlue, 0.0); // red hates blue

game.addPeopleToCommunity(1, factory.TrustComplexPerson(0.5, 0.02, 0.05, 0.1, 0.05), groupBlue);
game.addPeopleToCommunity(1, factory.TrustComplexPerson(0.5, 0.1, 0.05, 0.1, 0.05), groupRed);