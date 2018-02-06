package com.praire.fire.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by sunlo on 2018/1/5.
 */

public class CommonMethod {


    public String uriToBase64(Uri uri, Context context) {
        String str="";
        Boolean b=true;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    context.getContentResolver(), uri);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();// outputstream

            while (true){
                if (bitmap.getByteCount() > 512000) {

                    Matrix matrix = new Matrix();
                    matrix.setScale(0.9f, 0.9f);
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                            bitmap.getHeight(), matrix, true);

                } else {
                    break;
                }
                Log.d("psize", "psize: "+bitmap.getByteCount());
            }



            bitmap.compress(Bitmap.CompressFormat.JPEG ,100, baos);
            byte[] appicon = baos.toByteArray();// 转为byte数组
            str= Base64.encodeToString(appicon, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return str;
    }


    public void  keepPhotoSize(Bitmap bitmap){


    }
}
