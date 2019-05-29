package idu.cs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import idu.cs.domain.User;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;

@Controller
public class UserController {
	@Autowired UserRepository userRepo; // Dependency Injection
	//UserRepository userRepo=new UserRepositoryImpl();
	//HTTP URI
	//create=POST
	//read=GET
	//update=PUT
	//delete=DELETE
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("test", "인덕 컴소");
		model.addAttribute("egy", "유응구");
		return "index";
	}
	@GetMapping("/user-login-form")
	public String getLoginForm(Model model) {
		return "login";
	}
	@PostMapping("/login")
	public String loginUser(@Valid User user,HttpSession session, Model model) {
		User sessionUser= userRepo.findByUserId(user.getUserId());
		if(sessionUser==null) {
			System.out.println("ID ERROR : ");
			return "redirect:/user-login-form";
		}
		if(!sessionUser.getUserPw().equals(user.getUserPw())) {
			System.out.println("PW ERROR : ");
			return "redirect:/user-login-form";
		}
		//userRepo.save(user);
		session.setAttribute("user", sessionUser);
		return "redirect:/";
	}
	@GetMapping("/user-register-form")
	public String getRegForm(Model model) {
		return "register";
	}
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "userlist";
	}
	@PostMapping("/users")
	public String createUser(@Valid User user, Model model) {
		//user.setName("kjy");
		//user.setCompany("idu");
		if(userRepo.save(user)!=null) 
			System.out.println("DB등록");
		else
			System.out.println("DB등록실패");
		model.addAttribute("users", userRepo.findAll());
		return "redirect:/users";
	}
	@GetMapping("/users/{id}")
	public String getUserById(@PathVariable(value = "id") Long userId, Model model)
			throws ResourceNotFoundException {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		//model.addAttribute("id",user.getId());
		//model.addAttribute("name", user.getName());
		//model.addAttribute("company", user.getCompany());
		model.addAttribute("user",user);
		return "user"; 
		//return ResponseEntity.ok().body(user);
	}
	@GetMapping("/users/fn")
	public String getUserByName(@Param(value = "name") String name, Model model)
			throws ResourceNotFoundException {
		List<User> users = userRepo.findByName(name);//.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		model.addAttribute("users",users);
		return "userlist";
		//return ResponseEntity.ok().body(user);
	}
	@PutMapping("/users/{id}") //@patchMapping-특수목적으로 성능향상을 위해 쓰는 매핑
	public String updateUser(@PathVariable(value="id") Long userId,@Valid User userDetails, Model model) {
		User user=userRepo.findById(userId).get();//DB로 부터 읽어옴
		
		user.setName(userDetails.getName());//userDetails가 전송한 객체
		user.setCompany(userDetails.getCompany());
		userRepo.save(user);
		return "redirect:/users";
	}
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable(value="id") Long userId, Model model) {
		User user=userRepo.findById(userId).get();
		userRepo.delete(user);
		model.addAttribute("name", user.getName());
		return "user-deleted";
	}
}
