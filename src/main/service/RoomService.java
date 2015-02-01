package main.service;

import main.model.Room;
import main.model.RoomCategoryName;
import main.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepo roomRepo;

    @Autowired
    RoomCategoryService roomCategoryService;

    public void create(String name, BigDecimal chargePerHour, int noOfSeats, int roomCategoryId) {
        Room room = new Room();
        room.setName(name);
        room.setChargePerHour(chargePerHour);
        room.setNoOfSeats(noOfSeats);
        room.setRoomCategory(roomCategoryService.findById(roomCategoryId));
        roomRepo.save(room);
    }

    public List<Room> findAll() {
        return roomRepo.findAll();
    }

    public Room findBy(int id) {
        return roomRepo.findById(id);
    }

    public List<Room> findBy(RoomCategoryName roomCategoryName) {
        return roomRepo.findByCategory(roomCategoryName);
    }

    public Room findByName(String name) {
        return roomRepo.findByName(name);
    }
}
