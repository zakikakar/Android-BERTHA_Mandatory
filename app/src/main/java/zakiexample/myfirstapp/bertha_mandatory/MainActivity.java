package zakiexample.myfirstapp.bertha_mandatory;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String POSTURL = "https://berthabackendrestprovider.azurewebsites.net/api/data";
    //public static final String TAG = "zaki";

    private GestureDetector gestureDetector;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    ArrayList<WristbandData> wristbandList = new ArrayList<>();
    ArrayAdapter<WristbandData> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                boolean leftMovement = e1.getX() < e2.getX();
                if (leftMovement) {
                    finish();
                }
                return true;
            }

//            private boolean DoIt(MotionEvent e1, MotionEvent e2) {
//                boolean leftMovement = e1.getX() < e2.getX();
//                if (leftMovement) {
//                    finish();
//                }
//                return true;
//            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                boolean leftMovement = e1.getX() < e2.getX();
                if (leftMovement) {
                    finish();
                }
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(getApplicationContext(),"Hey", Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }
        });
    }
    //Tjekker hvilken event der skal bruges, flinges der eller scrolles



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_button:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                //intent.putExtra(USERNAME, username);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "You have succesfully logged out", Toast.LENGTH_LONG).show();
                finish();

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onStart() {
        super.onStart();

        ReadTask readTask = new ReadTask();
        readTask.execute(POSTURL + "/zaki1");

        //GET DATA
        final Button button = findViewById(R.id.get_data_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostWristbandData task = new PostWristbandData();
                task.execute("https://berthawristbandrestprovider.azurewebsites.net/api/wristbanddata");
            }
        });

        //LOGOUT
//        final Button logoutBtn = findViewById(R.id.logout_button);
//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                //intent.putExtra(USERNAME, username);
//                startActivity(intent);
//                Toast.makeText(MainActivity.this, "You have succesfully logged out", Toast.LENGTH_LONG).show();
//                finish();
//            }
//        });


    }





    private class PostWristbandData extends AsyncTask<String, Void, WristbandData> {

        @Override
        protected WristbandData doInBackground(String... urls) {

            String urlString = urls[0];
            CharSequence result = "";
            try {
                result = HttpHelper.GetHttpResponse(urlString);


            } catch (IOException ex) {
                cancel(true);
                String errorMessage = ex.getMessage() + "\n" + urlString;
                Log.e("ERROR", errorMessage);

            }

            Gson gson = new GsonBuilder().create();
            final WristbandData wristbandData = gson.fromJson(result.toString(), WristbandData.class);

            Date currentTime = Calendar.getInstance().getTime();
            wristbandData.setUtc(currentTime.getTime()); // SET UTC

            wristbandData.setLatitude(111); // SET LATITUDE

            wristbandData.setLongitude(120); // SET LONGITUDE

            wristbandData.setNoise(new Random().nextInt(30) + 1); // SET NOISE

            wristbandData.setUserId("zaki"); // SET USERID

            String json = gson.toJson(wristbandData);

            //Log.d("zaki", wristbandData.toString());

            //POST
            try {
                URL url = new URL(POSTURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                osw.write(json);
                osw.flush();
                osw.close();
                int responseCode = connection.getResponseCode();
                if (responseCode / 100 != 2) {
                    String responseMessage = connection.getResponseMessage();
                    throw new IOException("HTTP response code: " + responseCode + " " + responseMessage);
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();

            } catch (MalformedURLException ex) {
                cancel(true);
                String message = ex.getMessage() + " " + urlString;
                Log.e("ERROR", message);

            } catch (IOException ex) {
                cancel(true);
                Log.e("ERROR", ex.getMessage());

            }

            return wristbandData;

        }


        @Override
        protected void onPostExecute(WristbandData wristbandData) {
            wristbandList.add(0,wristbandData);
            adapter.notifyDataSetChanged();
        }
    }


    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(final CharSequence jsonString) {


            Gson gson = new GsonBuilder().create();
            final WristbandData[] wristbands = gson.fromJson(jsonString.toString(), WristbandData[].class);

            wristbandList.addAll(Arrays.asList(wristbands));

            ListView listView = findViewById(R.id.main_message_listview);
            adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, wristbandList);

            listView.setAdapter(adapter);

        }

    }
}
