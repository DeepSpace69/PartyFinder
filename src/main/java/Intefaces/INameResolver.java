package main.java.Intefaces;

public interface INameResolver {
    String getDAOTypeName(String nameDTO);
    String getDAOValueName(String typeDAO, String nameDTO);
}
