package OOP.ec22549.MP;
interface Visitable {

    Direction visit( // Returns direction the visitor leaves towards.
        Visitor visitor,
        Direction directionVisitorArrivesFrom);
}