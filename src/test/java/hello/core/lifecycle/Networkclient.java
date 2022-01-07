package hello.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct; //javax로 시작하면 java 진영에서 공식적으로 지원하는 것
import javax.annotation.PreDestroy;    //그러므로 다른 컨테이너를 사용해도 해당 기능 사용 가능!

//public class Networkclient implements InitializingBean, DisposableBean {
public class Networkclient {

    private String url;

    public Networkclient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

//    @Override //스프링 의존관계 주입 끝난 후 실행된다
//    public void afterPropertiesSet() throws Exception {
    @PostConstruct
    public void init() {
        System.out.println("Networkclient.init");
        connect();
        call("초기화 연결 메시지");
    }

//    @Override
//    public void destroy() throws Exception {
    @PreDestroy
    public void close() {
        System.out.println("Networkclient.close");
        disconnect();
    }
}
