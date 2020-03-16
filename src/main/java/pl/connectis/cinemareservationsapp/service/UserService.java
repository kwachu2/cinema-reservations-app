package pl.connectis.cinemareservationsapp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.connectis.cinemareservationsapp.dto.UserDTO;
import pl.connectis.cinemareservationsapp.exceptions.BadRequestException;
import pl.connectis.cinemareservationsapp.mapper.UserMapper;
import pl.connectis.cinemareservationsapp.model.User;
import pl.connectis.cinemareservationsapp.repository.UserRepository;
import pl.connectis.cinemareservationsapp.security.IAuthenticationFacade;

@Slf4j
@Service
public class UserService {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Autowired
    UserRepository userRepository;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public UserDTO getUser() {

        User user = findByUsername(authenticationFacade.getAuthentication().getName());

        UserMapper userMapper = new UserMapper();
        UserDTO userDTO = userMapper.mapDTOFromEntity(user);

        return userDTO;

    }

    public boolean userExists(UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
            return true;
        }

        return false;

    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User save(User user) {

        log.info(user.toString());
        return userRepository.save(user);

    }

    public Iterable<User> saveAll(Iterable<User> userList) {
        return userRepository.saveAll(userList);
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {

        if (userDTO.getUsername() != null && !userDTO.getUsername().equals(authenticationFacade.getAuthentication().getName())) {
            throw new BadRequestException("{username=" + userDTO.getUsername() + "} does not correspond to the logged user");
        }

        User existingUser = userRepository.findByUsername(authenticationFacade.getAuthentication().getName());;

        if (userDTO.getFirstName() != null) {
            existingUser.setFirstName(userDTO.getFirstName());
        }

        if (userDTO.getLastName() != null) {
            existingUser.setLastName(userDTO.getLastName());
        }

        if (userDTO.getBirthDate() != null) {
            existingUser.setBirthDate(userDTO.getBirthDate());
        }

        UserMapper userMapper = new UserMapper();

        return userMapper.mapDTOFromEntity(existingUser);
    }

//    public void deleteById(String id) {
//        userRepository.deleteById(id);
//    }

}
