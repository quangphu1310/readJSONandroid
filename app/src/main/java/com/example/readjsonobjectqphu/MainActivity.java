package com.example.readjsonobjectqphu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvUser;
    ArrayList<User> arrayUsers;
//    ArrayList<String> arrayLink;
    UserAdapter adapter;

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadJSONObject().execute("https://dummyjson.com/users");

        lvUser = (ListView) findViewById(R.id.lvUsers);
        arrayUsers = new ArrayList<>();
        adapter = new UserAdapter(MainActivity.this, R.layout.item_user, arrayUsers);
        lvUser.setAdapter(adapter);
    }
    private class ReadJSONObject extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("users");
                for(int i =0;i< array.length();i++){
                    JSONObject jsonObject = array.getJSONObject(i);
                    String name = jsonObject.getString("lastName");
                    String email = jsonObject.getString("email");
                    String phone = jsonObject.getString("phone");
                    String birthDate = jsonObject.getString("birthDate");

                    arrayUsers.add(new User(name, email, phone, birthDate));
//                    Toast.makeText(MainActivity.this, name + " " + email + " " + phone + " " + birthDate , Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
