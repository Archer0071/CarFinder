package com.pskpartha.carsolution;

import com.pskpartha.carsolution.extra.AllConstants;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CarSolutionActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
	// Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in
	// Milliseconds

	protected LocationManager locationManager;

	private LinearLayout gas_station, dealer, rental, repair, wash;
	private static Context con;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.homemenu);
		con = this;
		iUI();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new MyLocationListener());
	}

	/* AlertMethod */

	protected void alertbox(String title, String mymessage) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder
				.setMessage(
						"Your Device's GPS is Disable.Please,Turn on and wait few seconds.")
				.setCancelable(false).setTitle("Gps Status").setPositiveButton(
						"Gps On", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// finish the current activity
								// AlertBoxAdvance.this.finish();
								Intent myIntent = new Intent(
										Settings.ACTION_SECURITY_SETTINGS);
								startActivity(myIntent);
								dialog.cancel();
							}
						}).setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// cancel the dialog box
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}

	protected void showCurrentLocation() {

		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude());
			Toast
					.makeText(CarSolutionActivity.this, message,
							Toast.LENGTH_LONG).show();

			Log.e("GeoData:", message);
			// final TextView myLat = (TextView) findViewById(R.id.myLat);

			double lat = location.getLatitude();

			// myLat.setText(String.valueOf(lat));

			// final TextView myLng = (TextView) findViewById(R.id.myLng);

			double lng = location.getLongitude();
			// myLng.setText(String.valueOf(lng));

			AllConstants.UPlat = String.valueOf(lat);
			AllConstants.UPlng = String.valueOf(lng);
		}

	}

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
			String message = String.format(
					"New Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude());

			// Toast.makeText(SplashScreenActivity.this, message,
			// Toast.LENGTH_LONG).show();
		}

		public void onStatusChanged(String s, int i, Bundle b) {
			// Toast.makeText(CityGuideActivity.this, "Provider status changed",
			// Toast.LENGTH_LONG).show();
		}

		public void onProviderDisabled(String s) {
			alertbox("Gps Status!!", "Your GPS is: OFF");

			// Toast.makeText(CityGuideActivity.this,
			// "Provider disabled by the user. GPS turned off",
			// Toast.LENGTH_LONG).show();
		}

		public void onProviderEnabled(String s) {
			Toast.makeText(CarSolutionActivity.this, "GPS turned on",
					Toast.LENGTH_LONG).show();
		}

	}

	/* Initialize User Interface */

	private void iUI() {

		gas_station = (LinearLayout) findViewById(R.id.gas_station);
		gas_station.setOnClickListener(this);

		dealer = (LinearLayout) findViewById(R.id.car_dealer);
		dealer.setOnClickListener(this);

		rental = (LinearLayout) findViewById(R.id.car_rental);
		rental.setOnClickListener(this);

		wash = (LinearLayout) findViewById(R.id.car_wash);
		wash.setOnClickListener(this);

		repair = (LinearLayout) findViewById(R.id.car_repair);
		repair.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		 showCurrentLocation();

		switch (v.getId()) {

		case R.id.gas_station:
			AllConstants.topTitle="GAS STATION LIST";
			AllConstants.query = "gas_station";
			final Intent gas_station = new Intent(this, ListActivity.class);
			gas_station.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(gas_station);

			break;

		case R.id.car_dealer:
			
			AllConstants.query = "car_dealer";
			final Intent car_dealer = new Intent(this, ListActivity.class);
			car_dealer.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(car_dealer);

			break;

		case R.id.car_repair:
			
			AllConstants.query = "car_repair";
			final Intent car_repair = new Intent(this, ListActivity.class);
			car_repair.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(car_repair);

			break;
		case R.id.car_wash:
			
			AllConstants.query = "car_wash";
			final Intent car_wash = new Intent(this, ListActivity.class);
			car_wash.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(car_wash);

			break;
		case R.id.car_rental:
		
			AllConstants.query = "car_rental";
			final Intent car_rental = new Intent(this, ListActivity.class);
			car_rental.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(car_rental);

		}

	}

	
	//.............Top Bar Details Change--------------//
	
	
	public void btnAbout(View v) {
		AllConstants.webUrl = "http://www.google.com";
		AllConstants.topTitle="ABOUT";
		Intent next = new Intent(con, DroidWebViewActivity.class);
		next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(next);
	}

	public void btnFacebook(View v) {
		AllConstants.webUrl = "https://www.facebook.com/Google";
		AllConstants.topTitle="FACEBOOK FAN PAGE";
		Intent next = new Intent(con, DroidWebViewActivity.class);
		next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(next);
	}

	public void btnTwitter(View v) {
		AllConstants.webUrl = "http://www.twitter.com/google";
		AllConstants.topTitle="TWITTER FAN PAGE";
		Intent next = new Intent(con, DroidWebViewActivity.class);
		next.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(next);
	}

	public void btnShare(View v) {

		Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
		sharingIntent.setType("text/plain");
		sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject...");
		sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "shareBody...");
		startActivity(Intent.createChooser(sharingIntent, "Share via"));
	}
}