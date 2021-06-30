//package com.adpro.tasc.appointment.db;
//
//import com.adpro.tasc.appointment.db.model.Schedule;
//import com.adpro.tasc.appointment.db.model.Slot;
//import com.adpro.tasc.user.db.model.AcademicUser;
//import com.adpro.tasc.user.db.model.Role;
//import com.adpro.tasc.user.db.model.User;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ScheduleTest {
//    @Test
//    void testScheduleIDExists() throws Exception {
//        Schedule schedule = new Schedule();
//        schedule.setId(1);
//        assertEquals(1, schedule.getId());
//    }
//
//    @Test
//    void testScheduleUserExists() throws Exception {
//        User user = new User("Test", "test", "123", Role.ROLE_UNASSIGNED);
//        List<Slot> availableSlots = new ArrayList<Slot>();
//        Schedule schedule = new Schedule();
//        schedule.setUser(user);
//        assertEquals(user, schedule.getUser());
//    }
//
//    @Test
//    void testScheduleSlotsExist() throws Exception {
//        List<Slot> availableSlots = new ArrayList<Slot>();
//        Schedule schedule = new Schedule();
//        schedule.setAvailableSlots(availableSlots);
//        assertEquals(availableSlots, schedule.getAvailableSlots());
//    }
//
//    @Test
//    void testScheduleSlotsPermissionExist() throws Exception {
//        List<Slot> availableSlots = new ArrayList<Slot>();
//        Schedule schedule = new Schedule();
//        schedule.setAdminHasPermission(false);
//        assertEquals(false, schedule.isAdminHasPermission());
//    }
//
//}
