package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	//アノテーション付きのメソッド追加
	@RequestMapping("/")  //("/")は　パス/ すなわちトップページにマッピングすること
	//メソッドはpublic 戻り型はString テンプレートエンジンを使ってHTMLを返す場合
	public String index() {  //メソッド名の命名ルールは特にない（indexでなくてもいい、その時のプロジェクトのルールに従えばいい)
		return "index";  //どのテンプレートファイルを使うかを指示している
		//この場合 src/main/resource/templates/index.htmlが指定されている
	}
	
	
	
}
