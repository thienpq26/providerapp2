package com.example.providerapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.example.providerapp2.adapter.StudentsAdapter;
import com.example.providerapp2.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements StudentsAdapter.OnNoteListener {

    private RecyclerView rvStudents;
    private ContentResolver contentResolver;
    private Uri uri;
    private Cursor cursor;
    private List<Student> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = new ArrayList<>();
        rvStudents = findViewById(R.id.rvStudents);
        contentResolver = getContentResolver();
        uri = Uri.parse("content://com.example.providerapp1.provider.StudentsProvider/students");
        //Select theo ID:
        //cursor = contentResolver.query(uri, null, "students_id=?", arrayOf("3"), null)

        //Select 1 columns:
        //cursor = contentResolver.query(uri, arrayOf("students_address"), null, null, null)

        //Select all:
        cursor = contentResolver.query(uri, null, null, null, null);
//        if (cursor != null) {
//            do {
//                mList.add(new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
//            } while (cursor.moveToNext());
//            setAdapter(mList);
//        }
    }

    private void setAdapter(List<Student> mList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        StudentsAdapter adapter = new StudentsAdapter(mList, this);
        rvStudents.setLayoutManager(layoutManager);
        rvStudents.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {

    }
}