package util;

import java.io.Serializable;

public class Player implements Serializable
{
    private String name, country, club, position;
    private int age, number, weeklySalary;
    private double height;
    private boolean transferListed;

    public Player(String name, String country, int age, double height, String club, String position, int number, int weeklySalary, boolean transferListed)
    {
        SetTransferListed(transferListed);
        SetName(name);
        SetCountry(country);
        SetAge(age);
        SetHeight(height);
        SetClub(club);
        SetPosition(position);
        SetNumber(number);
        SetWeeklySalary(weeklySalary);
    }

    public Player(Player other)
    {
        SetTransferListed(other.transferListed);
        SetName(other.name);
        SetCountry(other.country);
        SetAge(other.age);
        SetHeight(other.height);
        SetClub(other.club);
        SetPosition(other.position);
        SetNumber(other.number);
        SetWeeklySalary(other.weeklySalary);
    }

    public void SetTransferListed(boolean transferListed)
    {
        this.transferListed = transferListed;
    }

    public void SetName(String name)
    {
        this.name = name;
    }

    public void SetCountry(String country)
    {
        this.country = country;
    }

    public void SetAge(int age)
    {
        this.age = age;
    }

    public void SetHeight(double height)
    {
        this.height = height;
    }

    public void SetClub(String club)
    {
        this.club = club;
    }

    public void SetPosition(String position)
    {
        this.position = position;
    }

    public void SetNumber(int number)
    {
        this.number = number;
    }

    public void SetWeeklySalary(int weeklySalary)
    {
        this.weeklySalary = weeklySalary;
    }

    public String GetName()
    {
        return name;
    }

    public String GetCountry()
    {
        return country;
    }

    public boolean GetTransferListed()
    {
        return transferListed;
    }

    public int GetAge()
    {
        return age;
    }

    public double GetHeight()
    {
        return height;
    }

    public String GetClub()
    {
        return club;
    }

    public String GetPosition()
    {
        return position;
    }

    public int GetNumber()
    {
        return number;
    }

    public int GetWeeklySalary()
    {
        return weeklySalary;
    }

    public void PrintAllInfo()
    {
        System.out.println(" Name: " + GetName());
        System.out.println(" Country: " + GetCountry());
        System.out.println(" Age: " + GetAge());
        System.out.println(" Height: " + GetHeight());
        System.out.println(" Club: " + GetClub());
        System.out.println(" Position: " + GetPosition());
        System.out.println(" Number: " + GetNumber());
        System.out.println(" Weekly Salary: " + GetWeeklySalary());
    }
}