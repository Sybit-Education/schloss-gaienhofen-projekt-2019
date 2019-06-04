package de.schlossgaienhofen.project2019.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Authentication failed")
public class UserAuthenticationFailed extends RuntimeException {

  public UserAuthenticationFailed() {
  }

  public UserAuthenticationFailed(String message) {
    super(message);
  }

  public UserAuthenticationFailed(String message, Throwable cause) {
    super(message, cause);
  }

  public UserAuthenticationFailed(Throwable cause) {
    super(cause);
  }

  public UserAuthenticationFailed(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
