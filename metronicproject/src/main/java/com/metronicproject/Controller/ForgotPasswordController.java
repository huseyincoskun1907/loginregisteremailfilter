package com.metronicproject.Controller;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.metronicproject.Service.UserService;
import com.metronicproject.Service.Utility;
import com.metronicproject.model.User;

@Controller
public class ForgotPasswordController {
	@Autowired
	private UserService userService;
	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("/forgot_password")
	public String showForgotPasswordForm(Model model) {
		
		return "forgot_password_form";
	}

	@PostMapping("/forgot_password")
	public String processForgotPassword(HttpServletRequest request, Model model) {
		String username = request.getParameter("username");
		String a = UUID.randomUUID().toString();

		try {
			userService.updateResetPasswordToken(a, username);
			String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + a;
			sendEmail(username, resetPasswordLink);
			model.addAttribute("message", "Hey naberr");
		} catch (UsernameNotFoundException e) {
			model.addAttribute("error", e.getMessage());
		} catch (UnsupportedEncodingException | MessagingException e) {
			model.addAttribute("error", "Error oldu ");
		}

		return "reset_password_form";
	}

	private void sendEmail(String recipientemail, String link) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("coskunhuseyin40@gmail.com", "Hey hey");
		helper.setTo(recipientemail);
		String subject = "Şifre Yenileme Linki";
		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "http://localhost:8080/" + link + "<br>"
				+ "<p>Ignore this email if you do remember your password, " + "or you have not made the request.</p>";
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}

	@GetMapping("/reset_password")
	public String shorResetPasswordForm(@Param(value = "token") String token, Model model) {
		User user = userService.getByResetPasswordToken(token);
		if (user == null) {
			model.addAttribute("title", "Reset your password ");
			model.addAttribute("message", "Invalid Token ");
			return "message";
		}
		model.addAttribute("token", token);
		model.addAttribute("pageTitle", "Reset Yout Password");

		return "reset_password_form";
	}

	@PostMapping("/reset_password")
	public String processResetPassword(HttpServletRequest request, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");

		User user = userService.getByResetPasswordToken(token);

		if (user == null) {
			model.addAttribute("message", "Invalid Token ");
			return "message";
		} else {
			userService.updatePassword(password, user);
			model.addAttribute("message", "Başarılı ");

		}
		return "login";
	}

}
