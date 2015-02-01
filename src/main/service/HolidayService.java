package main.service;

import main.model.Holiday;
import main.repo.HolidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HolidayService {
    @Autowired
    HolidayRepo holidayRepo;

    public void create(Date date,String reason){
        Holiday holiday = new Holiday();
        holiday.setHoliday(date);
        holiday.setReason(reason);
        holidayRepo.save(holiday);
    }

    public List<Holiday> findAllBetween(Date fromDate, Date toDate) {
        return holidayRepo.findAllBetween(fromDate, toDate);
    }
}
