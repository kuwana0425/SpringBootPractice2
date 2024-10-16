package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminRegisterController {

    @Autowired
    private AdminService adminService;

    // 新規登録画面
    @GetMapping("/admin/signup")
    public String signup(Model model) {
        model.addAttribute("adminForm", new AdminForm());
        return "signup";
    }

    // 新規登録後、確認画面へ
    @PostMapping("/admin/signup")
    public String performSignup(@Validated @ModelAttribute("adminForm") AdminForm adminForm, BindingResult errorResult, HttpServletRequest request) {
        if (errorResult.hasErrors()) {
            return "signup";
        }
        HttpSession session = request.getSession();
        session.setAttribute("adminForm", adminForm);
        return "redirect:/signup/confirm";
    }

    // 確認画面
    @GetMapping("/signup/confirm")
    public String confirm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");
        model.addAttribute("adminForm", adminForm);
        return "adminconfirmation";
    }

    // 登録アクション
    @PostMapping("/signup/register")
    public String register(HttpServletRequest request) {
    	
        HttpSession session = request.getSession();
        AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");
        
        adminService.saveAdmin(adminForm);
        
        return "redirect:/signup/complete";
    }

    // 登録完了画面
    @GetMapping("/signup/complete")
    public String complete(Model model,HttpServletRequest request) {
    	
    	if (request.getSession(false) == null) {
            return "redirect:/signup";
          }
    	HttpSession session = request.getSession();
        AdminForm adminForm = (AdminForm) session.getAttribute("adminForm");
        model.addAttribute("adminForm", adminForm);

        session.invalidate();
        
        return "admincompletion";
    }
}