package com.xyxy.coversafetyapp.ui.fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.autonavi.base.amap.mapcore.FileUtil;
import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.xyxy.coversafetyapp.R;
import com.xyxy.coversafetyapp.entity.respond.GetAllCoverErrorBean;
import com.xyxy.coversafetyapp.ui.adapter.RvLogErrorAdapter;
import com.xyxy.coversafetyapp.utils.api.ApiCoverError;
import com.xyxy.coversafetyapp.utils.api.RetrofitNetWork;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarningFragment extends Fragment {


    private RecyclerView mRvWarning;
    private FloatingActionButton mBtnAddError;
    private ApiCoverError apiCoverError = RetrofitNetWork.getApiCoverError();
    private static final String TAG = "WarningFragment";
    private ImageView mImgInfo;
    private byte[] imageByte;
    private Uri fileUri;


    public static WarningFragment newInstance() {
        WarningFragment fragment = new WarningFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_warning, container, false);
        initView(view);
        //适配error的rv
        Observable<GetAllCoverErrorBean> observable = apiCoverError.getAllCoverError(10, 1);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<GetAllCoverErrorBean>() {


                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull GetAllCoverErrorBean getAllCoverErrorBean) {
                        mRvWarning.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                        mRvWarning.setAdapter(new RvLogErrorAdapter(getAllCoverErrorBean,
                                (GetAllCoverErrorBean.PageBean.RecordsBean recordsBean) -> {
                                    //创建dialog
                                    MaterialAlertDialogBuilder madb = new MaterialAlertDialogBuilder(getContext());
                                    View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_error_info, null);
                                    madb.setView(view);
                                    madb.show();
                                    ContentLoadingProgressBar mPbarLoaf = (ContentLoadingProgressBar) view.findViewById(R.id.pbar_loaf);
                                    TextView mTvInfo = (TextView) view.findViewById(R.id.tv_info);
                                    mImgInfo = (ImageView) view.findViewById(R.id.img_info);
                                    MaterialButton mMbtnUploadImage = (MaterialButton) view.findViewById(R.id.mbtn_upload_image);
                                    MaterialButton mMbtnSubmit = (MaterialButton) view.findViewById(R.id.mbtn_submit);
                                    mTvInfo.setText(String.format("Euid：%s", recordsBean.getUid()));
                                    if (recordsBean.getErrorImage() != null && recordsBean.getErrorImage().length() != 0) {
                                        Glide.with(getContext()).load(recordsBean.getErrorImage()).into(mImgInfo);
                                    }
                                    //选择图片
                                    mMbtnUploadImage.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                            intent.setType("image/*");
                                            startActivityForResult(intent, 1);
                                        }
                                    });
                                    mMbtnSubmit.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            //上传文件
                                            try {
                                                if (fileUri != null) {
                                                    //上传
                                                    Log.i(TAG, "onClick: " + imageByte.length);
                                                    //网络请求提交图片
                                                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageByte);
                                                    MultipartBody.Part part = MultipartBody.Part.createFormData("file", uri2file(fileUri), requestBody);
                                                    Observable<Map<String, String>> mapObservable = apiCoverError.putFile(recordsBean.getUid(), part);
                                                    mapObservable.observeOn(AndroidSchedulers.mainThread())
                                                            .subscribeOn(Schedulers.io())
                                                            .subscribe(new Observer<Map<String, String>>() {
                                                                @Override
                                                                public void onSubscribe(@NonNull Disposable d) {

                                                                }

                                                                @Override
                                                                public void onNext(@NonNull Map<String, String> stringStringMap) {
                                                                    Toast.makeText(getContext(), "上传成功", Toast.LENGTH_SHORT).show();
                                                                }

                                                                @Override
                                                                public void onError(@NonNull Throwable e) {
                                                                    Toast.makeText(getContext(), "上传失败", Toast.LENGTH_SHORT).show();
                                                                }

                                                                @Override
                                                                public void onComplete() {

                                                                }
                                                            });
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }));
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        Toast.makeText(getContext(), "网络请求错误", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return view;
    }

    /**
     * 获取图片的回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //选择图片的回调
        if (requestCode == 1) {
            fileUri = data.getData();
            Log.i(TAG, "onActivityResult: " + fileUri);
            ContentResolver contentResolver = getContext().getContentResolver();
            try {
                ParcelFileDescriptor pfd = contentResolver.openFileDescriptor(fileUri, "r");
                FileDescriptor fileDescriptor = pfd.getFileDescriptor();
                Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                mImgInfo.setImageBitmap(bitmap);
                FileInputStream fileInputStream = new FileInputStream(fileDescriptor);
                imageByte = new byte[fileInputStream.available()];
                fileInputStream.read(imageByte);
                pfd.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initView(View view) {
        mRvWarning = (RecyclerView) view.findViewById(R.id.rv_warning);
        mBtnAddError = (FloatingActionButton) view.findViewById(R.id.btn_add_error);
    }

    private String uri2file(Uri uri) {
        String image_path;
        Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            image_path = uri.getPath();
        } else {
            int columnIndexOrThrow = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
            cursor.moveToFirst();
            image_path = cursor.getString(columnIndexOrThrow);
        }
        Log.i(TAG, "uri2file: " + image_path);
        return image_path;
    }
}