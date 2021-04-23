package com.myclass.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.TargetsDto;
import com.myclass.dto.VideoDto;
import com.myclass.entity.Courses;
import com.myclass.entity.Targets;
import com.myclass.entity.Video;
import com.myclass.repository.CoursesRepository;
import com.myclass.repository.VideoRepository;
import com.myclass.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private CoursesRepository coursesRepository;

	@Override
	public List<VideoDto> getAll() {
		List<VideoDto> dtos = new ArrayList<VideoDto>();
		List<Video> videos = videoRepository.findAll();
		List<Courses> courses = coursesRepository.findAll();
		for(Video video: videos) {
			for(Courses course: courses) {
				if(video.getCourseId()==course.getId()) {
					dtos.add(new VideoDto(video.getId(),
							video.getTitle(),
							video.getUrl(),
							video.getTimeCount(),
							video.getCourseId(),
							course.getTitle()
							));
				}
				
			}
		}
		return dtos;
	}

	@Override
	public VideoDto getById(int id) {
		Video video = videoRepository.findById(id).get();
		return entityToDto(video);
	}
	public VideoDto entityToDto(Video entity) {
		return new VideoDto(entity.getId(),
								 entity.getTitle(),
								 entity.getUrl(),
								 entity.getTimeCount(),								 
								 entity.getCourseId()
								 );
	}

	@Override
	public ServiceInfo add(VideoDto dto) {
		
		System.out.println("DTO");
		System.out.println("id " + dto.getId());
		System.out.println("title " + dto.getTitle());
		System.out.println("url " +dto.getUrl());
		System.out.println("coId " +dto.getCourseId());
		
		int count = videoRepository.countByTitle(dto.getTitle());
		if(count>0) {
			return new ServiceInfo(false, "Tên đã sử dụng!");
		}
		try {
			Video video = new Video();
			video.setId(dto.getId());
			video.setTitle(dto.getTitle());
			video.setUrl(dto.getUrl());
			video.setTimeCount(dto.getTimeCount());
			video.setCourseId(dto.getCourseId());
			videoRepository.save(video);
			return new ServiceInfo(true, "Thêm mới thành công!", video.getId());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ServiceInfo(false, "Thêm mới thất bại!");
	}

	@Override
	public boolean edit(int id, VideoDto dto) {
		Optional<Video> optional = videoRepository.findById(dto.getId());
		if(optional.isPresent()==false) return false;
		Video video = optional.get();
		video.setId(dto.getId());
		video.setTitle(dto.getTitle());
		video.setUrl(dto.getUrl());
		video.setTimeCount(dto.getTimeCount());
		video.setCourseId(dto.getCourseId());
		
		System.out.println("DTO");
		System.out.println("id " + dto.getId());
		System.out.println("title " + dto.getTitle());
		System.out.println("url " +dto.getUrl());
		System.out.println("coId " +dto.getCourseId());
		videoRepository.save(video);
		return true;
	}

	@Override
	public void delete(int id) {
		videoRepository.deleteById(id);
		
	}

}
