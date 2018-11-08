package su.gun.thisiswar;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText loginId, loginPw;
    private Button goRegistActivity, goMainActivity;
    private ProgressBar progressBar2;
    private FirebaseAuth firebaseAuth;
    private Button goFindPwActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        // Get Firebase auth instance
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            Toast.makeText(this, "로그인 하였습니다.", Toast.LENGTH_LONG).show();
            finish();
        }
//        사용자가 로그인 중이면 자동으로 메인액티비티로 넘어간다. 일단 막음

//        툴바가 뭐하는 거지요? 어디다가 쓰는데 포함되어 있나 이거
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        loginId = (EditText) findViewById(R.id.loginId);
        loginPw = (EditText) findViewById(R.id.loginPw);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        goRegistActivity = (Button) findViewById(R.id.goRegistActivity);
        goMainActivity = (Button) findViewById(R.id.goMainActivity);
        firebaseAuth = FirebaseAuth.getInstance();
        goFindPwActivity = (Button) findViewById(R.id.goFindPwActivity);

        Button goFindIdActivity = (Button) findViewById(R.id.goFindIdActivity);
        goFindIdActivity.setPaintFlags(goFindIdActivity.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
//        Button goFindPwActivity = (Button) findViewById(R.id.goFindPwActivity);
//        goFindPwActivity.setPaintFlags(goFindPwActivity.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        // 아이디찾기, 비밀버호 찾기에 중간 줄 귿기.

        goFindPwActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent findPwIntent = new Intent(LoginActivity.this, FindPwActivity.class);
                startActivity(findPwIntent);
            }
        });

        goRegistActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registIntent = new Intent(LoginActivity.this, RegistActivity.class);
                startActivity(registIntent);
            }
        });

        goMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginId.getText().toString();
                final String password = loginPw.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    loginId.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    loginPw.requestFocus();
                    return;
                }

                progressBar2.setVisibility(View.VISIBLE);
                                firebaseAuth.signInWithEmailAndPassword(email, password)
                                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                progressBar2.setVisibility(View.GONE);
                                                if (!task.isSuccessful()){
                                                    Toast.makeText(LoginActivity.this, "이메일과 비밀번호가 정확하지 않습니다.", Toast.LENGTH_SHORT).show();
                                                } else {

//                                                    Toast.makeText(LoginActivity.this, "로그인 하였습니다.", Toast.LENGTH_LONG).show();
                                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                            }
                        });
            }
        });

    }
}
