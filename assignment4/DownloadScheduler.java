package assignment4;

public class DownloadScheduler {
	private DownloadQueue regularQ;	//This is the queue where you will store the regular download jobs
	private DownloadQueue premiumQ; // This is the queue where you will store the premium download jobs. 
	//Connected with the regularQ, this forms a two-level priority queue. 
	//You will always dequeue from this queue until it is empty before dequeueing from the regularQ.
	private int currentTime; //This is where you store the time elapsed since the start of the simulation.
	private int simulationEndTime; //this stores the time when the simulation should end.
	private DownloadRandomizer random; // this will generate jobs at each timestep
	private DownloadJob[] CurrentJobs; //This is where you store the jobs that are currently downloading.
	//Once a job is done downloading, you can remove it from the array (at the end of the timestep), update the statistics,
	//and replace it with a new job. 
	private int downloadSpeed; //This stores the speed of the download in Megabytes Per Second
	private double probPrem; //probability of a premium job
	private double probReg; //probability of a regular job
	private int servers; //amount of servers
	
	
	//getters and setter made for all important data fields
	public int getServer(){
		return servers;
	}
	
	public void setServers(int servers){
		this.servers = servers;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
	
	public int getSimulationEndTime() {
		return simulationEndTime;
	}

	public void setSimulationEndTime(int simTime) {
		this.simulationEndTime = simTime;
	}

	public DownloadJob[] getCurrentJobs() {
		return CurrentJobs;
	}

	public void setCurrentJobs(DownloadJob[] currentJobs) {
		CurrentJobs = currentJobs;
	}
	
	public void setProbPrem(double prem){
		this.probPrem = prem;
	}
	
	public void setProbReg(double reg){
		this.probReg = reg;
	}
	
	public double getProbPrem(){
		return probPrem;
	}
	
	public double getProbReg(){
		return probReg;
	}
	
	public int getDownloadSpeed(){
		return downloadSpeed;
	}
	
	public void setDownloadSpeed(int speed){
		this.downloadSpeed = speed;
	}
	
	
//chief method of this class, creates the simulation user sees
	public String simulate() throws EmptyQueueException{
		CurrentJobs = new DownloadJob[servers]; //intializes the servers
		int jobsServedTotal = 0;//initialize variables that are printed in summary
		int jobsServedPrem = 0;
		int jobsServedReg = 0;
		int dataServedTotal = 0;
		int dataServedPrem = 0;
		int dataServedReg = 0;
		int totalWaitPrem = 0;
		int totalWaitReg = 0;
		String output = ""; //create empty string to print total output 
		int id = 1; //intialize id of job 
		System.out.println("--------------------------Simulation Starting--------------------------");
		for(int i = 0; i <= simulationEndTime; i++){ //create main loop in which each timestep takes place
			
			currentTime = i;
			
			for(int f = 0; f < CurrentJobs.length; f++){ //controls the decrementing of remaining download size
				if(CurrentJobs[f] != null){
					if(CurrentJobs[f].getDownloadSizeRemaining() < downloadSpeed &&
					CurrentJobs[f].getDownloadSizeRemaining() != 0){
						CurrentJobs[f].setDownloadSizeRemaining(0);
					}
					else if(CurrentJobs[f].getDownloadSizeRemaining() < downloadSpeed &&
					CurrentJobs[f].getDownloadSizeRemaining() == 0){
						CurrentJobs[f].setDownloadSizeRemaining(-1);
					}
					else{
						CurrentJobs[f].setDownloadSizeRemaining(CurrentJobs[f].getDownloadSizeRemaining() - downloadSpeed);
					}
				}
			}
			
			
			
			output += "\nTimestep " + i + ":"; //prints the timestep
			
			random = new DownloadRandomizer(probPrem, probReg);//creates a new job
			
			regularQ = new DownloadQueue();//decalre the queues
			premiumQ = new DownloadQueue();
			
			int premSize = random.getPremium();
			int regSize = random.getRegular();
			
			DownloadJob jobPrem = null;
			DownloadJob jobReg = null;
			
			if(regSize != -1){//creates a new regular job
				jobReg = new DownloadJob(regSize, regSize, i, false, id);
				jobReg.setCreateTime(currentTime);
				id++;
			}
			
			
			if(premSize != -1){//creates a new premium job
				jobPrem = new DownloadJob(premSize, premSize, i, true, id);
				jobPrem.setCreateTime(currentTime);
				id++;
			}
			
			
			
			String jobsDone = "";//string for displaying when a job is completed, initialized as empty
			
			for(int j = 0; j < CurrentJobs.length; j++){//this controls the jobsDone string and removes from server if a job is complete
				if(CurrentJobs[j] != null && CurrentJobs[j].getDownloadSizeRemaining() == 0){
					DownloadJob temp = CurrentJobs[j];
					CurrentJobs[j] = null;
					jobsServedTotal++;
					dataServedTotal += temp.getDownloadSize();
					jobsDone += "\nJob " + temp.getId() + " finished, ";
					if(temp.isPremium() == true){
						jobsDone += "Premium job.";
						jobsServedPrem++;
						dataServedPrem += temp.getDownloadSize();
						totalWaitPrem += (currentTime - temp.getCreateTime());
					}
					else{
						jobsDone += "Regular job.";
						jobsServedReg++;
						dataServedReg += temp.getDownloadSize();
						totalWaitReg += (currentTime - temp.getCreateTime());
					}
					jobsDone += " " + temp.getDownloadSize() + "Mb served, Total wait time: " + (currentTime - temp.getCreateTime());
					
				}
			}
			
			
			
			
			
			
			
			if(premSize != -1){//adds a premium job to the server from the queue if one is empty
				premiumQ.enqueue(jobPrem);
				for(int k = 0; k < CurrentJobs.length; k++){
					if(CurrentJobs[k] == null && premiumQ.isEmpty()){
						break;
					}
					if(CurrentJobs[k] == null){
						CurrentJobs[k] = premiumQ.dequeue();
					}
				}
			}
			
			
			
			
			if(regSize != -1){//adds regular job to the server from the queue if on is empty and there are no premium jobs in queue
				regularQ.enqueue(jobReg);
				for(int j = 0; j < CurrentJobs.length; j++){
					if(CurrentJobs[j] == null && premiumQ.isEmpty() && regularQ.isEmpty()){
						break;
					}
					if(CurrentJobs[j] == null && premiumQ.isEmpty()){
						CurrentJobs[j] = regularQ.dequeue();
					}
				}
			}
			
			
			
			
			if(jobReg == null){//prints the creation of a regular job
				output += "\n\t New Regular Job: n/a";
			}
			else{
				output += "\n\t New Regular Job: Job#" + jobReg.getId() + ": Size: " + jobReg.getDownloadSize();
			}
			
			
			
			
			if(jobPrem == null){//prints the creation of a premium job
				output += "\n\t New Premium Job: n/a";
			}
			else{
				output += "\n\t New Premium Job: Job#" + jobPrem.getId() + ": Size: " + jobPrem.getDownloadSize();
			}
			
			
			
			if(regularQ.isEmpty()){//prints the regular queue and its contents
				output += "\n\t RegularQueue:empty";
			}
			else{
				output += "\n\t RegularQueue: ";
				for(int j = 0; j < regularQ.size(); j++){
					DownloadJob temp = regularQ.peek();
					regularQ.dequeue();
					regularQ.enqueue(temp);
					
					output += "[#" + temp.getId() + ":" + temp.getDownloadSize() + "Mb]"; 
				}
			}
			
			
			
			if(premiumQ.isEmpty()){//prints the premium queue and its contents
				output += "\n\t PremiumQueue:empty";
			}
			else{
				output += "\n\t PremiumQueue: ";
				for(int j = 0; j < premiumQ.size(); j++){
					DownloadJob temp = premiumQ.peek();
					premiumQ.dequeue();
					premiumQ.enqueue(temp);
					
					output += "[#" + temp.getId() + ":" + temp.getDownloadSize() + "Mb]"; 
				}
			}
			
			
			
			
			for(int j = 0; j < CurrentJobs.length; j++){//prints the servers and their contents
				if(CurrentJobs[j] != null){
					output += "\n\t Server " + (j+1) + ": [#" + CurrentJobs[j].getId() + ": " + CurrentJobs[j].getDownloadSize() + "Mb total, "
							+ CurrentJobs[j].getDownloadSizeRemaining() + "Mb remaining, Request Time: "
							+ CurrentJobs[j].getTimeRequested() + ", ";
					if(CurrentJobs[j].isPremium()){
						output += "Premium]";
					}
					else{
						output += "Regular]";
					}
				}
				else{
					output += "\n\t Server " + (j+1) + ": idle";
					
				}
				
			}
			
			
			output += jobsDone;//adds the string of if any jobs are finished
			
			
			
			//divider line
			output += "\n -----------------------------------------------------------------------";
		}
		
		
		//outside of main for loop, at end of simulation this prints the summary
		output += "\nSimulation Ended: \n\t Total Jobs Served: " + jobsServedTotal;
		output += "\n\t Total Premium Jobs Served: " + jobsServedPrem;
		output += "\n\t Total Regular Jobs Served: " + jobsServedReg;
		output += "\n\t Total Data Served: " + dataServedTotal + "Mb";
		output += "\n\t Total Premium Data Served: " + dataServedPrem + "Mb";
		output += "\n\t Total Regular Data Served: " + dataServedReg + "Mb";
		if(jobsServedPrem == 0){
			output += "\n\t Average Premium Wait Time: n/a";
		}
		else{
			output += "\n\t Average Premium Wait Time: " + (totalWaitPrem/jobsServedPrem);
		}
		if(jobsServedReg == 0){
			output += "\n\t Average Regular Wait Time: n/a";
		}
		else{
			output += "\n\t Average Regular Wait Time: " + (totalWaitReg/jobsServedReg);
		}
		
		output += "\n-----------------Thank You For Running the Simulator--------------";
		
		return output;//returns the entire output to be printed
	}

}
