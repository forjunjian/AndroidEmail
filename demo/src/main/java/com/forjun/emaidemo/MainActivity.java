package com.forjun.emaidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.forjun.email.MailSenderInfo;
import com.forjun.email.SimpleMailSender;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private EditText et_test;
    private Button btn_send;
    private EditText et_title;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        et_test = (EditText) findViewById(R.id.et_test);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(this);
        et_title = (EditText) findViewById(R.id.et_title);
        et_title.setOnClickListener(this);
        et_content = (EditText) findViewById(R.id.et_content);
        et_content.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                sendEmail();
                break;
        }
    }


    /**
     * 发送邮件核心代码 ，部分操作请参考readme
     */
    public void preformSendEmail(String destEmailAddress, String title, String content) {
        MailSenderInfo mailInfo = new MailSenderInfo();
        // 设定邮箱代理服务器 网易的163代理为smtp.163.com
        mailInfo.setMailServerHost("smtp.163.com");
        // 设定邮箱代理服务器端口号
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        // 参考readme获取网易客户端授权密码,这里不能为空
        String username = null;
        String password = null;
        if (password == null || username == null) {
            throw new RuntimeException("请到网易申请，具体参考readme，这里帐号密码不能为空！");
        }
        //163邮箱账户名
        mailInfo.setUserName(username);
        // 您的邮箱密码或者客户端授权密码
        mailInfo.setPassword(password);
        // 默认可以没有，显示在对方邮件的抬头
        mailInfo.setFromAddress(username + "@163.com");
        mailInfo.setToAddress(destEmailAddress);
        mailInfo.setSubject(title + new Date().toString());
        mailInfo.setContent(content);
        // 这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendTextMail(mailInfo);// 发送文体格式
//		sms.sendHtmlMail(mailInfo);// 发送html格式
        System.out.println("sending success");
    }

    private void sendEmail() {
        // validate
        final String title = et_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "请输入title", Toast.LENGTH_SHORT).show();
            return;
        }

        final String content = et_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入邮件内容", Toast.LENGTH_SHORT).show();
            return;
        }

        // validate
        final String destEmailAddress = et_test.getText().toString().trim();
        if (TextUtils.isEmpty(destEmailAddress)) {
            Toast.makeText(this, "目标邮箱地址不为空", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, " title:" + title + " content:" + content);
                preformSendEmail(destEmailAddress, title, content);
            }
        }).start();

    }
}
