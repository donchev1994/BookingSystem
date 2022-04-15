package dao.administrator;


import dao.UserRepository;

public interface AdministratorDao extends UserRepository {

    void updateRole(int role,String username);
    void getAllRoles();
    boolean deleteUser(String username);
}
