import java.util.ArrayList;

public class Utility {
    // static utility class
    // true if name repeated (not desired), false if not repeated (desired)
    public static boolean checkRepeatedNameDrive(ArrayList<PhysicalHardDrive> list, String name)
    {
        for (PhysicalHardDrive phd : list)
        {
            if (phd.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    public static boolean checkRepeatedNamePV(ArrayList<PhysicalVolume> list, String name)
    {
        for (PhysicalVolume pv : list)
        {
            if (pv.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    public static boolean checkRepeatedNameVG(ArrayList<VolumeGroup> list, String name)
    {
        for (VolumeGroup vg : list)
        {
            if (vg.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    public static boolean checkRepeatedNameLV(ArrayList<LogicalVolume> list, String name)
    {
        for (LogicalVolume lv : list)
        {
            if (lv.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    //
}
