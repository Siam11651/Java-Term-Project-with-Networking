package util;

import java.io.Serializable;

public class PlayerSearchRequest implements Serializable
{
    private String from;
    private String name, country, position;
    private String age, number, weeklySalary;
    private String height;

    public PlayerSearchRequest(String from, String name, String country, String age, String height, String position, String number, String weeklySalary)
    {
        SetFrom(from);
        SetName(name);
        SetCountry(country);
        SetAge(age);
        SetHeight(height);
        SetPosition(position);
        SetNumber(number);
        SetWeeklySalary(weeklySalary);
    }

    public void SetFrom(String from)
    {
        this.from = from;
    }

    public void SetName(String name)
    {
        this.name = name;
    }

    public void SetCountry(String country)
    {
        this.country = country;
    }

    public void SetAge(String age)
    {
        this.age = age;
    }

    public void SetHeight(String height)
    {
        this.height = height;
    }

    public void SetPosition(String position)
    {
        this.position = position;
    }

    public void SetNumber(String number)
    {
        this.number = number;
    }

    public void SetWeeklySalary(String weeklySalary)
    {
        this.weeklySalary = weeklySalary;
    }

    public String GetFrom()
    {
        return from;
    }

    public String GetName()
    {
        return name;
    }

    public String GetCountry()
    {
        return country;
    }

    public String GetAge()
    {
        return age;
    }

    public String GetHeight()
    {
        return height;
    }

    public String GetPosition()
    {
        return position;
    }

    public String GetNumber()
    {
        return number;
    }

    public String GetWeeklySalary()
    {
        return weeklySalary;
    }
}
