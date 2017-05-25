## android客户端通过Email发送验证码或者反馈信息的解决方案。
 本例子整合下面的资源，邮箱服务器使用网易的163。  

- contect me [forjunjian@gmail.com]
### 准备工作

- [开通一个163账号 注意需要在163中设置（设置-》邮件设置-》客户端授权密码，注意这个密码才是下面中需要使用的密码，设置POP3/SMTP/IMAP。](http://blog.csdn.net/faicm/article/details/46603605)

### java方案
- [起源](http://blog.csdn.net/qqgrid/article/details/40402287)
- [实际参考方案](http://blog.csdn.net/qqgrid/article/details/40402287)
### android出现问题 
#### 问题一 ： java的jar包不能用
- [android下为什么发不了邮件?](http://www.iteye.com/problems/71677)

#### 问题二： 抛出异常
			
			com.forjun.emaidemo W/System.err: com.sun.mail.smtp.SMTPSendFailedException: 554 DT:SPM 163 smtp11,D8CowADXqSz+WiZZ_YeGHA--.41353S2 1495685886,please see http://mail.163.com/help/help_spam_16.htm?ip=110.19.183.192&hostid=smtp11&time=1495685886
			com.forjun.emaidemo W/System.err:     at com.sun.mail.smtp.SMTPTransport.issueSendCommand(SMTPTransport.java:1515)
			com.forjun.emaidemo W/System.err:     at com.sun.mail.smtp.SMTPTransport.finishData(SMTPTransport.java:1321)
			com.forjun.emaidemo W/System.err:     at com.sun.mail.smtp.SMTPTransport.sendMessage(SMTPTransport.java:637)
			com.forjun.emaidemo W/System.err:     at javax.mail.Transport.send0(Transport.java:189)
			com.forjun.emaidemo W/System.err:     at javax.mail.Transport.send(Transport.java:118)
			com.forjun.emaidemo W/System.err:     at com.forjun.email.SimpleMailSender.sendTextMail(SimpleMailSender.java:58)
			com.forjun.emaidemo W/System.err:     at preformSendEmail(MainActivity.java:70)
			com.forjun.emaidemo W/System.err:     at com.forjun.emaidemo.MainActivity$1.run(MainActivity.java:99)
			com.forjun.emaidemo W/System.err:     at java.lang.Thread.run(Thread.java:761)
		
- 异常原因：高端网易觉得邮件是垃圾邮件，帮忙拒绝了。 请标题和内容里面别包含测试等字眼。	
![icon](/pic/reject.png)
### 扩展知识
- [什么是POP3、SMTP和IMAP?以及网易邮箱服务器的端口](http://help.163.com/09/1223/14/5R7P6CJ600753VB8.html)
- reference :
 - [http://blog.csdn.net/qqgrid/article/details/40402287](http://blog.csdn.net/qqgrid/article/details/40402287)
 - [http://www.jianshu.com/p/87b5eaef7d22](http://www.jianshu.com/p/87b5eaef7d22)