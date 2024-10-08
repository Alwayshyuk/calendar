package me.sanghyuk.calendar.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import me.sanghyuk.calendar.dto.ScheduleDTO;
import me.sanghyuk.calendar.service.ScheduleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequiredArgsConstructor
@ApiResponse(responseCode = "400", description = "입력 형식이 맞지않습니다.", content = @Content(mediaType = "application/json"))
@ApiResponse(responseCode = "500", description = "해당 데이터가 존재하지 않습니다.", content = @Content(mediaType = "application/json"))
@RequestMapping("/api/schedule")
public class ScheduleApiController {

    private final ScheduleService scheduleService;

    @Operation(summary = "모든 일정 조회", description = "userNo를 클라이언트로 받아와 출력하는 API")
    @GetMapping("/list")
    @Parameter(name = "userNo", description = "유저 번호", example = "1")
    public ResponseEntity list(@RequestParam(value = "userNo") Long userNo) {
        return ResponseEntity.ok(scheduleService.findByUser(userNo));
    }

    @Operation(summary = "일정 추가", description = "일정을 작성하는 API")
    @PostMapping("/save")
    @Parameters({
            @Parameter(name = "userNo", description = "유저 번호", example = "1"),
            @Parameter(name = "title", description = "일정 제목", example = "술"),
            @Parameter(name = "content", description = "일정 내용", example = "동기"),
            @Parameter(name = "cno", description = "1:red / 2:yellow / 3:green / 4:blue / 5:black", example = "1"),
            @Parameter(name = "scheduledate", description = "약속 일자", example = "2024-09-05"),
            @Parameter(name = "scheduletime", description = "약속 시간", example = "1800"),
            @Parameter(name = "chno", description = "1:실행 전 / 2:실행 후 / 3:변경", example = "1")
    })
    public ResponseEntity save(@RequestBody ScheduleDTO dto) {
        return ResponseEntity.ok(scheduleService.save(dto));
    }

    @Operation(summary = "일정 삭제", description = "일정을 삭제하는 API")
    @DeleteMapping("/delete")
    @Parameters({
            @Parameter(name = "userNo", description = "유저 번호", example = "1"),
            @Parameter(name = "sno", description = "일정 번호", example = "1")
    })
    public ResponseEntity modify(@RequestBody Long userNo, @RequestBody Long sno) {
        scheduleService.delete(userNo, sno);
        return ResponseEntity.ok(200);
    }

    // 일정 상태 변경, 일정 색상(중요도 등) 변경, 일정 변경 모두 modify 메서드로 구현
    @Operation(summary = "일정 변경", description = "일정을 변경하는 API")
    @PutMapping("/modify")
    @Parameters({
            @Parameter(name = "sno", description = "변경 할 일정 번호", example = "1"),
            @Parameter(name = "userNo", description = "유저 번호", example = "1"),
            @Parameter(name = "title", description = "일정 제목", example = "식사 약속"),
            @Parameter(name = "content", description = "일정 내용", example = "회사 앞"),
            @Parameter(name = "cno", description = "1:red / 2:yellow / 3:green / 4:blue / 5:black", example = "1"),
            @Parameter(name = "scheduledate", description = "약속 일자", example = "2024-09-05"),
            @Parameter(name = "scheduletime", description = "약속 시간", example = "1800"),
            @Parameter(name = "chno", description = "1:실행 전 / 2:실행 후 / 3:변경", example = "1")
    })
    public ResponseEntity modify(@RequestBody ScheduleDTO dto) {
        return ResponseEntity.ok(scheduleService.modify(dto));
    }

    @Operation(summary = "키워드 일정 검색", description = "키워드로 일정을 조회하는 API")
    @GetMapping("/keywordList")
    @Parameters({
            @Parameter(name = "userNo", description = "유저 번호", example = "1"),
            @Parameter(name = "keyword", description = "검색어", example = "동기"),
            @Parameter(name = "type", description = "1:제목 / 2:내용 / 3:제목+내용", example = "1")
    })
    public ResponseEntity keywordList(
            @RequestParam(value = "userNo") Long userNo,
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "type") int type
    ) {
        return ResponseEntity.ok(scheduleService.findByKeyword(userNo, keyword, type));

    }

    @Operation(summary = "기간별 일정 조회", description = "기간에 해당하는 일정을 조회하는 API")
    @GetMapping("/periodList")
    @Parameters({
            @Parameter(name = "userNo", description = "유저 번호", example = "1"),
            @Parameter(name = "start", description = "시작일", example = "2024-09-05"),
            @Parameter(name = "end", description = "종료일", example = "2024-09-06")
    })
    public ResponseEntity periodList(
            @RequestParam(value = "userNo") Long userNo,
            @RequestParam(value = "start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam(value = "end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end
    ) {
        return ResponseEntity.ok(scheduleService.findByPeriod(userNo, start, end));
    }

    @Operation(summary = "상태별 일정 조회", description = "일정 상태에 따라 일정을 조회하는 API")
    @GetMapping("/checkedList")
    @Parameters({
            @Parameter(name = "userNo", description = "유저 번호", example = "1"),
            @Parameter(name = "checkedName", description = "실행 전 / 실행 후 / 변경", example = "실행 전")
    })
    public ResponseEntity checkedList(
            @RequestParam(value = "userNo") Long userNo,
            @RequestParam(value = "checkedName") String checkedName
    ) {
        return ResponseEntity.ok(scheduleService.findByCheckedName(userNo, checkedName));
    }

    @Operation(summary = "성격별 일정 조회", description = "일정 성격(색상)에 따라 일정을 조회하는 API")
    @GetMapping("/colorList")
    @Parameters({
            @Parameter(name = "userNo", description = "유저 번호", example = "1"),
            @Parameter(name = "colorName", description = "red / yellow / green / blue / black", example = "red")
    })
    public ResponseEntity colorList(
            @RequestParam(value = "userNo") Long userNo,
            @RequestParam(value = "colorName") String colorName
    ) {
        return ResponseEntity.ok(scheduleService.findByColorName(userNo, colorName));
    }

    @Operation(summary = "일정 공유", description = "일정을 타 이용자에게 공유하는 API")
    @PutMapping("/sharing")
    @Parameters({
            @Parameter(name = "userNo", description = "공유 받을 유저 번호", example = "2"),
            @Parameter(name = "sno", description = "공유 할 일정 번호", example = "1")
    })
    public ResponseEntity shareSchedule(
            @RequestParam(value = "userNo") Long userNo,
            @RequestParam(value = "sno") Long sno
    ) {
        return ResponseEntity.ok(scheduleService.shareSchcedule(userNo, sno));
    }

}
