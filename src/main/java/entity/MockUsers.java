package entity;

import entity.users.RegisteredUser;
import entity.users.User;

public class MockUsers {
    public static final RegisteredUser[] MOCK_USERS;
    static{
        MOCK_USERS = new RegisteredUser[]{
                new RegisteredUser("Marian","Donchev","marian","password","email123@abv.bg"),
            new RegisteredUser("Marian2","Donchev","marian","password","email")
        };
    }
}
