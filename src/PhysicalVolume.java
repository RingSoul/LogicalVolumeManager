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
        this.phd = phd;
        this.uuid = UUID.randomUUID().toString();

    }

}
