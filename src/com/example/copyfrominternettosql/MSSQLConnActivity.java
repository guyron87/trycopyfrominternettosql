package com.example.copyfrominternettosql;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


/**
 * Main activity class for the demo, 
 * 
 * Read http://blog.althafkbacker.com/2013/10/android
 * -and-microsoft-sql-ms-sql-server.html
 * 
 * To setup jTDS - SQL Server and Sybase JDBC driver, for android.
 * 
 * @author Althaf K Backer <althafkbacker@gmail.com>
 * 
 */
public class MSSQLConnActivity extends Activity implements DBConnectionListener {

	private static final String LOG_TAG = MSSQLConnActivity.class.getName();
	private TextView tvConnectionLog;
	private Dao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvConnectionLog = (TextView) findViewById(R.id.tv_connection_log);
		dao = Dao.instance(this);

	}



	@Override
	public void onResume() {

		super.onResume();
		dao.connect("199.203.64.235:1433", "", "Samara-PC", "music_bar2");

	}

	@Override
	protected void onDestroy() {

		super.onDestroy();
		dao.disconnect();

	}

	@Override
	public void onConnectionStatusInfo(final String status) {

		this.runOnUiThread(new Runnable() {

			@Override
			public void run() {

				tvConnectionLog.setText(tvConnectionLog.getText() + "\n"
						+ status);

			}
		});

	}

	@Override
	public void onConnectionSuccessful() {

		// Once connection is established
		// dao.addUser();
		// dao.delUser();

	}

	@Override
	public void onConnectionFailed() {

		// May be retry or show some error, errors are usually reported in
		// onConnectionStatusInfo()

	}

}