package advancedweb.project.datacollector.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WelfareBatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job welfareJob;

    @Scheduled(cron = "0 0 3 ? * MON")
    public void runWelfareJob() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(welfareJob, params);
    }
}

