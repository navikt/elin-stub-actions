package no.nav.elin.stub.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
    reason = "Personens rolle eksisterer ikke i databasen. Lagre sak med rolle først.")
public class MissingRolleException extends RuntimeException {

  private static final long serialVersionUID = -922689026150595262L;

}
