package com.demo.zhangshuibo.springandroiddemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by zhangshuibo on 14-10-10.
 */
public class HelloActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //new HttpRequestTask().execute();
        new HttpRequestTaskNote().execute();
    }

    private class HttpRequestTask extends AsyncTask<Void,Void,Greeting> {
        @Override
        protected Greeting doInBackground(Void... voids) {
            try{
                final String url = "http://192.168.8.113:8080/hello-world";
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters()
                        .add(new SimpleXmlHttpMessageConverter());

                Greeting greeting = restTemplate.getForObject(url,Greeting.class);

                return greeting;
            }catch (Exception e){
                Log.e("message", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Greeting greeting) {
            TextView textView = (TextView)findViewById(R.id.text_value);
            textView.setText(greeting.getId());
        }
    }
    private class HttpRequestTaskNote extends AsyncTask<Void,Void,Note> {

        @Override
        protected Note doInBackground(Void... voids) {
            try{
                final String url = "http://www.w3schools.com/xml/note.xml";
                RestTemplate restTemplate = new RestTemplate();

                restTemplate.getMessageConverters()
                        .add(new SimpleXmlHttpMessageConverter());

                Note note = restTemplate.getForObject(url,Note.class);

                return note;
            }catch (Exception e){
                Log.e("message", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Note note) {
            TextView textView = (TextView)findViewById(R.id.text_value);
            textView.setText(note.getBody());
        }
    }


}
