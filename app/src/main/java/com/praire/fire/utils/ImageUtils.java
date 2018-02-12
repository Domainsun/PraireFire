/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.praire.fire.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.provider.MediaStore;
import android.view.View.MeasureSpec;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {
    private static final String[] STORE_IMAGES = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA,};



    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void LoadImage(ImageView view, String path) {
        int measureSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        view.measure(measureSpec, measureSpec);
        view.setImageBitmap(decodeStream(path, view.getMeasuredWidth(), view.getMeasuredHeight()));
    }

    public static void LoadImageWithOutMeasure(ImageView view, String path) {
        try {
            view.setImageBitmap(BitmapFactory.decodeFile(path));
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
    }

    public static synchronized Bitmap decodeStream(String path, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        return decodeStream(path, InSampleSize(options, reqWidth, reqHeight));
    }

    public static Bitmap decodeStream(String path, int inSampleSize) {
        Bitmap bitmap = null;
        InputStream stream = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = inSampleSize;
            options.inPreferredConfig = Config.RGB_565;
            stream = new FileInputStream(path);
            Matrix matrix = new Matrix();
            if (readPictureDegree(path) != 0) {
                matrix.postRotate(readPictureDegree(path));
            }
            bitmap = BitmapFactory.decodeStream(stream, null, options);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError o) {
            return decodeStream(path, inSampleSize * 2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
        return bitmap;
    }

    public static int InSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if (reqHeight > 0 && reqWidth > 0) {
            int height = options.outHeight;
            int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                int heightRatio = Math.round((float) height / (float) reqHeight);
                int widthRatio = Math.round((float) width / (float) reqWidth);
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
            return inSampleSize;
        } else if (reqHeight <= 0 && reqWidth > 0) {
            int width = options.outWidth;

            if (width > reqWidth) {
                return Math.round((float) width / (float) reqWidth);
            } else {
                return 1;
            }
        } else if (reqWidth <= 0 && reqHeight > 0) {
            int height = options.outHeight;

            if (height > reqHeight) {
                return Math.round((float) height / (float) reqHeight);
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }
}
