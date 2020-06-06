package nl.dierenasiel.opdracht.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
