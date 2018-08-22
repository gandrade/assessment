package com.mytaxi.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Could not find entity with id.")
public class EntityNotFoundException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(EntityNotFoundException.class);


    public EntityNotFoundException(String message)
    {
        super(message);
        LOG.warn(message);
    }

}
