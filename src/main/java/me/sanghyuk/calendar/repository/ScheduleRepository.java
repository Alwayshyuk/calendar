package me.sanghyuk.calendar.repository;

import me.sanghyuk.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
}
