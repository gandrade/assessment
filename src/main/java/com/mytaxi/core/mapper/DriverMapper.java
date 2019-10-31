package com.mytaxi.core.mapper;

import com.mytaxi.datatransferobject.DriverCriteriaDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverMapper
{
    /**
     *
     * @param driverDTO
     * @return
     */
    public static DriverDO makeDriverDO(DriverDTO driverDTO)
    {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
    }


    /**
     *
     * @param driverDTO
     * @return
     */
    public static DriverDO makeDriverDO(DriverCriteriaDTO driverDTO)
    {
        DriverDO driverDO = new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
                driverDO.setOnlineStatus(driverDTO.getOnlineStatus());
                driverDO.setCarDO(CarMapper.makeDriverDO(driverDTO.getCarDTO()));
        return driverDO;
    }


    /**
     *
     * @param driverDO
     * @return
     */
    public static DriverDTO makeDriverDTO(DriverDO driverDO)
    {
        if (driverDO == null)
        {
            return null;
        }

        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
            .setId(driverDO.getId())
            .setPassword(driverDO.getPassword())
            .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null)
        {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        driverDTOBuilder.setCarDTO(CarMapper.makeCarDTO(driverDO.getCarDO()));
        return driverDTOBuilder.createDriverDTO();
    }


    /**
     *
     * @param drivers
     * @return
     */
    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers)
    {
        return drivers.stream()
            .map(DriverMapper::makeDriverDTO)
            .collect(Collectors.toList());
    }
}
