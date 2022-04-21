package dao;

import entity.users.User;

public interface UserRepository extends CrudRepository<Long, User>{
     User getUserByUsername(String username);
}
