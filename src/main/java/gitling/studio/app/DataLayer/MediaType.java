package gitling.studio.app.DataLayer;


import gitling.studio.app.DataBaseHelper.IdGenerator;
import gitling.studio.app.IdHelper.MediaTypeId;

public class MediaType {
    private final MediaTypeId id;
    private String name;

    public MediaType(long mediaTypeId, String name) {
        this.id = new MediaTypeId(IdGenerator.generateId());
        this.name = name;
    }

    public long getId() {
        return id.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MediaType{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
