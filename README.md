# 수강신청 API
### 프로젝트 실행 전 준비 과정
- MySQL 실행 (없다면 설치 필요, 맥 기준-터미널에서 mysql.server start 명령어로 실행)
- MySQLWorkbench 또한 실행(없다면 설치 필요-MySQL을 편하게 이용하기 위한 툴)
- course라는 이름의 schema를 생성한다.(테이블은 추후에 프로젝트 실행 시 알아서 생성된다)
  - 이름을 다르게 생성시 프로젝트와 연동이 되지 않으므로 주의해야 한다.
- zip파일을 풀고 IntelliJ IDEA에서 프로젝트 오픈
- 프로젝트의 resources/application.yml로 이동
- username, password는 본인의 MySQL 로컬 서버 이름과 비밀번호로 바꾼다.(반드시 :다음 한칸 띄워야 한다)
> 예)     
username: admin  
password: 1234

### 프로젝트 실행 후 진행 과정
api 테스트를 위해서는 tutor와 student는 미리 넣어두어야 한다.  
MySQLWorkbench의 course라는 이름의 Schema에 가면 테이블들이 만들어져 있다. student와 tutor을 원하는 만큼 데이터를 삽입한다.  
이제 api 테스트를 시작한다.

### api 테스트 진행 과정
#### Swagger API 문서 사용
- http://localhost:8081/swagger-ui/index.html Swagger API 주소로 이동
- tutor, student API가 모두 존재한다.
- 원하는 시간대와 수업 길이로 테스트를 해보면서 DB와 비교해보면 된다.
- 입력 예시는 아래에 있다.
  - 이때 parameter로 넣어야 하는 id들은 미리 생성한 tutor와 student의 id이다. 예시는 1이라고 가정한다.

#### 1. 튜터 API
- 수업 생성
> 예)  
{  
    "startTime": "2025-05-23T15:30:00",  
    "durationMinutes": "60"  
}

- 수업 삭제
  - 원하는 id만 입력 후 실행

#### 2. 학생 API
- 수업 가능 시간대 조회
  - 수업 시작, 종료 시각, 수업 길이를 입력한다.  
> 시간 형식은 2025-05-23T15:30:00 이런 식으로 적으면 된다.  
날짜와 시간 구분자인 T는 반드시 작성해야 한다.  
길이(duration)는 숫자만 입력한다.

- 수업 가능한 튜터 조회
  - 수업 가능 시간대 조회와 같은 형식을 이용한다.
  
- 수강 신청
  - 앞서 조회한 시작시간과 수업 길이, 튜터 id를 입력해야 하며 로그인 방식이 없으므로 원하는 학생 id도 입력해야 한다.  
> 예)  
{  
    "studentId": 1,  
    "tutorId": 1,  
    "startTime": "2025-05-23T15:30:00",  
    "durationMinutes": 60  
}

- 신청한 수업 조회
  - 원하는 학생 id를 입력한다.
