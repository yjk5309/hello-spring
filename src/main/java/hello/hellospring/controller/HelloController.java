package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // static에서 hello를 찾음!
    }

    @GetMapping("hello-mvc") // get 방식
    public String helloMvc(@RequestParam(name = "name", required = true) String name, Model model) { // 인자로 넘기는 방식
        model.addAttribute("name", name);
        return "hello-template"; // mvc 예제
    }

    @GetMapping("hello-string") // API 예제
    @ResponseBody // 응답바디에 return값을 직접 넣어주겠다는 의미
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api") // API 방식 정석
    @ResponseBody // 객체가 오면 json 방식으로 넘기기로 default
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name; // 클래스 만들고 그 클래스를 상속받아서 helloApi 생성?

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        } // private라 바로 사용할 수 없으니까 get set 같은 메서드를 통해 사용
        // 자바 bean 표준 방식 Getter Setter (property 접근 방식)
    }
}