package main.service;

import main.model.Room;
import main.model.RoomCategoryName;
import main.repo.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    RoomRepo roomRepo;

    public List<Room> findAll() {
        return roomRepo.findAll();
    }

    public Room findBy(int id){
        return roomRepo.findById(id);
    }

    public List<Room> findBy(RoomCategoryName roomCategoryName){
        return roomRepo.findByCategory(roomCategoryName);
    }
}
