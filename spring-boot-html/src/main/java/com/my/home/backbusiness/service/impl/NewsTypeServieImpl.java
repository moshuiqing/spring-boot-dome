package com.my.home.backbusiness.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.home.backbusiness.mapper.NewsTypeMapper;
import com.my.home.backbusiness.po.NewsType;
import com.my.home.backbusiness.service.NewsTypeService;
import com.my.home.other.util.LayuiPage;

@Service
public class NewsTypeServieImpl implements NewsTypeService{
	
	@Autowired
	private NewsTypeMapper newsTypeMapper;

	@Override
	public List<NewsType> simpleFound(NewsType newsType) {
		
		return newsTypeMapper.simpleFound(newsType);
	}

	@Override
	public Integer update(NewsType newsType) {
		
		return newsTypeMapper.update(newsType);
	}

	@Override
	public Integer delete(NewsType newsType) {
	
		return newsTypeMapper.delete(newsType);
	}

	@Override
	public Integer pageCount(NewsType newsType) {
		
		return newsTypeMapper.pageCount(newsType);
	}

	@Override
	public List<NewsType> pageFound(NewsType newsType, LayuiPage p) {
	
		return newsTypeMapper.pageFound(newsType, p.getStart(), p.getEnd());
	}

	@Override
	public Integer insert(NewsType newsType) {
		
		return newsTypeMapper.insert(newsType);
	}

}
