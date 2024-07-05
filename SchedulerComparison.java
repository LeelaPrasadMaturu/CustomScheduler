import java.util.List;

public class SchedulerComparison {
    public static void main(String[] args) {
        List<List<Process>> testCases = TestCasesGenerator.generateTestCases(20);

        for (int i = 0; i < testCases.size(); i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            for (Process process : testCases.get(i)) {
                System.out.println("Process ID: " + process.getProcessId() +
                                   ", Arrival Time: " + process.getArrivalTime() +
                                   ", Burst Time: " + process.getBurstTime() +
                                   ", Priority: " + process.getPriority());
            }

            System.out.println("\nCustom Scheduler:");
            CustomScheduler customScheduler = new CustomScheduler();
            for (Process p : testCases.get(i)) {
                customScheduler.addProcess(p);
            }
            customScheduler.executeProcesses();

            System.out.println("\nFIFO Scheduler:");
            Scheduler fifoScheduler = new Scheduler();
            for (Process p : testCases.get(i)) {
                fifoScheduler.addProcess(p);
            }
            fifoScheduler.executeProcesses();

            System.out.println("\nRound Robin Scheduler:");
            RoundRobinScheduler roundRobinScheduler = new RoundRobinScheduler(2);
            for (Process p : testCases.get(i)) {
                roundRobinScheduler.addProcess(p);
            }
            roundRobinScheduler.executeProcesses();

            System.out.println("\n-------------------------\n");
        }
    }
}
