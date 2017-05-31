package pl.lborowy.com.springTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by RENT on 2017-05-31.
 */
@Controller
public class HelloController {
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    @ResponseBody
    public String hello() {
        System.out.println("GET /hello");
        return "Hello Łukasz Borowy";
    }
    // localhost:9000/hello

    @RequestMapping(method = RequestMethod.GET, value = "/hello2/{name}") // przekazywanie
    @ResponseBody // ciało odpowiedzi
    public String helloPathParam(@PathVariable String name) { // adnotacja do przekazywania
        return "Hello " + name;
    }
    //localhost:9000/hello2/boro
    // niewygodnie się używa, kiedy jest kilka parametrów

    @RequestMapping(method = RequestMethod.GET, value = "/hello3")
    @ResponseBody
    public String helloRequestParam(@RequestParam String name) {
        return "Hello " + name;
    }
    // localhost:9000/hello3?name=boro

    @RequestMapping(method = RequestMethod.GET, value = "/hello4")
    @ResponseBody
    public String helloRequestParam(@RequestParam String name, @RequestParam String surname) {
        return "Hello " + name + " " + surname;
    }
    // localhost:9000/hello4?name=boro&surname=boro
}
