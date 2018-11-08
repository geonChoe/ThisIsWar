package su.gun.thisiswar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
//    boolean blog = false; // 폴스는 로그아웃 상태, 로그인을 하지않으면 메인액티비티를 보여주지 않을 것 이므로 필요 없다 아직은.

    private FirebaseListAdapter<ListViewItem> adapter;

    private FloatingActionButton floating_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        floating_btn = (FloatingActionButton) findViewById(R.id.floating_btn);
//          플로팅버튼 눌렀을 때
        floating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chatIntent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(chatIntent);
            }
        });


        displayFriendsList();
    }

    private void displayFriendsList() {
        ListView listOfFriends = (ListView) findViewById(R.id.listview1);
        DatabaseReference DR = FirebaseDatabase.getInstance().getReference();
        DatabaseReference Ref = DR.child("사용자");
        adapter = new FirebaseListAdapter<ListViewItem>(this, ListViewItem.class,
                R.layout.activity_main_listview, Ref) {
            @Override
            protected void populateView(View v, ListViewItem model, int position) {
                // Get references to the views of message.xml
//                ImageView friendsImage = (ImageView)v.findViewById(R.id.imageView1);
                TextView friendsName = (TextView)v.findViewById(R.id.textView1);
                TextView friendsReputation = (TextView)v.findViewById(R.id.textView2);

                // Set their text
                friendsName.setText(model.getName());
                friendsReputation.setText(model.getEmail());
            }
        };
        listOfFriends.setAdapter(adapter);
    }

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
                Intent logout = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logout);
        }
        return super.onOptionsItemSelected(item);
    }
}
