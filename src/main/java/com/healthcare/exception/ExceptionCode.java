package com.healthcare.exception;


import lombok.Getter;

/*
HTTP 상태 코드 (4XX 코드 , 5XX 코드) 개념
     4xx 코드 - Client errors - 클라이언트의 요청이 유효하지 않아 서버가 해당 요청을 수행하지 않았다는 의미

     400 - Bad Request: 클라이언트의 요청이 유효하지 않아 더 이상 작업을 진행하지 않는 경우를 의미.
     401 - Unauthorized : 클라이언트의 권한이 없기 때문에 작업을 진행할 수 없는 경우를 의미.
     403 - Forbidden: 클라이언트가 권한이 없기 때문에 작업을 진행할 수 없는 경우를 의미.
     404 - Not Found: 클라이언트가 요청한 자원이 존재하지 않는다는 경우를 의미.
     405 - Method Not Allowed: 클라이언트의 요청이 허용되지 않는 메소드인 경우를 의미.
     409 - Conflict: 클라이언트의 요청이 서버의 상태와 충돌이 발생한 경우를 의미.
     429 - Too Many Requests: 클라이언트가 일정 시간 동안 너무 많은 요청을 보낸 경우를 의미.

     429 code - ex 1) 기밀성에 대한 공격
     - 해커는 사용자의 비밀번호를 알아내기 위해 POST/login API에 password를 무차별로 대입하면서 요청할 수 있다.
     - 서버 입장에선 자원의 기밀성(Confidentiality) 피해를 입을 수 있는 공격이면서, 이러한 무차별 요청으로 다른 요청을 처리할 수 없거나
     처리가 늦을 수 있는 가용성(Availability)에 피해를 입을 수 있다.
     - 서버는 이러한 공격에 대비해 인증 API의 경우 각 클라이언트는 N 시간 동안 N 회만 요청 가능하다는
     룰을 정하고 이것을 초과하면 429 상태코드를 응답해야 한다.

     429 code - ex 2) 가용성에 대한 공격
     - 해커는 시스템에 과부하를 주기 위해 특정 API에 지속적으로 요청을 보낼 수 있다. 해커의 비정상적인 요청으로 인해 실제로 서비스를
     받아야 할 정상적인 사용자가 서비스를 받지 못하는 가용성(Availability)에 피해를 입을 수 있다.
     - 서버는 이러한 공격에 대비해 클라이언트의 요청에 대해 n 시간 동안 n회 이상 요청 한다면 그 이후의 요청은 429 상태 코드로 응답해야 한다.

    5xx 코드 - Server errors - 서버 오류로 인해 요청을 수행할 수 없다는 의미.

    500 - Internal Server Error - 서버 내부 문제 발생
    서버 사용량의 폭주로 인해 서비스가 일시적으로 중단되거나, 스크립트의 오류, 웹 서버 문제 등 다양한 원인으로 에러가 발생한다.
    501 - Not Implemented - 요청에 대해 구현되지 않아 수행하지 아니한다.
    클라이언트가 서버의 능력을 넘은 요청을 했을 때, 서버가 기능을 지원하지 않음을 나타내는 것입니다. 추후에 기능이 개발되면 지원한다는 의미.

    502 - Bad Gateway - 게이트웨이가 잘못되어, 서버가 잘못된 응답을 수신함을 의미합니다.
    - 서로 다른 프로토콜을 연결해주는 장치가 잘못된 프로토콜을 연결하거나 어느 쪽에 문제가 있어 통신이 제대로 되지 않을 때 발생된다.
    - 접속이 폭주하는 원인으로 서버에서 통신장애가 발생하였을 경우, 인터넷상의 서버가 다른 서버로부터 유효하지 않은 응답을 받은 경우,
    - 사용자 브라우저에 이상이 있거나, 잘못된 네트워크 연결 혹은 설정 등을 했을 때 발생됩니다.

    알아두기)
    1. 검색 봇 및 기타 크롤러가 사이트를 방문하는 속도에 영향을 줄 수 있습니다.
    - 502 상태코드를 반환하는 동안 서버가 오랫동안 다운된 상태라면 사이트의 검색 순위에 영향을 줄 수 있다.
    - 따라서 사이트가 일시적으로 다운된 경우에는 502 대신 503 상태코드를 반환하는 것이 좋습니다.

    2. Gateway - 서로 다른 네트워크(영역)으로 접근 하기 위한 출입문과 같은 역할로 보면 된다.
    - 일상생활에서 보면 노트북, 스위치 허브와 같은 장비로 인터넷을 사용하기 위해서는 ip 주소가 필요하다.
    - ip 주소를 가지고 외부와 통신하기 위해서 gateway 를 통해서 통신한다.

    503 - Service Unavailable - 서비스 이용 불가 (일시적)
    - 지금 서버가 요청을 처리해 줄 수 없지만, 나중에 가능함을 의미하고자 할때 응답된다.
    - 갑작스러운 트래픽 급증으로 서버가 과부하되거나 특정 시간대에 서버 패치 및 업데이트 등 다양한 작업을 수행하기 위해
    서버 다운을 시켰거나, 서버가 재부팅되거나 방화벽 설정에 잘못된 구성이 있거나 등 다양한 원인으로 일어나는 것이다.
    서버가 언제 그 리소스를 사용할 수 있게 될지 알고 있다면, 서버는 Retry-after 헤더를 응답에 포함시켜 언제 그 리소스를
    사용할 수 있는지에 대한 안내를 클라이언트에게 해줄 수 있다. 502 코드와 달리 웹 크롤러의 검색 순위에 영향을 미치지 않는다.

    504 - Gateway Timeout - 게이트웨이 시간 초과로 서버에서 요청을 처리하지 아니하고 연결을 닫음.
    - 클라이언트가 서버에게 요청을 할 때 서버에서 갑작스러운 트래픽 급증으로 서버가 과부하 되어 일시적인 문제로 인해
    리소스를 일시적으로 처리할 수 없다. 일정시간 뒤에 요청하라고 안내하는 것과 같다.

    505 - Http Version Not Supported - 서버에서 지원되지 않는 http 버전이라 처리 불가
    - http 버전으로 HTTP/1.0, HTTP/1.1, HTTP/2, HTTP/3 이 있다.
    - 이중 서버에서 지원하지 않는 버전의 프로토콜로 된 요청을 받았을 떄 응답된다.

    506 - Variant Also Negotiates - 콘텐츠 협상과 관련있는 상태코드, 실험적인 프로토콜이며 공식적으로 표준으로 채택하지 않은 응답 코드
    507 - Insufficient Storage - 스토리지 공간 부족, 서버에 HTTP 요청을 수용할 충분한 공간이 없음을 나타내는 응답코드입니다.
    508 - Loop Detected - 무한 루프를 감지, 서버가 요청을 처리하는 동안 무한 루프를 감지한 경우 요청을 종료한다.
    510 - Not Extended - 추가 확장이 필요함, 실험적인 프로토콜이며 공식적으로 표준으로 채택하지 않은 응답 코드
    511 - Network Authentication Required - 네트워크 인증 요구
    - 클라이언트가 네트워크 액세스를 얻으려면 인증이 필요하다는 것을 나타낸다. 보통 네트워크에 엑세스 할 때 로그인이 필요한 경우를 들 수 있다.
    - 인터넷 포털에서 WIFI 네트워크에 연결한 후 일종의 로그인을 거쳐야 함을 컴퓨터에 알리는데 사용된다.
    이 응답 코드는 원 서버에서 생성되는 것이 아니라, 네트워크에 대한 액세스를 제어하는 프록시 서버에서 생성된다.

    599 - Network Connect Timeout Error - 네트워크 연결 시간 초과 오류
    - 일부 프록시에서 사용하는 비공식 http 상태코드
    - 로컬 네트워크를 찾을 수 없거나 로컬 네트워크에 대한 HTTP 연결 시간이 초과되어 코드에서 실행한 HTTP 요청이 실패했음을 나타낼 수 있다.

    출처: https://sanghaklee.tistory.com/61 이상학의 개발블로그,
     https://inpa.tistory.com/entry/HTTP-%F0%9F%8C%90-5XX-Server-Error-%EC%83%81%ED%83%9C-%EC%BD%94%EB%93%9C-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EA%B8%B0 Inpa Dev 블로그
     ,최용준(본인)개발자 예정자의 네트워크 지식


    자세한 코드는 org.springframework.http.HttpStatus 이곳에서 참고할 수 있다. 아래와 같은 형태로 제공된다.
     public enum HttpStatus{
 CONTINUE(100, HttpStatus.Series.INFORMATIONAL, "Continue"),
    SWITCHING_PROTOCOLS(101, HttpStatus.Series.INFORMATIONAL, "Switching Protocols"),
    PROCESSING(102, HttpStatus.Series.INFORMATIONAL, "Processing"),
    CHECKPOINT(103, HttpStatus.Series.INFORMATIONAL, "Checkpoint"),
    OK(200, HttpStatus.Series.SUCCESSFUL, "OK"),
    CREATED(201, HttpStatus.Series.SUCCESSFUL, "Created"),
 }

no.3
   이 개념을 바탕으로 코드로 구현해보자.
   enum명(code, message)
----------------------------
 */
public enum ExceptionCode  {

    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(402,"Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method not Allowed"),
    CONFLICT(409, "Conflict"),
    TOO_MANY_REQUESTS(429, "Too many Requests"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504,"Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "Http Version Not Supported"),
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    LOOP_DETECTED(508, "Loop Detected"),
    NOT_EXTENDED(510, "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required"),
    NETWORK_CONNECT_TIMEOUT_ERROR(599, "Network Connect Timeout Error");

    @Getter
    private int code;
    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }




}
