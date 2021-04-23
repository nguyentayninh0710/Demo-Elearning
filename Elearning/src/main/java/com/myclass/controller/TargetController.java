package com.myclass.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CoursesDto;

import com.myclass.dto.TargetsDto;

import com.myclass.service.CoursesService;
import com.myclass.service.TargetService;

@Controller
@RequestMapping("targets")
public class TargetController {
	@Autowired
	private TargetService targetService;
	
	@Autowired
	private CoursesService coursesService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model,HttpServletRequest request,RedirectAttributes redirect ) {
		request.getSession().setAttribute("targetlist", null);
		if(model.asMap().get("success") != null)
			redirect.addFlashAttribute("success",model.asMap().get("success").toString());
		return "redirect:/targets/page/1";
	}
	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showUserPage(HttpServletRequest request, 

			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession()
				.getAttribute("targetlist");
		int pagesize = 5;
		List<TargetsDto> list =targetService.getAll();
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
		request.getSession().setAttribute("targetlist", pages);
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
		String baseUrl = "/targets/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("targets", pages);
		return "targets/index";
	} 
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		List<CoursesDto> list = coursesService.getAll();
		TargetsDto target = new TargetsDto();
		model.addAttribute("target", target);
		model.addAttribute("list", list);
		return "targets/add";
	}
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("target") TargetsDto target, Model model){		
		ServiceInfo info = targetService.add(target);
		if(info.isStatus()==false) {
			model.addAttribute("message", info.getMessage());
			return "targets/add";
		}
		return "redirect:/targets";
	}
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Model model) {
		TargetsDto target = targetService.getById(id);
		List<CoursesDto> list = coursesService.getAll();
		model.addAttribute("target", target);
		model.addAttribute("courses", list);
		return "targets/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("target") TargetsDto target) {
		targetService.edit(target.getId(), target);
		return "redirect:/targets";

	}
	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("id") int id) {
		TargetsDto target = targetService.getById(id);
		try {
			targetService.delete(target.getId());
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Người dùng không thể xóa");
		return "redirect:/targets";
	}
}
