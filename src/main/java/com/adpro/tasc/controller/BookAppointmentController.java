package com.adpro.tasc.controller;

import com.adpro.tasc.appointment.db.dao.AppointmentDAO;
import com.adpro.tasc.appointment.db.dao.CourseDAO;
import com.adpro.tasc.appointment.db.dao.ScheduleDAO;
import com.adpro.tasc.appointment.db.model.*;
import com.adpro.tasc.user.db.dao.UserDAO;
import com.adpro.tasc.user.db.model.AcademicUser;
import com.adpro.tasc.user.db.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.YearMonth;
import java.util.*;

@Controller
public class BookAppointmentController {
  @Autowired
  AppointmentDAO appointmentDAO;

  @Autowired
  ScheduleDAO scheduleDAO;

  @Autowired
  UserDAO userDAO;

  @Autowired
  CourseDAO courseDAO;

  private String[] daysArr = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY","SUNDAY"};
  private String[] calendarDaysArr = {"SUNDAY","MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY"};
  private List<String> calendarDaysList = Arrays.asList(calendarDaysArr);

  @GetMapping("/book-appointment/home")
  public String bookAppointmentHome(Model model, Principal principal) {
    AcademicUser currentUser = (AcademicUser) userDAO.getUser(principal.getName());
    List<Course> courseList = currentUser.getCourses();

    model.addAttribute("courseList", courseList);
    model.addAttribute("currentUser", currentUser);
    return "book_appointment/pick_course";
  }

  @GetMapping("/book-appointment/pick-ta")
  public String pickTA(Model model, Principal principal, @RequestParam("courseName") String courseName) {
    User currentUser = userDAO.getUser(principal.getName());
    Course course = courseDAO.getCourseByName(courseName);
    List<User> taList = userDAO.getTAbyCourse(course);

    model.addAttribute("taList", taList);

    // Backtrack values
    model.addAttribute("courseName",courseName);
    model.addAttribute("currentUser", currentUser);
    return "book_appointment/pick_ta";
  }

  @GetMapping("/book-appointment/see-ta-schedule")
  public String seeTaSchedule(
          Model model,
          Principal principal,
          @RequestParam("taUserName") String taUserName,
          @RequestParam("courseName") String courseName
  ) {
    User currentUser = userDAO.getUser(principal.getName());
    List<String> days = Arrays.asList(daysArr);
    AcademicUser ta = (AcademicUser) userDAO.getUser(taUserName);
    Schedule taSchedule = scheduleDAO.getUserSchedule(ta);

    if (Objects.isNull(taSchedule)) {
      // Kode buat instantiate schedule 1 to 1 baru
      Schedule newSchedule = new Schedule();
      newSchedule.setUser(ta);
      newSchedule.setAdminHasPermission(true);

      scheduleDAO.createSchedule(newSchedule);
      taSchedule = scheduleDAO.getUserSchedule(ta);
    }

    model.addAttribute("slots", taSchedule.getAvailableSlots());

    //Backtrack values
    model.addAttribute("taUserName", taUserName);
    model.addAttribute("courseName", courseName);
    model.addAttribute("currentUser", currentUser);

    return "book_appointment/see_ta_schedule";
  }

  @GetMapping("/book-appointment/book")
  public String bookAppointment(
          Model model,
          Principal principal,
          @RequestParam("startTime") int startTime,
          @RequestParam("finishTime") int finishTime,
          @RequestParam("day") String day,
          @RequestParam("taUserName") String taUserName,
          @RequestParam("courseName") String courseName,
          @RequestParam("bookTime") long bookTime,
          @RequestParam("error") String error
  ) {
    User currentUser = userDAO.getUser(principal.getName());
    System.out.println(error);
    System.out.println(startTime + " " + finishTime + " " + day + " " + bookTime);
    Calendar currentCalendar = Calendar.getInstance();
    currentCalendar.setTimeInMillis(bookTime);
    Calendar targetBookCalendar = (Calendar) currentCalendar.clone();
//    targetBookCalendar.setTimeInMillis(bookTime);
//    currentCalendar.set(2021,Calendar.MAY,31);
//    targetBookCalendar.set(2021,Calendar.MAY,31);

    int currentDate = currentCalendar.get(Calendar.DATE);
    int currentDayOfWeek = currentCalendar.get(Calendar.DAY_OF_WEEK);
    int currentYear = currentCalendar.get(Calendar.YEAR);
    int currentMonth = currentCalendar.get(Calendar.MONTH) + 1;
    int daysInCurrentMonth = YearMonth.of(currentYear,currentMonth).lengthOfMonth();

    int slotDayOfWeek = calendarDaysList.indexOf(day) + 1;
    int dayDifference = slotDayOfWeek - currentDayOfWeek;

    if (dayDifference < 0) {
      targetBookCalendar.add(Calendar.DATE,7 - (dayDifference * -1));
    } else {
      targetBookCalendar.add(Calendar.DATE, dayDifference);
    }

//    int targetBookDate = targetBookCalendar.get(Calendar.DATE);
//    int targetBookMonth = targetBookCalendar.get(Calendar.MONTH)+1;
//    int targetBookYear = targetBookCalendar.get(Calendar.YEAR);

    System.out.println("CurrentDate:"+currentDate+"."+currentMonth+"."+currentYear);
    System.out.println("CurrentDay:"+ calendarDaysArr[currentDayOfWeek-1]);
    System.out.println("TargetBookDay:"+calendarDaysArr[slotDayOfWeek-1]);
    System.out.println("TargetBookDate:"+targetBookCalendar.get(Calendar.DATE)+"."+
                               (targetBookCalendar.get(Calendar.MONTH)+1)+"."+
                               targetBookCalendar.get(Calendar.YEAR));

    List<String> targetBookDates = new ArrayList<>();
    Calendar targetBookDateIterator = (Calendar) targetBookCalendar.clone();

    for (int i = 0; i < 5; i++) {
      int targetBookDate = targetBookDateIterator.get(Calendar.DATE);
      int targetBookMonth = targetBookDateIterator.get(Calendar.MONTH)+1;
      int targetBookYear = targetBookDateIterator.get(Calendar.YEAR);
      String dateString = targetBookDate+"-"+targetBookMonth+"-"+targetBookYear;
      targetBookDates.add(dateString);
      targetBookDateIterator.add(Calendar.DATE, 7);
    }

//    System.out.println(calendarDaysArr[calendar.get(Calendar.DAY_OF_WEEK)-1]);
//    System.out.println(calendar.get(Calendar;.YEAR));

//    Calendar currentDate = Calendar(bookTime);
//    currentDate.getDate()

    model.addAttribute("startTime",startTime);
    model.addAttribute("finishTime",finishTime);
    model.addAttribute("day",day);
    model.addAttribute("taUserName",taUserName);
    model.addAttribute("courseName",courseName);
    model.addAttribute("targetBookDate",targetBookCalendar.getTimeInMillis());
    model.addAttribute("targetBookDates", targetBookDates);
    model.addAttribute("error",error);
    model.addAttribute("currentUser", currentUser);

    return "book_appointment/book_appointment";
  }

  @PostMapping("book-appointment/validate-book")
  public String processBookRequest(
          Principal principal,
          HttpServletRequest request,
          @RequestParam("slotStartTime") long slotStartTime,
          @RequestParam("slotFinishTime") long slotFinishTime,
          @RequestParam("bookDateWeekOffset") int bookDateWeekOffset,
          @RequestParam("startHourForm") int startHour,
          @RequestParam("endHourForm") int endHour,
          @RequestParam("startMinuteForm") int startMinute,
          @RequestParam("endMinuteForm") int endMinute,
          @RequestParam("targetBookDate") long targetBookMillis,
          @RequestParam("taUserName") String taUserName,
          @RequestParam("courseName") String courseName
  ) {
    boolean valid =true;

    long targetBookEpochStart = java.time.Duration.ofMinutes(startMinute).toMillis() +
            java.time.Duration.ofHours(startHour).toMillis();
    long targetBookEpochEnd = java.time.Duration.ofMinutes(endMinute).toMillis() +
            java.time.Duration.ofHours(endHour).toMillis();

    if (
            !(
                targetBookEpochStart < targetBookEpochEnd &&
                targetBookEpochStart >= slotStartTime &&
                targetBookEpochStart < slotFinishTime &&
                targetBookEpochEnd <= slotFinishTime &&
                targetBookEpochEnd > slotStartTime
            ) ||
            !(
                startHour >= 0 && startHour < 24 &&
                startMinute >= 0 && startMinute < 60 &&
                endHour >= 0 && endHour < 24 &&
                endMinute >= 0 && endMinute < 60
            )

    ) {
      valid = false;
    }

    if (!valid) {
      System.out.println("invalid");
      String referer = request.getHeader("Referer").replaceAll("false","true");
      System.out.println(referer);
      return "redirect:"+referer;
    } else {
      System.out.println("valid");
      Calendar calendarBookDate = Calendar.getInstance();
      calendarBookDate.setTimeInMillis(targetBookMillis);

      int targetBookDate = calendarBookDate.get(Calendar.DATE);
      int targetBookMonth = calendarBookDate.get(Calendar.MONTH)+1;
      int targetBookYear = calendarBookDate.get(Calendar.YEAR);
      long duration = targetBookEpochEnd - targetBookEpochStart;

      calendarBookDate.set(targetBookYear,targetBookMonth,targetBookDate,startHour,startMinute);
      calendarBookDate.add(Calendar.DATE,bookDateWeekOffset*7);
      long targetBookEpochDate = calendarBookDate.getTimeInMillis();

      Appointment appointment = new Appointment();
      appointment.setCourse(courseDAO.getCourseByName(courseName));
      appointment.setDuration(duration);
      appointment.setDate(targetBookEpochDate);

      AppointmentRequest appointmentRequest = new AppointmentRequest();
      appointmentRequest.setAppointment(appointment);
      appointmentRequest.setRequestTime(System.currentTimeMillis());
      appointmentRequest.setStatus(AppointmentRequest.Status.PENDING);
      appointmentRequest.setAdminHasPermission(true);
      appointmentRequest.setStudent(userDAO.getUser(principal.getName()));
      appointmentRequest.setTa(userDAO.getUser(taUserName));

      appointmentDAO.createAppointment(appointmentRequest);

      return "redirect:/see-appointment-student";
    }
  }
}
