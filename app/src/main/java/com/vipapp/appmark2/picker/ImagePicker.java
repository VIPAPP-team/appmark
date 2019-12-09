package com.vipapp.appmark2.picker;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.vipapp.appmark2.R;
import com.vipapp.appmark2.callback.PushCallback;
import com.vipapp.appmark2.item.Image;
import com.vipapp.appmark2.util.Const;
import com.vipapp.appmark2.util.ContextUtils;
import com.vipapp.appmark2.util.FileUtils;
import com.vipapp.appmark2.util.ImageUtils;
import com.vipapp.appmark2.util.wrapper.mActivity;
import com.vipapp.appmark2.util.wrapper.Str;
import com.vipapp.appmark2.widget.RecyclerView;
import com.vipapp.appmark2.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;
import static com.vipapp.appmark2.util.Const.GALLERY_LOADED;
import static com.vipapp.appmark2.util.Const.IMAGE_CLICKED;

public class ImagePicker extends DefaultPicker<Image> {
    private final static int PICK_BITMAP = 18545;

    private RecyclerView images;
    private ProgressBar progress;
    private TextView gallery;
    private TextView internal_storage;

    public ImagePicker(PushCallback<Image> callback) {
        super(callback);
        setView(R.layout.gallery_picker);
        findViews(getView());
        setCallbacks();
    }

    private void findViews(View view){
        gallery = view.findViewById(R.id.gallery);
        internal_storage = view.findViewById(R.id.internal_storage);
        progress = view.findViewById(R.id.progress);
        images = view.findViewById(R.id.images);
    }
    private void setCallbacks(){
        mActivity.get().addOnActivityResultCallback(activityResult -> {
            if(activityResult.getResultCode() == RESULT_OK &&
                    activityResult.getData().getData() != null &&
                    activityResult.getRequestCode() == PICK_BITMAP) {
                try {
                    InputStream inputStream = ContextUtils.context.getContentResolver().openInputStream(activityResult.getData().getData());
                    File file = FileUtils.getFileFromInputStream(new File(Const.TMP_IMAGE_FILE), inputStream);
                    ImageUtils.load(file, bitmap -> {
                        pushItem(new Image(file, bitmap));
                    });
                }catch(FileNotFoundException ignored){}
            }
        });
        internal_storage.setOnClickListener(view -> {
            cancel();
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            mActivity.get().startActivityForResult(Intent.createChooser(intent, str(R.string.select_image)), PICK_BITMAP);
        });
        gallery.setOnClickListener(view -> {
            cancel();
            Intent pickIntent = new Intent(Intent.ACTION_PICK);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(pickIntent, "Select Image");
            mActivity.get().startActivityForResult(chooserIntent, PICK_BITMAP);
        });
        images.addOnPushCallback(item -> {
            switch(item.getType()) {
                case GALLERY_LOADED:
                    progress.setVisibility(View.GONE); break;
                case IMAGE_CLICKED:
                    cancel();
                    Image image = (Image) item.getInstance();
                    ImageUtils.load(image.getFile(), bitmap -> {
                        image.setBitmap(bitmap);
                        pushItem(image);
                    });
            }
        });
    }

    private String str(int res){
        return Str.get(res);
    }

}
