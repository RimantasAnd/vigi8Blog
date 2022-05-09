package lt.codeacademy.vigi8BLog.Service;

import lt.codeacademy.vigi8BLog.Entity.User;
import lt.codeacademy.vigi8BLog.Exception.UserNotFoundException;
import lt.codeacademy.vigi8BLog.Repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
//@Slf4j
public record UserService(UserRepository userRepository) {
    public User create(User user) {
        user.setPassword(new DigestUtils("SHA3-512").digestAsHex(user.getPassword()));
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
    public Boolean nickNameExist(User user) {
        return true;
    }

}
