package OOP.ec22549.MP;
class Room_ec22995 extends Room {

	public Direction visit(Visitor visitor, Direction directionVisitorArrivesFrom){
		int directionNumber = get_directionNumber(directionVisitorArrivesFrom);

		switch (directionNumber) {
			case 1:
				return enteringMysteryRoom(directionVisitorArrivesFrom, visitor);
			case 2:
				return enteringMysteryRoom(directionVisitorArrivesFrom, visitor);
			case 3:
				return wrongDirection(directionVisitorArrivesFrom, visitor);
			case 4:
				return wrongDirection(directionVisitorArrivesFrom, visitor);
			case 5:
				return Lost(directionVisitorArrivesFrom, visitor);
			default: break;
		}
		return Direction.opposite(directionVisitorArrivesFrom);
	}

	private int get_directionNumber(Direction arriving) {
		if (arriving == Direction.FROM_SOUTH) return 1;
		if (arriving == Direction.FROM_EAST) return 2;
		if (arriving == Direction.FROM_NORTH) return 3;
		if (arriving == Direction.FROM_WEST) return 4;
		if (arriving == Direction.UNDEFINED) return 5;
		else return 0;
	}

	private Direction Lost(Direction arriving, Visitor visitor) {
		visitor.tell("Sorry my brother you're lost.");
		return Direction.UNDEFINED;
	}

	private Direction wrongDirection(Direction arriving, Visitor visitor) {
		visitor.tell("Sorry you need to arrive from either south or east in order to see the door of the mystery room. \nYou turn around and leave.");
		return Direction.opposite(arriving);
	}

	private Direction enteringMysteryRoom(Direction arriving, Visitor visitor) {
		char[] YesNo = {'y', 'n'};
		visitor.tell("Welcome visitor, you are about to enter the mystery Room of the Winchester Mystery House. Are you sure you want to be part of this thrilling adventure ?");
		char choice = visitor.getChoice("(Y) Yes, bring me to wonderland, (N) No, let me stay an ignorant fool...", YesNo);
		
		if (choice == 'y') {
			MysteryRoomQuest(visitor);
			visitor.tell("You found the exit door congratulations, goodbye :)");
		}
		else {
			visitor.tell("You chose not to enter the Room. \nBye Bye, you're missing something... \nYou turn around and leave.");
			return Direction.opposite(arriving);
		}
		return Direction.TO_NORTH;
	}
	
	private void MysteryRoomQuest(Visitor visitor) {
		visitor.tell("As you enter the room a ninja asks you if you want to take one of his weapons, careful in your choice, this quest might get dangerous.");
		ninjaNewItem(visitor);
		
		visitor.tell("Now that you're ready and armed (or not?) you walk into the room.");
		
	}
	
	private void ninjaNewItem(Visitor visitor) {
		char[] possibleChoices = {'a', 'b', 'c', 'd'};
		char choice = visitor.getChoice("(a) Shuriken (b) Sword (c) Dagger (d) No thanks", possibleChoices);
		if (choice == 'a') {
			Item Shuriken = new Item("Shuriken");
			visitor.giveItem(Shuriken);
		}
		if (choice == 'b') {
			Item Sword = new Item("Sword");
			visitor.giveItem(Sword);
		}
		if (choice == 'c') {
			Item Dagger = new Item("Dagger");
			visitor.giveItem(Dagger);
		}
		return;
	}
	
	private void openChest(Visitor visitor) {
		char[] possibleChoices = {'y', 'n'};
		char choice = visitor.getChoice("There is a chest in front of you do you choose to open it? (y) YESSSS (n) no", possibleChoices);
		if (choice == 'y'){
			visitor.tell("You open the chest and find 7 pieces of gold");
			visitor.takeGold(7);
		}
		if (choice == 'y') {
			visitor.tell("You don't open the chest and move on.");
		}
	}
}