package de.hdm.socialmediaprojekt.shared.report;

public abstract class ReportWriter {
	
	public abstract void process(AlleInfosVonEinemUserReport r);
	
	public abstract void process(AlleInfosVonEinemBeitragReport r);
	
	public abstract void process(AlleInfosVonAllenUsernReport r);
	
	public abstract void process(AlleInfosVonAllenBeitraegenReport r);

}
