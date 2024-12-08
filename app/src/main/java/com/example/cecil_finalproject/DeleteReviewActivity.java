package com.example.cecil_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DeleteReviewActivity extends AppCompatActivity {

    TextView delRev_U;
    ListView delRev_L;
    Button delRev_B;

    DatabaseHelper dbHelper;
    DeleteReviewAdapter delR_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_delete_review);

        //Connect GUI elements
        delRev_U = findViewById(R.id.deleteReview_username);
        delRev_L = findViewById(R.id.deleteReview_list);
        delRev_B = findViewById(R.id.deleteReview_button);

        //Set the text.
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");
        delRev_U.setText(loggedUser + ", please choose the review you want to delete.");

        //Call button listener
        delRevBtn();

        //Fill list view
        dbHelper = new DatabaseHelper(this);
        getOneUsersReviews();

        deleteReview();
    }

    private void delRevBtn() {
        delRev_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent dR_to_selDel = new Intent(DeleteReviewActivity.this, SelectDeleteActivity.class);
                dR_to_selDel.putExtra("Username:", loggedUser);
                startActivity(dR_to_selDel);
            }
        });
    }

    private void getOneUsersReviews() {
        Intent cameFrom = getIntent();
        String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

        delR_adapter = new DeleteReviewAdapter(this, dbHelper.oneUsersReviews(loggedUser));
        delRev_L.setAdapter(delR_adapter);
        delR_adapter.notifyDataSetChanged();
    }

    private void deleteReview() {
        delRev_L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent cameFrom = getIntent();
                String loggedUser = (String) cameFrom.getSerializableExtra("Username:");

                Intent delR_to_Welc = new Intent(DeleteReviewActivity.this, WelcomeActivity.class);

                Review reviewToDelete = (Review) adapterView.getItemAtPosition(i);
                Integer revID = reviewToDelete.getRevID();

                dbHelper.deleteReview(revID);

                delR_to_Welc.putExtra("Username:", loggedUser);
                startActivity(delR_to_Welc);
            }
        });
    }
}