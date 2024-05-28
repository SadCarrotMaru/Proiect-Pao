import DatabaseManager.DatabaseManager;
import Reader.Reader;

public class Main
{
    public static void main(String[] args)
    {
        DatabaseManager database = DatabaseManager.getInstance();
        Reader reader = new Reader(database);
        reader.startReader();
    }
}