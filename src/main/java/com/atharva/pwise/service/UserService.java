package com.atharva.pwise.service;

import com.atharva.pwise.entity.User;
import com.atharva.pwise.entity.payload.UserPayload;
import com.atharva.pwise.exception.ResourceNotFoundException;
import com.atharva.pwise.repository.BankAccountRepository;
import com.atharva.pwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import static com.atharva.pwise.utility.Constants.USER;
import static com.atharva.pwise.utility.Constants.USER_ID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;

    public User addUser(UserPayload userPayload) {
        User user = getUserEntity(userPayload);
        User dbInsertedUser = userRepository.save(user);
        if(!CollectionUtils.isEmpty(user.getBankAccounts()))
        {
            user.getBankAccounts().forEach(bankAccountInfo -> {
                bankAccountInfo.setUser(dbInsertedUser);
                bankAccountRepository.save(bankAccountInfo);
            });
        }
        return dbInsertedUser;
    }

    private User getUserEntity(UserPayload userPayload) {
        return User.builder()
                .firstName(userPayload.getFirstName())
                .lastName(userPayload.getLastName())
                .budget(userPayload.getBudget())
                .phone(userPayload.getPhone())
                .build();
    }

    public User getUserInfo(String lastName, String phone) {
        return userRepository.findByLastNameAndPhone(lastName, phone);
    }
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(USER, USER_ID, userId));
    }
    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

}
