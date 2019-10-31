package com.mytaxi.core.mapper;

import com.mytaxi.datatransferobject.DriverCriteriaDTO;
import com.mytaxi.domainobject.DriverDO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class DriverMapperTest
{

    @Test
    public void shouldReturnNullMakingDriver()
    {
        Assert.assertNull(DriverMapper.makeDriverDTO(null));
    }


    @Test
    public void shouldReturnNullMakingDriverCriteria()
    {
        DriverCriteriaDTO driverDTO = DriverCriteriaDTO.newBuilder().createDriverDTO();
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        Assert.assertThat(driverDO.getUsername(), Matchers.nullValue());
        Assert.assertThat(driverDO.getCarDO(), Matchers.nullValue());
        Assert.assertThat(driverDO.getCoordinate(), Matchers.nullValue());
        Assert.assertThat(driverDO.getOnlineStatus(), Matchers.nullValue());
        Assert.assertThat(driverDO.getPassword(), Matchers.nullValue());
    }

    @Test
    public void shouldReturnNullMakingDriverCriteria2()
    {
        DriverCriteriaDTO driverDTO = DriverCriteriaDTO.newBuilder().setUsername("username")
            .createDriverDTO();
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        Assert.assertThat(driverDO.getUsername(), Matchers.equalTo("username"));
    }

}
