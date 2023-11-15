package com.example.readjsonobjectqphu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    Context myContext;
    int myLayout;
    List<User> arrayUsers;
    public UserAdapter(Context context, int layout, List<User> newsList){
        myContext = context;
        myLayout = layout;
        arrayUsers = newsList;

    }
    @Override
    public int getCount() {
        return arrayUsers.size();
    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(myLayout, null);

        //Ánh xạ và gán giá trị
        TextView txtName = (TextView) convertView.findViewById(R.id.name);
        txtName.setText(arrayUsers.get(position).name);

        TextView txtBirth = (TextView) convertView.findViewById(R.id.birth);
        txtBirth.setText(arrayUsers.get(position).birthDate);

        TextView txtPhone = (TextView) convertView.findViewById(R.id.phone);
        txtPhone.setText(arrayUsers.get(position).phone);

        TextView txtEmail = (TextView) convertView.findViewById(R.id.email);
        txtEmail.setText(arrayUsers.get(position).email);

        return convertView;
    }
}
