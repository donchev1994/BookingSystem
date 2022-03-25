package service.impl;

import dao.RegisterUserRepository;
import entity.users.RegisteredUser;

import service.RegisterUserService;

import java.util.Collection;

public class RegisterUserServiceImpl implements RegisterUserService {
    private final RegisterUserRepository registerUserRepository;

    public RegisterUserServiceImpl(RegisterUserRepository registerUserRepository) {
        this.registerUserRepository = registerUserRepository;
    }

    @Override
    public Collection<RegisteredUser> getUsers() {
        return registerUserRepository.read();
    }

    @Override
    public RegisteredUser addUser(RegisteredUser user) {
        return registerUserRepository.create(user);
    }

    @Override
    public RegisteredUser updateUser(RegisteredUser user) {
        return registerUserRepository.update(user);
    }

    @Override
    public RegisteredUser deleteUser(RegisteredUser user) {
        return registerUserRepository.delete(user);
    }

    @Override
    public RegisteredUser findById(Long id) {
        return registerUserRepository.findById(id);
    }
}
