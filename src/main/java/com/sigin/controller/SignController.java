package com.sigin.controller;

import com.sigin.domain.User;
import com.sigin.service.UserService;
import com.sigin.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dam on 14-5-20.
 */
@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/to-sign")
    public String toSign(){
        String result = "welcome";
        return result;
    }

    @RequestMapping(value = "/check-user")
    public String checkUser(HttpServletRequest request,Model model){
        String result = "";
        String userId = CookieUtil.findCookie(request, "qmUserId");
        if(StringUtils.isBlank(userId)){
            result = "step1";
        }else{
            model.addAttribute("userId",userId);
            result = "sign";
        }
        return result;
    }
    @RequestMapping(value = "/to-step2",method = RequestMethod.POST)
    public String toStep2(User user,Model model,HttpServletResponse response){
        String result = "step2";
        User queryUser = userService.saveUserNameAndPhone(user);
        CookieUtil.addCookie(response,"qmUserId",queryUser.getUserId());
        model.addAttribute("user",queryUser);
        return result;
    }
    @RequestMapping(value = "/to-step2/{userId}")
    public String toStep2(@PathVariable String userId,Model model){
        String result = "step2";
        User user = userService.findUserById(userId);
        model.addAttribute("user",user);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/save-user",method = RequestMethod.POST)
    public String saveUser(User user){
        String result = "success";
        User queryUser = userService.updateUser(user);
        return result;
    }


}
