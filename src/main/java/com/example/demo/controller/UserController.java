package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.data.entity.User;
import com.example.demo.data.repository.UserRepository;
import com.example.demo.form.UserForm;

@Controller
public class UserController {
	
	@Autowired //SpringBootが自動でインスタンスをIngect（注入）するアノテーション
	private UserRepository userRepository;
	
	//@RequestMappingの省略版、HTTPのmethod="GET"のみ受け付ける
	@GetMapping("/users") //これは/usersに対してHTTPのGETメソッドを受け付けるための設定
	//引数にorg.springframework.ui.Model　を追加
	public String getUsers(Model model) { //ModelクラスはSpringBootのJava側の処理とHTMLテンプレートの間でデータの受け渡しをするためのクラス
		//ユーザーリストの取得処理を追加
		List<User> users = userRepository.findAll();
		//取得したリストをテンプレートに渡す
		model.addAttribute("users", users);  //第一引数はテンプレートでユーザーリストを呼び出すときの名付け、第二引数はユーザーリストの実体
		//テンプレはsrc/main/resource/templates/users.htmlとする
		return "users"; 
	}
	
	@GetMapping("/newuser")
	public String getNewUser(Model model) {
		//テンプレはsrc/main/resources/templates/newuser.htmlとする
		UserForm userForm = new UserForm();
		model.addAttribute("userForm", userForm);
		return "newuser";
	}
	
	@PostMapping("/newuser") //HTTPのPOSTメソッドを受け付けるようにする
	//引数のuserFormにValidatedアノテーションを追加　→　UserFormクラスに設定したバリデーションを実行する
	//引数にBindingResultクラスを追加してUserFormクラスのフィールドに対するバリデーションの結果を保持させる
	public String registerUser(@Validated UserForm userForm, BindingResult bindingResult) {
		//バリデーションの結果エラーがあるかどうかチェック(if文)
		if(bindingResult.hasErrors()) {
			//エラーがある場合はユーザー登録画面を返す
			return "newuser";
		}
		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		
		//データベースに保存
		userRepository.save(user);
		
		//ユーザー一覧画面へリダイレクト
		return "redirect:/users";
	}
	
	//deleteUserメソッドを追加
	//リクエストマッピング設定を追加
	@PostMapping("/users/delete/{id}")
	// 処理の中でidを使えるように、引数にidを追加
	public String deleteUser(@PathVariable Long id) {
		// 指定したIDのユーザーを削除
		userRepository.deleteById(id);
		return "redirect:/users";
	}
}
