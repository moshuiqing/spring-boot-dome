package com.my.home.other.chat.handler;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.http.common.HttpRequest;
import org.tio.http.common.HttpResponse;
import org.tio.websocket.common.WsRequest;
import org.tio.websocket.common.WsResponse;
import org.tio.websocket.common.WsSessionContext;
import org.tio.websocket.server.handler.IWsMsgHandler;

import com.alibaba.fastjson.JSONObject;
import com.my.home.other.chat.config.ShowcaseServerConfig;
import com.my.home.other.util.systemutil.SpringUtils;
import com.my.home.system.po.SysUser;
import com.my.home.system.service.UserService;

public class ShowcaseWsMsgHandler implements IWsMsgHandler {
	private static Logger log = LoggerFactory.getLogger(ShowcaseWsMsgHandler.class);

	public static final ShowcaseWsMsgHandler me = new ShowcaseWsMsgHandler();

	private UserService service;

	private ShowcaseWsMsgHandler() {

		if (service == null) {
			service = SpringUtils.getBean("userService");
		}

	}

	/**
	 * 握手时走这个方法，业务可以在这里获取cookie，request参数等
	 */
	@Override
	public HttpResponse handshake(HttpRequest request, HttpResponse httpResponse, ChannelContext channelContext)
			throws Exception {
		String clientip = request.getClientIp();
		String uid = request.getParam("id");// 用户id
		Tio.bindUser(channelContext, uid);
		// String key=channelContext.getClientNode().toString();
		channelContext.setAttribute("id", uid);
		log.info("收到来自{}的ws握手包\r\n{}", clientip, request.toString());

		
		return httpResponse;
	}

	/**
	 * @param httpRequest
	 * @param httpResponse
	 * @param channelContext
	 * @throws Exception
	 * @author tanyaowu
	 */
	@Override
	public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext)
			throws Exception {

		// 绑定到群组，后面会有群发
		// Tio.bindGroup(channelContext, Const.GROUP_ID);

		String groups = httpRequest.getParam("groups");
		if (groups != null) {
			String[] groupIds = groups.split(",");
			for (int i = 0; i < groupIds.length; i++) {
				Tio.bindGroup(channelContext, groupIds[i]);
			}
		}

		// int count =
		// Tio.getAllChannelContexts(channelContext.groupContext).getObj().size();

		// String msg = "{name:'+ channelContext.userid +',message:'" + null + "'}";
		// tio-websocket，服务器发送到客户端的Packet都是WsResponse
		// WsResponse wsResponse = WsResponse.fromText("",
		// ShowcaseServerConfig.CHARSET);
		// 群发
		// Tio.sendToGroup(channelContext.groupContext, Const.GROUP_ID, wsResponse);
	}

	/**
	 * 字节消息（binaryType = arraybuffer）过来后会走这个方法
	 */
	@Override
	public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		return null;
	}

	/**
	 * 当客户端发close flag时，会走这个方法
	 */
	@Override
	public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
		Tio.remove(channelContext, "receive close flag");
		return null;
	}

	/*
	 * 字符消息（binaryType = blob）过来后会走这个方法
	 */
	@Override
	public Object onText(WsRequest wsRequest, String text, ChannelContext channelContext) throws Exception {
		WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
		HttpRequest httpRequest = wsSessionContext.getHandshakeRequestPacket();// 获取websocket握手包
		if (log.isDebugEnabled()) {
			log.debug("握手包:{}", httpRequest);
		}

		log.info("收到ws消息:{}", text);

		if (Objects.equals("心跳内容", text)) {
			return null;
		}
		System.out.println(text);
		String type = null;
		String userid = null;

		if (text != null && !text.equals("心跳内容") && !text.equals("hello 连上了哦")) {
			JSONObject obj = JSONObject.parseObject(text);
			WsResponse wsResponsen = null;
			switch (obj.getString("type")) {
			case "chat":
				// 表示聊天
				JSONObject send = (JSONObject) obj.get("send");
				type = send.getString("type");
				userid = send.getString("toid");
				wsResponsen = WsResponse.fromText(text, ShowcaseServerConfig.CHARSET);

				if (type != null && type.equals("friend")) {
					// 指定发送
					Tio.sendToUser(channelContext.groupContext, userid, wsResponsen);
				} else if (type != null && type.equals("group")) {
					Tio.sendToGroup(channelContext.groupContext, userid, wsResponsen);
				}

				break;
			case "apply":
				type = obj.getString("usertype");
				userid = obj.getString("toid");
				wsResponsen = WsResponse.fromText(text, ShowcaseServerConfig.CHARSET);
				// 发送申请信息
				if (type != null && type.equals("friend")) {
					// 指定发送
					Tio.sendToUser(channelContext.groupContext, userid, wsResponsen);
				} else if (type != null && type.equals("group")) {
					Tio.sendToGroup(channelContext.groupContext, userid, wsResponsen);
				}

				break;
			case "systemagree":
				// 表示系统消息通知
				JSONObject send1 = (JSONObject) obj.get("send");
				type = send1.getString("type");
				userid = obj.getString("toid");
				wsResponsen = WsResponse.fromText(text, ShowcaseServerConfig.CHARSET);

				if (type != null && type.equals("friend")) {
					// 指定发送
					Tio.sendToUser(channelContext.groupContext, userid, wsResponsen);
				}

				break;
			case "systemGroup":
				// 给群里发送系统消息
				String groupid = obj.getString("groupid");
				Tio.bindGroup(channelContext, groupid);
				wsResponsen = WsResponse.fromText(text, ShowcaseServerConfig.CHARSET);

				Tio.sendToGroup(channelContext.groupContext, groupid, wsResponsen);

				break;
			case "delFriend":
				//删除好友通知
				userid = obj.getString("toid");
				wsResponsen = WsResponse.fromText(text, ShowcaseServerConfig.CHARSET);
				Tio.sendToUser(channelContext.groupContext, userid, wsResponsen);
				break;
			case "systemGroupRefund":
				//接受群解散消息
				groupid = obj.getString("groupid");
				wsResponsen = WsResponse.fromText(text, ShowcaseServerConfig.CHARSET);
				Tio.sendToGroup(channelContext.groupContext, groupid, wsResponsen);
				break;
			case "unbindGroup":
				//解除绑定
				groupid = obj.getString("groupid");
				Tio.unbindGroup(groupid, channelContext);
				break;

			}

		}

		// 返回值是要发送给客户端的内容，一般都是返回null
		return null;
	}

}
