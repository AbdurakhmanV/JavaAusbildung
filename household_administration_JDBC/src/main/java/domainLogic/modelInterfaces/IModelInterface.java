package domainLogic.modelInterfaces;

public interface IModelInterface<T>{
    T createObjectByUsersTextInput(String objectInformationText) throws Exception;
}
