package gitling.studio.app.DataLayer;


import gitling.studio.app.DataBaseHelper.IdGenerator;
import gitling.studio.app.IdHelper.CategoryId;

public class Category {
    private final CategoryId id;
    private String name;

    public Category(String name) {
        this.id = new CategoryId(IdGenerator.generateId());
        this.name = name;
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
