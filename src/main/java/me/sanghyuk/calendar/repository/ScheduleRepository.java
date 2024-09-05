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

    @Query("select s from Schedule s " +
            "where s.scheduledate = :scheduledate " +
            "and s.userNo =:userNo")
    List<Schedule> findByScheduledate(@Param("userNo") Long userNo, @Param("scheduledate") Date scheduledate);

    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.scheduledate between :start and :end")
    List<Schedule> findByPeriod(@Param("userNo") Long userNo, @Param("start") Date start, @Param("end") Date end);

    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.title like concat('%', :keyword, '%')")
    List<Schedule> findByTitle(@Param("userNo") Long userNo, @Param("keyword") String keyword);

    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.content like concat('%', :keyword, '%')")
    List<Schedule> findByContent(@Param("userNo") Long userNo, @Param("keyword") String keyword);

    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and " +
            "s.title like concat('%', :keyword, '%') " +
            "or " +
            "s.content like concat('%', :keyword, '%')")
    List<Schedule> findByTitleAndContent(@Param("userNo") Long userNo, @Param("keyword") String keyword);

    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.chno = " +
            "(select c.chno from Checked c where c.checkedName = :checkedName)")
    List<Schedule> findByCheckedName (@Param("userNo") Long userNo, @Param("checkedName") String checkedName);

    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.cno = " +
            "(select c.cno from Color c where c.colorName = :colorName)")
    List<Schedule> findByColorName (@Param("userNo") Long userNo, @Param("colorName") String colorName);

}
