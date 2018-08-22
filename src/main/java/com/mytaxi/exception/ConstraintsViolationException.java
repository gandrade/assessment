package com.mytaxi.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Some constraints are violated ...")
public class ConstraintsViolationException extends Exception
{

    static final long serialVersionUID = -3387516993224229948L;

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(ConstraintsViolationException.class);


    public ConstraintsViolationException(String message)
    {
        super(message);
        LOG.warn(message);
    }

}
