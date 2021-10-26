package com.techbros.myflat;

import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UsersSheet {

    public static ArrayList<UserDetails> extractUsersDetails() {

        ArrayList<UserDetails> arrayList = new ArrayList<>();

//        try {
//            JSONArray list = new JSONArray(sendData);
//            for (int i=0; i<list.length(); i++) {
//                JSONObject users = null;
//                users = list.getJSONObject(i);
//                String index = users.getString("index");
//                String block = users.getString("block");
//                String flatNumber = users.getString("flat_number");
//                String name = users.getString("name");
//                String phone = users.getString("phone");
//                String occupied = users.getString("occupied");
//                String tenantName = users.getString("tenant_name");
//                UserDetails userDetails = new UserDetails(index,block,flatNumber,name,phone,occupied,tenantName);
//                arrayList.add(userDetails);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        arrayList.add(new UserDetails("1","a","a1","qwerty","7868","y","null"));
        arrayList.add(new UserDetails("2","b","a1","qwerty","7868","y","null"));
            return arrayList;

    }
}
