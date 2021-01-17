package com.example.myapplication;

public class Contact
{
    private String id;
    private String name;
    private String phonenumber;
    public Contact(String id,String name, String phonenumber)
    {
        this.id=id;
        this.name = name;
        this.phonenumber = phonenumber;
    }
    public Contact(String name, String phonenumber)
    {
        this.name = name;
        this.phonenumber = phonenumber;
    }
    public String getName()
    {
        return name;
    }
    public String getPhoneNumber()
    {
        return phonenumber;
    }
    public String getId()
    {
        return  id;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Contact custom = (Contact) o;

        if (!name.equals(custom.getName()))
        {
            return false;
        }
        if (!phonenumber.equals(custom.getPhoneNumber()))
        {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode()
    {
        int result = name.hashCode();
        result = 31 * result + phonenumber.hashCode();
        return result;
    }
}