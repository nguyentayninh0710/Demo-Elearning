package com.myclass.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;



import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,RedirectAttributes redirect ) {
		request.getSession().setAttribute("userlist", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/user/page/1";
	}
	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showUserPage(HttpServletRequest request, 

			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession()
				.getAttribute("userlist");
		int pagesize = 5;
		List<UserDto> list =userService.getAll();
		System.out.println(list.size());
		if (pages == null) {
			pages = new PagedListHolder<>(list);
			pages.setPageSize(pagesize);
		} else {
			final int goToPage = pageNumber - 1;
			if (goToPage <= pages.getPageCount() && goToPage >= 0) {
				pages.setPage(goToPage);
			}
		}
		request.getSession().setAttribute("userlist", pages);
		int current = pages.getPage() + 1;		
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 9, pages.getPageCount());	
		int totalPageCount = pages.getPageCount();
		if ( current>=end) {
			current = pages.getPage() + 1;
			begin = Math.max(current - end+1, current - list.size());
			end = Math.min(current, pages.getPageCount());
			//totalPageCount = pages.getPageCount();
		}
		String baseUrl = "/user/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("users", pages);
		return "user/index";
	} 
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		List<RoleDto> list = roleService.getAll();
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		model.addAttribute("list", list);
		return "user/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("user") UserDto user, Model model, 
			 @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		user.setAvatar(fileName);
		
		ServiceInfo info = userService.add(user);
		if(info.isStatus()==false) {
			model.addAttribute("message", info.getMessage());
			return "user/add";
		}
		
		String uploadDir = "./user-photos/" +info.getId();
		Path uploadPath = Paths.get(uploadDir);
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try(InputStream inputStream = multipartFile.getInputStream()){
		Path filePath = uploadPath.resolve(fileName);
		System.out.println(filePath.toFile().getAbsolutePath());
		Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Không save được" + fileName);
		}
		
		return "redirect:/user";
	}
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Model model) {
		UserDto user = userService.getById(id);
		List<RoleDto> list = roleService.getAll();
		model.addAttribute("user", user);
		model.addAttribute("roles", list);
		return "user/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("user") UserDto user,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		String fileName = "";
		fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		if (fileName.equals("")) {
			userService.edit(user.getId(), user);
			return "redirect:/user";
		}
		else {
			user.setAvatar(fileName);
			int id = user.getId();
			userService.edit(user.getId(), user);
			String uploadDir = "./user-photos/" +id;
			Path uploadPath = Paths.get(uploadDir);
			if(!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try(InputStream inputStream = multipartFile.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Không save được" + fileName);
			}
			return "redirect:/user";
		}
		
		
	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("id") int id) {
		UserDto user = userService.getById(id);
		try {
			userService.delete(user.getId());
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Người dùng không thể xóa");
		return "redirect:/user";
	}
	
}
