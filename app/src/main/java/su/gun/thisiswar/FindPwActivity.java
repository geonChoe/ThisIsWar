package su.gun.thisiswar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FindPwActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button resetButton, backButton;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);

        inputEmail = (EditText) findViewById(R.id.inputEmail);
        resetButton = (Button) findViewById(R.id.resetButton);
        backButton = (Button) findViewById(R.id.backButton);
        progressBar3 = (ProgressBar) findViewById(R.id.progressBar3);
        firebaseAuth = FirebaseAuth.getInstance();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(FindPwActivity.this, "이메일을 입력해주세요요", Toast.LENGTH_SHORT).show();
                    inputEmail.requestFocus();
                    return;
                }
                progressBar3.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(FindPwActivity.this, "비밀번호 재설정 메일이 보내졌습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(FindPwActivity.this, "비밀번호 재설정 메일 오류", Toast.LENGTH_SHORT).show();
                                }
                                progressBar3.setVisibility(View.GONE);
                            }
                        });
            }
        });
    }
}
