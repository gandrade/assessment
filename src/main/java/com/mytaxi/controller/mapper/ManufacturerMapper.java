package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.ManufacturerDTO;
import com.mytaxi.domainobject.ManufacturerDO;

public class ManufacturerMapper
{

    public static ManufacturerDTO makeManufacturerDTO(ManufacturerDO manufacturerDO)
    {
        if (manufacturerDO == null)
        {
            return null;
        }
        return ManufacturerDTO.newBuilder()
            .setId(manufacturerDO.getId())
            .setName(manufacturerDO.getName())
            .createNewManufacturerDTO();
    }


    public static ManufacturerDO makeManufacturerDO(ManufacturerDTO manufacturerDTO)
    {

        return new ManufacturerDO(manufacturerDTO.getName());
    }
}
