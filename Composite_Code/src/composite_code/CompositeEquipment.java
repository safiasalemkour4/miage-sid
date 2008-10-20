package composite_code;

import java.util.ArrayList;

public class CompositeEquipment extends ComputerComponent implements Equipment {

    private ArrayList<ComputerComponent> listEquipment;

    public CompositeEquipment() {
        super();
        this.listEquipment = new ArrayList<ComputerComponent>();
    }

    public CompositeEquipment(String name) {
        super(name);
        this.listEquipment = new ArrayList<ComputerComponent>();
    }

    public void operation() {
    }

    public void add(ComputerComponent e) {
        this.listEquipment.add(e);
    }

    public void remove(ComputerComponent e) {
        this.listEquipment.add(e);
    }

    public ComputerComponent getChild(int index) {
        return this.listEquipment.get(index);
    }

    public float getNetPrice() {
        float res = 0;

        for (ComputerComponent c : listEquipment) {
            res += c.getNetPrice();
        }
        return res;
    }

    public float getDiscountPrice() {
        float res = 0;

        for (ComputerComponent c : listEquipment) {
            res += c.getDiscountPrice();
        }
        return res;
    }

    public String toString() {
        String res = "Je suis " + super.getName() + ", un element composite, mon prix net est de " + this.getNetPrice()+
                " et mon prix soldé est de " + this.getDiscountPrice()+ ".\nJe suis composé de :\n";
        
        for (ComputerComponent c : listEquipment) {
            res += "- "+c.getName()+"\n";
        }
        
        return res;
    }
}

