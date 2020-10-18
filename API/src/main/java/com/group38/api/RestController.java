package com.group38.api;

import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping(
            path = "/home",
            produces = "application/json"
    )
    public String home(){
        return "Hello, Welcome to ship api";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/userLogin",
            produces = "application/json"
    )
    public String userLogin(@RequestParam("userId") int id , @RequestParam("pass") String pass){
        db db =new db();
        String status = db.checkUserLogin(id , '"'+pass+'"');
        return "{ 'status' = \""+status +"\"}";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/adminLogin",
            produces = "application/json"
    )
    public String adminLogin(@RequestParam("userId") int id , @RequestParam("pass") String pass){
        db db =new db();
        String status = db.checkAdminLogin(id , '"'+pass+'"');
        return "{ 'status' = \""+status +"\"}";
    }


}
