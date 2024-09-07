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

![erd](https://github.com/user-attachments/assets/ec6e8b60-c290-4dfa-abe9-fd96c7d26fe2)



# 주력 컴포넌트
@Service 컴포넌트를 가장 주력으로 사용했습니다.        
API 특성상 비즈니스 로직이 가장 중요하고 많은 내용을 담고 있다고 생각되어 가장 주력으로 사용한 컴포넌트입니다.              
@Service 컴포넌트의 메서드들의 기능 및 사용 이유는 모두 주석으로 기재해두었습니다.


# API 명세
http://localhost:[IDE포트번호]/swagger-ui/index.html
