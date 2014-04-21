package com.alvaromerino.android.sqlite.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

	private int id;
	private String user;
	private String password;
	
	public Usuario() {
		super();
	}
	
	public Usuario(int id, String user, String password) {
		super();
		this.id = id;
		this.user = user;
		this.password = password;
	}
	
	public Usuario(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	// Getters & Setters
	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public String getUser() { return user; }

	public void setUser(String user) { this.user = user; }

	public String getPassword() { return password; }

	public void setPassword(String password) { this.password = password; }

	// toString
	@Override
	public String toString() {
		return "id=" + id + ", user=" + user + ", password="
				+ password;
	}

	/* PARCELABLE */
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(user);
		dest.writeString(password);
	}
	
	public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {

		@Override
		public Usuario createFromParcel(Parcel source) {
			return new Usuario(source);
		}

		@Override
		public Usuario[] newArray(int size) {
			return new Usuario[size];
		}
		
	};
	
	public Usuario(Parcel in) {
		this.id = in.readInt();
		this.user = in.readString();
		this.password = in.readString();
	}
	
}
