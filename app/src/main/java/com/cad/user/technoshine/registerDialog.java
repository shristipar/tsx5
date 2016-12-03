package com.cad.user.technoshine;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.cad.user.technoshine.fragment.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sony on 12-09-2015.
 */
public class RegisterDialog extends Dialog implements
        android.view.View.OnClickListener{
    Button register;
    public Context c;
    JSONParser jsonParser = new JSONParser();
    EditText email,password,repass,college,contact,country,firstname,lastname;



    public RegisterDialog(Context a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.signup);
        register = (Button) findViewById(R.id.confirmRg);
//        sign_up.setOnClickListener(this);
        register.setOnClickListener(this);
        email=(EditText)findViewById(R.id.email);
        repass=(EditText)findViewById(R.id.repassword);
        college=(EditText)findViewById(R.id.college);
        password=(EditText)findViewById(R.id.password);
        contact=(EditText)findViewById(R.id.contact);
        country=(EditText)findViewById(R.id.country);
        firstname=(EditText)findViewById(R.id.firstname);
        lastname=(EditText)findViewById(R.id.lastname);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.confirmRg:
            call();
                break;

            default:
                break;
        }
        dismiss();
    }

    public void call(){
       new SavetoServer().execute();
    }

    private class SavetoServer extends AsyncTask<String, String, String> {

        String mail=email.getText().toString();
        String pass=password.getText().toString();
        String repassword=repass.getText().toString();
        String strcollege=college.getText().toString();
        String strcountry=country.getText().toString();
        String fname=firstname.getText().toString();
        String lname=lastname.getText().toString();
        String number=contact.getText().toString();
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_DESCRIPTION = "description";
        private static final String reg =  "http://2015.cadnitd.co.in/backend/android_reg.php";
        private ProgressDialog pDialog;
        String message="Some error occured!! Please try again";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        protected String doInBackground(String... args) {

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("var1", mail));
            params.add(new BasicNameValuePair("var2", pass));
            params.add(new BasicNameValuePair("var3", strcollege));
            params.add(new BasicNameValuePair("var4", strcountry));
            params.add(new BasicNameValuePair("var5", fname));
            params.add(new BasicNameValuePair("var6", lname));
            params.add(new BasicNameValuePair("var7", number));
            if(haveNetworkConnection()){
                if(strcountry.equals(null) || number.length()!=10 || strcollege.equals(null) && firstname.equals(null) || lastname.equals(null)){
                    message="Required field(s) missing";
                }


                else if (!isValidPassword(pass)) {
                    message="Password Invalid!! Please enter an alphanumeric password with a minimum of six characters";
                }

                else if(!pass.equals(repassword)){
                    message="Passwords do not match. Try again!!";
                }
                else  if (!isValidEmail(mail)) {
                    message="Invalid email";
                }else{
                    JSONObject json = jsonParser.makeHttpRequest(reg,
                            "POST", params);

                    try {
                        Log.d(" Details", json.toString());
                        int success = json.getInt(TAG_SUCCESS);
                        message=json.getString(TAG_DESCRIPTION);
                        if (success == 1) {
                            Log.e("SUCCEStAG", message);
                        } else {
                            Log.e("SUCCEStAG",message );

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }





            }
            else {
                message="Check your internet connection";
            }

            return null;
        }

        protected void onPostExecute(String s) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();

        }
    }



    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating password with retype password
    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;
        Context context= getContext().getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }
    }




