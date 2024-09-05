package me.sanghyuk.calendar.repository;

import me.sanghyuk.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    @Query("select s from Schedule s where s.userNo = :userNo")
    List<Schedule> findByUser(@Param("userNo") Long userNo);

    @Query("select s from Schedule s where s.scheduledate = :scheduledate and s.userNo =:userNo")
    List<Schedule> findByScheduledate(@Param("scheduledate") Date scheduledate, @Param("userNo") Long userNo);

    @Query("select s from Schedule s where s.userNo = :userNo and s.scheduledate between :start and :end")
    List<Schedule> findByPeriod(@Param("start") Date start, @Param("end") Date end, @Param("userNo") Long userNo);
}
