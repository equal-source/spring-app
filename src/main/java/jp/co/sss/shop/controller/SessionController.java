package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SessionController {
	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String login() {
		return "session/login";
	}

	// 「/doLogin」の URL に GET でのリクエストが送信された場合、doLoginGet()メソッドを実行する
	@RequestMapping(path = "/doLogin", method = RequestMethod.GET)
	public String doLoginGet(Integer userId) {
		System.out.println("ユーザ ID:" + userId);
		return "session/login";
	}

	// 「/doLogin」の URL に POST でのリクエストが送信された場合、doLoginPost()メソッドを実行する
	@RequestMapping(path = "/doLogin", method = RequestMethod.POST)
	public String doLoginPost(Integer userId) {
		System.out.println("ユーザ ID:" + userId);
		return "session/login";
	}
}