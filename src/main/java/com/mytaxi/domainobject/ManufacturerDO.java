package com.mytaxi.domainobject;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "manufacturer", uniqueConstraints = @UniqueConstraint(name = "un_name", columnNames = {"name"}))
public class ManufacturerDO
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private String name;

    @OneToMany(
        mappedBy = "manufacturerDO",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<CarDO> cars = new ArrayList<>();


    public void addCar(CarDO carDO)
    {
        cars.add(carDO);
        carDO.setManufacturerDO(this);
    }


    public void removeComment(CarDO carDO)
    {
        cars.remove(carDO);
        carDO.setManufacturerDO(this);
    }


    public ManufacturerDO()
    {

    }


    public ManufacturerDO(String name)
    {
        this.name = name.toUpperCase();
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public ZonedDateTime getDateCreated()
    {
        return dateCreated;
    }


    public void setDateCreated(ZonedDateTime dateCreated)
    {
        this.dateCreated = dateCreated;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


}
