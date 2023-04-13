package com.example.bai_2;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    private static final String INTERNAL_FILE_NAME = "lab06_ex2_internal_file.txt";

    private static final String EXTERNAL_FILE_NAME = "lab07_ex2_external_file.txt";

    private String TAG = MainActivity.class.getName();
    private EditText edtContent;
    private Button btnReadInternal,btnReadExternal,btnWriteInternal,btnWriteExternal;
    private TextView txtInternalFilePath, txtSDFilePath;
    private String mySdPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        clickReadInternal();
        clickReadExternal();
        clickWriteInternal();
        clickWriteExternal();
        mySdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        txtInternalFilePath.setText("/data/data/" + getPackageName() + "/" + INTERNAL_FILE_NAME);
        txtSDFilePath.setText(mySdPath + EXTERNAL_FILE_NAME);
    }
    private void initView(){
        edtContent= findViewById(R.id.edtContent);
        btnReadInternal=findViewById(R.id.btnReadInternal);
        btnReadExternal=findViewById(R.id.btnReadExternal);
        btnWriteInternal=findViewById(R.id.btnWriteInternal);
        btnWriteExternal=findViewById(R.id.btnWriteExternal);
        txtInternalFilePath=findViewById(R.id.txtInternalFilePath);
        txtSDFilePath=findViewById(R.id.txtSDFilePath);

    }
    private void clickReadInternal(){
        btnReadInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String internalFileContent = null;
                try {
                    internalFileContent = readInternalFileContent();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                edtContent.setText(internalFileContent);
                Toast.makeText(getApplicationContext(), "Done reading internal file", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
    private void clickReadExternal(){
        btnReadExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextMsg();
                String externalFileContent = null;
                try {
                    externalFileContent = readExternalFileContent();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                edtContent.setText(externalFileContent);
                Toast.makeText(getApplicationContext(), "Done reading SD " + EXTERNAL_FILE_NAME,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickWriteInternal(){
        btnWriteInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    writeInternalFileContent();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(getBaseContext(), "Done writing internal file: " + INTERNAL_FILE_NAME,
                        Toast.LENGTH_SHORT).show();
                clearTextMsg();
            }
        });
    }

    private void clickWriteExternal(){
        btnWriteExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearTextMsg();
                try {
                    writeExternalFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Toast.makeText(getBaseContext(), "Done writing SD file: " + EXTERNAL_FILE_NAME,
                        Toast.LENGTH_SHORT).show();
                clearTextMsg();
            }
        });
    }
    private String readExternalFileContent() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader myReader = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            myReader = new BufferedReader(
                    new InputStreamReader(
                            Files.newInputStream(new File(mySdPath + "/" + EXTERNAL_FILE_NAME).toPath())));
        }

        String aDataRow = "";
        while (true) {
            assert myReader != null;
            if (!((aDataRow = myReader.readLine()) != null)) break;
            stringBuilder.append(aDataRow).append("\n");
        }
        myReader.close();
        return stringBuilder.toString();
    }

    private void writeInternalFileContent() throws IOException {

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                openFileOutput(INTERNAL_FILE_NAME, 0));
        outputStreamWriter.write(edtContent.getText().toString());
        outputStreamWriter.close();
    }

    private void writeExternalFile() throws IOException {

        File myFile = new File(mySdPath + "/" + EXTERNAL_FILE_NAME);
        OutputStreamWriter outputStreamWriter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(myFile.toPath()));
        }

        outputStreamWriter.append(edtContent.getText().toString());
        outputStreamWriter.close();
    }

    private String readInternalFileContent() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = openFileInput(INTERNAL_FILE_NAME);
        if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String aDataRow = "";

            while ((aDataRow = reader.readLine()) != null) {
                stringBuilder.append(aDataRow).append("\n");
            }
            inputStream.close();
        }
        return stringBuilder.toString();
    }





    private void clearTextMsg() {
        // clear text in edittext message
        edtContent.getText().clear();
    }




}