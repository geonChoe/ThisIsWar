package su.gun.thisiswar.firstlogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import su.gun.thisiswar.R;

public class UserFirstLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int SELECT_PHOTO = 1;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private EditText nameEditText;
    private CircleImageView profileImageView;
    private Button startButton;

    private boolean hasImageChaged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstlogin);

        String image = getIntent().getStringExtra("image");
        // getIntent() 자신을 호출한 Activity의 인텐트값을 받는다는 말이다.
        // putExtra("image", "값") 으로 전달되는 인텐트를 받음
        // String image = "값"

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference();

//        nameEditText = (EditText) findViewById(R.id.nameEditText);
//        if (firebaseUser.getDisplayName() != null && firebaseUser.getDisplayName().length() > 0)
//            nameEditText.setText(firebaseUser.getDisplayName());
        profileImageView = (CircleImageView) findViewById(R.id.profileImageView);
        startButton = (Button) findViewById(R.id.startButton);

        profileImageView.setOnClickListener(this);
        startButton.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECT_PHOTO :
                if (requestCode == RESULT_OK) {
                    try {
                        final Uri imageUri = data.getData();
                        // Uniform Resource Identifier
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        profileImageView.setImageBitmap(selectedImage);
                        hasImageChaged = true;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @Override
    public void onClick(View view) {

    }
}
