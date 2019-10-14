package com.my.home.backbusiness.service;

import java.util.List;

import com.my.home.backbusiness.po.NewsType;
import com.my.home.other.util.LayuiPage;

public interface NewsTypeService {

	List<NewsType> simpleFound(NewsType newsType);

	Integer update(NewsType newsType);

	Integer delete(NewsType newsType);

	Integer pageCount(NewsType newsType);
	
	Integer insert(NewsType newsType);
	
	List<NewsType> pageFound(NewsType newsType,LayuiPage p);

}
