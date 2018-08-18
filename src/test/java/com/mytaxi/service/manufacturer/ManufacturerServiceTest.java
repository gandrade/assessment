package com.mytaxi.service.manufacturer;

import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.ConstraintsViolationException;
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

    @Test
    public void shouldNotReturnManufacturerWhenFindByNameIgnoreCase(){
        Optional<ManufacturerDO> result = service.findByNameIgnoreCase("Manufacturer Test");
        assertThat(result, Matchers.equalTo(Optional.empty()));
    }

    @Test
    public void shouldReturnManufacturerWhenFindByNameIgnoreCase(){
        ManufacturerDO manufacturerDO = new ManufacturerDO("Toyota");
        Optional<ManufacturerDO> result = service.findByNameIgnoreCase("Toyota");
        assertThat(result.get().getName(), equalTo("TOYOTA"));
        assertThat(result.get().getDateCreated(), notNullValue());
        assertThat(result.get().getId(), notNullValue());
    }

    @Test
    public void shouldSaveManufacturerDO() throws ConstraintsViolationException
    {
        ManufacturerDO manufacturerDO = new ManufacturerDO("Manufacturer Test");
        ManufacturerDO manufacturerReturned = service.save(manufacturerDO);

        assertThat(manufacturerReturned.getId(), notNullValue());
        assertThat(manufacturerReturned.getName(), equalTo("MANUFACTURER TEST"));
        assertThat(manufacturerReturned.getDateCreated(), notNullValue());
    }

    @Test(expected = ConstraintsViolationException.class)
    public void shouldThrowConstraintsViolationExceptionWhenSavingManufacturerDO() throws ConstraintsViolationException
    {
        ManufacturerDO manufacturer = new ManufacturerDO("Toyota");
        service.save(manufacturer);
    }
}
