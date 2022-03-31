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
        pvList = new ArrayList<PhysicalVolume>();
        pvList.add(pv);
        lvList = new ArrayList<LogicalVolume>();
        size = getSize();
        freeSpaceLeft = getFreeSpaceLeft();
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

    public void update()
    {
        size = getSize();
        freeSpaceLeft = getFreeSpaceLeft();
    }

    public String toString()
    {
        update();
        String str = getName() + ": total:[" + size + "G] available:[" + freeSpaceLeft + "G] [";
        str += pvList.get(0).getName();
        for (int i = 1; i < pvList.size(); i++)
        {
            str += "," + pvList.get(i).getName();
        }
        str += "] [" + getUUID() + "]";
        return str;
    }
}
