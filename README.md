# wanted-pre-onboarding-backend

### 김태헌

### 애플리케이션의 실행 방법(엔드포인트 호출 방법)

#### 현재 서버 : http://3.38.97.150

#### 회원가입, 로그인 후 제공받은 accessToken의 값을 Header에 Authorization key에 해당하는 value로 넣어서 나머지 API를 요청하면 됩니다.


### 데이터베이스 테이블 구조
<img width="1054" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/f02213c5-30a5-474d-b903-80cf02a9d5b3">

Member 테이블 : primary Key로 id를 두고, 로그인 회원가입을 위한 email, password column을 만들었습니다.

Bullet 테이블 : primary Key로 id를 두고, 제목 내용을 위한 title, content를 만들었습니다. Member 정보를 저장하기 위해 member의 key를 외래키로 가지고 있습니다. 


### 구현한 API의 동작을 촬영한 데모 영상 링크



### 구현 방법 및 이유에 대한 간략한 설명

#### 과제1: 회원가입
- 회원가입을 하기 위해서 이메일과 비밀번호를 입력받아야 합니다. 

- 조건을 충족하기 위하여 @Valid 어노테이션을 사용하여 검증하였습니다.

- 만족하지 않을 경우

<img width="1087" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/bb3207b3-7807-40e4-81a4-127854962cc6">
<img width="1085" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/1b31414d-2ab0-465e-9299-3f08a6411e0e">

- 비밀번호는 BCrypt 알고리즘을 사용하여 암호화하여 데이터베이스에 저장하였습니다.
<img width="451" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/379a029a-01af-4ac8-a44d-2774ec702c92">

#### 과제2: 로그인

- 과제1과 동일하게 @Valid 어노테이션을 사용하여 검증하였습니다.

- 만족하지 않을 경우
<img width="1068" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/b9c46b8d-4aa9-404d-b49f-3f843271a45e">
<img width="1078" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/1df4d36c-3c1c-4e55-9726-14889bb010c4">

- 비밀번호로 입력받은 값을 인코딩하여, 데이터베이스의 비밀번호와 같은지 검증하여서 성공한다면 사용자 인증에 성공합니다. 

- JWT 토큰의 Key의 Claim에는 memberId와 memberEmail 정보를 HS256 알고리즘을 이용해 인코딩하여서 Signature에 저장합니다. 

- Header와 Payload, Signature는 Base64로 다시 인코딩하여 전달합니다.

#### 과제 3: 게시글 생성

- 게시글은 JWT 토큰을 Header에 넣어서 보내주어야 하며, Body에 JSON형식으로 제목과 내용을 입력받아서 저장합니다. 

- JWT 토큰을 인증하고, 해당 토큰을 이용하여 멤버정보를 가져와 멤버의 이메일도 게시글 데이터베이스에 같이 저장해줍니다.

#### 과제 4: 게시글 모두 조회

- 게시글은 JWT 토큰을 Header에 넣어서 보내주어야 해당 API에 인가됩니다.

- 파라미터로 페이지 번호(pageNo)와 페이지 사이즈(pageSize)를 입력받습니다. 만약 입력받지 않았을 시 default로 페이지 번호는 0번, 페이지 크기는 3으로 자동으로 조회합니다.

- 해당 기능은 Pagination 인터페이스를 사용하여 구현하였습니다. 

#### 과제 5: 특정 게시글 조회

- 게시글은 JWT 토큰을 Header에 넣어서 보내주어야 해당 API에 인가됩니다.

- 파라미터로 게시글의 ID를 받습니다. 해당 게시글에 대한 제목, 내용, 게시글 작성자 정보를 Response로 반환합니다.

#### 과제 6: 특정 게시글 수정

- 게시글은 JWT 토큰을 Header에 넣어서 보내주어야 해당 API에 인가됩니다.

- 파라미터로 게시글의 ID를 받습니다. 그리고 JSON형태의 Body에 수정하고 싶은 제목과 내용을 Request합니다.

- 수정이 되면 어떤 제목의 게시글이 수정되었는지 전달해줍니다.

- JWT 토큰을 이용하여 만약 수정하려는 게시글이 게시글 작성자가 아니라면 **"게시글 작성자가 아닙니다. 다시 확인해주세요"** 메시지와 code로 406 반환합니다.

- 전달받은 게시글 ID 정보가 데이터베이스에 없다면 **"해당하는 게시글이 없습니다."** 와 code 406을 반환합니다.

#### 과제 7: 특정 게시글 삭제

- 게시글은 JWT 토큰을 Header에 넣어서 보내주어야 해당 API에 인가됩니다.

- 파라미터로 게시글의 ID를 받습니다.

- 삭제가 되면 "삭제되었습니다." 메시지를 전달합니다.

- JWT 토큰을 이용하여 만약 삭제하려는 게시글이 게시글 작성자가 아니라면 **"게시글 작성자가 아닙니다. 다시 확인해주세요"** 메시지와 code로 406 반환합니다.

- 전달받은 게시글 ID 정보가 데이터베이스에 없다면 **"해당하는 게시글이 없습니다."** 와 code 406을 반환합니다.


### 과제 조건 이외

#### Docker를 사용한 EC2 배포 

**http://3.38.97.150**

- local에서 docker로 빌드한 후 docker hub에 push한 뒤, EC2에서 해당 이미지를 pull 받아 백그라운드 실행하였습니다.
- MySQL 8.0 버전을 동일하게 이미지를 받아 실행하였습니다.

![image](https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/10b9a1dc-52f5-4a7d-bd7e-07ba833e0410)


#### Exception Handler

- 토큰 검증 : 유효하지 않거나, 만료, 지원되지 않는 토큰인지 4가지로 나누어서 검증 메시지를 전달합니다.
- <img width="786" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/2020c5b8-7fc5-40ed-90c1-cac50e6277df">
- 유효성 검증 : 이메일과 비밀번호 검증을 하고 올바르지 않으면 메시지를 전달하고 코드는 406으로 전달합니다.
- <img width="863" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/a0b0ac84-2a7f-4bb2-9d2e-fa7b611c7478">
- 에러메시지 전달 : error type, code, message로 나누어 보여주었습니다.
- <img width="511" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/e7c82590-0eb1-4848-9b0b-cd1a6c57415b">


#### Swagger 설정
- Swagger V2를 사용하여 API 명세서를 보여주었습니다.

#### Config 설정
- Security Config : 쿠키와 세션을 사용하지 않고, JWT 토큰만 사용하기 위한 환경을 만들고, httpBasic을 비활성화하고 httpBearer 방식만 사용하도록 하였습니다. 또한 swagger문서와 member/login, members/signup 이외에는 모두 JWT 토큰 검증을 필요하도록 설정하였습니다.
- Swagger Config : 앱의 정보와 버전, 설명을 주었습니다. com.example 안의 API를 보여주도록 설정하였습니다.

### API 명세(request/response) 포함

#### http://3.38.97.150/swagger-html

#### 회원가입 : POST http://3.38.97.150/members/signup

**REQUEST**

BODY
{ "email": String, "password": String }

**RESPONSE**
BODY
{
    "id": Long,
    "email": String,
    "password": String
}
<img width="1090" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/5d815c5c-709b-4401-9e8c-9d186a826948">

#### 로그인 : POST http://3.38.97.150/members/login

**REQUEST**

BODY
{
    "email": String,
    "password": String
}

**RESPONSE**

BODY
{
    "email": String,
    "grantType": String,
    "accessToken": String
}
<img width="1083" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/8f5c6bd7-7740-4e78-acff-5041d4946054">

#### 새로운 게시글 작성 : POST http://3.38.97.150/bullet/new

**REQUEST**

HEADER
{
  "Authorization": "Bearer ${ACCESS_TOKEN}"
}

BODY
{
    "title": String,
    "content": String
}

{
    "title": String,
    "content": String,
    "member": String
}
<img width="1077" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/2387bc18-0864-4945-ac94-28d61ff0f3b3">

#### 게시글 목록 조회 : GET http://3.38.97.150/bullet/all

**REQUEST**

HEADER
{
  "Authorization": "Bearer ${ACCESS_TOKEN}"
}

**RESPONSE**

{
    {
      "title": String,
      "content": String,
      "member": String
    },
    {
      "title": String,
      "content": String,
      "member": String
    }, // JSON배열 형태
}
<img width="1083" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/f3bb2d88-5015-4ea2-9a69-e8793f160e79">

#### 특정 게시글 조회 : GET http://3.38.97.150/bullet?id=

**REQUEST**

PARAMETER
id: int

HEADER
{
  "Authorization": "Bearer ${ACCESS_TOKEN}"
}

**RESPONSE**
{
    "title": String,
    "content": String,
    "member": String
}

<img width="1083" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/df75b7ea-cb33-4236-89c8-e17bfcb798f2">

#### 특정 게시글 수정 : POST http://3.38.97.150/bullet/edit?id=

**REQUEST**

PARAMETER
id: int

HEADER
{
  "Authorization": "Bearer ${ACCESS_TOKEN}"
}

BODY
{
    "title": String,
    "content": String
}

**RESPONSE**
String(게시글 수정본 제목의 게시물이 수정되었습니다.)
<img width="1084" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/9fad8ce5-8359-42e8-9703-6289aee992cd">

#### 특정 게시글 삭제 : POST http://3.38.97.150/bullet?id=
**REQUEST**

PARAMETER
id: int

HEADER
{
  "Authorization": "Bearer ${ACCESS_TOKEN}"
}

**RESPONSE**


String


<img width="1081" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/c7ddd911-3546-4a90-8df1-a3eeb33b3aec">

