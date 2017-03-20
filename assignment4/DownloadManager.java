package assignment4;
import java.util.*;
public class DownloadManager {
	public static void main(String[] args) throws EmptyQueueException{
		Scanner input = new Scanner(System.in);
		DownloadScheduler d = new DownloadScheduler();
		//method that everything is accessed from where user inputs their desired values
		//and the system uses the download scheduler class to run the program
		System.out.println("Hello and welcome to the Download Scheduler. \n");
		
		System.out.println("Please enter a number of servers:");
		int servers = input.nextInt();
		d.setServers(servers);
		System.out.println("Please enter a download speed: ");
		int dwnldSpeed = input.nextInt();
		d.setDownloadSpeed(dwnldSpeed);
		System.out.println("Please enter a length of time: ");
		int time = input.nextInt();
		d.setSimulationEndTime(time);
		System.out.println("Please enter a probability of a new premium "
				+ "job per timestep: ");
		double probPrem = input.nextDouble();
		while(probPrem > 1 || probPrem < 0){
			System.out.println("Please enter a probability of a new premium "
					+ "job per timestep: ");
			probPrem = input.nextDouble();
		}
		d.setProbPrem(probPrem);
		System.out.println("Please enter a probability of a new regular "
				+ "job per timestep: ");
		double probReg = input.nextDouble();
		while(probReg > 1 || probReg < 0){
			System.out.println("Please enter a probability of a new regular "
					+ "job per timestep: ");
			probReg = input.nextDouble();
		}
		d.setProbReg(probReg);
		
		
		
		System.out.println(d.simulate());
		
		
		
	}

}
