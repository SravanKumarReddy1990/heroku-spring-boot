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
import com.github.britter.springbootherokudemo.email.EmailUtil;
import com.github.britter.springbootherokudemo.model.RegistrationForm;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.sql.Blob;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Controller
public class WebController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value="/")
    public String home(){
        return "home";
    }
   
    @RequestMapping(value="/user")
    public String user(){
        return "user";
    }
	@RequestMapping(value = "/getStudentPhoto/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") String id) throws Exception {
		response.setContentType("image/jpeg");

		String query = "select yourphoto from user_reg where username=?";

		Blob ph = jdbcTemplate.queryForObject(query, new String[] { id }, Blob.class);


		byte[] bytes = ph.getBytes(1, (int) ph.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
   @PostMapping(value="/registration")
    public String registration(@ModelAttribute RegistrationForm form,Map<String, Object> model,HttpSession session){
	//System.out.println(form.getEmailsignup());
       // System.out.println(form.getAlbums());
try {
		String sql = "INSERT INTO user_reg (username,email,yourphoto) values (?,?,?)";
String loginsql = "INSERT INTO users (username,password,enabled) values (?,?,TRUE)";
String loginrolesql = "INSERT INTO user_roles (username,role) values (?,'ROLE_USER')";
		int random = (int)(Math.random() * 50 + 1);
		
		jdbcTemplate.update(sql, form.getUsernamesignup(),form.getEmailsignup(),form.getYourfile().getBytes());
		jdbcTemplate.update(loginsql, form.getUsernamesignup(),form.getPasswordsignup());
		jdbcTemplate.update(loginrolesql, form.getUsernamesignup());
		
		//return "Inserted Successfull";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//return "Registeration Failed";
		}
        return "login";
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

    @RequestMapping(value="/profile")
    public String profile(HttpServletRequest request,Map<String, Object> model){
        String userid=request.getUserPrincipal().getName();
        model.put("userid", userid);
        return "profile";
    }
    @RequestMapping(value="/comment")
    public String comment(HttpServletRequest request,Map<String, Object> model){
        String userid=request.getUserPrincipal().getName();
        String name=request.getParameter("name");
        String email=request.getParameter("email");
        String message=request.getParameter("message");
try{
        EmailUtil.generateAndSendEmail(email,"From :"+name,message);
        //model.put("userid", userid);
}catch(Exception e){
System.out.println(e+"");
}
        model.put("userid", userid);
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
