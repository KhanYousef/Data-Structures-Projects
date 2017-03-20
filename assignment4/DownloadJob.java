package assignment4;

public class DownloadJob {
	private int downloadSize;
	private int downloadSizeRemaining;//so you can easily keep track of how much has been downloaded
	private int timeRequested;//timestamp of when the job was requested (so you can calculate average wait time for the download)
	private boolean isPremium;//stores whether this is a regular or a premium download
	private int id;//starts at 1 , increases.
	private int createTime;//timestep when job was created
	
	public DownloadJob(int downloadSize, int downloadSizeRem, int timeReq, boolean isPrem, int id){
		this.downloadSize = downloadSize;
		this.downloadSizeRemaining = downloadSizeRem;
		this.timeRequested = timeReq;
		this.isPremium = isPrem;
		this.id = id;
	}

	
	//all necessary getters and setters
	public int getDownloadSize() {
		return downloadSize;
	}

	public void setDownloadSize(int downloadSize) {
		this.downloadSize = downloadSize;
	}

	public int getDownloadSizeRemaining() {
		return downloadSizeRemaining;
	}

	public void setDownloadSizeRemaining(int downloadSizeRemaining) {
		this.downloadSizeRemaining = downloadSizeRemaining;
	}

	public int getTimeRequested() {
		return timeRequested;
	}

	public void setTimeRequested(int timeRequested) {
		this.timeRequested = timeRequested;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	
	
	
	
	

}
