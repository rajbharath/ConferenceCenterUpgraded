package main.model;

import jdk.nashorn.internal.ir.annotations.Reference;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "charge_per_hour")
    private BigDecimal chargePerHour;

    @Column(name = "no_of_seats")
    private int noOfSeats;

    @Reference
    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
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
