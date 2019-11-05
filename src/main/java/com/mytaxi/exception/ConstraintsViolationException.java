package com.mytaxi.exception;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated ...")
public class ConstraintsViolationException extends Exception
{
    static final long serialVersionUID = -3387516993224229948L;


    public ConstraintsViolationException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public ConstraintsViolationException(String message)
    {
        super(message);
    }

}
