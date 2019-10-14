package com.my.home.other.chat.service;

import java.util.List;

import com.my.home.other.chat.po.ChatFriend;
import com.my.home.other.chat.po.FriendUser;

public interface ChatFriendService {
	/**
	 * @param userid
	 * @return
	 * 查询我的好友
	 */
	List<ChatFriend> foundChatFriend(String userid);
	
	/**
	 * @param chatFriend
	 * @return
	 * 新增
	 */
	Integer addChatFriend(ChatFriend chatFriend);
	
	
	/**
	 * @param friendUser
	 * @return
	 * 绑定好友关系
	 */
	Integer BindFriend(FriendUser friendUser);
	
	/**
	 * @param chatFriend
	 * @return
	 * 删除分组根据
	 */
	Integer removeChatFriend(ChatFriend chatFriend);
	
	/**
	 * @param array
	 * @param Friendid
	 * 批量新增
	 */
	void addChatFriends(List<FriendUser> list);
	
	/**
	 * @param userid
	 * @return
	 * 返回id串
	 */
	List<FriendUser> getStrUserid(String userid);
	
	/**
	 * @param chatFriend
	 * @return
	 * 修改分组名
	 */
	Integer upChatFriendName(ChatFriend chatFriend);
	
	/**
	 * @param uid
	 * @param frienduid
	 * @return
	 * 根据用户id和好友id删除分组下的好友
	 */
	Integer delFriendUid(String uid,String frienduid);
}
