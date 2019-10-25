package com.mytaxi.controller.mapper;

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
