package az.elixir.experise.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.admin.UserLoginRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.repository.UserRepository;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  private boolean login(UserLoginRequest userLoginRequest) {
    UserEntity userEntity =
        userRepository.findByUsernameAndPassword(
            userLoginRequest.getUsername(), userLoginRequest.getPassword());
    if (userEntity != null) {
      return true;
    }
    return false;
  }
}
