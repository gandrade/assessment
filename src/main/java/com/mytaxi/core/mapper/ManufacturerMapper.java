package com.mytaxi.core.mapper;

import com.mytaxi.datatransferobject.ManufacturerCriteriaDTO;
import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.ManufacturerDO;

public class ManufacturerMapper
{

    /**
     *
     * @param manufacturerDO
     * @return
     */
    public static ManufacturerDTO makeManufacturerDTO(ManufacturerDO manufacturerDO)
    {
        if (manufacturerDO == null)
        {
            return null;
        }
        return ManufacturerDTO.newBuilder()
            .setId(manufacturerDO.getId())
            .setName(manufacturerDO.getName())
            .createManufacturerDTO();
    }


    /**
     *
     * @param manufacturerDTO
     * @return
     */
    public static ManufacturerDO makeManufacturerDO(ManufacturerDTO manufacturerDTO)
    {

        return new ManufacturerDO(manufacturerDTO.getName());
    }


    /**
     *
     * @param manufacturerDTO
     * @return
     */
    public static ManufacturerDO makeManufacturerDO(ManufacturerCriteriaDTO manufacturerDTO)
    {

        if (manufacturerDTO == null) {
            return null;
        }
        return new ManufacturerDO(manufacturerDTO.getName());
    }
}
