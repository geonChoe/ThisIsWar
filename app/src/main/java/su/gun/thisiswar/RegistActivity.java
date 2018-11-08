package su.gun.thisiswar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistActivity extends AppCompatActivity {

    private EditText registName, registId, registPw, registPwCheck;
    private Button goLoginActivity;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference DR;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        registName = (EditText) findViewById(R.id.registName);
        registId = (EditText) findViewById(R.id.registId);
        registPw = (EditText) findViewById(R.id.registPw);
        registPwCheck = (EditText) findViewById(R.id.registPwCheck);
        goLoginActivity = (Button) findViewById(R.id.goLoginActivity);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        firebaseAuth = FirebaseAuth.getInstance();

        // 빈공간 제거 registName = registName.trim(); 함수가 작동하지 않는다...... 앞에 String 처럼 무엇인지 위에서 정의하지 않았다.
        // 근데 밑에서 트림함

        // 비밀번호, 비밀번호확인이 일치하는지 아닌지 색깔로 바꿔줌.
        // 텍스트를 변경하기전
        registPwCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                String password = registPw.getText().toString();
                String confirm = registPwCheck.getText().toString();
                if (password.equals(confirm)) {
                    registPw.setBackgroundColor(Color.parseColor("#2ecc71"));
                    registPwCheck.setBackgroundColor(Color.parseColor("#2ecc71"));
                } else {
                    registPw.setBackgroundColor(Color.parseColor("#e74c3c"));
                    registPwCheck.setBackgroundColor(Color.parseColor("#e74c3c"));
                }
            }

            // 텍스트를 변경하는 중
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                String password = registPw.getText().toString();
                String confirm = registPwCheck.getText().toString();
                if (password.equals(confirm)) {
                    registPw.setBackgroundColor(Color.parseColor("#2ecc71"));
                    registPwCheck.setBackgroundColor(Color.parseColor("#2ecc71"));
                } else {
                    registPw.setBackgroundColor(Color.parseColor("#e74c3c"));
                    registPwCheck.setBackgroundColor(Color.parseColor("#e74c3c"));
                }
            }

            // 텍스트를 변경 완료 한 후
            @Override
            public void afterTextChanged(Editable editable) {
                String password = registPw.getText().toString();
                String confirm = registPwCheck.getText().toString();
                if (password.equals(confirm)) {
                    registPw.setBackgroundColor(Color.parseColor("#2ecc71"));
                    registPwCheck.setBackgroundColor(Color.parseColor("#2ecc71"));
                } else {
                    registPw.setBackgroundColor(Color.parseColor("#e74c3c"));
                    registPwCheck.setBackgroundColor(Color.parseColor("#e74c3c"));
                }
            }
        });

        // 회원가입 확인버튼을 누를 경우 온클릭리스너
        goLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                final DatabaseReference RegisterUser = FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectwar-357cd.firebaseio.com/회원정보");

                final String N = registName.getText().toString().trim();
                final String ID = registId.getText().toString().trim();
                String PW = registPw.getText().toString().trim();
                String PWC = registPwCheck.getText().toString().trim();

                if (TextUtils.isEmpty(N)){
                    Toast.makeText(RegistActivity.this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    registName.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ID)){
                    Toast.makeText(RegistActivity.this, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    registId.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(PW)){
                    Toast.makeText(RegistActivity.this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    registPw.requestFocus();
                    return;
                }if (TextUtils.isEmpty(PWC)){
                    Toast.makeText(RegistActivity.this, "비밀번호확인을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    registPwCheck.requestFocus();
                    return;
                }if (!PW.equals(PWC)){
                    Toast.makeText(RegistActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    registPw.setText("");
                    registPwCheck.setText("");
                    registPw.requestFocus();
                    return;
                } else {

                    progressBar.setVisibility(View.VISIBLE);
                                        firebaseAuth.createUserWithEmailAndPassword(ID, PW)
                            .addOnCompleteListener(RegistActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                            Toast.makeText(RegistActivity.this, "회원가입실패, 정확하게 기입사항을 입력해주세요.", Toast.LENGTH_LONG).show();
                                    } else {

                                        Toast.makeText(RegistActivity.this, "회원가입완료", Toast.LENGTH_LONG).show();

                                        FirebaseDatabase.getInstance().getReference().child("사용자").child(N).setValue(new ListViewItem(N, ID));
//                                        DR.child("아이디").setValue(ID);

                                        startActivity(new Intent(RegistActivity.this, LoginActivity.class));
                                        finish();
                                    }
                                }
                            });
                }
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
