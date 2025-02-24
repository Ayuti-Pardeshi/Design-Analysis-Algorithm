import java.util.Scanner;

class Job {
    char id;
    int deadline, profit;

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class JobScheduling {
    static void printJobScheduling(Job arr[], int n) {
        // Step 1: Sort jobs in decreasing order of profit (Using Normal Loops)
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i].profit < arr[j].profit) { //comparison
                    Job temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        // Step 2: Find the maximum deadline
        int maxDeadline = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].deadline > maxDeadline) {
                maxDeadline = arr[i].deadline;
            }
        }

        // Step 3: Initialize result and slot availability
        char[] result = new char[maxDeadline]; // Store job sequence
        boolean[] slot = new boolean[maxDeadline]; // Track used slots
        int totalProfit = 0; // Track total profit

        // Step 4: Assign jobs to the latest available slot before the deadline
        for (int i = 0; i < n; i++) {
            for (int j = Math.min(maxDeadline - 1, arr[i].deadline - 1); j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = arr[i].id;
                    slot[j] = true;
                    totalProfit += arr[i].profit;
                    break;
                }
            }
        }

        // Step 5: Print the scheduled job sequence with details
        System.out.println("\nScheduled Jobs:");
        System.out.println("Job ID | Profit | Deadline");
        System.out.println("-------------------------");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                for (Job job : arr) {
                    if (job.id == result[i]) {
                        System.out.printf("  %c    |  %d   |   %d%n", job.id, job.profit, job.deadline);
                        break;
                    }
                }
            }
        }

        // Print total profit
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Taking user input for the number of jobs
        System.out.print("Enter the number of jobs: ");
        int n = sc.nextInt();
        Job arr[] = new Job[n];

        // Taking input for job details
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Job ID (A-Z): ");
            char id = sc.next().charAt(0);
            System.out.print("Enter Job Deadline: ");
            int deadline = sc.nextInt();
            System.out.print("Enter Job Profit: ");
            int profit = sc.nextInt();
            arr[i] = new Job(id, deadline, profit);
        }

        // Call function to find the maximum profit schedule
        printJobScheduling(arr, n);

        sc.close();
    }
}
