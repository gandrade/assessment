package com.mytaxi.service.manufacturer;

import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManufacturerServiceTest
{

    private ManufacturerRepository repository;

    @Autowired
    private ManufacturerService service;

    @Test(expected = EntityNotFoundException.class)
    public void shouldNotReturnManufacturerWhenFindByNameIgnoreCase() throws EntityNotFoundException
    {
        service.findByNameIgnoreCase("Manufacturer Test");
    }

    @Test
    public void shouldReturnManufacturerWhenFindByNameIgnoreCase() throws EntityNotFoundException
    {
        ManufacturerDO manufacturerDO = new ManufacturerDO("Toyota");
        ManufacturerDO result = service.findByNameIgnoreCase("Toyota");
        assertThat(result.getName(), equalTo("TOYOTA"));
        assertThat(result.getDateCreated(), notNullValue());
        assertThat(result.getId(), notNullValue());
    }
}
