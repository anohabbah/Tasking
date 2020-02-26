package abbah.anoh.android.tasking;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTaskActivity extends AppCompatActivity {
    @BindView(R.id.act_create_task_txt_title)
    TextView txtPageTitle;

    @BindView(R.id.act_create_task_txt_title_label)
    TextView lblTitle;

    @BindView(R.id.act_create_task_txt_description_label)
    TextView lblDescription;

    @BindView(R.id.act_create_task_txt_date_label)
    TextView lblDate;

    @BindView(R.id.act_create_task_edt_title)
    EditText edtTitle;

    @BindView(R.id.act_create_task_edt_description)
    EditText edtDescription;

    @BindView(R.id.act_create_task_edt_date)
    EditText edtDate;

    @BindView(R.id.act_create_task_create_btn)
    Button btnCreateTask;

    @BindView(R.id.act_create_task_cancel_btn)
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        ButterKnife.bind(this);

        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MMedium.ttf");

        lblTitle.setTypeface(MLight);
        lblDescription.setTypeface(MLight);
        lblDate.setTypeface(MLight);

        edtTitle.setTypeface(MMedium);
        edtDescription.setTypeface(MMedium);
        edtDate.setTypeface(MMedium);

        txtPageTitle.setTypeface(MMedium);

        btnCreateTask.setTypeface(MMedium);
        btnCancel.setTypeface(MLight);
    }

    @OnClick(R.id.act_create_task_create_btn)
    public void createTask() {
        String title = edtTitle.getText().toString();
        String description = edtDescription.getText().toString();
        String date = edtDate.getText().toString();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("tasks");
        db.push().setValue(new Task(title, description, date));

        startActivity(new Intent(this, MainActivity.class));
    }
}
