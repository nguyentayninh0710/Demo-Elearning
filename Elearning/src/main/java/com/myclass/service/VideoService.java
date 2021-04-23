package com.myclass.service;

import java.util.List;

import com.myclass.common.ServiceInfo;
import com.myclass.dto.VideoDto;

public interface VideoService {

	List<VideoDto> getAll();
	VideoDto getById(int id);
	ServiceInfo add(VideoDto dto);
	boolean edit(int id, VideoDto dto);
	void delete(int id);
}
