package semana2.curso3.coursera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import semana2.curso3.coursera.bo.User;

public class ConfirmDataActivity extends AppCompatActivity {

    private TextView tv_full_name, tv_birthdate, tv_phone, tv_email, tv_contact_description;
    private Button btn_edit_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tv_full_name = (TextView) findViewById(R.id.tv_full_name);
        tv_birthdate = (TextView) findViewById(R.id.tv_birthdate);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_contact_description = (TextView) findViewById(R.id.tv_contact_description);
        btn_edit_data = (Button) findViewById(R.id.btn_edit_data);
        btn_edit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        User user = new User();
        Bundle bundle = getIntent().getExtras();
        user.setFullName(getString(R.string.full_name) + ": " + bundle.getString("fullName", ""));
        user.setBirthdate(getString(R.string.birthdate) + ": " + bundle.getString("birthdate", ""));
        user.setPhone(getString(R.string.phone) + ": " + bundle.getString("phone", ""));
        user.setEmail(getString(R.string.email) + ": " + bundle.getString("email", ""));
        user.setContactDescription(bundle.getString("contactDescription", ""));
        fillConfirmData(user);
    }

    private void fillConfirmData(User user) {
        tv_full_name.setText(user.getFullName());
        tv_birthdate.setText(user.getBirthdate());
        tv_phone.setText(user.getPhone());
        tv_email.setText(user.getEmail());
        tv_contact_description.setText(user.getContactDescription());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}