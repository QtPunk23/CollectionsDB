package gitling.studio.app.DataBaseHelper;

public class IdGenerator {
    private static long currentId = 1;

    public static long generateId() {
        return currentId++;
    }
}
