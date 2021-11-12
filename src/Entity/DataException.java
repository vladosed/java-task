package Entity;

public class DataException extends Exception
{
    private String data;
    public String GetData()
    {
        return this.data;
    }
    public DataException()
    {
        super();
    }
    public DataException(String _message, String _data)
    {
        super(_message);
        this.data = _data;
    }
}
