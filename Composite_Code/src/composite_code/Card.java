package composite_code;

public class Card extends ComputerComponent {

    public Card() {
        super();
    }
        
    public Card(String name) {
        super(name);
    }

    public Card(String name, float netPrice, float discountPrice, double watt) {
       super(name, netPrice, discountPrice, watt);
    }
}

