package com.adpro.tasc.repository;

import com.adpro.tasc.appointment.db.model.Slot;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SlotRepository {
  private List<Slot> slots = new ArrayList<>();

  public List<Slot> getSlots() {
    return slots;
  }

  public void addSlot(Slot slot) {
    slots.add(slot);
  }
}
