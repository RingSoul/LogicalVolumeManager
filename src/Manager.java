import java.util.ArrayList;

public class Manager {
    // instance variables
    // storage for user inputs
    ArrayList<PhysicalHardDrive> phdList;
    ArrayList<PhysicalVolume> pvList;
    ArrayList<VolumeGroup> vgList;
    ArrayList<LogicalVolume> lvList;


    // constructor
    public Manager()
    {
        phdList = new ArrayList<PhysicalHardDrive>();
        pvList = new ArrayList<PhysicalVolume>();
        vgList = new ArrayList<VolumeGroup>();
        lvList = new ArrayList<LogicalVolume>();
    }


    // boolean literals for decisions between different print statements
    // true if name repeated (not desired), false if not repeated (desired)
    public boolean checkRepeatedNamePHD(String name)
    {
        for (PhysicalHardDrive phd : phdList)
        {
            if (phd.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    public boolean checkRepeatedNamePV(String name)
    {
        for (PhysicalVolume pv : pvList)
        {
            if (pv.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    public boolean checkRepeatedNameVG(String name)
    {
        for (VolumeGroup vg : vgList)
        {
            if (vg.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }
    public boolean checkRepeatedNameLV(String name)
    {
        for (LogicalVolume lv : lvList)
        {
            if (lv.getName().equals(name))
            {
                return true;
            }
        }
        return false;
    }

    // void methods for functionality of Runner class
    public void processChoice(String choice)
    {
        int spaceCount = 0;
        for (int i = 0; i < choice.length(); i++)
        {
            String current = choice.substring(i, i+1);
            if (current.equals(" "))
            {
                spaceCount++;
                /* ** 2 spaces required for commands:
                        - install-drive
                        - pvcreate
                        - vgcreate
                        - vgextend
                   ** 3 spaces required for commands:
                        - lvcreate
                 */
            }
        }
        if (spaceCount != 2 || spaceCount != 3)
        {
            System.out.println("Inaccurate format of user input, make sure that spacing and text are correct.");
        }
        else
        {
            if (choice.indexOf("install-drive") != -1) {

            } else if (choice.indexOf("pvcreate") != -1) {

            }
        }
    }
}
