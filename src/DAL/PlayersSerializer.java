package DAL;

import Entity.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class PlayersSerializer
{
    private static final String file = "PlayersData.dat";
    public static void Serialize(HashMap<String,Player> _players) throws Exception
    {
        try (ObjectOutputStream output_stream = new ObjectOutputStream(new FileOutputStream(file)))
        {
            output_stream.writeObject(_players);
            output_stream.close();
        }
        catch (Exception exception)
        {
            throw exception;
        }
    }
    public static HashMap<String,Player> Deserialize() throws Exception
    {
        try (ObjectInputStream input_stream = new ObjectInputStream(new FileInputStream(file)))
        {
            HashMap<String,Player> players = (HashMap<String,Player>)input_stream.readObject();
            input_stream.close();
            return players;
        }
        catch (Exception exception)
        {
            throw exception;
        }
    }
}
