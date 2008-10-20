package composite_code;

/**
 *
 * @author Arnaud Knobloch
 */

public interface Equipment {

    public abstract void add (ComputerComponent e);

    public abstract void remove (ComputerComponent e);

    public ComputerComponent getChild (int index);
    
}

