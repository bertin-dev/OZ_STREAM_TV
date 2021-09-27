package com.oz_stream.tv.ui.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oz_stream.tv.App;
import com.oz_stream.tv.R;
import com.oz_stream.tv.data.api.TheMovieDbAPI;
import com.oz_stream.tv.data.models.Access_token;
import com.oz_stream.tv.provider.PrefManager;
import com.oz_stream.tv.translate.LocaleHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class IDCode extends Activity {

    @Inject
    TheMovieDbAPI theMovieDbAPI;
    /*@BindView(R.id.text_input_editor_text_activity_id_code)
    EditText text_input_editor_text_activity_id_code;
    @BindView(R.id.linear_layout_id_code_activity)
    LinearLayout linear_layout_id_code_activity;*/
    private PrefManager prf;
    private ProgressDialog register_progress;
    private AlertDialog.Builder build_error;
    private static final String TAG = "IDCode";



    /*authentification phone*/
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.ln_id_code_activity)
    LinearLayout ln_id_code_activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.instance().appComponent().inject(this);
        setContentView(R.layout.activity_idcode);
        ButterKnife.bind(this);
        prf = new PrefManager(getApplicationContext());
        build_error = new AlertDialog.Builder(this);


        if(!prf.getString("ACCESS_TOKEN").equals("")){
            Intent i = new Intent(IDCode.this, MainActivity.class);
            startActivity(i);
            // close this activity
            finish();
        }
    }


    @OnClick(R.id.envoyer)
    void envoyer() {
        /*if (!validatIDCode())
            return;*/
        if (!validatePhone())
            return;
        if (!validatePassword())
            return;
        submitIDCode2(phone.getText().toString(), password.getText().toString());
        //   submitIDCode(text_input_editor_text_activity_id_code.getText().toString());
    }

    private void submitIDCode(String idCode) {

        register_progress = ProgressDialog.show(this, null, getResources().getString(R.string.operation_progress), true);

        theMovieDbAPI.codeID(idCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    //bindIDCodeResponse(response, register_progress, idCode);
                }, e -> {
                    Toast.makeText(this, getString(R.string.annulProcess), Toast.LENGTH_SHORT).show();
                    register_progress.dismiss();
                    Timber.e(e, "Error fetching post id code: %s", e.getMessage());
                });
    }

    private void submitIDCode2(String phone, String password) {
        Log.w(TAG, "submitIDCode2: " + phone  + "---------" + password);
        register_progress = ProgressDialog.show(this, null, getResources().getString(R.string.operation_progress), true);

        theMovieDbAPI.login(phone, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Log.w(TAG, "submitIDCode2: "+response.getAccess_token() );
                    bindIDCodeResponse(response, register_progress, phone, password);
                }, e -> {
                    Toast.makeText(this, "Error fetching login or password: %s---" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    register_progress.dismiss();
                    Timber.e(e, "Error fetching login or password: %s", e.getMessage());
                });
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @SuppressLint("LogNotTimber")
    private void bindIDCodeResponse(Access_token response, ProgressDialog register_progress, String phone, String password) {

        if (response != null) {

            if (response.getAccess_token() != null) {
                Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "bindIDCodeResponse: " + response.getAccess_token());

                prf.setString("ACCESS_TOKEN", response.getAccess_token());
                prf.setString("REFRESH_TOKEN", response.getRefresh_token());

                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                register_progress.dismiss();
                                Intent i = new Intent(IDCode.this, MainActivity.class);
                                startActivity(i);
                                // close this activity
                                finish();
                            }
                        }, 2000
                );


            } else if(response.getSuccess() == false){
                Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
                register_progress.dismiss();
                View view = LayoutInflater.from(IDCode.this).inflate(R.layout.alert_dialog_success, null);
                TextView title = (TextView) view.findViewById(R.id.title);
                TextView statutOperation = (TextView) view.findViewById(R.id.statutOperation);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);
                title.setText(getString(R.string.infoEnTeteAlertDialog));
                imageButton.setImageResource(R.drawable.ic_baseline_cancel_24);
                statutOperation.setText(response.getMessage());
                build_error.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                build_error.setCancelable(false);
                build_error.setView(view);
                build_error.show();
            } else {
                Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
                /*register_progress.dismiss();
                View view = LayoutInflater.from(IDCode.this).inflate(R.layout.alert_dialog_success, null);
                TextView title = (TextView) view.findViewById(R.id.title);
                TextView statutOperation = (TextView) view.findViewById(R.id.statutOperation);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);
                title.setText(getString(R.string.infoEnTeteAlertDialog));
                imageButton.setImageResource(R.drawable.ic_baseline_cancel_24);
                statutOperation.setText(response.getMessage());
                build_error.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                build_error.setCancelable(false);
                build_error.setView(view);
                build_error.show();*/
            }

        } else {
            Toast.makeText(this, getString(R.string.annulProcess), Toast.LENGTH_SHORT).show();
        }
        register_progress.dismiss();
    }


    /*private boolean validatIDCode() {
        if (text_input_editor_text_activity_id_code.getText().toString().trim().isEmpty() || text_input_editor_text_activity_id_code.getText().length() < 3) {
            text_input_editor_text_activity_id_code.setError(getString(R.string.error_short_value));
            requestFocus(text_input_editor_text_activity_id_code);
            return false;
        }
        return true;
    }*/


    private boolean validatePhone() {
        if (phone.getText().toString().trim().isEmpty() || phone.getText().length() < 3) {
            phone.setError(getString(R.string.error_short_value));
            requestFocus(phone);
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty() || password.getText().length() < 3) {
            password.setError(getString(R.string.error_short_value));
            requestFocus(password);
            return false;
        }
        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * attachBaseContext(Context newBase) methode callback permet de verifier la langue au demarrage
     *
     * @param newBase
     * @since 2021
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase));
    }

}