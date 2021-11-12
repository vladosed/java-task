package DAL;

import Entity.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GamesSerializer
{
    private static final String file = "GamesData.dat";
    public static void Serialize(Game[] _games) throws Exception
    {
        try (ObjectOutputStream output_stream = new ObjectOutputStream(new FileOutputStream(file)))
        {
            output_stream.writeObject(_games);
            output_stream.close();
        }
        catch (Exception exception)
        {
            throw exception;
        }
    }
    public static Game[] Deserialize() throws Exception
    {
        try (ObjectInputStream input_stream = new ObjectInputStream(new FileInputStream(file)))
        {
            Game[] games = (Game[])input_stream.readObject();
            input_stream.close();
            return games;
        }
        catch (Exception exception)
        {
            throw exception;
        }
    }
}
