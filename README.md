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
    			"userCocunt":29,
    			"userLimit":30
    		},
    		{
    			"lectureId":1,
    			"userCocunt":29,
    			"userLimit":30
    		},
    		{
    			"lectureId":1,
    			"userCocunt":29,
    			"userLimit":30
    		}
    	
    	
    	]
    }
    ```
