package personAdministration;


public class Person {
    private String firstName;
    private String lastName;
    private String birthdate;

    private Gender gender;
    private Address address;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthdate;
    }

    public void setBirthday(String birthday) {
        this.birthdate = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person(String firstName, String lastName, String birthday, Gender gender, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthday;
        this.address = address;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return String.format("%-30s%s\n%-30s%s\n%-30s%s\n%-30s%s\n%-30s\n%s\n\n", "Vorname:", firstName, "Nachname:", lastName, "Geburtstag:", birthdate, "Geschlecht:", gender == null ? "-----" : gender, "Adresse", address == null ? "-----" : address);
    }
}
