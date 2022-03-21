import java.util.UUID;

public class LogicalVolume extends VolumeRelated {
    private double size;
    private VolumeGroup vg;

    public LogicalVolume(String name, double size, VolumeGroup vg)
    {
        super(name);
        this.size = size;
        this.vg = vg;
    }

    // methods
    // accessor methods
    // (getName and getUUID inherited from VolumeRelated superclass)
    public double getSize() {
        return size;
    }
    public VolumeGroup getVg() {
        return vg;
    }
}
