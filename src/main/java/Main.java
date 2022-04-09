
import controller.UserController;
import dao.RegisterUserRepository;
import dao.impl.RegisterUserRepositoryImpl;
import service.RegisterUserService;
import service.impl.RegisterUserServiceImpl;



public class Main {

    public static void main(String[] args) {

        RegisterUserRepository us = new RegisterUserRepositoryImpl();
        RegisterUserService userService = new RegisterUserServiceImpl(us);

        // user login - username: marian123 password: marian123
        var userController = new UserController(userService);
        userController.init();



    }
}

