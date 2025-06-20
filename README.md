# 아웃 소싱 프로젝트
<br/>

---
## 목표
<br/>
할 일과 댓글 CRUD, JWT 토큰과 AOP, 그리고  Global Exception 적용 등 필수구현과 최대한의 도전기능 구현

---
## 팀 소개

### 팀장 :  문정호
### 팀원 : 차순영, 김광수, 이시온, 남궁교 

---
## 개발환경
<br/>

| 제목      | 내용                 |
|---------|--------------------|
| 언어      | Java 17            |
| 프레임워크   | Spring Boot        |
| 개발 환경   | IntelliJ IDEA      |
| 데이터 베이스 | MySQL(JDBC Driver) |
| ORM     | Spring Data JPA    |
| 보안      | JWT                |
| 빌드 도구   | Gradle             |
| 라이브러리   | Lombok             |
| API 테스트 | Postman            |

---

## 깃 브랜치 전략
### - 작명
### - 기능 완성할 때 마다 브랜치를 새로 파기
### - 한 도메인 테스트 다 거친 후 PR 머지하기
### - 머지 후 브랜치 삭제/보존 선택
### - 2명 이상  동의해야 머지 조건
### - **작명 정도**
###     - **feat/이름 - 기능 (중요)**

<br/>

---

<br/>

##  깃 컨벤션
<br/>

### - `feat` : 새로운 기능 추가

### - `fix` : 버그 수정

### - `docs` : 문서 수정

### - `style` : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우

### - `refactor` : 코드 리펙토링

### - `test` : 테스트 코드, 리펙토링 테스트 코드 추가

<br/>

---

## ERD 링크
https://www.notion.so/teamsparta/ERD-2112dc3ef514802395d6c1a601724928

<br/>

## API 문서 링크
<br/>

https://www.notion.so/teamsparta/API-2112dc3ef5148090b4f0d725bf7682de

<br/>

---

## Wireframe

![Image](https://github.com/user-attachments/assets/35fd6105-401d-4400-8ec7-cad298f11f0f.png)

![Image](https://github.com/user-attachments/assets/b1cd3d1d-85e7-4691-8cb7-bf5347dbc0ec)

![Image](https://github.com/user-attachments/assets/0fdc68bf-b4c0-40cc-9821-abe713942e70)

![Image](https://github.com/user-attachments/assets/501580ba-064d-4804-bb38-829957cb37a1)

![Image](https://github.com/user-attachments/assets/e309940c-9b24-434c-aacc-f7eca6fa9505)

![Image](https://github.com/user-attachments/assets/d5541f41-57fb-490d-8f7b-d2cb6ba2e3e0)


---

## 팀 트러블 슈팅

### 1.할일 생성 API, 댓글 생성 API 에서 로그인 하지 않아도 할 일 과 댓글이 작성 가능

- JWT 토큰에서  userId 추출 하여 해결


### 2. CommentList API 에서 포스트맨에서 String이 아닌 Enum으로 Request를 보내니 Enum요청을 받지 못하는 에러가 발생.

- Enum클래스 내부에 String을 Enum으로 변환하는 메서드 구현. 

### 3. 대부분의 API 에서 403,401 에러 발생
 
- 스프링 시큐리티에 막혀서 생기는 오류
- 화이트리스트에 사용할 주소들을 입력해서 오류를 해결

### 4. 모든 API 401 에러 반환

- Postman에서 로그인 API 요청을 보냈는데 서버에 요청 자체가 도달하지 않고 401 에러 발생
- 8080 포트가 이미 점유되어 있어서 새로운 서버가 제대로 실행되지 않음 -> 해결

### 5. 로그아웃 API 토큰인증 에러 발생 
- 클라이언트에서는 Bearer {token} 형식으로 토큰 전송,
  서버에서는 Bearer 없이 순수 토큰 값만 비교하고 있어서 인증 실패  
- 서버 쪽에서 substring 기능을 활용해서 문제 해결
- 로그아웃 정상 작동

### 6. 대부분의 API 공통적인 응답이 나오지 않음

- APIresponse을 사용 하여 해결
