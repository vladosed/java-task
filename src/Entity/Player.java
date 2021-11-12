package Entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

public abstract class Player implements Serializable {
    protected String first_name;
    protected String last_name;
    protected Calendar date;
    protected boolean play_status;//false - гравець закінчив кар'єру, true - гравець грає в клубі
    protected String health_status;
    protected int salary;

    protected Player(String _first_name, String _last_name, int _day, int _month, int _year,
                     boolean _play_status, String _health_status, int _salary) throws DataException {
        this.first_name = CheckString(_first_name);
        this.last_name = CheckString(_last_name);
        int day = CheckInt(_day),
                month = CheckInt(_month),
                year = CheckInt(_year);
        this.date = new GregorianCalendar(year, month - 1, day);
        this.play_status = _play_status;
        this.health_status = CheckString(_health_status);
        this.salary = CheckInt(_salary);
    }
    public abstract String GetPlayerInfo();
    public String GetFirstName()
    {
        return this.first_name;
    }
    public String GetLastName()
    {
        return this.last_name;
    }
    public void ChangeFirstName(String _first_name) throws DataException
    {
        this.first_name = CheckString(_first_name);
    }
    public void ChangeLastName(String _last_name) throws DataException
    {
        this.last_name = CheckString(_last_name);
    }
    public void ChangeDate(int _day, int _month, int _year) throws DataException
    {
        int day = CheckInt(_day),
                month = CheckInt(_month),
                year = CheckInt(_year);
        this.date = new GregorianCalendar(year, month - 1, day);
    }
    public void ChangePlayStatus(boolean _play_status)
    {
        this.play_status = _play_status;
    }
    public void ChangeHealthStatus(String _health_status) throws DataException
    {
        this.health_status = CheckString(_health_status);
    }
    public void ChangeSalary(int _salary) throws DataException
    {
        this.salary = CheckInt(_salary);
    }
    protected static String CheckString(String _data) throws DataException
    {
        Pattern pattern = Pattern.compile("(\\w+|[А-я,і,ї,І,Ї]+)\\s?(\\w+|[А-я,і,ї,І,Ї]+)");
        if (pattern.matcher(_data).matches())
        {
            return _data;
        }
        throw new DataException("Неправильні дані: " + _data, _data);
    }
    protected static int CheckInt(int _data) throws DataException
    {
        if (_data > 0)
        {
             return _data;
        }
        throw new DataException("Неправильні дані: " + String.valueOf(_data), String.valueOf(_data));
    }
}
