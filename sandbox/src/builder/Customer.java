public class Customer {

    private final String firstname;
    private final String lastname;

    private final String streetAddress;
    private final String city;
    private final int postalCode;
    private final int age;
    private final int phone;
    private final String email;

    public static class Builder
    {
        private String firstname;       // required
        private String lastname;        // required

        // optional
        private String streetAddress = null;
        private String city = null;
        private int postalCode = 0;
        private int age = 0;
        private int phone = 0;
        private String email = null;

        public Builder(String firstname, String lastname)
        {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public Builder street(String val)
        {
            streetAddress = val;
            return this;
        }

        public Builder city(String val)
        {
            city = val;
            return this;
        }

        public Builder email(String val)
        {
            email = val;
            return this;
        }

        public Builder postalCode(int val)
        {
            postalCode = val;
            return this;
        }

        public Builder age(int val)
        {
            age = val;
            return this;
        }

        public Builder phone(int val)
        {
            phone = val;
            return this;
        }

        public Customer build()
        {
            return new Customer(this);
        }
    }

    public Customer(Builder builder)
    {
        firstname = builder.firstname;
        lastname = builder.lastname;
        email = builder.email;
        age = builder.age;
        phone = builder.phone;
        streetAddress = builder.streetAddress;
        postalCode = builder.postalCode;
        city = builder.city;
    }

    public String toString(){
        return firstname + " " + lastname + " " + age + " " + phone + " " + streetAddress + " " + postalCode + " " + city;
    }
}
