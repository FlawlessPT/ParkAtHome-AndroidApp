package pt.park_at_home.parkathome.managers;

public class SettingsLogin
{
    private String username = "admin";
    private String password = "root";

    public SettingsLogin()
    {
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
