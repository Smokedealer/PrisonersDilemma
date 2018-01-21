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



We add 2 TrustRobotPerson. Each will act as TFT, but with opposite initial trust.
*/


game.setIterations(20);
game.addPeopleToCommunity(1, factory.TrustRobotPerson(0.0, 1.0, 1.0), factory.EthnicGroup("RobotTFT_0")); // act as tit for tat, begin with faul
game.addPeopleToCommunity(1, factory.TrustRobotPerson(1.0, 1.0, 1.0), factory.EthnicGroup("RobotTFT_1")); // act as tit for tat, begin with cooperation