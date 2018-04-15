package group.service.iko.DB;

public class DbAdapter {

    private static DbAdapter instance;

    public static DbAdapter getInstance() {
        if (instance == null) {
            instance = new DbAdapter();

        }
        return instance;
    }



}