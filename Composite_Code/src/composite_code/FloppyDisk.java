package composite_code;

public class FloppyDisk extends ComputerComponent {

    public FloppyDisk() {
        super();
    }
        
    public FloppyDisk(String name) {
        super(name);
    }

        public FloppyDisk(String name, float netPrice, float discountPrice, double watt) {
       super(name, netPrice, discountPrice, watt);
    }
}

