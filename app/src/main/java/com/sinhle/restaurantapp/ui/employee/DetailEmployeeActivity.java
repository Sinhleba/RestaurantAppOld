package com.sinhle.restaurantapp.ui.employee;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.sinhle.restaurantapp.R;
import com.sinhle.restaurantapp.databinding.ActivityDetailEmployeeBinding;
import com.sinhle.restaurantapp.ui.database.DatabaseHandler;
import com.sinhle.restaurantapp.ui.entity.Employee;

public class DetailEmployeeActivity extends AppCompatActivity {

    public static String DetailStudent = "DetailStudent";
    ActivityDetailEmployeeBinding binding;
    private DatabaseHandler databaseHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailEmployeeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHandler = new DatabaseHandler(this, DatabaseHandler.DATABASE_NAME, null, DatabaseHandler.DATABASE_VERSION);

        Employee student = getIntent().getParcelableExtra(DetailStudent);
        binding.tvCode.setText(getString(R.string.student_code,student.getStudentCode()));
        binding.tvName.setText(getString(R.string.student_name,student.getStudentName()));
        binding.tvPhone.setText(getString(R.string.student_phone,student.getStudentPhone()));

        initListener();


    }


    private void initListener() {
        try {


            binding.ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
