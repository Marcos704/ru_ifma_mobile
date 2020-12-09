package com.engcria.ruifma_mobile.functions;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.engcria.ruifma_mobile.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HashTest {

    private JsonObjectRequest getRequest;
    private RequestQueue requicicao;
    private Context localContext;
    User user = new User();


    public HashTest(Context localContext) {
        this.localContext = localContext;
    }

    public void chechHash(String email){
        String status = "....";
        String token = "http://192.168.1.55/webServices/token_api.php?email="+email+"";
        requicicao = Volley.newRequestQueue(localContext);
        getRequest = new JsonObjectRequest(Request.Method.GET, token, null,
                response -> {
                    JSONArray jsonArray = response.optJSONArray("hashPassword");
                    JSONObject object = null;
                    try {
                        assert jsonArray != null;
                        object = jsonArray.getJSONObject(0);
                        user.setPassword(object.optString("password"));
                        //Toast.makeText(localContext, "Pass:"+user.getPassword(), Toast.LENGTH_SHORT).show();
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(localContext, "Erro", Toast.LENGTH_SHORT).show();

        });
        requicicao.add(getRequest);
    }
}
