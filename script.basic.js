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
	factory.TrustRobotPerson(defaultTrust, trustIncrement, trustDecrement, groupTrustIncrement, groupTrustDecrement)

		@desc  Factory for generating people. Can have additional parameters (in case of TrustRobotPerson and TrustComplexPerson).
		@param defaultTrust        - Default trust level towards strangers  <0,1>
		@param trustIncrement      - Increment in trust when opponent cooperates  <0,1>
		@param trustDecrement      - Decrement in trust when opponent doesn't cooperate  <0,1>
		@param groupTrustIncrement - Increment in trust towards ethnic group when opponent cooperates  <0,1>
		@param groupTrustDecrement - Decrement in trust towards ethnic group when opponent doesn't cooperate  <0,1>


	factory.EthnicGroup(name)

		@desc  Factory for creating new ethnic group
		@param name - Name of the ethnic group


	group.setBiasTowards(otherGroup, trust);

		@desc  Sets bias (trust level) of ethnic group towards other ethnic group
		@param otherGroup - Other group, to which we are setting bias
		@param trust      - Initial trust level  <0,1>



We add 6 different persons into community. Each will have it's own generated ethnic group (named after their class).
*/

game.addPeopleToCommunity(1, factory.Kavka());
game.addPeopleToCommunity(1, factory.Podrazak());
game.addPeopleToCommunity(1, factory.TitForTat());
game.addPeopleToCommunity(1, factory.TitForTwoTats());
game.addPeopleToCommunity(1, factory.Optimalni());
game.addPeopleToCommunity(1, factory.Rozmar());
