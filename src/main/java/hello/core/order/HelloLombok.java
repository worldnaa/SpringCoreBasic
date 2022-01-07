package hello.core.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("홍길동");
        helloLombok.setAge(30);

        System.out.println("helloLombok = " + helloLombok);
    }
}
