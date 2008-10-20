
import composite_code.Bus;
import composite_code.Cabinet;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import composite_code.Card;
import composite_code.Chassis;
import composite_code.FloppyDisk;

/**
 *
 * @author arnaud
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        Cabinet cabinet = new Cabinet("PC Boitier");
        Chassis chassis = new Chassis("PC Chassis");

        cabinet.add(chassis);

        Bus bus = new Bus("MCA Bus");
        Card card = new Card("16Mbs Token Ring", 13.35f, 12.0f, 30.0);
        bus.add(card);

        chassis.add(bus);
        FloppyDisk floppyDisk = new FloppyDisk("3.5in Floppy", 3.95f, 1.50f, 10.2);
        chassis.add(floppyDisk);

        System.out.println(cabinet);
        System.out.println(chassis);
        System.out.println(bus);
        System.out.println(card);
        System.out.println(floppyDisk);

    }
}
