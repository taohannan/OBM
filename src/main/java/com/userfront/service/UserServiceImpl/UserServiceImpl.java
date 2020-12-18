package com.userfront.service.UserServiceImpl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userfront.dao.RoleDao;
import com.userfront.dao.UserDao;
import com.userfront.domain.User;
import com.userfront.domain.security.UserRole;
import com.userfront.service.AccountService;
import com.userfront.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private AccountService accountService;
	
	public void save(User user) {
        //REQUIRES: user as  User object type
        //MODIFIES: save user details
        userDao.save(user);
    }

    public User findByUsername(String username) {
        //REQUIRES: username as  String type
        //EFFECTS: return username if found in database
        return userDao.findByUsername(username);
    }

    public User findByEmail(String email) {
        //REQUIRES: email as  String type
        //EFFECTS: return email if found in database
        return userDao.findByEmail(email);
    }
    
    
    public User createUser(User user, Set<UserRole> userRoles) {
        //OVERVIEW: create a new user details
        //REQUIRES: user as  User object type, userRoles as UserRole object
        //MODIFIES: create account number for Primary Account and Saving Account & assign userRole
        //EFFECTS: if user already exist, print 'User with username {} already exist. Nothing will be done' message
        //          else, create account number for Primary Account and Saving Account & assign userRole

        User localUser = userDao.findByUsername(user.getUsername());

        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            

            for (UserRole ur : userRoles) {
                roleDao.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userDao.save(user);
        }

        return localUser;
    }
    
    public boolean checkUserExists(String username, String email){
        //OVERVIEW: check the exisitince of user in database
        //REQUIRES: username as  String type and email as String type
        //EFFECTS: if username or email does not exist in database, return false
        //          else, return true

        if (checkUsernameExists(username) || checkEmailExists(username)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernameExists(String username) {
        //OVERVIEW: check the exisitince username of user in database
        //REQUIRES: username as  String type
        //EFFECTS: if username does not exist in database, return false
        //          else, return true

        if (null != findByUsername(username)) {
            return true;
        }

        return false;
    }
    
    public boolean checkEmailExists(String email) {
        //OVERVIEW: check the exisitince email of user in database
        //REQUIRES: email as String type
        //EFFECTS: if email does not exist in database, return false
        //          else, return true

        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

    public User saveUser (User user) {
        //REQUIRES: user as  User type
        //EFFECTS: return user details to store into databse
        return userDao.save(user);
    }
    
    public List<User> findUserList() {
        //EFFECTS: return user details from database
        return userDao.findAll();
    }

    public void enableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(true);
        userDao.save(user);
    }

    public void disableUser (String username) {
        User user = findByUsername(username);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        userDao.save(user);
        System.out.println(username + " is disabled.");
    }
}
