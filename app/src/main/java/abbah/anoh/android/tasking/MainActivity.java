package abbah.anoh.android.tasking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.act_main_txt_title)
    TextView txtTitle;

    @BindView(R.id.act_main_txt_subtitle)
    TextView txtSubtitle;

    @BindView(R.id.act_main_txt_no_more)
    TextView txtNoMore;

    @BindView(R.id.act_main_tasks_list)
    RecyclerView tasksListView;

    @BindView(R.id.act_main_btn_add)
    Button btnAddTask;

    private DatabaseReference db;
    private List<Task> tasks;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");

        txtTitle.setTypeface(MMedium);
        txtNoMore.setTypeface(MLight);
        txtSubtitle.setTypeface(MLight);
        btnAddTask.setTypeface(MLight);

        tasksListView.setLayoutManager(new LinearLayoutManager(this));
        tasks = new ArrayList<>();

        // fetch data
        db = FirebaseDatabase.getInstance().getReference().child("tasks");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    tasks.add(snap.getValue(Task.class));
                }
                taskAdapter = new TaskAdapter(MainActivity.this, tasks);
                tasksListView.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick(R.id.act_main_btn_add)
    public void startCreateActivity() {
        startActivity(new Intent(this, CreateTaskActivity.class));
    }
}
