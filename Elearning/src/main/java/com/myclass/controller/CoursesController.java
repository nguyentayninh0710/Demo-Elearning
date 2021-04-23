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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CategoriesDto;
import com.myclass.dto.CoursesDto;
import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.service.CategorieService;
import com.myclass.service.CoursesService;

@Controller
@RequestMapping("courses")
public class CoursesController {
	
	@Autowired
	private CoursesService coursesService;
	
	@Autowired
	private CategorieService categorieService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,RedirectAttributes redirect ) {
		request.getSession().setAttribute("courseslist", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/courses/page/1";
	}
	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showUserPage(HttpServletRequest request, 

			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession()
				.getAttribute("courseslist");
		int pagesize = 5;
		List<CoursesDto> list = coursesService.getAll();
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
		request.getSession().setAttribute("courseslist", pages);
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
		String baseUrl = "/courses/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("courses", pages);
		return "courses/index";
	} 
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		List<CategoriesDto> list = categorieService.getAll();
		CoursesDto courses = new CoursesDto();
		model.addAttribute("courses", courses);
		model.addAttribute("list", list);
		return "courses/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("courses") CoursesDto courses, Model model, 
			 @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String time = java.time.LocalDate.now().toString();
		courses.setLastUpdate(time);
		courses.setImage(fileName);
		System.out.println(fileName+ "----" + courses.getImage());
		ServiceInfo info = coursesService.add(courses);
		if(info.isStatus()==false) {
			model.addAttribute("message", info.getMessage());
			return "courses/add";
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
		
		return "redirect:/courses";
	}
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Model model) {
		CoursesDto courses = coursesService.getById(id);
		List<CategoriesDto> list = categorieService.getAll();
		model.addAttribute("courses", courses);
		model.addAttribute("categories", list);
		return "courses/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("courses") CoursesDto courses,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		String fileName = "";
		fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		String time = java.time.LocalDate.now().toString();
		courses.setLastUpdate(time);
		if (fileName.equals("")) {
			coursesService.edit(courses.getId(), courses);
			return "redirect:/courses";
		}
		else {
			courses.setImage(fileName);
			int id = courses.getId();
			coursesService.edit(courses.getId(), courses);
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
		CoursesDto courses = coursesService.getById(id);
		try {
			coursesService.delete(courses.getId());
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Người dùng không thể xóa");
		return "redirect:/courses";
	}

}
