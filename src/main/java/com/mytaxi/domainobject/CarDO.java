package com.mytaxi.domainobject;

import com.mytaxi.domainvalue.EngineType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "car",
        uniqueConstraints = @UniqueConstraint(name = "uc_license_plate", columnNames = {"licensePlate"}))
public class CarDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private String licensePlate;

    private Integer seatCount;

    private Boolean convertible = false;

    private Float rating;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType engineType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id")
    private ManufacturerDO manufacturerDO;

    @OneToOne(mappedBy = "carDO", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private DriverDO driverDO;

    public CarDO(){
    }

    public CarDO(String licensePlate, Integer seatCount, Boolean convertible, Float rating, EngineType engineType) {
        this.licensePlate = licensePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
    }

    public CarDO(String licensePlate, Integer seatCount, Boolean convertible, Float rating, EngineType engineType, ManufacturerDO manufacturerDO) {
        this(licensePlate, seatCount, convertible, rating, engineType);
        this.manufacturerDO = manufacturerDO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getConvertible() {
        return convertible;
    }

    public void setConvertible(Boolean convertible) {
        this.convertible = convertible;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public ManufacturerDO getManufacturerDO() {
        return manufacturerDO;
    }

    public void setManufacturerDO(ManufacturerDO manufacturerDO) {
        this.manufacturerDO = manufacturerDO;
    }
}
