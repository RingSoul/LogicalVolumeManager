import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Manager {
    // instance variables
    // storage for user inputs
    private ArrayList<PhysicalHardDrive> phdList;
    private ArrayList<PhysicalVolume> pvList;
    private ArrayList<VolumeGroup> vgList;
    private ArrayList<LogicalVolume> lvList;
    // Scanner object for user input
    private Scanner scanner;
    // data saver?
    private File file;
    private FileReader reader;
    private FileWriter writer;



    // constructor
    public Manager() {
        phdList = new ArrayList<PhysicalHardDrive>();
        pvList = new ArrayList<PhysicalVolume>();
        vgList = new ArrayList<VolumeGroup>();
        lvList = new ArrayList<LogicalVolume>();
        scanner = new Scanner(System.in);
        file = new File("/Data");
        try {
            reader = new FileReader(file);
            writer = new FileWriter(file);
        } catch (FileNotFoundException e) {
            System.out.println("Data storage is not found.");
        }
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
    // helper method for pvlist, vgcreate, vgextend
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
    private void processChoice(String choice)
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
            // find demanded phd
            PhysicalHardDrive phd = null;
            for (PhysicalHardDrive p : phdList) // select the corresponding PHD
            {
                if (p.getName().equals(phdName))
                {
                    phd = p;
                }
            }
            // process
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
            int spaceIndex = choice.indexOf(" ");
            choice = choice.substring(spaceIndex+1); // update; first part of command excluded
            spaceIndex = choice.indexOf(" ");
            String vgName = choice.substring(0, spaceIndex);
            String pvName = choice.substring(spaceIndex+1);
            // find demanded pv
            PhysicalVolume pv = null;
            for (PhysicalVolume p : pvList)
            {
                if (p.getName().equals(pvName))
                {
                    pv = p;
                }
            }
            // process
            if (checkRepeatedNameVG(vgName))
            {
                System.out.println("ERROR: A volume group with the same name already exists, try a different name.");
            }
            else if (pv == null)
            {
                System.out.println("ERROR: The physical volume you selected does not exist.");
            }
            else
            {
                VolumeGroup associatedVG = getAssociatedVG(pv);
                if (associatedVG == null)
                {
                    VolumeGroup vg = new VolumeGroup(vgName, pv);
                    vgList.add(vg);
                    System.out.println("Volume Group " + vg.getName() + " created.");
                }
                else
                {
                    System.out.println("ERROR: The physical volume you selected is already a part of another volume group.");
                }
            }
        }
        else if (choice.indexOf("vgextend") != -1)
        {
            int spaceIndex = choice.indexOf(" ");
            choice = choice.substring(spaceIndex+1); // update; first part of command excluded
            spaceIndex = choice.indexOf(" ");
            String vgName = choice.substring(0, spaceIndex);
            String pvName = choice.substring(spaceIndex + 1);
            // check existence
            VolumeGroup vg = null;
            for (VolumeGroup v : vgList)
            {
                if (v.getName().equals(vgName))
                {
                    vg = v;
                }
            }
            PhysicalVolume pv = null;
            for (PhysicalVolume p : pvList)
            {
                if (p.getName().equals(pvName))
                {
                    pv = p;
                }
            }
            // process
            if (vg == null || pv == null)
            {
                System.out.println("ERROR: The volume group or physical volume you selected does not exist.");
            }
            else
            {
                VolumeGroup associatedVG = getAssociatedVG(pv);
                if (associatedVG == null)
                {
                    vg.getPvList().add(pv);
                    vg.update();
                    System.out.println("The Physical Volume " + pv.getName() + " is successfully added to the Volume Group " + vg.getName() + ".");
                }
                else
                {
                    System.out.println("ERROR: The physical volume you selected is already associated with another volume group.");
                }
            }
        }
        else if (choice.indexOf("vglist") != -1)
        {
            for (VolumeGroup vg : vgList)
            {
                System.out.println(vg);
            }
        }
        else if (choice.indexOf("lvcreate") != -1)
        {
            int spaceIndex = choice.indexOf(" ");
            choice = choice.substring(spaceIndex+1); // update; first part of command excluded
            spaceIndex = choice.indexOf(" ");
            String lvName = choice.substring(0, spaceIndex);
            choice = choice.substring(spaceIndex + 1);
            int indexOfG = choice.indexOf("G"); // for getting the size of lv later if applicable
            spaceIndex = choice.indexOf(" ");
            String vgName = choice.substring(spaceIndex + 1);
            VolumeGroup vg = null;
            for (VolumeGroup v : vgList)
            {
                if (v.getName().equals(vgName))
                {
                    vg = v;
                }
            }
            if (indexOfG == -1)
            {
                System.out.println("ERROR: Input format is inaccurate, remember to put \"G\" after specifying the size.");
            }
            else if (checkRepeatedNameLV(lvName))
            {
                System.out.println("ERROR: A logical volume with the same name already exists, try a different name.");
            }
            else if (vg == null)
            {
                System.out.println("ERROR: The volume group you selected does not exist.");
            }
            else
            {
                double givenSize = Double.parseDouble(choice.substring(0, indexOfG));
                double freeSpaceLeft = vg.getFreeSpaceLeft();
                if (freeSpaceLeft >= givenSize)
                {
                    LogicalVolume lv = new LogicalVolume(lvName, givenSize, vg);
                    lvList.add(lv);
                    System.out.println("The Logical Volume " + lv.getName() + " is created.");
                }
                else
                {
                    System.out.println("ERROR: The volume group you selected does not have enough space.");
                }
            }
        }
        else if (choice.indexOf("lvlist") != -1)
        {
            ArrayList<String> data = new ArrayList<String>();
            for (int i = 0; i < lvList.size(); i++)
            {
                LogicalVolume lv = lvList.get(i);
                data.add(lv.toString());
                System.out.println("One element identified.");
            }
            sortString(data);
            for (String line : data)
            {
                System.out.println(line);
            }
        }
        else
        {
            System.out.println("ERROR: Command is not recognized.");
        }
    }


    public void sortString(ArrayList<String> list)
    {
        // insertion sort approach
        for (int i = 1; i < list.size(); i++)
        {
            int index = i;
            while ((index - 1) >= 0 && list.get(index).compareTo(list.get(index-1)) < 0)
            {
                list.set(index - 1, list.get(index));
                System.out.println("Position shift");
                index--;
            }
        }
    }
}
