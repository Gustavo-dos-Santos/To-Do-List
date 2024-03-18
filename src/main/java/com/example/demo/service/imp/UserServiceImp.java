package com.example.demo.service.imp;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    @Autowired
    private final UserRepository repository;
    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override @Transactional(readOnly = true)
    public User getUser(int id) {
        Optional<User> optional =  repository.findById(id);
        return optional.orElse(null);
    }

    @Override @Transactional(readOnly = false)
    public void save(User user) {
        repository.save(user);
    }

    @Override @Transactional(readOnly = false)
    public void delete(User user) {
        repository.deleteById(user.getId());
    }

    @Override @Transactional(readOnly = false)
    public void edit(User user) {
        repository.save(user);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return repository.findUserByEmailAndPassword(email,password);
    }

    @Override
    public User convertUser(User main, User convert) {
        main.setName(convert.getName());
        main.setEmail(convert.getEmail());
        main.setLastname(convert.getLastname());
        main.setPhone(convert.getPhone());
        if(!convert.getPassword().isEmpty()){
            main.setPassword(convert.getPassword());
        }
        return main;
    }

    @Override
    public boolean userExist(Integer userId) {
        return repository.existsById(userId);
    }

    @Override
    public boolean validUser(User user) {
        User verify = repository.findUserByEmailAndPhone(user.getEmail(),user.getPhone());
        return verify == null;
    }


}
