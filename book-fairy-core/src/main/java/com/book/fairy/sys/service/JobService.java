package com.book.fairy.sys.service;

import com.book.fairy.sys.model.JobModel;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

public interface JobService {

	void saveJob(JobModel jobModel);

	void doJob(JobDataMap jobDataMap);

	void deleteJob(Long id) throws SchedulerException;
}
