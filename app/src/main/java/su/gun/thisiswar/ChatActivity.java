package su.gun.thisiswar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    private FirebaseListAdapter<ChatMessage> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            Intent findPwIntent = new Intent(ChatActivity.this, LoginActivity.class);
            startActivity(findPwIntent);

        } else {
            // User is already signed in. Therefore, display
            // a welcome Toast
            Toast.makeText(this,
                    "Welcome " + FirebaseAuth.getInstance()
                            .getCurrentUser()
                            .getDisplayName(),
                    Toast.LENGTH_LONG)
                    .show();

            // Load chat room contents
            displayChatMessages();
        }

//        displayChatMessages();

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("공개채팅")
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),
                                FirebaseAuth.getInstance()
                                        .getCurrentUser()
                                        .getDisplayName())
                        );

                // Clear the input
                input.setText("");
            }
        });

    }

    private void displayChatMessages() {
        ListView listOfMessages = (ListView)findViewById(R.id.list_of_messages);
        DatabaseReference DR = FirebaseDatabase.getInstance().getReference();
        DatabaseReference messageRef = DR.child("공개채팅");

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
//                R.layout.message, FirebaseDatabase.getInstance().getReference("공개채팅")) {
                R.layout.message, messageRef) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView)v.findViewById(R.id.message_text);
                TextView messageUser = (TextView)v.findViewById(R.id.message_user);
                TextView messageTime = (TextView)v.findViewById(R.id.message_time);

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
//                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                messageTime.setText(DateFormat.format("yyyy-M-d (HH:m)",
                        model.getMessageTime()));
            }
        };

        listOfMessages.setAdapter(adapter);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//                                    Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == SIGN_IN_REQUEST_CODE) {
//            if(resultCode == RESULT_OK) {
//                Toast.makeText(this,
//                        "Successfully signed in. Welcome!",
//                        Toast.LENGTH_LONG)
//                        .show();
//                displayChatMessages();
//            } else {
//                Toast.makeText(this,
//                        "We couldn't sign you in. Please try again later.",
//                        Toast.LENGTH_LONG)
//                        .show();
//
//                // Close the app
//                finish();
//            }
//        }
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 메뉴버튼이 처음 눌려졌을 때 실행되는 콜백메서드 // 메뉴버튼이 눌렀을 때 보여줄 메뉴에 대해서 정의
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_logout:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "로그아웃 하였습니다.", Toast.LENGTH_SHORT).show();
                Intent logout = new Intent(ChatActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
