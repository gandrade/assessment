package com.mytaxi.service.driver;

import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverServiceTest
{
    @Autowired
    private DriverService driverService;


    @Test(expected = CarAlreadyInUseException.class)
    public void shouldAssignSameCarForDifferentDrivers() throws CarAlreadyInUseException, EntityNotFoundException, ConstraintsViolationException
    {
        driverService.assign(6L, 1L);
        driverService.assign(5L, 1L);
    }


    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenAssignCarUnknownCarForADriver() throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException
    {
        driverService.assign(999L, 999L);
    }


    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenAssignUnknownCarForADriver() throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException
    {
        driverService.assign(0000L, 1L);
    }


    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenAssingUnknownDriverForACar() throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException
    {
        driverService.assign(0000L, 1L);
    }


    @Test
    @DirtiesContext
    public void shouldUnassignCarForADriver() throws EntityNotFoundException, CarAlreadyInUseException, ConstraintsViolationException
    {
        driverService.assign(6L, 1L);
        driverService.unassign(6L, 1L);
    }


    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowEntityNotFoundExceptionWhenUnknownDriver() throws EntityNotFoundException
    {
        driverService.unassign(999L, 1L);
    }

}
