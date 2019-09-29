package com.lulu.architecturedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private UserModel userModel;

    private TextView outTv;
    private EditText firstEt;
    private EditText lastEt;
    private Button saveBt;
    private Button readBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userModel = ViewModelProviders.of(this).get(UserModel.class);
        outTv = findViewById(R.id.text);
        firstEt = findViewById(R.id.edit1);
        lastEt = findViewById(R.id.edit2);
        saveBt = findViewById(R.id.save);
        readBt = findViewById(R.id.read);

        userModel.getmCurUserList().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (users == null) {
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder();
                for (User user : users) {
                    stringBuilder.append(user.firstName).append(" ").append(user.lastName).append("\n");
                }
                outTv.setText(stringBuilder.toString());
                Log.d(TAG, "accept: " + stringBuilder.toString());
            }
        });

        saveBt.setOnClickListener(this);
        readBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                saveUser();
                break;
            case R.id.read:
                readUser();
                break;
        }
    }

    private void saveUser() {
        User user = new User();
        user.firstName = firstEt.getText().toString();
        user.lastName = lastEt.getText().toString();
        UserHandle.getInstance().insert(user).subscribe();
    }

    private void readUser() {
        Disposable subscribe = UserHandle.getInstance().getAllUser().subscribe(new Consumer<List<User>>() {
            @Override
            public void accept(List<User> users) throws Exception {
                userModel.getmCurUserList().setValue(users);
            }
        });
    }
}
