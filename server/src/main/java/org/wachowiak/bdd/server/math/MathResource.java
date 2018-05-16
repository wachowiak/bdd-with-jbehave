package org.wachowiak.bdd.server.math;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.wachowiak.bdd.server.exception.BadRequestException;

import static org.wachowiak.bdd.common.math.Constants.*;

@Controller
@RequestMapping(ENDPOINT_MATH)
class MathResource {

    @GetMapping(ENDPOINT_ADD)
    @ResponseBody
    public long add(@RequestParam(value = PARAM_A) long a, @RequestParam(value = PARAM_B) long b){
        return a + b;
    }

    @GetMapping(ENDPOINT_SUB)
    @ResponseBody
    public long sub(@RequestParam(value = PARAM_A) long a, @RequestParam(value = PARAM_B) long b){
        return a - b;
    }

    @GetMapping(ENDPOINT_MUL)
    @ResponseBody
    public long mul(@RequestParam(value = PARAM_A) long a, @RequestParam(value = PARAM_B) long b){
        return a * b;
    }

    @GetMapping(ENDPOINT_DIV)
    @ResponseBody
    public long div(@RequestParam(value = PARAM_A) long a, @RequestParam(value = PARAM_B) long b){
        if(b == 0){
            throw new BadRequestException("Division by 0");
        }
        return a / b;
    }

}
