package com.my.home.other.chat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.home.other.chat.mapper.ChatFriendMapper;
import com.my.home.other.chat.po.ChatFriend;
import com.my.home.other.chat.po.FriendUser;
import com.my.home.other.chat.service.ChatFriendService;

@Service
public class ChatFriendServiceImpl implements ChatFriendService {
	
	@Autowired
	private ChatFriendMapper chatFriendMapper;

	@Override
	public List<ChatFriend> foundChatFriend(String userid) {
		
		return chatFriendMapper.foundChatFriend(userid);
	}

	@Override
	public Integer addChatFriend(ChatFriend chatFriend) {
		
		return chatFriendMapper.addChatFriend(chatFriend);
	}

	@Override
	public Integer BindFriend(FriendUser friendUser) {
		
		return chatFriendMapper.BindFriend(friendUser);
	}

	@Override
	public Integer removeChatFriend(ChatFriend chatFriend) {
		
		return chatFriendMapper.removeChatFriend(chatFriend);
	}

	@Override
	public void addChatFriends(List<FriendUser> list) {
		
		chatFriendMapper.addChatFriends(list);
	}

	@Override
	public List<FriendUser> getStrUserid(String userid) {
		
		return chatFriendMapper.getStrUserid(userid);
	}

	@Override
	public Integer upChatFriendName(ChatFriend chatFriend) {
		
		return chatFriendMapper.upChatFriendName(chatFriend);
	}

	@Override
	public Integer delFriendUid(String uid, String frienduid) {
		
		return chatFriendMapper.delFriendUid(uid, frienduid);
	}
	
	

}
