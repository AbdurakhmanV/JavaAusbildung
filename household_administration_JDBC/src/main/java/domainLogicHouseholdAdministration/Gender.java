package domainLogicHouseholdAdministration;

public enum Gender {
    MASKULINE ("maennlich"),
    FEMININE ("weiblich");;

    String selection;

   Gender(String selection){
        this.selection = selection;
    }

    @Override
    public String toString(){
        return String.format("%-30s", selection);
    }
}
