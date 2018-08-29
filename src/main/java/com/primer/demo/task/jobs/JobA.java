package com.primer.demo.task.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import com.primer.demo.task.ScheduledJob;

@Component
@ScheduledJob(name = "JobA", cronExp = "*/10 * * * * ?")
@DisallowConcurrentExecution
public class JobA implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("JobA................");
	}

}
