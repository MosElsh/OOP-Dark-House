package OOP.ec22549.MP;
class Room_ec22549 extends Room {
    public Direction visit(Visitor visitor, Direction directionVisitorArrivesFrom) {
        visitor.tell("You are in ec22549's Dark Room.");
        visitor.tell("You have 3 options:");
        
        char[] options = {'a', 'b', 'c'};
        char optionChosen = visitor.getChoice("\na) Return where you came from.\nb) Run across the room into another room.\nc) Find a torch.", options);
        
        if (optionChosen == 'c') {
            Item torch = new Item("Torch");
            if (visitor.giveItem(torch) == true) {
                visitor.tell("You have now acquired a torch! The room is not so dark anymore.");
            }
            else {
                visitor.tell("You have not found a torch. The room is still very dark.");
            }
            visitor.tell("You now have only 2 options:");
            char[] options2 = {'a', 'b'};
            char option2Chosen = visitor.getChoice("\na) Return where you came from.\nb) Run across the room into another room.\n", options2);
            if (option2Chosen == 'a') {
                visitor.tell("You have now left ec22549's room!");
                return directionVisitorArrivesFrom.opposite(directionVisitorArrivesFrom);
            }
            else {
                visitor.tell("You have now left ec22549's room!");
                return directionVisitorArrivesFrom;
            }
        }
        else if (optionChosen == 'b') {
            visitor.tell("You have now left ec22549's room!");
            return directionVisitorArrivesFrom;
        }
        visitor.tell("You have now left ec22549's room!");
        return directionVisitorArrivesFrom.opposite(directionVisitorArrivesFrom);
    }
}