package de.hdm.socialmediaprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.socialmediaprojekt.shared.report.AlleInfosVonAllenBeitraegenReport;
import de.hdm.socialmediaprojekt.shared.report.AlleInfosVonAllenUsernReport;
import de.hdm.socialmediaprojekt.shared.report.AlleInfosVonEinemBeitragReport;



public interface ReportGeneratorAsync {

void createAlleInfosVonAllenUsernReport(
			AsyncCallback<AlleInfosVonAllenUsernReport> callback);

void createAlleInfosVonEinemUserReport(
		AsyncCallback<AlleInfosVonAllenUsernReport> callback);

void createAlleInfosVonAllenBeitraegenReport(
		AsyncCallback<AlleInfosVonAllenBeitraegenReport> callback);

void createAlleInfosVonEinemBeitragReport(
		AsyncCallback<AlleInfosVonEinemBeitragReport> callback);

// Kontrolle, 2 Methoden fehlen (FINK LEPIORZ TITZE);

	
	
}
