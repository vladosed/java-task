package BLL;

import DAL.GamesSerializer;
import DAL.PlayersSerializer;
import Entity.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Games
{
    private Game[] games;
    private int pointer;
    public Games()
    {
        try
        {
            this.games = GamesSerializer.Deserialize();
            this.pointer = 0;
            while(this.games[this.pointer] != null)
            {
                this.pointer++;
            }
        }
        catch (Exception exception)
        {
            this.games = new Game[100];
            this.pointer = 0;
        }
    }
    public Game[] GetGames() { return this.games; }
    public void AddGame(Game _game) throws Exception
    {
        for (int i = 0; i < this.pointer; i++)
        {
            if (this.games[i].GetShortInfo().equalsIgnoreCase(_game.GetShortInfo()))
            {
                throw new Exception("Така гра вже існує.");
            }
        }
        this.games[this.pointer] = _game;
        this.pointer++;
        if (this.pointer == this.games.length)
        {
            Game[] _games = this.games;
            this.games = new Game[_games.length + 100];
            for (int i = 0; i < _games.length; i++)
            {
                this.games[i] = _games[i];
            }
        }
    }
    public void RemoveGame(String _team_1, String _team_2, int _day, int _month, int _year) throws Exception
    {
        int j = -1;
        for (int i = 0; i < this.pointer; i++)
        {
            Calendar date = games[i].GetDate();
            if ((this.games[i].GetTeam1().equalsIgnoreCase(_team_1) || this.games[i].GetTeam1().equalsIgnoreCase(_team_2))
                    && (this.games[i].GetTeam2().equalsIgnoreCase(_team_1) || this.games[i].GetTeam2().equalsIgnoreCase(_team_2))
                    && _day == date.get(5) && _month == (date.get(2) + 1) && _year == date.get(1))
            {
                j = i;
                break;
            }
        }
        if (j == -1)
        {
            throw new Exception("Такої гри не існує.");
        }
        else
        {
            for (int i = j; i < this.pointer - 1; i++)
            {
                this.games[i] = this.games[i + 1];
            }
            this.pointer--;
            this.games[this.pointer] = null;
        }
    }
    public Game SearchGame(String _team_1, String _team_2, int _day, int _month, int _year) throws Exception
    {
        for (int i = 0; i < this.pointer; i++)
        {
            Calendar date = games[i].GetDate();
            if ((this.games[i].GetTeam1().equalsIgnoreCase(_team_1) || this.games[i].GetTeam1().equalsIgnoreCase(_team_2))
                    && (this.games[i].GetTeam2().equalsIgnoreCase(_team_1) || this.games[i].GetTeam2().equalsIgnoreCase(_team_2))
                    && _day == date.get(5) && _month == (date.get(2) + 1) && _year == date.get(1))
            {
                return this.games[i];
            }
        }
        throw new Exception("Такої гри не існує.");
    }
    public ArrayList<Game> SearchGames(String _team, int _day, int _month, int _year) throws Exception
    {
        ArrayList<Game> result = new ArrayList<>();
        for (int i = 0; i < this.pointer; i++)
        {
            Calendar date = games[i].GetDate();
            if ((this.games[i].GetTeam1().equalsIgnoreCase(_team) || this.games[i].GetTeam2().equalsIgnoreCase(_team))
            && _day == date.get(5) && _month == (date.get(2) + 1) && _year == date.get(1))
            {
                result.add(this.games[i]);
            }
        }
        return result;
    }
    public void AddPlayer(String _team_1, String _team_2,
                          int _day, int _month, int _year,
                          String _player, int _team) throws Exception
    {
        for (int i = 0; i < this.pointer; i++)
        {
            Calendar date = games[i].GetDate();
            if ((this.games[i].GetTeam1().equalsIgnoreCase(_team_1) || this.games[i].GetTeam1().equalsIgnoreCase(_team_2))
                    && (this.games[i].GetTeam2().equalsIgnoreCase(_team_1) || this.games[i].GetTeam2().equalsIgnoreCase(_team_2))
                    && _day == date.get(5) && _month == (date.get(2) + 1) && _year == date.get(1))
            {
                this.games[i].AddPlayer(_player, _team);
                return;
            }
        }
        throw new Exception("Такої гри не існує.");
    }
    public void RemovePlayer(String _team_1, String _team_2,
                          int _day, int _month, int _year,
                          String _player) throws Exception
    {
        for (int i = 0; i < this.pointer; i++)
        {
            Calendar date = games[i].GetDate();
            if ((this.games[i].GetTeam1().equalsIgnoreCase(_team_1) || this.games[i].GetTeam1().equalsIgnoreCase(_team_2))
                    && (this.games[i].GetTeam2().equalsIgnoreCase(_team_1) || this.games[i].GetTeam2().equalsIgnoreCase(_team_2))
                    && _day == date.get(5) && _month == (date.get(2) + 1) && _year == date.get(1))
            {
                this.games[i].RemovePlayer(_player);
                return;
            }
            throw new Exception("Такої гри не існує.");
        }
    }
    public void ChangeDate(String _team_1, String _team_2,
                           int _day, int _month, int _year,
                           int _new_day, int _new_month, int _new_year)
                            throws Exception
    {
        for (int i = 0; i < this.pointer; i++)
        {
            Calendar date = games[i].GetDate();
            if ((this.games[i].GetTeam1().equalsIgnoreCase(_team_1) || this.games[i].GetTeam1().equalsIgnoreCase(_team_2))
                    && (this.games[i].GetTeam2().equalsIgnoreCase(_team_1) || this.games[i].GetTeam2().equalsIgnoreCase(_team_2))
                    && _day == date.get(5) && _month == (date.get(2) + 1) && _year == date.get(1))
            {
                this.games[i].ChangeDate(_new_day, _new_month, _new_year);
                return;
            }
        }
        throw new Exception("Такої гри не існує.");
    }
    public void ChangeStadium(String _team_1, String _team_2,
                              int _day, int _month, int _year,
                              String _name, Stadiums stadiums) throws Exception
    {
        for (int i = 0; i < this.pointer; i++)
        {
            Calendar date = games[i].GetDate();
            if ((this.games[i].GetTeam1().equalsIgnoreCase(_team_1) || this.games[i].GetTeam1().equalsIgnoreCase(_team_2))
                    && (this.games[i].GetTeam2().equalsIgnoreCase(_team_1) || this.games[i].GetTeam2().equalsIgnoreCase(_team_2))
                    && _day == date.get(5) && _month == (date.get(2) + 1) && _year == date.get(1))
            {
                this.games[i].ChangeStadium(stadiums.GetStadium(_name));
                return;
            }
        }
        throw new Exception("Такої гри не існує.");
    }
    public void ChangeViewers(String _team_1, String _team_2,
                              int _day, int _month, int _year,
                              int _viewers) throws Exception
    {
        for (int i = 0; i < this.pointer; i++)
        {
            Calendar date = games[i].GetDate();
            if ((this.games[i].GetTeam1().equalsIgnoreCase(_team_1) || this.games[i].GetTeam1().equalsIgnoreCase(_team_2))
                    && (this.games[i].GetTeam2().equalsIgnoreCase(_team_1) || this.games[i].GetTeam2().equalsIgnoreCase(_team_2))
                    && _day == date.get(5) && _month == (date.get(2) + 1) && _year == date.get(1))
            {
                this.games[i].ChangeViewers(_viewers);
                return;
            }
        }
        throw new Exception("Такої гри не існує.");
    }
    public ArrayList<String> StadiumGames(Stadium _stadium)
    {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < this.pointer; i++)
        {
            if (this.games[i].GetStadium().GetName().equalsIgnoreCase(_stadium.GetName()))
            {
                result.add(this.games[i].GetShortInfo());
            }
        }
        return result;
    }
    public void SortByResult()
    {
        for (int i = 1; i < this.pointer; i++)
        {
            Game _game = this.games[i];
            int j = i - 1;
            while (j >= 0 && this.games[j].GetGameResult().compareTo(_game.GetGameResult()) > 0)
            {
                this.games[j+1] = this.games[j];
                j--;
            }
            this.games[j+1] = _game;
        }
    }
    public void SortByDate()
    {
        for (int i = 0; i < this.pointer; i++)
        {
            for (int j = 0; j < this.pointer - i - 1; j++)
            {
                int day_1 = games[j+1].GetDate().get(5),
                        month_1 = games[j+1].GetDate().get(2),
                        year_1 = games[j+1].GetDate().get(1),
                        day_2 = games[j].GetDate().get(5),
                        month_2 = games[j].GetDate().get(2),
                        year_2 = games[j].GetDate().get(1);
                if (year_1 > year_2)
                {
                    Swap(j+1,j);
                }
                else if (year_1 == year_2)
                {
                    if (month_1 > month_2)
                    {
                        Swap(j+1,j);
                    }
                    else if (month_1 == month_2)
                    {
                        if (day_1 > day_2)
                        {
                            Swap(j+1,j);
                        }
                    }
                }
            }
        }
    }
    private void Swap(int _index_1, int _index_2)
    {
        Game tmp = this.games[_index_1];
        this.games[_index_1] = this.games[_index_2];
        this.games[_index_2] = tmp;
    }
    public void Serialize() throws Exception
    {
        GamesSerializer.Serialize(this.games);
    }
}
