package main.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name",unique = true,nullable = false)
    @NotBlank
    @Size(max = 10)
    private String name;

    @Column(name = "charge_per_hour",nullable = false)
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    @NotNull
    private BigDecimal chargePerHour;

    @Column(name = "no_of_seats",nullable = false)
    @Min(value = 1)
    @Digits(integer = 4, fraction = 0)
    private int noOfSeats;

    @OneToOne
    private RoomCategory roomCategory;

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getChargePerHour() {
        return chargePerHour;
    }

    public void setChargePerHour(BigDecimal chargePerHour) {
        this.chargePerHour = chargePerHour;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }
}
