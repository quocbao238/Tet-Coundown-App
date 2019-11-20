package bao.bon.tet2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.format.Time;
import android.util.Half;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.DataFormatException;

public class MainActivity extends AppCompatActivity {

    ProgressBar pDay,pHour,pMinute,pSeconds;
    int hour,minutes,seconds,day;
    int hourback,minutesback,secondsback,dayback;
    TextView txtDay,txtHour,txtMinutes,txtSeconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innit();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(500);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar now = Calendar.getInstance();
                                hour = now.get(Calendar.HOUR_OF_DAY);
                                minutes  = now.get(Calendar.MINUTE);
                                seconds = now.get(Calendar.SECOND);
                                day = now.get(Calendar.DAY_OF_YEAR);

                                dayback = 389-day;
                                hourback = 23-hour;
                                secondsback = 59-seconds;
                                minutesback = 59-minutes;

                                pDay.setProgress(dayback);
                                pHour.setProgress(hourback);
                                pMinute.setProgress(minutesback);
                                pSeconds.setProgress(secondsback);

                                txtDay.setText(dayback+"");
                                txtHour.setText(hourback+"");
                                txtMinutes.setText(minutesback+"");
                                txtSeconds.setText(secondsback+"");

                            }
                        });
                    }
                } catch (InterruptedException ex) {
                }
            }
        };
        t.start();



    }

    private void innit() {
        txtDay = findViewById(R.id.textViewDay);
        txtHour = findViewById(R.id.textViewHour);
        txtMinutes = findViewById(R.id.textViewMinutes);
        txtSeconds = findViewById(R.id.textViewSeconds);

        pDay = findViewById(R.id.progress_bar_day);
        pHour = findViewById(R.id.progressBar_hour);
        pMinute = findViewById(R.id.progressBar_minute);
        pSeconds = findViewById(R.id.progressBar_second);
        pSeconds.setMax(60);
        pMinute.setMax(60);
        pHour.setMax(24);
        pDay.setMax(100);
    }
}
