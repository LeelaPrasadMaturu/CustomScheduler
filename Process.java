class Process {
    private int processId;
    private int arrivalTime;
    private int burstTime;
    private int priority;
    private long remainingBurstTime;

    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingBurstTime = burstTime;
        this.priority = priority;
    }
    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }   
    public int getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public long getRemainingBurstTime() {
        return remainingBurstTime;
    }

    public void setRemainingBurstTime(long remainingBurstTime) {
        this.remainingBurstTime = remainingBurstTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getPriorityNumber() {
        return priority * burstTime;
    }
    public String toString() {
        return "Process ID: " + processId +
               ", Arrival Time: " + arrivalTime +
               ", Burst Time: " + burstTime +
               ", Priority: " + priority;
    }
}