package domainLogic.modelInterfaces;

import domainLogic.Household;

public class HouseholdImpl implements IHouseholdInterface{

    @Override
    public Household createObjectByUsersTextInput(String householdName) {
        return new Household(0, householdName);
    }
}
