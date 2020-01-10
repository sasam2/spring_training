package com.boraji.tutorial.spring.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.boraji.tutorial.spring.dao.Post;
import com.boraji.tutorial.spring.dao.PostJDBCTemplate;
import com.boraji.tutorial.spring.dao.UserInfo;

@Controller
public class MyContoller {
	
	@Autowired
	PostJDBCTemplate pJdbcTempl;

	@GetMapping("/")
	public String index(Model model, Principal principal) {
	    model.addAttribute("message", "You are logged in as " + principal.getName());
	    return "index";
	}
  
  	@RequestMapping(value = "/post_view", method = RequestMethod.GET)
  	public String post_view(Model model, Principal principal) {
  		
  		if(principal != null)
  			model.addAttribute("loggedInUser", principal.getName());
  		//PostJDBCTemplate pJdbcTempl = (PostJDBCTemplate)context.getBean("postJDBCTemplate");
  		List<Post> ps = pJdbcTempl.listPosts();
  		List<UserInfo> ui = pJdbcTempl.listUsers();
  		
  		for(Post p: ps) {
  			int author_id = p.getAuthorId();
  			for(UserInfo u: ui) {
  				if(u.getId()==author_id) {
  					p.setAuthorName(u.getUsername());
  					break;
  				}
  			}
  		}
  		model.addAttribute("posts", ps);
  		return "postsListDisplay";
  	}
  	
  	@RequestMapping(value = "/post_create", method = RequestMethod.GET)
	public String post_create(Model model, Principal principal) {
  		if(principal != null)
  			model.addAttribute("loggedInUser", principal.getName());
		return "postCreate";
	}  	
  
	@RequestMapping(value = "/post_create", method = RequestMethod.POST)
	@ResponseBody
	public void post_create(@ModelAttribute("post") Post post, HttpServletResponse httpServletResponse) {
		//System.out.println("NEW POST: "+post.getId()+" "+post.getTitle());
		//PostJDBCTemplate pJdbcTempl = (PostJDBCTemplate)context.getBean("postJDBCTemplate");
		User principal = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = principal.getUsername();
		UserInfo u = pJdbcTempl.getUser(username);
		int rAff = pJdbcTempl.addPost(post, u.getId());
		//System.out.println("FORM ITEMS: "+post.getTitle()+" rAff="+rAff); //request.size()); //getParameter("title"));  //.size());
		
		httpServletResponse.setHeader("Location", "post_view");
	    httpServletResponse.setStatus(302);
	}
  
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, Principal principal) {
  		if(principal != null)
  			model.addAttribute("loggedInUser", principal.getName());
		model.addAttribute("message", "You are logged in as " + principal.getName());
	    return "logout";
	}  	
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup_form(Model model, Principal principal) {
		if(principal != null)
  			model.addAttribute("loggedInUser", principal.getName());
  		
		return "signup";
	} 
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public void signup(UserInfo  user, HttpServletResponse httpServletResponse) {
		
		pJdbcTempl.addUser(user);
		httpServletResponse.setHeader("Location", "post_view");
	    httpServletResponse.setStatus(302);
	} 
	
}
