import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.IsoChronology;
import java.util.Date;

/**
 * Created by alex on 3/31/15.
 */

interface Predicate<Customer>
{
    boolean test(Customer c);
}

public class Customer
{
    private Date created;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String address;
    private String city;

    public Customer()
    {
        this.created = new Date();
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public Date getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(Date birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public int getAge()
    {
        LocalDate date = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return date.until(IsoChronology.INSTANCE.dateNow()).getYears();
    }

    @Override
    public String toString()
    {
        return String.format("%s %s(%d), %s - %s",
                firstname, lastname, getAge(), address, city);
    }
}
