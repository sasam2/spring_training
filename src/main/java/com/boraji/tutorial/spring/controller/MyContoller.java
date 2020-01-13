package com.boraji.tutorial.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value="/login")
	public String login(Model model, Principal principal, HttpServletResponse httpServletResponse) {
		if(principal != null) {
			httpServletResponse.setHeader("Location", "logout");
		    httpServletResponse.setStatus(302);
		}
	    return "login";
	}
  
  	@RequestMapping(value = "/post_view", method = RequestMethod.GET)
  	public String post_view(@RequestParam(name="lastPost", required=false) String id, Model model, Principal principal,  HttpServletResponse httpServletResponse) {
  		
  		if(principal != null)
  			model.addAttribute("loggedInUser", principal.getName());
  		//PostJDBCTemplate pJdbcTempl = (PostJDBCTemplate)context.getBean("postJDBCTemplate");
  		
  		if(id==null) {
  			int maxId = pJdbcTempl.getPostLatestId();
  			List<Post> ps = pJdbcTempl.listPostsLessThanId(maxId, 2);
  	  		
  			model.addAttribute("posts", ps);
  			return "postsListDisplay";
  		} else {
  			int lastPostId = Integer.parseInt(id);
  			List<Post> ps = pJdbcTempl.listPostsLessThanId(lastPostId-1, 2);  			
  			
  			//TODO convert to json
  			/*"\"[{\\\"title\\\": \\\"123\\\", \\\"photo\\\": \\\"/\\\", \\\"author\\\": \\\"tete\\\", 
  			 * \\\"content\\\": \\\"4566\\\", \\\"date\\\": \\\"2020-01-04 16:45:09\\\", \\\"id\\\": 18}, {\\\"title\\\": \\\"344\\ */
  			String jsonArr = "[";
  			for(int i = 0; i < ps.size(); i++) {
  				Post p = ps.get(i);
  				
  				if(i != 0)jsonArr+=", ";
  				String jsonObj = "{";
	  			jsonObj+="\"title\": \""+p.getTitle()+"\", ";
	  			jsonObj+="\"photo\": \""+p.getPhoto()+"\", ";
	  			jsonObj+="\"author\": \""+p.getAuthorName()+"\", ";
	  			jsonObj+="\"content\": \""+p.getContent()+"\", ";
	  			jsonObj+="\"date\": \""+p.getDate()+"\", ";
	  			jsonObj+="\"id\": \""+p.getId()+"\"";
	  			jsonObj+="}";
	  			
	  			jsonArr+=jsonObj;
  			}
  			jsonArr+="]";
  			
  			
  			httpServletResponse.setContentType("application/json");
  			httpServletResponse.setCharacterEncoding("UTF-8");
  			
  			PrintWriter wr;
			try {
				wr = httpServletResponse.getWriter();
				wr.write(jsonArr);
				//wr.write("{\"hello\": \"world\"}");
	  			wr.close();
	  			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  			
  			
  			return null; 			
  		}
  		//List<Post> ps = pJdbcTempl.listPosts();
  		//List<Post> ps = pJdbcTempl.listPostsGreaterThanId(maxId, 2);
  		
  	}
  	/*
  	@RequestMapping(value = "/post_view", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  	@ResponseBody
  	public String post_view_params(@RequestParam(name="id") String id, Model model, Principal principal,  HttpServletResponse httpServletResponse) {
  		return "{\"hello\": \"world\"}";
  	}*/
  	
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
	public String signup_form(Model model, Principal principal, HttpServletResponse httpServletResponse) {
		if(principal != null) {
			httpServletResponse.setHeader("Location", "logout");
		    httpServletResponse.setStatus(302);
		}
  		
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
