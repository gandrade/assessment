package com.mytaxi.exception;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find entity with id.")
public class EntityNotFoundException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;


    public EntityNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public EntityNotFoundException(String message)
    {
        super(message);
    }
}
