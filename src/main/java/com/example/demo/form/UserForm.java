package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm {
	
	//nameへのバリデーション設定を追加
	@Size(min = 1, max = 200) //文字列やコレクションのサイズを指定するためのアノテーション
	private String name; 
	
	@Size(min = 1, max = 200)
	@Email                   //メールアドレスの形式であることをチェックするアノテーション
	private String email;
}
