package com.klef.fsad.dto;

public class TaskReviewUpdateDTO 
{
	 private Long taskid;
	 private Long progressid;
	 private Double progress;
	 private String remarks;
	 
	public Long getTaskid() {
		return taskid;
	}
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	public Long getProgressid() {
		return progressid;
	}
	public void setProgressid(Long progressid) {
		this.progressid = progressid;
	}
	public Double getProgress() {
		return progress;
	}
	public void setProgress(Double progress) {
		this.progress = progress;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}


