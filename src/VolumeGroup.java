import java.util.UUID;
import java.util.ArrayList;

public class VolumeGroup extends VolumeRelated {
    // instance variables
    private double size;
    private double freeSpaceLeft;
    private ArrayList<PhysicalVolume> pvList;
    private ArrayList<LogicalVolume> lvList;

    // constructor
    public VolumeGroup(String name, PhysicalVolume pv)
    {
        super(name);
        size = getSize();
        freeSpaceLeft = getFreeSpaceLeft();
        pvList = new ArrayList<PhysicalVolume>();
        pvList.add(pv);
        lvList = new ArrayList<LogicalVolume>();
    }

    // methods
    // accessor methods
    // (getName and getUUID inherited from VolumeRelated superclass)
    public ArrayList<LogicalVolume> getLvList() {
        return lvList;
    }
    public ArrayList<PhysicalVolume> getPvList() {
        return pvList;
    }
    // requiring computation
    public double getSize()
    {
        double size = 0;
        for (PhysicalVolume pv : pvList)
        {
            size += pv.getSize();
        }
        return size;
    }
    public double getFreeSpaceLeft()
    {
        double freeSpaceLeft = 0;
        double lvSize = 0;
        for (LogicalVolume lv : lvList)
        {
            lvSize += lv.getSize();
        }
        freeSpaceLeft = getSize() - lvSize;
        return freeSpaceLeft;
    }

    public void update() // every time the extend command or lv command is called
    {
        size = getSize();
        freeSpaceLeft = getFreeSpaceLeft();
    }
}
