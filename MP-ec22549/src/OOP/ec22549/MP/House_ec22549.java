package OOP.ec22549.MP;
import java.util.Random;

class House_ec22549 extends House {
    
    private int row = 1;
    private int column = 1;

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    private void setRow(int r) {
        row = r;
        return;
    }

    private void setColumn(int c) {
        column = c;
        return;
    }
    
    public Direction visit(Visitor v, Direction d) {
        
        // Random 9 rooms.
        // You leave the house by ending up outside the 2D array (when the row/column variable is bigger than 2 or smaller than 0).
        
        Direction newDirection = d;
        
        Room[][] roomsArray = {
            {new Room_ec22549(), new Room_ec22852(), new Room_ec22617()},
            {new Room_ec22621(), new Room_ec22675(), new Room_ec22726()},
            {new Room_ec22752(), new Room_ec22597(), new Room_ec22995()}
        };
        
        // All direction returns seem to be going north or south so to make this interesting I am going to randomise direction returns.
        
        boolean insideHouse = true;
        
        while (insideHouse) {
            v.updateLocation(row, column);
            roomsArray[getRow()][getColumn()].visit(v, d);
            newDirection = calculateNewDirection();
            
            if ("heading North".equals(newDirection.toString())) {
                setRow(getRow() - 1);
            }
            else if ("heading East".equals(newDirection.toString())) {
                setColumn(getColumn() + 1);
            }
            else if ("heading South".equals(newDirection.toString())) {
                setRow(getRow() + 1);
            }
            else {
                setColumn(getColumn() - 1);
            }
            
            // Check if outside the "house" (array) index range.
            if (getRow() < 0 || getRow() >= roomsArray.length || getColumn() < 0 || getColumn() >= roomsArray.length) {
                insideHouse = false;
            }
        }

        // Removes Old Marker but fails to set new marker (as no index exists) so signals end of house tour.
        v.updateLocation(2000, 2000);
        
        return newDirection;
    }
    
    Direction calculateNewDirection() {
        Direction[] directionsList = {Direction.TO_NORTH, Direction.TO_EAST, Direction.TO_SOUTH, Direction.TO_WEST};
        return directionsList[(new Random()).nextInt(4)];
    }
    
}