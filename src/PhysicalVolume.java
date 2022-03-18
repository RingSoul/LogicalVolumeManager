import java.util.UUID;

public class PhysicalVolume extends VolumeRelated {
    // instance variables
    private String name;
    private String uuid;
    private PhysicalHardDrive phd;

    // constructor
    public PhysicalVolume(String name, PhysicalHardDrive phd)
    {
        super(name);
        this.phd = phd;
    }

    // methods
    // accessor methods

    public double getSize()
    {
        return phd.getSize();
    }
}
