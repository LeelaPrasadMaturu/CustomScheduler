import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class CustomScheduler {
    private PriorityQueue<Process> processQueue;
    private List<Process> allProcesses;
    private long currentTime;

    public CustomScheduler() {
        this.processQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getPriorityNumber).reversed());
        this.allProcesses = new ArrayList<>();
        this.currentTime = 0;
    }

    public void addProcess(Process process) {
        allProcesses.add(process);
    }

    public void executeProcesses() {
        long totalWaitingTime = 0;
        long totalTurnaroundTime = 0;
        int index = 0;

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

            Process currentProcess = processQueue.poll();
            long executeTime = Math.min(3000, currentProcess.getRemainingBurstTime() * 1000);
            executeProcess(currentProcess, executeTime);
            currentTime += executeTime;

            if (currentProcess.getRemainingBurstTime() > 0) {
                processQueue.add(currentProcess);
            } else {
                long turnaroundTime = currentTime - (currentProcess.getArrivalTime() * 1000);
                totalTurnaroundTime += turnaroundTime;
                long waitingTime = turnaroundTime - (currentProcess.getBurstTime() * 1000);
                totalWaitingTime += waitingTime;
            }
        }

        System.out.println("Average Waiting Time: " + (totalWaitingTime / allProcesses.size()) + " ms");
        System.out.println("Average Turnaround Time: " + (totalTurnaroundTime / allProcesses.size()) + " ms");
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
