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
import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.service.CategorieService;

@Controller
@RequestMapping("categorie")
public class CategorieController {

	@Autowired
	private CategorieService categorieService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, RedirectAttributes redirect) {
		request.getSession().setAttribute("categorielist", null);
		if (model.asMap().get("success") != null)
			redirect.addFlashAttribute("success", model.asMap().get("success").toString());
		return "redirect:/categorie/page/1";
	}

	@RequestMapping(value = "page/{pageNumber}", method = RequestMethod.GET)
	public String showUserPage(HttpServletRequest request,

			@PathVariable int pageNumber, Model model) {
		PagedListHolder<?> pages = (PagedListHolder<?>) request.getSession().getAttribute("categorielist");
		int pagesize = 10;
		List<CategoriesDto> list = categorieService.getAll();
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
		request.getSession().setAttribute("categorielist", pages);
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
		String baseUrl = "/categorie/page/";
		model.addAttribute("beginIndex", begin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("baseUrl", baseUrl);
		model.addAttribute("categories", pages);
		return "categorie/index";
	}

	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		CategoriesDto categorie = new CategoriesDto();
		model.addAttribute("categorie", categorie);
		return "categorie/add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("categorie") CategoriesDto categorie, Model model,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		categorie.setIcon(fileName);
		System.out.println(fileName + "----" + categorie.getIcon());
		ServiceInfo info = categorieService.add(categorie);
		if (info.isStatus() == false) {
			model.addAttribute("message", info.getMessage());
			return "categorie/add";
		}
		System.out.println("info.getId() " + info.getId());
		String uploadDir = "./user-photos/" + info.getId();
		Path uploadPath = Paths.get(uploadDir);
		if (!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileName);
			System.out.println(filePath.toFile().getAbsolutePath());
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("Không save được" + fileName);
		}

		return "redirect:/categorie";
	}

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int id, Model model) {
		CategoriesDto categorie = categorieService.getById(id);

		model.addAttribute("categorie", categorie);

		return "categorie/edit";
	}

	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute("user") CategoriesDto categorie,
			@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		String fileName = "";

		fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

		System.out.println("filename " + fileName);
		if (fileName.equals("")) {
			categorieService.edit(categorie.getId(), categorie);
			return "redirect:/categorie";
		} else {
			categorie.setIcon(fileName);
			int id = categorie.getId();
			categorieService.edit(categorie.getId(), categorie);
			String uploadDir = "./user-photos/" + id;
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = multipartFile.getInputStream()) {
				Path filePath = uploadPath.resolve(fileName);
				System.out.println(filePath.toFile().getAbsolutePath());
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Không save được" + fileName);
			}
			return "redirect:/categorie";
		}

	}

	@RequestMapping(value = "delete", method = RequestMethod.GET)
	public String delete(ModelMap model, @RequestParam("id") int id) {
		CategoriesDto categorie = categorieService.getById(id);
		try {
			categorieService.delete(categorie.getId());

		} catch (Exception e) {
			// TODO: handle exception
		}
		model.addAttribute("message", "Người dùng không thể xóa");
		return "redirect:/categorie";
	}

}
