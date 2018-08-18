package com.mytaxi.service.driver;

import com.mytaxi.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverServiceTest
{
    @Autowired
    private DriverService driverService;

    @Test
    public void shouldAssignCarForADriver(){


    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenAssignCarUnknownCarForADriver() throws EntityNotFoundException
    {
        driverService.assign(999L, 999L);

    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenAssignUnknownCarForADriver() throws EntityNotFoundException
    {
        driverService.assign(0000L, 1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenAssingUnknownDriverForACar() throws EntityNotFoundException
    {
        driverService.assign(1L, 0000L);
    }

    @Test
    public void shouldUnassignCarForADriver(){

    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenUnassignUnknownCarForADriver() throws EntityNotFoundException
    {
        driverService.unassign(999L, 1L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenUnassingUnknownDriverForACar() throws EntityNotFoundException
    {
        driverService.unassign(1L, 0000L);
    }




}
