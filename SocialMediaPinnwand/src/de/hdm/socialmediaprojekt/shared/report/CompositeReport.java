package de.hdm.socialmediaprojekt.shared.report;

import java.io.Serializable;
import java.util.ArrayList;

public class CompositeReport extends Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Report> subReports = new ArrayList<Report>();
	
	public void addReport(Report r){
		this.subReports.add(r);
	}
	
	public void removeReport(Report r){
		this.subReports.remove(r);
	}
	
	public int getReports(){
		return this.subReports.size();
	}
	
	public Report getReport(int i){
		return this.subReports.get(i);
	}

}
