package BLL;

import DAL.PlayersSerializer;
import Entity.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Players {
    private HashMap<String, Player> players;
    public Players()
    {
        try
        {
            this.players = PlayersSerializer.Deserialize();
        }
        catch (Exception exception)
        {
            this.players = new HashMap<>();
        }
    }
    public HashMap<String,Player> GetPlayers() { return this.players; }
    public ArrayList<Player> GetPlayer(String _name)
    {
        ArrayList<Player> return_players = new ArrayList<>();
        for (String _key : players.keySet())
        {
            if (_key.contains(_name))
            {
                return_players.add(this.players.get(_key));
            }
        }
        return return_players;
    }
    public Player GetPlayer(String _first_name, String _last_name) throws Exception
    {
        String key = _first_name + _last_name;
        if (players.containsKey(key))
        {
            return this.players.get(key);
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public Player GetPlayerKey(String _key) throws Exception
    {
        if (players.containsKey(_key))
        {
            return this.players.get(_key);
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public void AddPlayer(Player _player) throws Exception
    {
        String key = _player.GetFirstName() +  _player.GetLastName();
        if (!players.containsKey(key))
        {
            players.put(key,_player);
            return;
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем вже існує.");
    }
    public void RemovePlayer(String _first_name, String _last_name) throws Exception
    {
        String key = _first_name + _last_name;
        if (players.containsKey(key))
        {
            players.remove(key);
            return;
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public void ChangeFirstName(String _first_name, String _last_name, String _new_first_name) throws Exception
    {
        String key = _first_name + _last_name;
        if (players.containsKey(key))
        {
            Player new_player = players.get(key);
            new_player.ChangeFirstName(_new_first_name);
            players.remove(key);
            AddPlayer(new_player);
            return;
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public void ChangeLastName(String _first_name, String _last_name, String _new_last_name) throws Exception
    {
        String key = _first_name + _last_name;
        if (players.containsKey(key))
        {
            Player new_player = players.get(key);
            new_player.ChangeLastName(_new_last_name);
            players.remove(key);
            AddPlayer(new_player);
            return;
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public void ChangeDate(String _first_name, String _last_name,
                           int _day, int _month, int _year) throws Exception
    {
        String key = _first_name + _last_name;
        if (players.containsKey(key))
        {
            Player new_player = players.get(key);
            new_player.ChangeDate(_day,_month,_year);
            players.put(key, new_player);
            return;
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public void ChangePlayStatus(String _first_name, String _last_name, boolean _play_status) throws Exception
    {
        String key = _first_name + _last_name;
        if (players.containsKey(key))
        {
            Player new_player = players.get(key);
            new_player.ChangePlayStatus(_play_status);
            players.put(key, new_player);
            return;
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public void ChangeHealthStatus(String _first_name, String _last_name, String _health_status) throws Exception
    {
        String key = _first_name + _last_name;
        if (players.containsKey(key))
        {
            Player new_player = players.get(key);
            new_player.ChangeHealthStatus(_health_status);
            players.put(key, new_player);
            return;
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public void ChangeSalary(String _first_name, String _last_name, int _salary) throws Exception
    {
        String key = _first_name + _last_name;
        if (players.containsKey(key))
        {
            Player new_player = players.get(key);
            new_player.ChangeSalary(_salary);
            players.put(key, new_player);
            return;
        }
        throw new Exception("Гравець з таким ім'ям та прізвищем не існує.");
    }
    public void Serialize() throws Exception
    {
        PlayersSerializer.Serialize(this.players);
    }
}
