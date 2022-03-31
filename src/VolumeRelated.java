import java.util.UUID;

public class VolumeRelated {
    // superclass instance variables
    private String name;
    private String uuid;

    // superclass constructor
    public VolumeRelated(String name)
    {
        this.name = name;
        this.uuid = UUID.randomUUID().toString();
    }

    // superclass methods
    // accessor methods
    public String getName()
    {
        return name;
    }
    public String getUUID()
    {
        return uuid;
    }

    public void restoreUUID(String uuid)
    {
        this.uuid = uuid;
    }
}
