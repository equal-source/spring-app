package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.shop.form.LoginForm;

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

	@RequestMapping(path = "/loginUsingForm", method = RequestMethod.GET)
	public String loginUsingForm() {
		return "session/login_using_form";
	}

	@RequestMapping(path = "/doLoginUsingForm", method = RequestMethod.POST)
	public String doLoginUsingForm(LoginForm form) {
		System.out.println("ユーザ ID:" + form.getUserId());
		System.out.println("パスワード:" + form.getPassword());
		return "session/login_using_form";
	}

	@RequestMapping(path = "/loginOnRequest", method = RequestMethod.GET)
	public String loginOnRequest() {
		return "session/login_on_request";
	}

	@RequestMapping(path = "/doLoginOnRequest", method = RequestMethod.POST)
	public String doLoginOnRequest(LoginForm form, Model model) {
		model.addAttribute("userId", form.getUserId());
		return "session/login_on_request";
	}

}