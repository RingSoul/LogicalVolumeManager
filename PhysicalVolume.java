import java.util.UUID;

public class PhysicalVolume extends VolumeRelated {
    // instance variables
    private PhysicalHardDrive phd;

    // constructor
    public PhysicalVolume(String name, PhysicalHardDrive phd)
    {
        super(name);
        this.phd = phd;
    }

    // methods
    // accessor methods
    // (getName and getUUID inherited from VolumeRelated superclass)
    public PhysicalHardDrive getPHD()
    {
        return phd;
    }

    public double getSize()
    {
        return phd.getSize();
    }

}
