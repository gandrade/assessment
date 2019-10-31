package com.mytaxi.core.mapper;

import org.junit.Assert;
import org.junit.Test;

public class DriverMapperTest
{

    @Test
    public void shouldReturnNullMakingDriver()
    {
        Assert.assertNull(DriverMapper.makeDriverDTO(null));
    }

}
