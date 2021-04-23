package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.CoursesDto;
import com.myclass.dto.TargetsDto;
import com.myclass.entity.Courses;
import com.myclass.entity.Role;
import com.myclass.entity.Targets;
import com.myclass.repository.CoursesRepository;
import com.myclass.repository.TargetRepository;
import com.myclass.service.TargetService;

@Service
public class TargetServiceImpl implements TargetService{

	@Autowired
	private TargetRepository targetRepository;
	
	@Autowired
	private CoursesRepository coursesRepository;

	@Override
	public List<TargetsDto> getAll() {
		List<TargetsDto> dtos = new ArrayList<TargetsDto>();
		List<Targets> targets = targetRepository.findAll();
		List<Courses> courses = coursesRepository.findAll();
		for(Targets target: targets) {
			for(Courses course: courses) {
				if(target.getCourseId()==course.getId()) {
					dtos.add(new TargetsDto(target.getId(),
							target.getTitle(),
							target.getCourseId(),
							course.getTitle()
							));
				}
				
			}
		}
		return dtos;
	}

	@Override
	public TargetsDto getById(int id) {
		Targets target = targetRepository.findById(id).get();
		return entityToDto(target);
	}
	public TargetsDto entityToDto(Targets entity) {
		return new TargetsDto(entity.getId(),
								 entity.getTitle(),
								 entity.getCourseId()
								 );
	}

	@Override
	public ServiceInfo add(TargetsDto dto) {
		int count = targetRepository.countByTitle(dto.getTitle());
		if(count>0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			Targets target = new Targets();
			target.setId(dto.getId());
			target.setTitle(dto.getTitle());
			target.setCourseId(dto.getCourseId());
			targetRepository.save(target);
			return new ServiceInfo(true, "Thêm mới thành công!", target.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(int id, TargetsDto dto) {
		Optional<Targets> optional = targetRepository.findById(dto.getId());
		if(optional.isPresent()==false) return false;
		Targets target = optional.get();
		target.setId(dto.getId());
		target.setTitle(dto.getTitle());
		target.setCourseId(dto.getCourseId());
		targetRepository.save(target);
		return true;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		targetRepository.deleteById(id);
	}
	
	

}
