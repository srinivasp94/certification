package com.example.pegasys.pegasyshmis;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import com.example.pegasys.pegasyshmis.model.uploadDocModel;
import com.example.pegasys.pegasyshmis.network.APIInterface;
import com.example.pegasys.pegasyshmis.network.APIUtils;
import com.example.pegasys.pegasyshmis.utils.FilePath;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPDFActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 100;
    private Button upload;
    private Uri uri;
    private APIInterface mInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);

        upload = (Button) findViewById(R.id.btn_upload_pdf);
        progressDialog = new ProgressDialog(this);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRuntimePermissions();
            }
        });
    }

    private void checkRuntimePermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
            } else {
                selectfilefromLocal();
            }
        } else {
            selectfilefromLocal();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE) {
            selectfilefromLocal();
        } else {
            checkRuntimePermissions();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uri = data.getData();

            String path = FilePath.getPath(this, uri);
            Toast.makeText(this, "" + uri.getPath().toString(), Toast.LENGTH_SHORT).show();
//            String filePath = getRealPathFromURIPath(uri, this);
            File file = new File(path);
            uploadtoServerWS(file);
        }
    }

    private String getRealPathFromURIPath(Uri contentURI, Activity activity) {
       /* Cursor cursor = activity.getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Files.FileColumns.DATA);
            return cursor.getString(idx);
        }*/
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = activity.getContentResolver().query(contentURI, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void selectfilefromLocal() {

        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Pdf"), 1);
    }

    private void uploadtoServerWS(File file) {
        progressDialog.setMessage("uploading");
        progressDialog.setCancelable(false);
        progressDialog.create();
        progressDialog.show();
        MultipartBody.Part part = prepareFilePart("upload", Uri.fromFile(file), file);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), "1");

        mInterface = APIUtils.getAPIService();
        mInterface.uploadPDFfile(name, part).enqueue(new Callback<uploadDocModel>() {
            @Override
            public void onResponse(Call<uploadDocModel> call, Response<uploadDocModel> response) {
                Log.i("Response", response.body().toString());
                progressDialog.dismiss();
                uploadDocModel docModel = response.body();
            }

            @Override
            public void onFailure(Call<uploadDocModel> call, Throwable t) {
                Log.i("Failure", t.toString());
                progressDialog.dismiss();
            }
        });
    }

    private MultipartBody.Part prepareFilePart(String partName, Uri fileUri, File file) {
// https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
// use the FileUtils to get the actual file by uri
        File file1 = new File(fileUri.getPath());

// create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getMimeType(getApplicationContext(), fileUri)),
                        file
                );

// MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    public static String getMimeType(Context context, Uri uri) {
        String extension;

//Check uri format to avoid null
        if (uri.getScheme().equals(ContentResolver.SCHEME_CONTENT)) {
//If scheme is a content
            final MimeTypeMap mime = MimeTypeMap.getSingleton();
            extension = mime.getExtensionFromMimeType(context.getContentResolver().getType(uri));
        } else {
//If scheme is a File
//This will replace white spaces with %20 and also other special characters. This will avoid returning null values on file name with spaces and special characters.
            extension = MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(new File(uri.getPath())).toString());

        }

        return extension;
    }


}
