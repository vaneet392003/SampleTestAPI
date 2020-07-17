package main.java.com.harness.restassured.model;

public class ItemRequestObj
{
    private String Item1;

    private String Item2;

    public String getItem1 ()
    {
        return Item1;
    }

    public void setItem1 (String Item1)
    {
        this.Item1 = Item1;
    }

    public String getItem2 ()
    {
        return Item2;
    }

    public void setItem2 (String Item2)
    {
        this.Item2 = Item2;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Item1 = "+Item1+", Item2 = "+Item2+"]";
    }
}