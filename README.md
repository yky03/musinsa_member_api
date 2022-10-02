# 개발환경
- tomcat-embed-core-9.0.65.jar
- java 8
- spring boot 2.6.12
- h2-embed-database mysql mode
- maven
- junit5
- server port : 8080

# 실행방법
git clone -> intellij -> maven clean and install(build) -> MemberApiApplication 실행(내장 톰캣 구동)

# Test
```
1. 회원가입 Rest API
POST(JSON) http://localhost/members

*postman 등의 tool 을 활용해 json 형식의 data post api 아래의 값과 같은 형식으로 호출
{
	"id" : "gyyang",
	"phone" : "010-2708-0663",
	"email" : "ykycome00@gmail.com",
	"password" : "rldufaaaaaaA11!"
}

2. 회원조회 Rest API
GET http://localhost:8080/members/{memberNo}
ex) GET http://localhost:8080/members/1
회원 가입 API 등록후 최초 생성된 seq 1번으로 회원 정보 조회 혹은 h2 table 확인 후 seq 확인하여 조회


3. h2 member table 조회
http://localhost:8080/h2-console
JDBC URL : jdbc:h2:~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;MODE=MYSQL
User Name : sa
CONNECT 후 MEMBER 테이블 확인 가능

4. Junit5 Test Case 실행
test 패키지에 있는 킅래스들을 실행하면 단정문을 통해 결과를 검증합니다.
```
