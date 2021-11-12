package Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Game implements Serializable
{
    private String name_team_1;
    private String name_team_2;
    private ArrayList<String> team_1;
    private ArrayList<String> team_2;
    private Calendar date;
    private Stadium stadium;
    private int viewers;
    private int team_1_score;
    private int team_2_score;
    private String game_result;
    public Game(String _name_team_1, String _name_team_2, ArrayList<String> _team_1, ArrayList<String> _team_2,
                int _day, int _month, int _year, Stadium _stadium, int _viewers) throws DataException
    {
        this.name_team_1 = Player.CheckString(_name_team_1);
        this.name_team_2 = Player.CheckString(_name_team_2);
        this.team_1 = _team_1;
        this.team_2 = _team_2;
        int day = Player.CheckInt(_day),
                month = Player.CheckInt(_month),
                year = Player.CheckInt(_year);
        this.date = new GregorianCalendar(year, month - 1, day);
        this.stadium = _stadium;
        this.viewers = Player.CheckInt(_viewers);
        this.game_result = "Ще не проведений";
    }
    public Game(String _name_team_1, String _name_team_2, ArrayList<String> _team_1, ArrayList<String> _team_2,
                 int _day, int _month, int _year, Stadium _stadium, int _viewers,
                int _team_1_score, int _team_2_score) throws DataException
    {
        this.name_team_1 = Player.CheckString(_name_team_1);
        this.name_team_2 = Player.CheckString(_name_team_2);
        this.team_1 = _team_1;
        this.team_2 = _team_2;
        int day = Player.CheckInt(_day),
                month = Player.CheckInt(_month),
                year = Player.CheckInt(_year);
        this.date = new GregorianCalendar(year, month - 1, day);
        this.stadium = _stadium;
        this.viewers = Player.CheckInt(_viewers);
        if (team_1_score >= 0 && team_2_score >= 0) {
            this.team_1_score = _team_1_score;
            this.team_2_score = _team_2_score;
        }
        else
        {
            throw new DataException("Неправильні дані: рахунок матчу.", String.valueOf(_team_1_score));
        }
        SetGameResult();
    }
    public String GetTeam1()
    {
        return this.name_team_1;
    }
    public String GetTeam2()
    {
        return this.name_team_2;
    }
    public Calendar GetDate()
    {
        return this.date;
    }
    public Stadium GetStadium()
    {
        return this.stadium;
    }
    public String GetGameResult()
    {
        return this.game_result;
    }
    public void AddPlayer(String _player, int _team) throws DataException
    {
        if (_team == 1)
        {
            this.team_1.add(_player);
        }
        else if(_team == 2)
        {
            this.team_2.add(_player);
        }
        else
        {
            throw new DataException("Неправильні дані: назва команди.", String.valueOf(_team));
        }
    }
    public void RemovePlayer(String _player) throws Exception
    {
        for (String player : team_1)
        {
            if (player.equalsIgnoreCase(_player))
            {
                team_1.remove(player);
                return;
            }
        }
        for (String player : team_2)
        {
            if (player.equalsIgnoreCase(_player))
            {
                team_2.remove(player);
                return;
            }
        }
        throw new Exception("Гравця не знайдено.");
    }
    public void ChangeDate(int _day, int _month, int _year) throws DataException
    {
        int day = Player.CheckInt(_day),
                month = Player.CheckInt(_month),
                year = Player.CheckInt(_year);
        this.date = new GregorianCalendar(year, month - 1, day);
    }
    public void ChangeStadium(Stadium _stadium)
    {
        this.stadium = _stadium;
    }
    private void SetGameResult()
    {
        if (team_1_score > team_2_score)
        {
            this.game_result = "Виграла 1 команда";
        }
        else if(team_2_score > team_1_score)
        {
            this.game_result = "Виграла 2 команда";
        }
        else
        {
            this.game_result = "Нічия";
        }
    }
    public void ChangeViewers(int _viewers) throws DataException
    {
        this.viewers = Player.CheckInt(_viewers);
    }
    public String GetShortInfo()
    {
        return String.format("|%14s - %14s |%2d.%2d.%4d |%14s |%17s |",
                this.name_team_1, this.name_team_2,
                this.date.get(5), (this.date.get(2) + 1), this.date.get(1),
                this.stadium.GetName(), this.game_result);
    }
    public ArrayList<String> GetFullInfo()
    {
        ArrayList<String> result = new ArrayList<>();
        result.add(String.format("%14s                        %14s",this.name_team_1,this.name_team_2));
        int size = Math.max(this.team_1.size(), this.team_2.size());
        String[] players = new String[size];
        for (int i = 0; i < size; i++)
        {
            try {
                players[i] = String.format("%14s                        ", this.team_1.get(i));
            }
            catch (IndexOutOfBoundsException exception)
            {
                players[i] = String.format("%14s                        ", " ");
            }
        }
        for (int i = 0; i < size; i++)
        {
            try {
                players[i] += this.team_2.get(i);
            }
            catch (IndexOutOfBoundsException exception)
            {
                players[i] += " ";
            }
            result.add(players[i]);
        }
        result.add(String.format("Дата проведення: %2d.%2d.%4d",this.date.get(5),
                (this.date.get(2) + 1), this.date.get(1)));
        result.add("Місце проведення: " + this.stadium.GetName());
        result.add("Кількість глядачів: " + String.valueOf(this.viewers));
        result.add("Результат гри: " + this.game_result);
        if (!this.game_result.equalsIgnoreCase("Ще не проведений"))
        {
            result.add("Рахунок: " + String.valueOf(this.team_1_score) + " - " + String.valueOf(this.team_2_score));
        }
        return result;
    }
}
