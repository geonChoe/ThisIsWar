package su.gun.thisiswar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivityBackUp2 extends AppCompatActivity {
//    boolean blog = false; // 폴스는 로그아웃 상태, 로그인을 하지않으면 메인액티비티를 보여주지 않을 것 이므로 필요 없다 아직은.

    private DatabaseReference DR;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ListView listview1;
//    private ListViewAdapter adapter;
    private FloatingActionButton floating_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



//        DR = FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectwar-357cd.firebaseio.com/사용자");
//
//        listview1 = (ListView) findViewById(R.id.listview1);
//
//        adapter = new ListViewAdapter();
//
//        listview1.setAdapter(adapter);
//
//        // 첫 번째 아이템 추가
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_36dp), "이름", "10점");
//         // 두 번째 아이템 추가
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_36dp), "이름", "50점");
//         // 세 번째 아이템 추가
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_black_36dp), "이름", "100점");
//
//        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View v, int position, long id) {
//                // 겟 아이템
//                ListViewItem item = (ListViewItem) parent.getItemAtPosition(position);
//
//                Drawable iconDrawable = item.getIcon();
//                String titleStr = item.getName();
//                String descStr = item.getEmail();
//                // 투두 : 유즈 아이템 데이터
//            }
//        });
//
//        floating_btn = (FloatingActionButton) findViewById(R.id.floating_btn);
////          플로팅버튼 눌렀을 때
//        floating_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent chatIntent = new Intent(MainActivityBackUp2.this, ChatActivity.class);
//                startActivity(chatIntent);
//            }
//        });
//
//    }
//
////    @Override
////    protected void onStart() {
////        super.onStart();
////
//////        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
////        listview1.setAdapter(adapter);
////        final ArrayList<String> listViewItemList = new ArrayList<>();
////
////        DR.addChildEventListener(new ChildEventListener() {
////            @Override
////            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
////                String titleStr = dataSnapshot.getKey();
////                listViewItemList.add(titleStr);
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
////                String titleStr = dataSnapshot.getKey();
////                listViewItemList.add(titleStr);
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onChildRemoved(DataSnapshot dataSnapshot) {
////                String titleStr = dataSnapshot.getKey();
////                listViewItemList.add(titleStr);
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
////                String titleStr = dataSnapshot.getKey();
////                listViewItemList.add(titleStr);
////                adapter.notifyDataSetChanged();
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////
////            }
////        });
////
////    }
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
//                Toast.makeText(this, "로그아웃 하였습니다.", Toast.LENGTH_SHORT).show();
//                Intent logout = new Intent(MainActivityBackUp2.this, LoginActivity.class);
//                startActivity(logout);
//        }
//        return super.onOptionsItemSelected(item);
    }
}
