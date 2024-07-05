import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestCasesGenerator {
    private static final int NUM_TEST_CASES = 20;
    private static final int MAX_BURST_TIME = 5; // Maximum burst time for processes
    private static final int MAX_ARRIVAL_TIME = 10; // Maximum arrival time for processes
    private static final int MAX_PRIORITY = 10; // Maximum priority value

    public static void main(String[] args) {
        // Generate and print test cases for verification
        List<List<Process>> testCases = generateTestCases(NUM_TEST_CASES);
        
        for (int i = 0; i < testCases.size(); i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            for (Process process : testCases.get(i)) {
                System.out.println(process);
            }
            System.out.println();
        }
    }

    public static List<List<Process>> generateTestCases(int numCases) {
        List<List<Process>> testCases = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numCases; i++) {
            List<Process> processes = new ArrayList<>();
            int numProcesses = random.nextInt(10) + 1; // Generate between 1 to 10 processes per test case

            for (int j = 0; j < numProcesses; j++) {
                int processId = j + 1;
                int arrivalTime = random.nextInt(MAX_ARRIVAL_TIME + 1);
                int burstTime = random.nextInt(MAX_BURST_TIME + 1) + 1; // Ensure burst time is at least 1
                int priority = random.nextInt(MAX_PRIORITY + 1);

                Process process = new Process(processId, arrivalTime, burstTime, priority);
                processes.add(process);
            }

            testCases.add(processes);
        }

        return testCases;
    }
}
