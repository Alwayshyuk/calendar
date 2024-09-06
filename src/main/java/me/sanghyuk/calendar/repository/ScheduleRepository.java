package me.sanghyuk.calendar.repository;

import me.sanghyuk.calendar.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    //유저별 일정 검색 쿼리
    @Query("select s from Schedule s where s.userNo = :userNo")
    List<Schedule> findByUser(@Param("userNo") Long userNo);

    //선택한 날에 맞는 일정 검색 쿼리
    @Query("select s from Schedule s " +
            "where s.scheduledate = :scheduledate " +
            "and s.userNo =:userNo")
    List<Schedule> findByScheduledate(@Param("userNo") Long userNo, @Param("scheduledate") Date scheduledate);

    //선택한 기간에 속하는 일정 검색 쿼리
    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.scheduledate between :start and :end")
    List<Schedule> findByPeriod(@Param("userNo") Long userNo, @Param("start") Date start, @Param("end") Date end);

    //제목 키워드에 맞는 일정 검색 쿼리
    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.title like concat('%', :keyword, '%')")
    List<Schedule> findByTitle(@Param("userNo") Long userNo, @Param("keyword") String keyword);

    //내용 키워드에 맞는 일정 검색 쿼리
    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.content like concat('%', :keyword, '%')")
    List<Schedule> findByContent(@Param("userNo") Long userNo, @Param("keyword") String keyword);

    //제목과 내용의 키워드에 맞는 일정 검색 쿼리
    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and " +
            "s.title like concat('%', :keyword, '%') " +
            "or " +
            "s.content like concat('%', :keyword, '%')")
    List<Schedule> findByTitleAndContent(@Param("userNo") Long userNo, @Param("keyword") String keyword);

    //상태 코드가 아닌 상태 명으로 검색하여 이에 맞는 일정 검색 쿼리
    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.chno = " +
            "(select c.chno from Checked c where c.checkedName = :checkedName)")
    List<Schedule> findByCheckedName (@Param("userNo") Long userNo, @Param("checkedName") String checkedName);

    //색상 코드가 아닌 색삭명으로 검색하여 이에 맞는 일정 검색 쿼리
    @Query("select s from Schedule s " +
            "where s.userNo = :userNo " +
            "and s.cno = " +
            "(select c.cno from Color c where c.colorName = :colorName)")
    List<Schedule> findByColorName (@Param("userNo") Long userNo, @Param("colorName") String colorName);

    /*
    유저별 일정 검색(색상, 상태가 코드가와 이름으로 출력) 쿼리
        추후 서비스 확장으로 색상, 상태 명을 사용자가 직접 입력할 수 있게 될 경우를 대비하여 작성
        (ex. 색상 코드를 클라이언트를 통해 rgb값으로 저장)
        서비스 확장시 Color, Checked 테이블에 userNo를 추가하고 외래키로 설정하여 유저별 색상, 상태 출력 등의 기능 추가 필요.
     */

    @Query("select s, co.colorName, ch.checkedName " +
            "from Schedule s, Color co, Checked ch " +
            "where s.userNo = :userNo " +
            "and s.cno = co.cno " +
            "and s.chno = ch.chno")
    List<Schedule> findByUserAllName (@Param("userNo") Long userNo);
}
