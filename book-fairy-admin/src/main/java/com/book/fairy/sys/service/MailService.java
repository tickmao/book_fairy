package com.book.fairy.sys.service;

import com.book.fairy.sys.model.Mail;

import java.util.List;

public interface MailService {
	void save(Mail mail, List<String> toUser);
}
