package inhatc.spring.shopjin.controller;

import inhatc.spring.shopjin.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public UserDto hello(){
        UserDto userDto = new UserDto().builder().name("홍길동").age(10).build();;
//        userDto.setAge(20);
//        userDto.setName("홍길동");
        System.out.println("userDto : " + userDto);
        return userDto;
    }
}
