package com.digitalized_nepal.localgovernance.utilpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;


/**
 * @author shiva
 */


public class GeneralUtil {
    public static Boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectivityMgr.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;

        return false;
    }

    public static String formatString(float str) {
        return String.format("%.2f", str);

    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static byte[] getBlob(Bitmap bitmap) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        return bArray;
    }


    public static void saveImage(Context context, ImageView imageView) {

        Bitmap b = Bitmap.createBitmap(imageView.getWidth(),
                imageView.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas c = new Canvas(b);

        imageView.draw(c);

        FileOutputStream fos = null;

        try {
            String imagePath = "/sdcard/DCIM/KVWIS"
                    + System.currentTimeMillis() + ".png";
            fos = new FileOutputStream(imagePath);

            if (fos != null) {
                b.compress(Bitmap.CompressFormat.PNG, 100, fos);

                fos.close();
            }
            Toast.makeText(context, "Image saved to:" + imagePath,
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Log.e("testSaveView", "Exception: " + e.toString());
        }

    }


    public static Bitmap getBitmap(byte[] byteArray) {

        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static boolean isFieldValid(EditText view) {
        boolean validation = true;

        if (view.getText().toString().length() == 0) {

            validation = false;
            view.setError("Please enter the value");
        }
        return validation;
    }

    public static String compareDate(String date1Str, String date2Str) {
        try {

            SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
            Date date1;
            date1 = curFormater.parse(date1Str);
            Date date2 = curFormater.parse(date2Str);

            long difference = Math.abs(date1.getTime() - date2.getTime());
            long differenceDates = difference / (24 * 60 * 60 * 1000);

            // Convert long to String
            String dayDifference = Long.toString(differenceDates);

            return dayDifference;

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "0";
    }

    public static int intToDpi(int integer, Context context) {
        return (int) (integer * context.getResources().getDisplayMetrics().density);
    }

    public static String generateRandomAlphaNumeric() {
        String ab = "0123456789ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rd = new Random();

        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(ab.charAt(rd.nextInt(ab.length())));

        }
        return sb.toString();
    }

    public static final String generatemd5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String md5(String s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")), 0, s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLoctionName(Context context, double MyLat, double MyLong) {
        String myLocationName = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(MyLat, MyLong, 1);
            String cityName = addresses.get(0).getAddressLine(0);
            String stateName = addresses.get(0).getAddressLine(1);
            String countryName = addresses.get(0).getAddressLine(2);
            myLocationName = cityName + " " + stateName + " " + countryName;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return myLocationName;
    }

    /**
     * This method checks whether mobile data is enabled.
     *
     * @param context
     * @return
     */

    public boolean isMobileDataEabled(Context context) {
        boolean mobileDataEnabled = false; // Assume disabled
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            Class cmClass = Class.forName(cm.getClass().getName());
            Method method = cmClass.getDeclaredMethod("getMobileDataEnabled");
            method.setAccessible(true); // Make the method callable
            // get the setting for "mobile data"
            mobileDataEnabled = (Boolean) method.invoke(cm);
        } catch (Exception e) {
            // Some problem accessible private API
            // TODO do whatever error handling you want here
        }
        return mobileDataEnabled;
    }

    /**
     * This method is used to convert String into ByteArray for storing purpose
     *
     * @param content
     * @return
     * @throws IOException
     */
    public static byte[] convertStringToByteArray(String content) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(content);
        bos.close();
        return bos.toByteArray();
    }

    public static String convertByteArrayToString(byte[] byteArray) {
        return new String(byteArray);
    }

    /**
     * Adds a touchable padding around a View by constructing a TouchDelegate
     * and adding it to parent View.
     *
     * @param parent     The "outer" parent View
     * @param delegate   The delegate that handles the TouchEvents
     * @param topPadding Additional touch area in pixels above View
     * @param topPadding Additional touch area in pixels left to View
     * @param topPadding Additional touch area in pixels right to View
     * @return A runnable that you can post as action to a Views event queue
     */
    public static Runnable getTouchDelegateAction(final View parent, final View delegate, final int topPadding,
                                                  final int bottomPadding, final int leftPadding, final int rightPadding) {
        return new Runnable() {
            @Override
            public void run() {

                // Construct a new Rectangle and let the Delegate set its values
                Rect touchRect = new Rect();
                delegate.getHitRect(touchRect);

                // Modify the dimensions of the Rectangle
                // Padding values below zero are replaced by zeros
                touchRect.top -= Math.max(0, topPadding);
                touchRect.bottom += Math.max(0, bottomPadding);
                touchRect.left -= Math.max(0, leftPadding);
                touchRect.right += Math.max(0, rightPadding);

                // Now we are going to construct the TouchDelegate
                TouchDelegate touchDelegate = new TouchDelegate(touchRect, delegate);

                // And set it on the parent
                parent.setTouchDelegate(touchDelegate);

            }
        };
    }


    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
         }
        return telephonyManager.getDeviceId();

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());

    }

    public static void getTimeInMillisecond(String oldTime) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss aa");
        formatter.setLenient(false);


        Date oldDate = formatter.parse(oldTime);
        long oldMillis = oldDate.getTime();
        Log.i("time", "time:" + oldMillis);
    }

    public boolean dateComparision(String dateValue1, String dateValue2, int intervalInMin) {
        String sss = "2/7/2017 12:27:54 PM";
        Calendar now = Calendar.getInstance();
//        String datevalue = now.getti

        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        int min = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR);


        String[] dateSplitValue1 = dateValue1.split(" ");
        String[] dateSplitValue2 = dateValue2.split(" ");

        String[] finalDateValue1 = dateSplitValue1[0].split("/");
        String[] finalDateValue2 = dateSplitValue2[0].split("/");

        String[] finalTimeValue1 = dateSplitValue1[1].split(":");
        String[] finalTimeValue2 = dateSplitValue2[1].split(":");


        if (min - Integer.parseInt(finalTimeValue1[1]) < intervalInMin) {
            return true;
        } else {
            return false;
        }


    }

    /**
     * method which returns blur bitmap
     *
     * @param
     * @return blur bitmap
     */
    public static Bitmap getBlurDrawable(Bitmap sentBitmap) {

        int radius = 25;

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
                        | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return bitmap;
    }


}
