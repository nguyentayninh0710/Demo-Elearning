package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CoursesDto;
import com.myclass.dto.UserDto;
import com.myclass.entity.Categories;
import com.myclass.entity.Courses;
import com.myclass.entity.User;
import com.myclass.repository.CategorieRepository;
import com.myclass.repository.CoursesRepository;
import com.myclass.service.CategorieService;
import com.myclass.service.CoursesService;

@Service
public class CoursesServiceImpl implements CoursesService{

	@Autowired
	private CoursesRepository coursesRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Override
	public List<CoursesDto> getAll() {
		List<CoursesDto> dtos = new ArrayList<CoursesDto>();
		List<Courses> courses = coursesRepository.findAll();
		List<Categories> categories = categorieRepository.findAll();
		for (Courses course : courses) {
			for(Categories categorie: categories) {
				if(course.getCategoryId()==categorie.getId()) {
					dtos.add(new CoursesDto(course.getId(), 
							course.getTitle(),
							course.getImage(),
							course.getLeturesCount(),
							course.getHourCount(),
							course.getViewCount(),
							course.getPrice(),
							course.getDiscount(),
							course.getPromotionPrice(),
							course.getDescription(),
							course.getContent(),
							course.getCategoryId(),
							categorie.getTitle(),
							course.getLastUpdate()
							));
				}
					
			}

		}
		return dtos;
	}

	@Override
	public CoursesDto getById(int id) {
		// TODO Auto-generated method stub
		Courses course = coursesRepository.findById(id).get();
		return entityToDto(course);
	}
	public CoursesDto entityToDto(Courses entity) {
		return new CoursesDto(entity.getId(),
								 entity.getTitle(),
								 entity.getImage(),
								 entity.getLeturesCount(),
								 entity.getHourCount(),
								 entity.getViewCount(),
								 entity.getPrice(),
								 entity.getDiscount(),
								 entity.getPromotionPrice(),
								 entity.getDescription(),
								 entity.getContent(),
								 entity.getCategoryId(),
								 entity.getLastUpdate()
								 );
	}

	@Override
	public ServiceInfo add(CoursesDto dto) {
		int count = coursesRepository.countByTitle(dto.getTitle());
		if(count>0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			Courses course = new Courses();
			course.setId(dto.getId());
			course.setTitle(dto.getTitle());
			course.setImage(dto.getImage());
			course.setLeturesCount(dto.getLetures_count());
			course.setHourCount(dto.getHour_count());
			course.setViewCount(dto.getView_count());
			course.setPrice(dto.getPrice());
			course.setDiscount(dto.getDiscount());
			course.setPromotionPrice(dto.getPromotionPrice());
			course.setDescription(dto.getDescription());
			course.setContent(dto.getContent());
			course.setCategoryId(dto.getCategoryId());
			course.setLastUpdate(dto.getLastUpdate());			
			coursesRepository.save(course);
			return new ServiceInfo(true, "Thêm mới thành công!", course.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(int id, CoursesDto dto) {
		Optional<Courses> optional = coursesRepository.findById(dto.getId());
		if(optional.isPresent()==false) return false;
		Courses course = optional.get();
		course.setId(dto.getId());
		course.setTitle(dto.getTitle());
		course.setImage(dto.getImage());
		course.setLeturesCount(dto.getLetures_count());
		course.setHourCount(dto.getHour_count());
		course.setViewCount(dto.getView_count());
		course.setPrice(dto.getPrice());
		course.setDiscount(dto.getDiscount());
		course.setPromotionPrice(dto.getPromotionPrice());
		course.setDescription(dto.getDescription());
		course.setContent(dto.getContent());
		course.setCategoryId(dto.getCategoryId());
		course.setLastUpdate(dto.getLastUpdate());			
		coursesRepository.save(course);
		return true;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		coursesRepository.deleteById(id);
	}

}
