package BLL;

import DAL.PlayersSerializer;
import DAL.StadiumsSerializer;
import Entity.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Stadiums
{
    private HashMap<String,Stadium> stadiums;
    public Stadiums()
    {
        try
        {
            this.stadiums = StadiumsSerializer.Deserialize();
        }
        catch (Exception exception)
        {
            this.stadiums = new HashMap<>();
        }
    }
    public HashMap<String,Stadium> GetStadiums () { return  this.stadiums; }
    public Stadium GetStadium(String _name) throws Exception
    {
        String key = _name;
        if (stadiums.containsKey(key))
        {
            return stadiums.get(key);
        }
        throw new Exception("Стадіон з таким ім'ям не існує.");
    }
    public void AddStadium(Stadium _stadium) throws Exception
    {
        String key = _stadium.GetName();
        if (!stadiums.containsKey(key))
        {
            stadiums.put(key,_stadium);
            return;
        }
        throw new Exception("Стадіон з таким ім'ям вже існує.");
    }
    public void RemoveStadium(String _name) throws Exception
    {
        String key = _name;
        if (stadiums.containsKey(key))
        {
            stadiums.remove(key);
            return;
        }
        throw new Exception("Стадіон з таким ім'ям не існує.");
    }
    public void ChangeSeats(String _name, int _seats) throws Exception
    {
        String key = _name;
        if (stadiums.containsKey(key))
        {
            Stadium new_stadium = stadiums.get(key);
            new_stadium.ChangeSeats(_seats);
            stadiums.put(key,new_stadium);
            return;
        }
        throw new Exception("Стадіон з таким ім'ям не існує.");
    }
    public void ChangePrice(String _name, int _price) throws Exception
    {
        String key = _name;
        if (stadiums.containsKey(key))
        {
            Stadium new_stadium = stadiums.get(key);
            new_stadium.ChangePrice(_price);
            stadiums.put(key,new_stadium);
            return;
        }
        throw new Exception("Стадіон з таким ім'ям не існує.");
    }
    public ArrayList<String> GetGames(String _name, Games _games) throws Exception
    {
        String key = _name;
        if (stadiums.containsKey(key))
        {
            return _games.StadiumGames(this.stadiums.get(key));
        }
        throw new Exception("Стадіон з таким ім'ям не існує.");
    }
    public void Serialize() throws Exception
    {
        StadiumsSerializer.Serialize(this.stadiums);
    }
}
