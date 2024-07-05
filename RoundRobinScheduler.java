import java.util.*;

public class RoundRobinScheduler {
    private Queue<Process> processQueue;
    private List<Process> allProcesses;
    private long currentTime;
    private int timeQuantum;

    public RoundRobinScheduler(int timeQuantum) {
        processQueue = new LinkedList<>();
        allProcesses = new ArrayList<>();
        this.timeQuantum = timeQuantum * 1000; // Convert to milliseconds
        this.currentTime = 0;
    }

    public void addProcess(Process process) {
        allProcesses.add(process);
    }

    public void executeProcesses() {
        long totalWaitingTime = 0;
        long totalTurnaroundTime = 0;
        int index = 0;
        int initialSize = allProcesses.size();

        while (!processQueue.isEmpty() || index < allProcesses.size()) {
            // Add processes that have arrived to the queue
            while (index < allProcesses.size() && allProcesses.get(index).getArrivalTime() * 1000 <= currentTime) {
                processQueue.add(allProcesses.get(index));
                index++;
            }

            if (processQueue.isEmpty()) {
                // No process is ready to execute, move time forward
                if (index < allProcesses.size()) {
                    currentTime = allProcesses.get(index).getArrivalTime() * 1000;
                }
                continue;
            }

            Process process = processQueue.poll();
            long executeTime = Math.min(timeQuantum, process.getRemainingBurstTime() * 1000);
            executeProcess(process, executeTime);
            currentTime += executeTime;

            if (process.getRemainingBurstTime() > 0) {
                processQueue.add(process);
            } else {
                long turnaroundTime = currentTime - (process.getArrivalTime() * 1000);
                totalTurnaroundTime += turnaroundTime;
                long waitingTime = turnaroundTime - (process.getBurstTime() * 1000);
                totalWaitingTime += waitingTime;
            }
        }

        System.out.println("Average Waiting Time: " + (totalWaitingTime / initialSize) + " ms");
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / initialSize) + " ms");
    }

    private void executeProcess(Process process, long executeTime) {
        System.out.println("Executing Process ID: " + process.getProcessId() + " for " + executeTime / 1000 + " seconds");
        process.setRemainingBurstTime(process.getRemainingBurstTime() - (executeTime / 1000));
        try {
            Thread.sleep(executeTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
