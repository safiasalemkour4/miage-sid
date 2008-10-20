package composite_code;


public interface Equipment {

    public abstract void add (ComputerComponent e);

    public abstract void remove (ComputerComponent e);

    public ComputerComponent getChild (int index);
    
}

