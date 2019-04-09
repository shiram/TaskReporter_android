package com.tr.hunter.taskreporter;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import java.io.ByteArrayOutputStream;

public class Register extends AppCompatActivity {

    private EditText user_firstname, user_lastname, employee_id, user_email, user_password;
    private ImageView user_image;
    private Button login, register;

    private String firstname, lastname, emp_id, email, password, item_img_loc, item_img_en, image_file, img_Decodable_Str;

    private AwesomeValidation awesomeValidation;
    private Bitmap item_image_bitmap;
            ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        user_firstname = findViewById(R.id.user_firstname);
        user_lastname = findViewById(R.id.user_lastname);
        employee_id = findViewById(R.id.employee_id);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        user_image = findViewById(R.id.user_image);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);

        user_image.setOnClickListener(mAddImage);
        register.setOnClickListener(mRegister);
        login.setOnClickListener(mLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(Config.REGISTER_PROGRESS);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    }

    private void initGUI(){
        firstname = user_firstname.getText().toString().trim();
        lastname = user_lastname.getText().toString().trim();
        emp_id = employee_id.getText().toString().trim();
        email = user_email.getText().toString().trim();
        password = user_password.getText().toString().trim();
        if(!(img_Decodable_Str.isEmpty() || img_Decodable_Str.equals(""))){
            item_img_en = getStringImage(item_image_bitmap);
            Log.d("IMAGE EN: ", item_img_en);
        }
        addValidation();
    }

    private void addValidation(){
        awesomeValidation.addValidation(user_firstname, RegexTemplate.NOT_EMPTY, getString(R.string.field_fill_err));
        awesomeValidation.addValidation(user_lastname, RegexTemplate.NOT_EMPTY, getString(R.string.field_fill_err));
        awesomeValidation.addValidation(employee_id, RegexTemplate.NOT_EMPTY, getString(R.string.field_fill_err));
        awesomeValidation.addValidation(user_email, RegexTemplate.NOT_EMPTY, getString(R.string.email_err));
        awesomeValidation.addValidation(user_password, RegexTemplate.NOT_EMPTY, getString(R.string.password_err));
    }

    private View.OnClickListener mAddImage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(Register.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                finish();
                startActivity(intent);
                return;

            }else{
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, Config.RESULT_LOAD);
            }

        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == Config.RESULT_LOAD && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                item_img_loc = cursor.getString(columnIndex);
                String fileNameSegments[] = item_img_loc.split("/");
                image_file = fileNameSegments[fileNameSegments.length - 1];
                Log.d("IMAGE FILE: ", image_file);
                img_Decodable_Str = cursor.getString(columnIndex);
                cursor.close();
                // Set the Image in ImageView after decoding the String

                user_image.setImageBitmap(BitmapFactory.decodeFile(img_Decodable_Str));
                item_image_bitmap = BitmapFactory.decodeFile(img_Decodable_Str);

            } else {
                Toast.makeText(this, "No Image Picked",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error Occured, try again.", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private View.OnClickListener mRegister = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            initGUI();
            if(awesomeValidation.validate()){

                progressDialog.show();
                Uploader uploader = new Uploader(Register.this, progressDialog);
                uploader.register(firstname,lastname,emp_id,email,password,image_file,item_img_en);
            }

        }
    };

    private View.OnClickListener mLogin = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);

        }
    };

    private String getStringImage(Bitmap bitmap) {

        BitmapFactory.Options options = null;
        options = new BitmapFactory.Options();

        options.inSampleSize = 3;
        bitmap = BitmapFactory.decodeFile(item_img_loc, options);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] byte_arr = stream.toByteArray();

        String image_en = Base64.encodeToString(byte_arr, 0);

        return image_en;

    }
}
