package in.naveen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.naveen.entity.Counselors;
import in.naveen.entity.LoginData;
import in.naveen.entity.Student;
import in.naveen.service.CouseloerService;
import in.naveen.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounselorController {

	@Autowired
	private CouseloerService service;

	@Autowired
	private StudentService stservice;

	@Autowired
	private HttpSession session;

	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/")
	public String showHome(Model model) {
		model.addAttribute("data", new LoginData());
		return "Home";
	}

	@GetMapping("/show")
	public String showRegister(Model model) {
		model.addAttribute("counselor", new Counselors());
		return "Register";
	}

	@PostMapping("/register")
	public String saveCounselor(@ModelAttribute("counselor") Counselors counselor, Model model) {
		String saveCouselor = service.SaveCouselor(counselor);
		model.addAttribute("success", saveCouselor);
		model.addAttribute("fail", saveCouselor);
		return "Register";
	}

	@PostMapping("/login")
	public String validateLogin(@ModelAttribute("data") LoginData data, Model model) {
		Counselors counselors = service.getCounselors(data);
		if (counselors != null) {
			session.setAttribute("cid", counselors.getCid());
			model.addAttribute("student", new Student());
			return "redirect:/dash";
		} else {
			model.addAttribute("failed", "invalid credentials");
		}
		return "Home";
	}

	@GetMapping("/dash")
	public String HandleDashboard(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		Integer id = (Integer) session.getAttribute("cid");
		System.out.println("cid :" + id);
		Integer totalEnq = stservice.GetTotalEnq(id);
		Integer enrollEnq = stservice.EnrollTotalEnq(id);
		Integer lostEnq = stservice.LostTotalEnq(id);

		model.addAttribute("total", totalEnq);
		model.addAttribute("enrolled", enrollEnq);
		model.addAttribute("lost", lostEnq);

		return "Dashboard";
	}

	@GetMapping("/forgotpwd")
	public String showForgotpwd(Model model) {
		model.addAttribute("pwd", new LoginData());
		return "forgot";
	}

	@PostMapping("/recoverpwd")
	public String RecoverPwd(@ModelAttribute("pwd") LoginData pwd, Model model) {
		boolean email = service.RecoverEmail(pwd.getEmail());
		if (email) {
			model.addAttribute("mail", "Password sent to your email");
		} else {
			model.addAttribute("fail", "invalid email");
		}
		return "forgot";
	}

	/*
	 * public void SendEmail(LoginData pwd) { SimpleMailMessage sem = new
	 * SimpleMailMessage(); System.out.println(pwd.getEmail());
	 * System.out.println("hello"); sem.setTo(pwd.getEmail());
	 * sem.setSubject("Registration successfylly completed..!"); sem.setText("Mr." +
	 * ", \n thanks for completing registration..Welcome to our Company..thank you!"
	 * ); jms.send(sem); }
	 */
}
