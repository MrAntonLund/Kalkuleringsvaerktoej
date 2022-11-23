package com.examprojectsummer2021.controllers;

import com.examprojectsummer2021.services.ValidateLoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Carsten
 */

@Controller
public class LoginController {

    ValidateLoginService loginService = new ValidateLoginService();


    @GetMapping(value = {"/", "/index"})
    public String renderIndex(){

        return "redirect:/view/dashboard";
    }


    @GetMapping("/login")
    public String renderLogin(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();


        //<editor-fold desc="Error message for login screen">
        boolean loginFailed = false;
            if (session.getAttribute("loginFail") != null){
                loginFailed = (boolean) session.getAttribute("loginFail");
            }

        model.addAttribute("loginFailed", loginFailed);
        //</editor-fold>

        return "login.html";
    }

    @PostMapping("/validateLogin")
    public String validateLogin(@RequestParam(name = "username") String username,
                                @RequestParam(name = "password") String password,
                                HttpServletRequest request){

        HttpSession session = request.getSession();

        boolean validLogin = loginService.validateLogin(username, password);

        if (validLogin){
            session.setAttribute("username", username);
            session.setAttribute("loginFail", false);
            return "redirect:/dashboard";
        }

        session.setAttribute("loginFail", true);
        return "redirect:/login";
    }

}
