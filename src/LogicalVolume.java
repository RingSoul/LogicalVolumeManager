import java.util.UUID;

public class LogicalVolume {
    private String name;
    private double size;
    private String uuid;
    private VolumeGroup vg;

    public LogicalVolume(String name, double size, VolumeGroup vg)
    {
        this.name = name;
        this.size = size;
        this.vg = vg;
        uuid = UUID.randomUUID().toString();
    }

    // methods
    // accessor methods
    public String getName() {
        return name;
    }
    public double getSize() {
        return size;
    }
    public String getUuid() {
        return uuid;
    }
    public VolumeGroup getVg() {
        return vg;
    }
}
