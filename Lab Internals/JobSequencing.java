import java.util.*;

class Job {
    int id, deadline, profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {
    public static void jobScheduling(Job[] jobs) {
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);
        int maxDeadline = Arrays.stream(jobs).mapToInt(j -> j.deadline).max().orElse(0);
        boolean[] slots = new boolean[maxDeadline];
        List<Integer> scheduledJobs = new ArrayList<>();

        for (Job job : jobs)
            for (int j = Math.min(maxDeadline, job.deadline) - 1; j >= 0; j--)
                if (!slots[j]) {
                    slots[j] = true;
                    scheduledJobs.add(job.id);
                    break;
                }

        System.out.println("Scheduled Jobs: " + scheduledJobs);
    }

    public static void main(String[] args) {
        jobScheduling(new Job[] {
                new Job(1, 4, 20),
                new Job(2, 1, 10),
                new Job(3, 1, 40),
                new Job(4, 1, 30),
                new Job(5, 3, 50) });
    }
}
