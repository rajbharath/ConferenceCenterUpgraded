package main.service;

import main.model.RoomCategory;
import main.repo.RoomCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomCategoryService {
    @Autowired
    RoomCategoryRepo roomCategoryRepo;

    public RoomCategory findById(int id) {
        return roomCategoryRepo.findById(id);
    }
}
