/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.britter.springbootherokudemo.controller;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.io.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.britter.springbootherokudemo.util.EmailUtil;

@Controller
public class WebController {
   
    @RequestMapping(value="/")
    public String home(){
        return "home";
    }
   
    @RequestMapping(value="/user")
    public String user(){
        return "user";
    }
  
    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }
   
    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value="/alayers")
    public String alayers(){
        return "alayers";
    }
    @RequestMapping(value="/players")
    public String players(){
        return "players";
    }

    @RequestMapping(value="/chat")
    public String chat(HttpServletRequest request,Map<String, Object> model){
        String userid=request.getUserPrincipal().getName();
        model.put("userid", userid);
        return "chat";
    }
    @RequestMapping(value="/comment")
    public String chat(HttpServletRequest request,Map<String, Object> model){
        String name=request.getParamenter("name");
        String email=request.getParamenter("email");
        String message=request.getParamenter("message");
try{
        EmailUtil.generateAndSendEmail(email,"From :"+name,message);
        //model.put("userid", userid);
}catch(Exception e){}
        return "chat";
    }
    @RequestMapping(value="/glayers")
    public String glayers(){
        return "glayers";
    }
    @RequestMapping(value="/loginmultiple")
    public String loginmultiple(){
        return "loginmultiple";
    }
   
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }
}
