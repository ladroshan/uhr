package onno.widget.uhr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Onno extends Activity{

	Timer t = new Timer();
	TimerTask tt;
	TextView tv;
	Handler handler = new Handler();
	DateFormat timeFormat;
	DateFormat dateFormat;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		tv = (TextView)findViewById(R.id.zeit);
		DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
		String datePattern       = ((SimpleDateFormat)dateFormatter).toPattern();
		String localDatePattern  = ((SimpleDateFormat)dateFormatter).toLocalizedPattern();
		DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, Locale.getDefault());
		String timePattern       = ((SimpleDateFormat)timeFormatter).toPattern();
		String localTimePattern  = ((SimpleDateFormat)timeFormatter).toLocalizedPattern();
		timeFormat = new SimpleDateFormat(localTimePattern);
		dateFormat = new SimpleDateFormat(localDatePattern);
		timer();
	}
	
	public void timer()
    {
	    tt = new TimerTask() {
	    	public void run() {
	    		handler.post(new Runnable() {
	    			public void run() {
	    		        Date date = new Date();
	    		        String s = (" " + timeFormat.format(date) + " " +  "\n" + " " +  dateFormat.format(date) + " " + "\n" + "->clock 2.0<-");
	    				tv.setText(s);
	                }
	    		});
	    	}
	    };
	    t.schedule(tt, 0, 100);
    }

	
	public void close(View v)
	{
		Intent appbIntent = new Intent(Intent.ACTION_VIEW);
        appbIntent.setData(Uri.parse("market://details?id=clock.two.oh"));
        this.startActivity(appbIntent);
	}
	
}
