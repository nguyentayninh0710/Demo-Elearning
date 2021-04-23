package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CategoriesDto;
import com.myclass.dto.RoleDto;
import com.myclass.entity.Categories;
import com.myclass.entity.Role;
import com.myclass.repository.CategorieRepository;
import com.myclass.service.CategorieService;

@Service
public class CategorieServiceImpl implements CategorieService{

	@Autowired
	private CategorieRepository categorieRepository;

	@Override
	public List<CategoriesDto> getAll() {
		List<CategoriesDto> dtos = new ArrayList<CategoriesDto>();
		List<Categories> categories = categorieRepository.findAll();
		for (Categories categorie : categories) {
			dtos.add(new CategoriesDto(categorie.getId(), categorie.getTitle(), categorie.getIcon()));
		}
		return dtos;
	}

	@Override
	public CategoriesDto getById(int id) {
		Categories categorie = categorieRepository.findById(id).get();
		return entityToDto(categorie);
	}
	private CategoriesDto entityToDto(Categories entity) {
		return new CategoriesDto(entity.getId(), entity.getTitle(), entity.getIcon());
	}

	@Override
	public ServiceInfo add(CategoriesDto dto) {
		int count = categorieRepository.countByTitle(dto.getTitle());
		if(count > 0) {
			return new ServiceInfo(false, "Tên đã sử dụng");
		}
		try {
			Categories categorie = new Categories();
			categorie.setId(dto.getId());
			categorie.setTitle(dto.getTitle());
			categorie.setIcon(dto.getIcon());
			categorieRepository.save(categorie);
			System.out.println("--IMPL id--" + categorie.getId());
			return new ServiceInfo(true, "Thêm mới thành công!", categorie.getId());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(int id, CategoriesDto dto) {
		Optional<Categories> optional = categorieRepository.findById(id);
		if(optional.isPresent()==false) return false;
		Categories categorie = optional.get();
		categorie.setId(dto.getId());
		categorie.setTitle(dto.getTitle());
		categorie.setIcon(dto.getIcon());
		categorieRepository.save(categorie);
		return true;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		categorieRepository.deleteById(id);
	}

	
}
