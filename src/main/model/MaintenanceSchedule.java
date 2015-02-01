package main.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "maintenance_schedule",uniqueConstraints = {@UniqueConstraint(columnNames = {"from_date","to_date","room_id"})})
public class MaintenanceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "from_date")
    @NotNull
    private Date fromDate;

    @Column(name = "to_date")
    @NotNull
    private Date toDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Room room;

    @Column(name="description")
    @NotNull
    @Size(max = 100)
    private String description;

    public MaintenanceSchedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
