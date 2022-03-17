import java.util.UUID;

public class PhysicalVolume {
    // instance variables
    private String name;
    private String uuid;
    private PhysicalHardDrive phd;

    // constructor
    public PhysicalVolume(String name, PhysicalHardDrive phd)
    {
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
        this.phd = phd;
    }

    // methods
    // accessor methods
    public String getName()
    {
        return name;
    }
    public String getUUID()
    {
        return uuid;
    }

    public double getSize()
    {
        return phd.getSize();
    }
}
