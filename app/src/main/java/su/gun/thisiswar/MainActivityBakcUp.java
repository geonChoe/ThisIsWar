package su.gun.thisiswar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivityBakcUp extends AppCompatActivity {
//    boolean blog = false; // 폴스는 로그아웃 상태, 로그인을 하지않으면 메인액티비티를 보여주지 않을 것 이므로 필요 없다 아직은.

    private DatabaseReference DR;
    private ArrayList<String> arrayList = new ArrayList<>();
    private TextView mainTV;
    private ListView mainLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DR = FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectwar-357cd.firebaseio.com/사용자");
//
//        mainTV = (TextView) findViewById(R.id.mainTV);
//        listview1 = (ListViewActivity) findViewById(R.id.listview1);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
//        listview1.setAdapter(adapter);
//
//        DR.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String username = dataSnapshot.getKey();
//                arrayList.add(username);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                String username = dataSnapshot.getKey();
//                arrayList.add(username);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//                String username = dataSnapshot.getKey();
//                arrayList.add(username);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//                String username = dataSnapshot.getKey();
//                arrayList.add(username);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // 메뉴버튼이 처음 눌려졌을 때 실행되는 콜백메서드 // 메뉴버튼이 눌렀을 때 보여줄 메뉴에 대해서 정의
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//            case R.id.menu_logout:
//                FirebaseAuth.getInstance().signOut();
//                Intent logout = new Intent(MainActivityBakcUp.this, LoginActivity.class);
//                startActivity(logout);
//        }
//        return super.onOptionsItemSelected(item);
    }
}
