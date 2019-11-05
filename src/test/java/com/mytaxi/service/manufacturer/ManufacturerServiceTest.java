package com.mytaxi.service.manufacturer;

import com.mytaxi.dataaccessobject.ManufacturerRepository;
import com.mytaxi.domainobject.ManufacturerDO;
import com.mytaxi.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DefaultManufacturerService.class)
public class ManufacturerServiceTest
{
    @Autowired
    private ManufacturerService service;

    @MockBean
    private ManufacturerRepository manufacturerRepository;


    @Test(expected = EntityNotFoundException.class)
    public void shouldNotReturnManufacturerWhenFindByNameIgnoreCase() throws EntityNotFoundException
    {
        String name = "Manufacturer Test";
        BDDMockito.given(manufacturerRepository.findByNameIgnoreCase(name))
            .willAnswer(answer -> {
                throw new EntityNotFoundException("Entity not found");
            });

        service.findByNameIgnoreCase(name);
    }


    @Test
    public void shouldReturnManufacturerWhenFindByNameIgnoreCase() throws EntityNotFoundException
    {
        String name = "Toyota";
        ManufacturerDO result = new ManufacturerDO("TOYOTA");
        result.setId(10L);

        BDDMockito.given(manufacturerRepository.findByNameIgnoreCase(name))
            .willReturn(Optional.of(result));

        result = service.findByNameIgnoreCase(name);
        assertThat(result.getName(), equalTo("TOYOTA"));
        assertThat(result.getDateCreated(), notNullValue());
        assertThat(result.getId(), notNullValue());
    }
}
