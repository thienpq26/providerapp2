package com.example.providerapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
    StudentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = new ArrayList<>();
        rvStudents = findViewById(R.id.rvStudents);
        setAdapter();
    }

    private void setAdapter() {
        mList.clear();
        contentResolver = getContentResolver();
        uri = Uri.parse("content://com.example.providerapp1.provider.StudentsProvider/students");
        cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                mList.add(new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new StudentsAdapter(mList, this);
        rvStudents.setLayoutManager(layoutManager);
        rvStudents.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {
        uri = Uri.parse("content://com.example.providerapp1.provider.StudentsProvider/students");
        String arrAgrs[] = {mList.get(position).getId() + ""};
        contentResolver.delete(uri, "students_id = ?", arrAgrs);
        setAdapter();
    }
}
