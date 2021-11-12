package Entity;

import java.io.Serializable;

public class Stadium implements Serializable {
    private String name;
    private int seats;
    private int price;
    public Stadium(String _name, int _seats, int _price) throws DataException
    {
        this.name = Player.CheckString(_name);
        this.seats = Player.CheckInt(_seats);
        this.price = Player.CheckInt(_price);
    }
    public String GetName()
    {
        return this.name;
    }
    public void ChangeSeats(int _seats) throws DataException
    {
        this.seats = Player.CheckInt(_seats);
    }
    public void ChangePrice(int _price) throws DataException
    {
        this.price = Player.CheckInt(_price);
    }
    public String GetStadiumInfo()
    {
        return String.format("|%14s |%6d |%5d |",
                this.name,this.seats,this.price);
    }
}
