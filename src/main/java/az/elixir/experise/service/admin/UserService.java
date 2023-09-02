package az.elixir.experise.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.UserLoginRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.repository.UserRepository;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  public Payload login(UserLoginRequest userLoginRequest) {
    Payload payload = new Payload();
    UserEntity userEntity =
        userRepository.findByUsernameAndPassword(
            userLoginRequest.getUsername(), userLoginRequest.getPassword());
    if (userEntity != null) {
      payload.setSuccess(true);
      payload.setResult(userEntity);
    }
    return payload;
  }

  public boolean checkUser(UserEntity user) {
    UserEntity userEntity =
        userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    if (userEntity != null) {
      return true;
    }
    return false;
  }
}
