package nl.dierenasiel.opdracht.exception;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class PreConditionFailedException extends RuntimeException {

    public PreConditionFailedException() {
    }

    public PreConditionFailedException(String message) {
        super(message);
    }
}
