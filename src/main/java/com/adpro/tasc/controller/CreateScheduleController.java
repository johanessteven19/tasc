package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.dao.SlotDAO;
import com.adpro.tasc.appointment.db.model.Schedule;
import com.adpro.tasc.appointment.db.model.Slot;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@PreAuthorize("hasRole('TEACHING_ASSISTANT')")
public class CreateScheduleController {
  @Autowired
  private ScheduleDAO scheduleDAO;

  @Autowired
  private UserDAO userDAO;

  @Autowired
  private SlotDAO slotDAO;

  private String[] daysArr = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY"};
  private List<Slot> slots = new ArrayList<>();
//  private final SlotRepository slots;

  @GetMapping(value="/create-schedule")
  public String displaySchedule(Model model, Principal principal) {
    List<String> days = Arrays.asList(daysArr);

    User currentUser = userDAO.getUser(principal.getName());

    Schedule schedule = scheduleDAO.getUserSchedule((AcademicUser) currentUser);

    if (Objects.isNull(schedule)) {
      // Kode buat instantiate schedule 1 to 1 baru
      Schedule newSchedule = new Schedule();
      newSchedule.setUser(currentUser);
      newSchedule.setAdminHasPermission(true);

      scheduleDAO.createSchedule(newSchedule);
      schedule = scheduleDAO.getUserSchedule(((AcademicUser) currentUser));
    }

    model.addAttribute("days",days);
    model.addAttribute("slots",schedule.getAvailableSlots());


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

//    slots.add(slot1);

    slotDAO.addSlot(slot1);

    return "redirect:/create-schedule";
  }
}
