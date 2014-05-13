package de.hdm.socialmediaprojekt.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.socialmediaprojekt.shared.smo.User;

public class CreateUserCallback implements AsyncCallback<User> {

		public void onFailure(Throwable caught){
			System.out.println(caught.getMessage());
			caught.getCause();
		}
		public void onSuccess(User user){
			if(user != null){
				ClientSideSettings.getLogger().info("User mit" +user.getNickname()+" wurde angelegt");
			}
			System.out.println("Test_Callback");

		}
	}

