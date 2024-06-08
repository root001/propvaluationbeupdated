package com.mcb.abdulbasit.valuation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcb.abdulbasit.valuation.enums.Role;
import com.mcb.abdulbasit.valuation.model.Users;
import com.mcb.abdulbasit.valuation.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * getAllUsers
     * @return
     */
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * getUser
     * @param id
     * @return
     */
    public Users getUser(Integer id){
        if(id < 0)
            throw new IllegalArgumentException("user id cannot be null or empty.");
        Optional<Users> user = userRepository.findById(id);
        if(!user.isPresent()){
            // return no user custom error msg
            throw new UsernameNotFoundException("Users not found");
        }
        return user.get();
    }

    /**
     * getUserByUsername
     * @param username
     * @return
     */
    public Users getUserByUsername(String username){
        if(StringUtils.isEmpty(username))
            throw new IllegalArgumentException("username cannot be null or empty.");
        Optional<Users> user = userRepository.findByUsername(username);
        if(!user.isPresent()){
            // return no user custom error msg
            throw new UsernameNotFoundException("Users not found");
        }
        return user.get();
    }

    /**
     * userDetailsService
     * @return
     */
    public UserDetailsService userDetailsService() {
        return username -> {
            Users user = getUserByUsername(username);
            if (!username.equals(user.getUsername())) throw new UsernameNotFoundException("Users not found");

            return new Users(username, user.getPassword());
        };
    }

    /**
     * create
     * @return
     * @throws Exception
     */
    public Users create() throws Exception {
        // encrypt user password
        String encryptedPwd = bCryptPasswordEncoder.encode("admin");
        // mocking user details for login etc
        Users user = Users.builder()
                .username("jack")
                .fullname("Jack Reacher")
                .businessUnit("MCBU-RB000236")
                .contactNumber("8098 8721")
                .password(encryptedPwd)
                .role(Role.USER)
                .build();

        ObjectMapper mapper  = new ObjectMapper();
        log.info("user details : {}", mapper.writeValueAsString(user));
        return userRepository.save(user);
    }

}
