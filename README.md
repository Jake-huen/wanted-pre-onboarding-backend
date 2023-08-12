# wanted-pre-onboarding-backend

### 김태헌

### 애플리케이션의 실행 방법(엔드포인트 호출 방법)

#### 회원가입, 로그인 후 제공받은 accessToken의 값을 Header에 Authorization key에 해당하는 value로 넣어서 나머지 API를 요청하면 됩니다.
#### 회원가입 : POST http://localhost:8080/members/signup

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

#### 로그인 : POST http://localhost:8080/members/login

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

#### 새로운 게시글 작성 : POST http://localhost:8080/bullet/new

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

**RESPONSE**

{
    "title": String,
    "content": String,
    "member": String
}
<img width="1077" alt="image" src="https://github.com/Jake-huen/wanted-pre-onboarding-backend/assets/57055730/2387bc18-0864-4945-ac94-28d61ff0f3b3">

#### 게시글 목록 조회 : GET http://localhost:8080/bullet/all

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

#### 특정 게시글 조회 : GET http://localhost:8080/bullet?id=

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

#### 특정 게시글 수정 : POST http://localhost:8080/bullet/edit?id=

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

#### 특정 게시글 삭제 : POST http://localhost:8080/bullet?id=
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

### 데이터베이스 테이블 구조

### 구현한 API의 동작을 촬영한 데모 영상 링크

### 구현 방법 및 이유에 대한 간략한 설명

### API 명세(request/response) 포함
