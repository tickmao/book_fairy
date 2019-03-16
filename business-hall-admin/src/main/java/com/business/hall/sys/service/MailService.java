package com.business.hall.sys.service;

import com.business.hall.sys.model.Mail;

import java.util.List;

public interface MailService {
	void save(Mail mail, List<String> toUser);
}
