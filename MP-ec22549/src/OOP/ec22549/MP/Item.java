package OOP.ec22549.MP;
class Item {
    
    final String name;
    
    Item(String nameOfItem) {
        name = nameOfItem;
    }
    
    boolean equals(Item x) {
        return name.equals(x.name);
    }
}