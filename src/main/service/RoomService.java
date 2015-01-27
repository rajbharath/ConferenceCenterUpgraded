package main.service;

import main.model.Room;
import main.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoomService {
    @Autowired
    RoomRepo roomRepo;

    public void create() {

    }

    public List<Room> findAll() {
        return roomRepo.findAll();
    }

    public Room findBy(int id){
        return roomRepo.findById(id);
    }
}
