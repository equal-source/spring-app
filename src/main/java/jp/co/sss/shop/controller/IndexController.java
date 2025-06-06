package jp.co.sss.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/before")
	public String before() {
		return "before";
	}

	@GetMapping("/after")
	public String after() {
		return "after";
	}

	@GetMapping("/transition")
	public String sampleTransition() {
		return "sample_transition";
	}

	@GetMapping("/index_f")
	public String indexForward(Model model) {
		model.addAttribute("hoge", "model value");
		return "index";
	}

	@GetMapping("/index_r")
	public String indexRedirect(Model model) {
		model.addAttribute("hoge", "model value");
		return "redirect:/";
	}

	public static class Hoge {
		public int publicField = 1;

		public int publicMethod() {
			return 2;
		}

		public int getPublicValue() {
			return 3;
		}
	}

}
