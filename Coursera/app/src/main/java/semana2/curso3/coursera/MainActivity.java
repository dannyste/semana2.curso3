package semana2.curso3.coursera;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout til_full_name, til_phone, til_email, til_contact_description;
    private TextInputEditText tiet_full_name, tiet_phone, tiet_email, tiet_contact_description;
    private DatePicker dp_birthdate;
    private Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        til_full_name = (TextInputLayout) findViewById(R.id.til_full_name);
        tiet_full_name = (TextInputEditText) findViewById(R.id.tiet_full_name);
        tiet_full_name.addTextChangedListener(new MyTextWatcher(tiet_full_name));
        dp_birthdate = (DatePicker) findViewById(R.id.dp_birthdate);
        til_phone = (TextInputLayout) findViewById(R.id.til_phone);
        tiet_phone = (TextInputEditText) findViewById(R.id.tiet_phone);
        tiet_phone.addTextChangedListener(new MyTextWatcher(tiet_phone));
        til_email = (TextInputLayout) findViewById(R.id.til_email);
        tiet_email = (TextInputEditText) findViewById(R.id.tiet_email);
        tiet_email.addTextChangedListener(new MyTextWatcher(tiet_email));
        til_contact_description = (TextInputLayout) findViewById(R.id.til_contact_description);
        tiet_contact_description = (TextInputEditText) findViewById(R.id.tiet_contact_description);
        tiet_contact_description.addTextChangedListener(new MyTextWatcher(tiet_contact_description));
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidations()) {
                    Intent confirmDataActivity = new Intent(MainActivity.this, ConfirmDataActivity.class);
                    confirmDataActivity.putExtra("fullName", tiet_full_name.getText().toString().trim());
                    confirmDataActivity.putExtra("birthdate", pad(dp_birthdate.getDayOfMonth()) + "/" + pad(dp_birthdate.getMonth() + 1) + "/" + pad(dp_birthdate.getYear()));
                    confirmDataActivity.putExtra("phone", tiet_phone.getText().toString().trim());
                    confirmDataActivity.putExtra("email", tiet_email.getText().toString().trim());
                    confirmDataActivity.putExtra("contactDescription", tiet_contact_description.getText().toString().trim());
                    startActivity(confirmDataActivity);
                }
            }
        });
    }

    public boolean checkValidations(){
        if (!validateFullName()) {
            return false;
        }
        if (!validatePhone()) {
            return false;
        }
        if (!validateEmail()) {
            return false;
        }
        if (!validateContactDescription()) {
            return false;
        }
        return true;
    }

    private boolean validateFullName() {
        if (tiet_full_name.getText().toString().trim().isEmpty()) {
            til_full_name.setError(getString(R.string.required_full_name));
            requestFocus(tiet_full_name);
            return false;
        }
        else {
            til_full_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        if (tiet_phone.getText().toString().trim().isEmpty()) {
            til_phone.setError(getString(R.string.required_phone));
            requestFocus(tiet_phone);
            return false;
        }
        else {
            til_phone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        if (tiet_email.getText().toString().trim().isEmpty()) {
            til_email.setError(getString(R.string.required_email));
            requestFocus(tiet_email);
            return false;
        }
        else if (!isValidEmail(tiet_email.getText().toString().trim())){
            til_email.setError(getString(R.string.incorrect_email));
            requestFocus(tiet_email);
            return false;
        }
        else {
            til_email.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateContactDescription() {
        if (tiet_contact_description.getText().toString().trim().isEmpty()) {
            til_contact_description.setError(getString(R.string.required_contact_description));
            requestFocus(tiet_contact_description);
            return false;
        }
        else {
            til_contact_description.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tiet_full_name:
                    validateFullName();
                    break;
                case R.id.tiet_phone:
                    validatePhone();
                    break;
                case R.id.tiet_email:
                    validateEmail();
                    break;
                case R.id.tiet_contact_description:
                    validateContactDescription();
                    break;
            }
        }

    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}