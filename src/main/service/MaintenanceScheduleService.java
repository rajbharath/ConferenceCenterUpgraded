package main.service;

import main.model.MaintenanceSchedule;
import main.model.Room;
import main.repo.MaintenanceScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaintenanceScheduleService {

    @Autowired
    MaintenanceScheduleRepo maintenanceScheduleRepo;

    @Autowired
    RoomService roomService;

    public void create(int roomId, Date fromDate, Date toDate, String description) {
        MaintenanceSchedule maintenanceSchedule = new MaintenanceSchedule();
        Room room = roomService.findBy(roomId);
        maintenanceSchedule.setRoom(room);
        maintenanceSchedule.setFromDate(fromDate);
        maintenanceSchedule.setToDate(toDate);
        maintenanceSchedule.setDescription(description);
        maintenanceScheduleRepo.save(maintenanceSchedule);
    }

    public List<MaintenanceSchedule> findBetween(int roomId,Date fromDate,Date toDate){
        Room room = roomService.findBy(roomId);
        return maintenanceScheduleRepo.findAllBetween(room,fromDate,toDate);
    }

}
