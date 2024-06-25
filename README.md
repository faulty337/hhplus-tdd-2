## API List

### 1. 특강 신청 API

`POST /lectures/apply`

- 특정 userId로 요청해 해당 lectures에 강의를 신청한다
- Request
    - userId, lectureId
    - lectures/{lectureId}/apply
    - Query Parameter : userId
    - path Variable : lectureId
- Response
    - status
    
    ```json
    {
    	"status":"SUCCESS"
    }
    ```
    
- 특강 시작일 : 4월 20일 토요일 1시
- 특강이 꽉 찼을 때 요청에 대한 실패(ex 30)
- 특강 신청에 대한 로그(history) 저장

### 2. 특강 신청 완료 여부 조회 API

`GET /lectures/application/{userId}` 

- 특정 userId로 특강 신청 완료 여부를 조회한다.
- 특강 신청의 실패/성공 유무에 따라 response(boolean)을 반환한다.
- Request
    - userId, lectureId
    - lectures/{lectureId}/apply
- Response
    - success
    
    ```json
    {
    	"success": "true"
    }
    ```
    

### 3. 특강 선택

`GET /lectures`

- 열린 날짜에 해당하는 강의 목록 출력
- Response
    
    ```json
    {
    	"lectureList":[
    		{
    			"lectureId":1,
    			"usercount":29,
    			"userLimit":30
    		},
    		{
    			"lectureId":1,
    			"usercount":29,
    			"userLimit":30
    		},
    		{
    			"lectureId":1,
    			"usercount":29,
    			"userLimit":30
    		}
    	]
    }
    ```

## ERD
![image](https://github.com/faulty337/hhplus-tdd-2/assets/37091532/2a0b6db2-a502-4802-a87a-efe94de24ffc)


## Test Case

1. POST /lectures/apply
    1. 기본 로직 동작 테스트
    2. opened_at 날짜 이전 등록 예외테스트
    3. parameter 예외 테스트
        1. userId
            1. 없는 user
            2. 정수 이외
        2. lectureId
            1. 없는 lecture
            2. 자료형
                1. 정수 이외
    4. 특강 Full 예외 테스트
    5. 동시성 테스트
2. GET /lectures/application/{userId}
    1. 기본 로직 동작 테스트
    2. parameter 예외 테스트
        1. 없는 user
        2. 정수 이외
3. GET /lectures
