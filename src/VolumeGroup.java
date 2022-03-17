import java.util.UUID;
import java.util.ArrayList;

public class VolumeGroup {
    // instance variables
    private String name;
    private double size;
    private double freeSpaceLeft;
    private ArrayList<PhysicalVolume> pvList;
    private ArrayList<LogicalVolume> lvList;
    private String uuid;

    // constructor
    public VolumeGroup(String name)
    {
        this.name = name;
        size = 0.0;
        freeSpaceLeft = 0.0;
        pvList = new ArrayList<PhysicalVolume>();
        lvList = new ArrayList<LogicalVolume>();
        uuid = UUID.randomUUID().toString();
    }

    // methods
    // accessor methods
    public String getName() {
        return name;
    }
    public String getUUID() {
        return uuid;
    }
    public ArrayList<LogicalVolume> getLvList() {
        return lvList;
    }
    public ArrayList<PhysicalVolume> getPvList() {
        return pvList;
    }
    // requiring computation
    public double getSize()
    {
        double size = 0.0;
        for (PhysicalVolume pv : pvList)
        {
            size += pv.getSize();
        }
        return size;
    }
    public double getFreeSpaceLeft()
    {
        double freeSpaceLeft = 0.0;
        double lvSize = 0.0;
        for (LogicalVolume lv : lvList)
        {
            lvSize += lv.getSize();
        }
        freeSpaceLeft = getSize() - lvSize;
        return freeSpaceLeft;
    }
}
