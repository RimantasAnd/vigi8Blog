package lt.codeacademy.vigi8BLog.service;

import lombok.extern.slf4j.Slf4j;
import lt.codeacademy.vigi8BLog.entity.User;
import lt.codeacademy.vigi8BLog.exception.UserNotFoundException;
import lt.codeacademy.vigi8BLog.repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

@Service
@Slf4j
public record UserService(UserRepository userRepository) {

    private static HttpServletRequest request ;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    private String getClientIp() {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
    private String getClientHostName() {
        String remoteHostname = "";
        if (request != null) {
            remoteHostname = request.getHeader("X-FORWARDED-FOR");
            if (remoteHostname == null || "".equals(remoteHostname)) {
                remoteHostname = request.getHeader("HOST");
            }
        }
        return remoteHostname;
    }

    public User create(User user)
    {
        user.setPassword(new DigestUtils("SHA3-512").digestAsHex(user.getPassword()));
        user.setLastIp(getClientIp());
        user.setLastLoginTime(LocalDateTime.now());
        user.setAdminLock(false);
        user.setHostName(getClientHostName());
        return userRepository
                .save(user) ;
    }

    public User findById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
    public boolean checkUserByUserName(String username){
        return userRepository.existsUserByUserName(username);
    }

    public User update(User user) {
        User newUser = findById(user.getId());
        if (newUser.getId() != null) {
            newUser.setUserName(user.getUserName());
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setEmail(user.getEmail());
            newUser.setLastIp(getClientIp());
            newUser.setHostName(getClientHostName());
            newUser.setLastLoginTime(LocalDateTime.now());
            newUser.setPassword(new DigestUtils("SHA3-512").digestAsHex(user.getPassword()));
        }
        return userRepository.save(newUser);
    }

    public boolean checkPasswordById(Long id,String passwd){
        User user = findById(id);
        String newPasswd;
        if (user.getId() == id) {
            newPasswd =new DigestUtils("SHA3-512").digestAsHex(passwd);
            if((newPasswd).equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public User findByUserName(String userName){

        return  userRepository.findByUserName(userName)
                .orElseThrow(UserNotFoundException::new);
    }

    public boolean checkPasswdByUserName(String userName,String passwd){
        User user = userRepository.findByUserName(userName)
                .orElseThrow(UserNotFoundException::new);
        String newPasswd;
        if (user.getUserName().equals(userName)) {
            newPasswd =new DigestUtils("SHA3-512").digestAsHex(passwd);
            if((newPasswd).equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }

    public User updateContacts(User user) {
        log.info("getted user for update : "+user.toString());
        User newUser= findByUserName(user.getUserName());
 log.info("retrieved user before update : "+newUser.toString());
        if (newUser.getUserName().equals(user.getUserName())){
            newUser.setName(user.getName());
            newUser.setSurname(user.getSurname());
            newUser.setEmail(user.getEmail());
            newUser.setLastIp(getClientIp());
            newUser.setHostName(getClientHostName());
            newUser.setLastLoginTime(LocalDateTime.now());
        }
        log.info("user after update : "+newUser.toString());
        return userRepository.save(newUser);
    }


    public User updatePassword(User user) {
        log.info("getted user for update : "+user.toString());
        User newUser= findByUserName(user.getUserName());
        log.info("retrieved user before update : "+newUser.toString());
        if (newUser.getUserName().equals(user.getUserName())){
           
            newUser.setLastIp(getClientIp());
            newUser.setHostName(getClientHostName());
            newUser.setLastLoginTime(LocalDateTime.now());

            String newPasswd = new DigestUtils("SHA3-512").digestAsHex(user.getPassword());
            
            
            newUser.setPassword(newPasswd);
        }
        log.info("user after update : "+newUser.toString());
        return userRepository.save(newUser);
    }


}







