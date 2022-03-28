import java.util.ArrayList;
import java.util.Scanner;

public class Manager {
    // instance variables
    // storage for user inputs
    ArrayList<PhysicalHardDrive> phdList;
    ArrayList<PhysicalVolume> pvList;
    ArrayList<VolumeGroup> vgList;
    ArrayList<LogicalVolume> lvList;
    // Scanner object for user input
    Scanner scanner;


    // constructor
    public Manager()
    {
        phdList = new ArrayList<PhysicalHardDrive>();
        pvList = new ArrayList<PhysicalVolume>();
        vgList = new ArrayList<VolumeGroup>();
        lvList = new ArrayList<LogicalVolume>();
        scanner = new Scanner(System.in);
    }


    public void start()
    {
        System.out.println("Welcome to the LVM system! Enter your commands:");
        while (true)
        {
            System.out.print("cmd# ");
            String choice = scanner.nextLine();
            if (choice.equals("exit"))
            {
                break;
            }
            processChoice(choice);
        }
        System.out.println("Data has been saved. Bye!");
    }


    // private checker methods (returning boolean literals)
    // true if name repeated (not desired), false if not repeated (desired)
    private boolean checkRepeatedNamePHD(String name)
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
    private boolean checkRepeatedNamePV(String name)
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
    private boolean checkRepeatedNameVG(String name)
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
    private boolean checkRepeatedNameLV(String name)
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
    // true if the PhysicalHardDrive object as the argument is associated with an already-existing
    // PhysicalVolume object (not desired), false otherwise (desired)
    private boolean isAlreadyAssociated(PhysicalHardDrive phd)
    {
        for (PhysicalVolume pv : pvList)
        {
            if (pv.getPHD() == phd)
            {
                return true;
            }
        }
        return false;
    }


    // return the VolumeGroup object associated with the given pv in the parameter
    // if none, return null
    private VolumeGroup getAssociatedVG(PhysicalVolume pv)
    {
        for (VolumeGroup vg : vgList)
        {
            ArrayList<PhysicalVolume> list = vg.getPvList();
            for (PhysicalVolume p : list)
            {
                if (p == pv)
                {
                    return vg;
                }
            }
        }
        return null;
    }



    // void methods for functionality of Runner class
    public void processChoice(String choice)
    {
        if (choice.indexOf("install-drive") != -1)
        {
            int spaceIndex = choice.indexOf(" ");
            choice = choice.substring(spaceIndex+1); // update; first part of command excluded
            spaceIndex = choice.indexOf(" ");
            String name = choice.substring(0, spaceIndex);
            int indexOfG = choice.indexOf("G");
            if (checkRepeatedNamePHD(name)) // repeated = quit
            {
                System.out.println("ERROR: A drive with the same name already exists, try a different name.");
            }
            else if (indexOfG == -1)
            {
                System.out.println("ERROR: Input format is inaccurate, remember to put \"G\" after specifying the size.");
            }
            else // not repeated = continue
            {
                String sizeStr = choice.substring(spaceIndex+1, indexOfG);
                double size = Double.parseDouble(sizeStr);
                PhysicalHardDrive phd = new PhysicalHardDrive(name, size);
                phdList.add(phd);
                System.out.println("Drive " + phd.getName() + " installed.");
            }
        }
        else if (choice.indexOf("list-drives") != -1)
        {
            for (PhysicalHardDrive phd : phdList)
            {
                System.out.println(phd);
            }
        }
        else if (choice.indexOf("pvcreate") != -1)
        {
            int spaceIndex = choice.indexOf(" ");
            choice = choice.substring(spaceIndex+1); // update; first part of command excluded
            spaceIndex = choice.indexOf(" ");
            String pvName = choice.substring(0, spaceIndex);
            String phdName = choice.substring(spaceIndex+1);
            PhysicalHardDrive phd = null;
            for (PhysicalHardDrive p : phdList) // select the corresponding PHD
            {
                if (p.getName().equals(phdName))
                {
                    phd = p;
                }
            }
            if (checkRepeatedNamePV(pvName))
            {
                System.out.println("ERROR: A physical volume with the same name already exists, try a different name.");
            }
            else if (phd == null)
            {
                System.out.println("ERROR: The drive you selected does not exist.");
            }
            else if (isAlreadyAssociated(phd))
            {
                System.out.println("ERROR: The drive you selected is already associated with another physical volume.");
            }
            else
            {
                PhysicalVolume pv = new PhysicalVolume(pvName, phd);
                pvList.add(pv);
                System.out.println("Physical Volume " + pv.getName() + " created.");
            }
        }
        else if (choice.indexOf("pvlist") != -1)
        {
            for (PhysicalVolume pv : pvList)
            {
                System.out.print(pv.getName() + ":[" + pv.getSize() + "G] [");
                VolumeGroup associatedVG = getAssociatedVG(pv);
                if (associatedVG != null)
                {
                    System.out.print(associatedVG.getName() + "] [");
                }
                System.out.println(pv.getUUID() + "]");
            }
        }
        else if (choice.indexOf("vgcreate") != -1)
        {

        }
        else if (choice.indexOf("vgextend") != -1)
        {

        }
        else if (choice.indexOf("vglist") != -1)
        {

        }
        else if (choice.indexOf("lvcreate") != -1)
        {

        }
        else if (choice.indexOf("lvlist") != -1)
        {

        }
        else if (choice.equals("exit"))
        {

        }
    }



}
