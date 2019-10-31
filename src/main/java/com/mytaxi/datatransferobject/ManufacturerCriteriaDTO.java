package com.mytaxi.datatransferobject;

import javax.validation.constraints.NotNull;

public class ManufacturerCriteriaDTO
{

    private Long id;

    @NotNull(message = "Name can not be null!")
    private String name;


    private ManufacturerCriteriaDTO()
    {

    }


    private ManufacturerCriteriaDTO(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }


    public static ManufacturerDTOBuilder newBuilder()
    {
        return new ManufacturerDTOBuilder();
    }


    public String getName()
    {
        return name;
    }


    public static class ManufacturerDTOBuilder
    {
        private Long id;
        private String name;


        public ManufacturerDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        public ManufacturerDTOBuilder setName(String name)
        {
            this.name = name;
            return this;
        }


        public ManufacturerCriteriaDTO createManufacturerDTO()
        {
            return new ManufacturerCriteriaDTO(id, name);
        }
    }

}
