package com.bm.tzj.mine;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.bm.api.UserManager;
import com.bm.base.BaseActivity;
import com.richer.tzjjl.R;
import com.bm.util.TimeCount;
import com.bm.util.Util;
import com.lib.http.ServiceCallback;
import com.lib.http.result.BaseResult;
import com.lib.http.result.StringResult;

/**
 * 找回密码
 * 
 * @author shiyt
 * 
 */
public class RetrievePasswordAc extends BaseActivity implements OnClickListener {
	Context mContext;
	private EditText et_phone, et_code;
	private TextView tv_next, tv_verifcode;
	TimeCount count;
	public static RetrievePasswordAc intance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		contentView(R.layout.ac_retrievepwd);
		setTitleName("找回密码");
		mContext = this;
		init();
	}

	public void init() {
		tv_next = findTextViewById(R.id.tv_next);
		tv_verifcode = findTextViewById(R.id.tv_verifcode);
		et_phone = findEditTextById(R.id.et_phone);
		et_code = findEditTextById(R.id.et_code);
		tv_next.setOnClickListener(this);
		tv_verifcode.setOnClickListener(this);
	}
	
	/**
	 * 获取验证码
	 * 
	 * @param phone 手机号码
	 *            
	 */
	public void getAuthCode(final String phone) {
		if(TextUtils.isEmpty(phone)){
			dialogToast("手机号码不能为空");
			return;
		}
		if(!Util.isMobileNO(phone)){
			dialogToast("请输入正确的手机号码");
			return;
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("type", "2");//验证入口  1 注册 2 忘记密码
		showProgressDialog();
		UserManager.getInstance().getTzjcasSendcode(mContext,map,new ServiceCallback<StringResult>() {

			@Override
			public void done(int what, StringResult obj) {
				hideProgressDialog();
				count = new TimeCount(120000, 1000, tv_verifcode, et_phone,mContext);
				count.start();
				et_phone.setFocusable(false);
			}

			@Override
			public void error(String msg) {
				hideProgressDialog();
				dialogToast(msg);
			}
		});
	}
	
	
	/**
	 * 验证手机号码和短信验证码
	 * 
	 * @param phone
	 *            手机号码
	 * @param authCode
	 *            验证码
	 */
	public void getCheckAuthCode(final String phone,String authCode) {
		if(TextUtils.isEmpty(phone)){
			dialogToast("手机号码不能为空");
			return;
		}
		if(!Util.isMobileNO(phone)){
			dialogToast("请输入正确的手机号码");
			return;
		}
		
		if(TextUtils.isEmpty(authCode)){
			dialogToast("验证码不能为空");
			return;
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("phone", phone);
		map.put("authCode", authCode);
		map.put("type", "2");//验证入口  1 注册 2 忘记密码
		
		showProgressDialog();
		UserManager.getInstance().getTzjcasCheckcode(mContext,map,new ServiceCallback<StringResult>() {

			@Override
			public void done(int what, StringResult obj) {
				hideProgressDialog();
				Intent intent = new Intent(mContext, ForgotpassAc.class);
				intent.putExtra("pageType", "RetrievePwdAc");
				intent.putExtra("phone", phone);
				startActivity(intent);
			}

			@Override
			public void error(String msg) {
				hideProgressDialog();
				dialogToast(msg);
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_next:  //找回密码下一步
			getCheckAuthCode(et_phone.getText().toString().trim(),et_code.getText().toString().trim());
			break;
		case R.id.tv_verifcode:  //获取验证码
			getAuthCode(et_phone.getText().toString().trim());
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		RetrievePasswordAc.intance=null;
	}
}
