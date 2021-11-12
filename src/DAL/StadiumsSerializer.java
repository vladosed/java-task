package DAL;

import Entity.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class StadiumsSerializer
{
    private static final String file = "StadiumsData.dat";
    public static void Serialize(HashMap<String,Stadium> _stadiums) throws Exception
    {
        try (ObjectOutputStream output_stream = new ObjectOutputStream(new FileOutputStream(file)))
        {
            output_stream.writeObject(_stadiums);
            output_stream.close();
        }
        catch (Exception exception)
        {
            throw exception;
        }
    }
    public static HashMap<String,Stadium> Deserialize() throws Exception
    {
        try (ObjectInputStream input_stream = new ObjectInputStream(new FileInputStream(file)))
        {
            HashMap<String,Stadium> stadiums = (HashMap<String,Stadium>)input_stream.readObject();
            input_stream.close();
            return stadiums;
        }
        catch (Exception exception)
        {
            throw exception;
        }
    }
}
