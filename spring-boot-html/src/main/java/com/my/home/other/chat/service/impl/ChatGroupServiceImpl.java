package com.my.home.other.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.home.other.chat.mapper.ChatGroupMapper;
import com.my.home.other.chat.po.ChatGoup;
import com.my.home.other.chat.po.ChatUser;
import com.my.home.other.chat.po.GroupUser;
import com.my.home.other.chat.service.ChatGroupService;
import com.my.home.other.util.LayuiPage;

@Service
public class ChatGroupServiceImpl implements ChatGroupService {
	
	@Autowired
	private ChatGroupMapper chatGroupMapper;

	@Override
	public List<ChatGoup> foundChatGroup(ChatUser chatUser) {
		
		return chatGroupMapper.foundChatGroup(chatUser);
	}

	@Override
	public List<ChatUser> foundGroupByid(String id) {
		
		return chatGroupMapper.foundGroupByid(id);
	}

	@Override
	public Integer getGroupCount(String id,String groupname) {
		
		return chatGroupMapper.getGroupCount(id,groupname);
	}

	@Override
	public List<ChatGoup> getPageGroup(String id,String groupname, LayuiPage page) {
		
		return chatGroupMapper.getPageGroup(id,groupname, page.getStart(), page.getEnd());
	}

	@Override
	public String getUidByGroupId(String id) {
		
		return chatGroupMapper.getUidByGroupId(id);
	}

	@Override
	public Integer addUserToGroup(String groupid, String userid,String role) {
		
		return chatGroupMapper.addUserToGroup(groupid, userid,role);
	}

	@Override
	public ChatGoup getGroupInfoByid(String id) {
		
		return chatGroupMapper.getGroupInfoByid(id);
	}

	@Override
	public Integer refundGroup(GroupUser groupUser) {
		
		return chatGroupMapper.refundGroup(groupUser);
	}

	@Override
	public ChatGoup getChatGroupInfo(String id) {
		
		return chatGroupMapper.getChatGroupInfo(id);
	}

	@Override
	public Integer dissolution(String id) {
		
		return chatGroupMapper.dissolution(id);
	}

}
