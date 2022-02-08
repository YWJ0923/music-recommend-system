package com.ywj.music_recommend_system.back.controller;

import com.ywj.music_recommend_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author ywj
 * @date 2021/11/08
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "admin/index";
    }

//    @GetMapping("/login")
//    @ResponseBody
//    public Result login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        System.out.println(username);
//        System.out.println(password);
//        Admin admin = adminService.login(username, password);
//        if (admin == null) {
//            return new Result(0, "用户名或密码错误");
//        } else {
//            return new Result(1, "登录成功");
//        }
//    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("id");
        session.removeAttribute("username");
        session.removeAttribute("errorMsg");
        return "redirect:/admin/login";
    }
}
