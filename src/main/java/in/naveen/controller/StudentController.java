package in.naveen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.naveen.entity.Filterdata;
import in.naveen.entity.Student;
import in.naveen.service.StudentService;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;

	@Autowired
	private HttpSession session;

	@GetMapping("/student")
	public String AddStudent(Model model) {
		model.addAttribute("student", new Student());
		return "addstudent";
	}

	@PostMapping("/add")
	public String AddStudent(@ModelAttribute("student") Student student, Model model) {
		Integer object = (Integer) session.getAttribute("cid");
		if (object == null) {
			return "redirect:/logout";
		}
		System.out.println(object);
		student.setCid(object);
		boolean status = service.saveStudent(student);

		if (status) {
			model.addAttribute("success", "Enquiry is submited..");
		} else {
			model.addAttribute("failed", "failed to add");
		}
		return "addstudent";
	}

	@GetMapping("/view")
	public String ViewStudent(Model model) {
		Integer object = (Integer) session.getAttribute("cid");
		if (object == null) {
			return "redirect:/logout";
		}
		model.addAttribute("filter", new Filterdata());
		List<Student> list = service.getStudents(object);
		model.addAttribute("StudentList", list);
		return "viewStudent";
	}

	@PostMapping("/filterData")
	public String addNumbers(@ModelAttribute("filter") Filterdata filter, Model model) {
		System.out.println(filter);
		Integer id = (Integer) session.getAttribute("cid");
		if (id == null) {
			return "redirect:/logout";
		}
		List<Student> list = service.Filter(filter, id);
		model.addAttribute("StudentList", list);
		return "filterview";
	}

}
