package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.model.Slot;
import com.adpro.tasc.repository.SlotRepository;
import com.adpro.tasc.user.db.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CreateScheduleController {
  @Autowired
  private ScheduleDAO scheduleDAO;

  private String[] daysArr = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY"};
  private List<Slot> slots = new ArrayList<>();
//  private final SlotRepository slots;


  @GetMapping(value="/create-schedule")
  public String displaySchedule(Model model) {
    List<String> days = Arrays.asList(daysArr);

//    Slot slot1 = new Slot();
//    slot1.setDay(Slot.Day.MONDAY);
//    slot1.setStartTime(System.currentTimeMillis());
//    slot1.setFinishTime(System.currentTimeMillis()+(long)100000000);
//    slots.add(slot1);

//    scheduleDAO.addUserScheduleSlot();

    model.addAttribute("days",days);
    model.addAttribute("slots",slots);
    model.addAttribute("slots",scheduleDAO.addUserScheduleSlot());



    return "display_schedule";
  }

  @GetMapping(value="/add-slot")
  public String addSlotForm(Model model) {
    model.addAttribute("days",daysArr);

    return "create_slot";
  }

  @PostMapping(value="/add-slot")
  public String createSlot(
          @RequestParam("day") int day,
          @RequestParam("startHour") int startHour,
          @RequestParam("startMinute") int startMinute,
          @RequestParam("endHour") int endHour,
          @RequestParam("endMinute") int endMinute
          )
  {
    System.out.println(day+" "+startHour+ " " + startMinute);

    long epochStart = java.time.Duration.ofMinutes(startMinute).toMillis() +
            java.time.Duration.ofHours(startHour).toMillis();

    long epochEnd = java.time.Duration.ofMinutes(endMinute).toMillis() +
            java.time.Duration.ofHours(endHour).toMillis();

    Slot slot1 = new Slot();
    slot1.setDay(Slot.Day.valueOf(daysArr[day]));
    slot1.setStartTime(epochStart);
    slot1.setFinishTime(epochEnd);
    slots.add(slot1);

    return "redirect:/create-schedule";
  }
}
