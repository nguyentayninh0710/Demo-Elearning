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

import com.myclass.service.RoleService;
import com.myclass.common.ServiceInfo;
import com.myclass.dto.*;

@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("rolelist", null);
		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/role/page/1";
	}

	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showUserPage(HttpServletRequest request,

			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("rolelist");
		int pagesize = 10;
		List<RoleDto> list = roleService.getAll();
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
		request.getSession().setAttribute("rolelist", pages);
		int current = pages.getPage() + 1;
		int begin = Math.max(1, current - list.size());
		int end = Math.min(begin + 9, pages.getPageCount());
		int totalPageCount = pages.getPageCount();
		if (current >= end) {
			current = pages.getPage() + 1;
			begin = Math.max(current - end + 1, current - list.size());
			end = Math.min(current, pages.getPageCount());
			// totalPageCount = pages.getPageCount();
		}
		String baseUrl = "/role/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("roles", pages);
		return "role/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		RoleDto role = new RoleDto();
		model.addAttribute("role", role);
		return "role/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("role") RoleDto role, Model model) {
		ServiceInfo info = roleService.add(role);
		if (info.isStatus() == false) {
			model.addAttribute("message", info.getMessage());
			return "role/add";
		}
		return "redirect:/role";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(ModelMap model, @RequestParam("id") int id) {
		RoleDto role = roleService.getById(id);

		model.addAttribute("role", role);
		
		return "role/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("role") RoleDto role) {
		roleService.edit(role.getId(), role);		
		return "redirect:/role";
	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("id") int id) {
		RoleDto role = roleService.getById(id);
		try {
			roleService.delete(role.getId());

		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/role";
	}
}
