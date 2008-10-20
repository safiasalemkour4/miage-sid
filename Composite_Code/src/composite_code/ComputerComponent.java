package composite_code;

/**
 *
 * @author arnaud
 */
public class ComputerComponent {

    private String name;
    private float netPrice;
    private float discountPrice;
    private double watt;

    public ComputerComponent() {

        this.name = "Unamed";
        this.netPrice = 0;
        this.discountPrice = 0;
        this.watt = 0.0;
    }

    public ComputerComponent(String name) {

        this.name = name;
        this.netPrice = 0;
        this.discountPrice = 0;
        this.watt = 0.0;
    }

    public ComputerComponent(String name, float netPrice, float discountPrice, double watt) {

        this.name = name;
        this.netPrice = netPrice;
        this.discountPrice = discountPrice;
        this.watt = watt;
    }

    public String getName() {
        return this.name;
    }

    public float getNetPrice() {
        return this.netPrice;
    }

    public float getDiscountPrice() {
        return this.discountPrice;
    }
    
    public double getWatt() {
        return this.watt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetPrice(float netPrice) {
        this.netPrice = netPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String toString() {
        return "Je suis "+this.name + ", prix net : " + this.netPrice + ", prix soldé : " +
                this.discountPrice + ", watt : " + this.watt+"\n";
    }
}
