package com.my.home.other.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.home.other.chat.mapper.ChatUserMapper;
import com.my.home.other.chat.po.ChatRecord;
import com.my.home.other.chat.po.ChatUser;
import com.my.home.other.chat.service.ChatUserService;
import com.my.home.other.util.LayuiPage;

@Service
public class ChatUserServiceImpl implements ChatUserService {
	
	@Autowired
	private ChatUserMapper chatUserMapper;

	@Override
	public List<ChatUser> foundChatUser(ChatUser user) {
		return chatUserMapper.foundChatUser(user);
	}

	@Override
	public List<ChatUser> getRecord(String sendId, String toId, String type) {
		
		return chatUserMapper.getRecord(sendId, toId, type);
	}

	@Override
	public Integer insertRecord(ChatRecord chatRecord) {
		
		return chatUserMapper.insertRecord(chatRecord);
	}

	@Override
	public Integer updateState(String id) {
		
		return chatUserMapper.updateState(id);
	}

	@Override
	public List<ChatUser> getRecom(String uid) {
		
		return chatUserMapper.getRecom(uid);
	}

	@Override
	public Integer getCount(String uid,String username) {
		
		return chatUserMapper.getCount(uid,username);
	}

	@Override
	public List<ChatUser> getPage(String uid,String username, LayuiPage page) {

		return chatUserMapper.getPage(uid,username,page.getStart(),page.getEnd());
	}

	@Override
	public List<ChatUser> getGroupRecord(String id) {
		
		return chatUserMapper.getGroupRecord(id);
	}

}
