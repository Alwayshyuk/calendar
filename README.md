# 일정 API
 자신의 일정을 등록하고 관리하는 API
 - 날짜, 시간, 일정 내용 등을 등록하여 일정 관리
 - 색상 변경을 통해 일정의 중요도, 성격 등을 구분
 - checked를 통해 일정의 상태 관리(실행 전 일정, 실행 후 일정, 미뤄진 일정 등)

# 빌드 방법 및 실행 방법

### 선행 조건
 - JDK 17 이상
 - Gradle
 - 스프링 부트 v3.0 이상
 - mysql

### 빌드 및 실행 방법
1. src/main/resources/application.properties 파일에 []를 자신의 mysql에 맞춰 작성
2. src/main/java/me/sanghyuk/calendar/schedulerApplication.java 실행
   > 실행시 schedule 테이블 생성됨        
   > 생성 확인 후 mysql.sql의 더미 데이터 생성 쿼리문 mysql에서 실행
3. src/test/java/me/sanghyuk/calendar/schedulerApplicationTests.java 테스트 코드 확인 및 실행

![erd](https://github.com/user-attachments/assets/7976b717-76f6-4a3d-8524-ee2105a0317d)


# 주력 컴포넌트

# API 명세
http://localhost:8080/swagger-ui/index.html
