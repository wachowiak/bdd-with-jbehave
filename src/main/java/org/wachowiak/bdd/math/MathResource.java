package org.wachowiak.bdd.math;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
class MathResource {

    @RequestMapping(value = "/math/add", method = RequestMethod.GET)
    @ResponseBody
    public int add(@RequestParam(value = "a") int a, @RequestParam(value = "b") int b){
        return a + b;
    }
}
