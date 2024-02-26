package domainLogic.modelInterfaces;

import domainLogic.Address;
import domainLogic.exceptionManager.ExceptionsHouseholdAdministration;


public class AddressImpl implements IAddressInterface {
    ExceptionsHouseholdAdministration exceptionManager = new ExceptionsHouseholdAdministration();

    @Override
    public Address createObjectByUsersTextInput(String addressInformation) throws Exception {

        addressInformation = addressInformation.replace(" ", "");
        String[] addressArray = addressInformation.split(",");
        addressArray = exceptionManager.controlAddress(addressArray);

        int addressId = Integer.parseInt(addressArray[0]);
        String zipCode = addressArray[1];
        String city = addressArray[2];
        String street = addressArray[3];
        String houseNumber = addressArray[4];
        int doorNumber;
        int householdId;

        if (addressArray.length == 6) {
            doorNumber = 0;
            householdId = Integer.parseInt(addressArray[5]);
        } else {
            doorNumber = Integer.parseInt(addressArray[5]);
            householdId = Integer.parseInt(addressArray[6]);
        }
        return new Address(addressId, zipCode, city, street, houseNumber, doorNumber, householdId);
    }


}
