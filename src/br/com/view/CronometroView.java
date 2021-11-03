package br.com.view;


import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.helpers.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

import br.com.controller.ConectaBancoController;

public class CronometroView implements Job {

	// verificar agenda para enviar email

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			
			
			ConectaBancoController c1 = new ConectaBancoController();
			
			
			JobDataMap jobData = new JobDataMap();
			jobData.put("requestContext", c1);
			

			JobDetail jobDetail = new JobDetail("job0", "group1", EmailCronometro.class);
			jobDetail.setDescription("myjob");
			jobDetail.setJobDataMap(jobData);
			
			
			
			Trigger trigger = TriggerUtils.makeDailyTrigger(17,29);


			trigger.setGroup("group1");
			trigger.setDescription("gatilho");
			trigger.setName("trigger");
			trigger.setStartTime(LoginView.hoje);

			sched.scheduleJob(jobDetail, trigger);

			sched.start();
			
			System.out.print("Cronometro ativado !!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
