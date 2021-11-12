package Entity;

import java.io.Serializable;

public class FootballPlayer extends Player implements Serializable
{
    private String team;
    public FootballPlayer(String _first_name, String _last_name, int _day, int _month, int _year,
                          boolean _play_status, String _health_status, int _salary, String _team) throws DataException
    {
        super(_first_name,_last_name,_day,_month,_year,_play_status,_health_status,_salary);
        this.team = _team;
    }
    public void ChangeTeam(String _data) throws DataException
    {
        this.team = CheckString(_data);
    }
    @Override
    public String GetPlayerInfo()
    {
        return String.format("|%14s |%14s |%2d.%2d.%4d |%5s |%10s |%6d |%14s |",
                this.first_name, this.last_name, this.date.get(5),
                (this.date.get(2) + 1), this.date.get(1),
                String.valueOf(this.play_status), this.health_status,
                this.salary,this.team);
    }
}