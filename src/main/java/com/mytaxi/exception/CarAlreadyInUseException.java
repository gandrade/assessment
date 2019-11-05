package com.mytaxi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Car already in use")
public class CarAlreadyInUseException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public CarAlreadyInUseException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public CarAlreadyInUseException(String message)
    {
        super(message);
    }
}
