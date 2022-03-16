public class PhysicalHardDrive {
    // instance variables
    private String name;
    private double size;

    // constructor
    public PhysicalHardDrive(String name, double size)
    {
        this.name = name;
        this.size = size;
    }

    // methods
    // accessor methods
    public String getName()
    {
        return name;
    }
    public double getSize()
    {
        return size;
    }
}
