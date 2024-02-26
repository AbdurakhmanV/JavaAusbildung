package domainLogic;

public class Household {
    //object attributes, instance variables begin---------------------------------------------------------------------------------------------------------------------

    private int householdId;
    private String householdName;
    private int addressId;
    //object attributes, instance variables end---------------------------------------------------------------------------------------------------------------------

    //helping objects and variables, getter ande setter begin--------------------------------------------------------------------------------------
    //helping objects and variables, getter ande setter end--------------------------------------------------------------------------------------

    //object attributes, instance variables, getter and setter begin---------------------------------------------------------------------------------------
    public int getHouseholdId() {
        return householdId;
    }

    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public void setHouseholdName(String householdName) {
        this.householdName = householdName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
    //object attributes, instance variables, getter and setter end---------------------------------------------------------------------------------------

    //constructor begin----------------------------------------------------------------------------------------------------------------------------------------
    public Household(int householdId, String householdName, int addressId){
        this.householdId = householdId;
        this.householdName = householdName;
        this.addressId = addressId;
    }

    public Household(int householdId, String householdName){
        this.householdId = householdId;
        this.householdName = householdName;
    }
    //constructor end----------------------------------------------------------------------------------------------------------------------------------------


    //methods

@Override
    public String toString(){
        String sendString;
        sendString = addressId==0? String.format("%-30d%-30s", householdId, householdName):String.format("%-30d%-30s%-30d", householdId, householdName, addressId);
        return sendString;
}

}
