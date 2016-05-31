# Getting started
## Requirements and Dependencies:

- Android  (API Version 9) and up;
- 2.3.1Google Play Services, we recommend at least 7.0.0 (You should compile your app against the Google Play ServicesLibrary in order to use the  instead of the device ID, as required by Google. Not using AndroidAndroid Advertising IDAdvertising ID may result your submission to the Play Store being rejected.).


## Jar integration

- Get latest sdk and add it to your project

- Upgrade your Android Manifest (add all permissions, activities and services):

```
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
 
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 
    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
 
    <!--for MRAID store picture-->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <!--for MRAID store picture end-->
 
    <uses-feature
        android:name="android.hardware.location"
        android:required="false" />
    <uses-feature
        android:name="android.hardware.location.gps"
        android:required="false" />
 
    <permission
        android:name="${applicationId}.flymob.sdk.broadcast"
        android:label="FlyMob sdk permission"
        android:protectionLevel="signature" />
    <uses-permission android:name="${applicationId}.flymob.sdk.broadcast" />
 
    <application>
        <activity
            android:name="net.flymob.sdk.common.ads.interstitial.activity.FlyMobActivity"
            android:configChanges="keyboardHidden|orientation|screenSize" />
        <!--MRAID video-->
        <activity
            android:name="net.flymob.sdk.common.ads.interstitial.activity.FlyMobVideoActivity"
            android:configChanges="keyboardHidden|orientation|screenSize" />
        <!--MRAID video end-->
        <service
            android:name="net.flymob.sdk.internal.server.FlyMobService"
            android:exported="false" />
    </application>
</manifest>
 
```
**Important:** Replace ${applicationId} by your package name if you are not using gradle.

**Note:** ACCESS_COARSE_LOCATION or ACCESS_FINE_LOCATION are only needed if you want the device to automatically send the user's location for targeting.

- Add Google Play Sevices. Follow the instructions on the Android Developer Site for setting up Google Play Services. We recommend always using the latest version of Google Play Services.

-  You can enable detailed logs by setting FlyMob.setDebugMode(true), or creating empty folder FlyMobSdkLog in the root of the external storage of the device.

##Interstitial

- The FlyMob SDK provides a custom class, FlyMobInterstitial, that handles fetching and displaying fullscreen interstitial ads.

To ensure a smooth experience, you should pre-fetch the content as soon as your Activity is created, then display it if the fetch was successful.

In the Activity in which you want to show the interstitial ad, declare a FlyMobInterstitial instance variable:

```
FlyMobInterstitial mFlyMobInterstitial;
```

- In your Activity’s onCreate() method, instantiate the FlyMobInterstitial using the context and the zoneId (use onCreateView for fragment):

```
mFlyMobInterstitial = new FlyMobInterstitial(this, YOUR_ZONE_ID/*605778 for example*/);
```

- Add listener to your FlyMobInterstitial (you can add more than one listener if it is necessary):

```
mFlyMobInterstitial.addListener(new IFlyMobInterstitialListener() {
    @Override
    public void loaded(FlyMobInterstitial interstitial) {
    }
 
    @Override
    public void failed(FlyMobInterstitial interstitial, ErrorResponse response) {
    }
 
    @Override
    public void shown(FlyMobInterstitial interstitial) {
    }
 
    @Override
    public void clicked(FlyMobInterstitial interstitial) {
    }
 
    @Override
    public void closed(FlyMobInterstitial interstitial) {
    }
 
    @Override
    public void expired(FlyMobInterstitial interstitial) {
    }
});
```

- Now you can display your ad.

Use load() to load add. And show() for showing it.

You should follow the following steps:

- In your onCreate() method, call load() to begin prefetching the ad. It’s important to fetch interstitial ad content before you plan to show it, since it often incorporates rich media and may take some time to load. We suggest prefetching when your Activity is first created, but you may also choose to do it based on events in your app, like beginning a game level.
- When you receive load in your listener, you can display your ad. Also you can check if your ad is loaded by isLoaded(). - If the ad was loaded, but expired by time, you'll receive expired event in your listener.
- You can add logic to the other interstitial lifecycle methods to handle what should happen when your user interacts with the ad.
- Don't forget to call onDestroy() on the interstitial in your Activity’s onDestroy method (use onDestroyView for fragment).
- You can enable writing logs by FlyMob.setDebugMode(true). By default it is switched off.

Your completed Activity code should look something like this:
```
public class InterstitialActivity extends AppCompatActivity {
    Toolbar mToolBar;
    View mButtonLoad;
    View mButtonShow;
    FlyMobInterstitial mFlyMobInterstitial;
    private static final int ZONE_ID = 605778;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
 
        mButtonLoad = findViewById(R.id.button_load);
        mButtonShow = findViewById(R.id.button_show);
 
        initInterstitial();
 
        //To ensure a smooth experience, you should pre-fetch the content as soon
        // as your Activity is created, then display it if the fetch was successful.
        loadInterstitial();
 
        mButtonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadInterstitial();
            }
        });
 
        mButtonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonShow.setEnabled(false);
                if (mFlyMobInterstitial.isLoaded()) {
                    mFlyMobInterstitial.show();
                }
            }
        });
    }
 
    private void loadInterstitial() {
        mButtonShow.setEnabled(false);
        mFlyMobInterstitial.load();
    }
 
    private void initInterstitial() {
        mFlyMobInterstitial = new FlyMobInterstitial(this, ZONE_ID);
        mFlyMobInterstitial.addListener(new IFlyMobInterstitialListener() {
            @Override
            public void loaded(FlyMobInterstitial interstitial) {
                showMessage("loaded");
                mButtonShow.setEnabled(true);
            }
 
            @Override
            public void failed(FlyMobInterstitial interstitial, ErrorResponse response) {
                showMessage("failed " + response.getResponseString());
            }
 
            @Override
            public void shown(FlyMobInterstitial interstitial) {
                showMessage("shown");
            }
 
            @Override
            public void clicked(FlyMobInterstitial interstitial) {
                showMessage("clicked");
            }
 
            @Override
            public void closed(FlyMobInterstitial interstitial) {
                showMessage("closed");
            }
 
            @Override
            public void expired(FlyMobInterstitial interstitial) {
                showMessage("expired");
                loadInterstitial();
            }
        });
    }
 
    @Override
    protected void onDestroy() {
        if (mFlyMobInterstitial != null) {
            mFlyMobInterstitial.onDestroy();
            mFlyMobInterstitial = null;
        }
        super.onDestroy();
    }
 
    public void showMessage(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 70);
        toast.show();
    }
}
```
__See our sample app code.__

## MoPub Mediation

- Integrate MoPub Sdk. Follow their [integration guide](https://github.com/mopub/mopub-android-sdk)
- Integrate FlyMob Sdk. Read Getting started.
- Create Custom Native Network in your MoPub account.
 - Click "Add a Network" in Networks section.
 - Select "Custom Native Network" in opened dialog.
 - Fill "Custom Event Class" field with com.mopub.mobileads.MoPubInterstitialProxy
 - Provide FlyMob zoneId for "Custom Event Class Data":
 ```
 {"zone_id":YOUR_ZONE_ID}
 ```
 
## NativeAd

- The FlyMob SDK provides a custom class, FlyMobNativeAd, that handles loading and util functions for native ads.

In the Activity in which you want to show the NativeAd, declare a FlyMobNativeAd instance variable:

```
FlyMobNativeAd mFlyMobNativeAd;
```

- Instantiate the FlyMobNativeAd using the context and the zoneId:
```
mFlyMobNativeAd = new FlyMobNativeAd(this, YOUR_ZONE_ID/*613296*/); 
```

- Add listener to your FlyMobNativeAd (you can add more than one listener if it is necessary):
```
mFlyMobNativeAd.addListener(new IFlyMobNativeAdListener() {
 
    @Override
    public void loaded(FlyMobNativeAd nativeAd) {
        //now you can display the add
    }
 
    @Override
    public void failed(FlyMobNativeAd nativeAd, ErrorResponse response) {
    }
 
    @Override
    public void shown(FlyMobNativeAd nativeAd) {
    }
 
    @Override
    public void clickUrlOpened(FlyMobNativeAd nativeAd) {
    }
 
    @Override
    public void expired(FlyMobNativeAd nativeAd) {
    }
 
});
```

- Now you can display your ad.

Use load() to load ad. You can set if sdk should preload image and icon or not, use preloadImage(boolean preload) and preloadIcon(boolean preload) before load. By default image and icon are loaded.

When you receive loaded in your listener, you can get all the necessary data from your FlyMobNativeAd object.

NativeAd data and how to get it:
- title (String) - use getTitle()
- text (String) - use getText()
- rating (float) - use getRating()
- cta (call to action, String) - use getCta()
- image (Bitmap - 1200x627)
You can set if sdk should preload image or not (depending on your needs and design) use preloadImage(boolean preload) to turn on and off preloading. By default it is turned on.
When the Ad is loaded you should use displayImage(yourImageView) to display image. Anyway if for some reason you can't use such method, you can get urlString by getImageUrl() (don't forget to set preloadImage(false) in this case). 
We recommend always use displayImage. 
displayImage asynchronously load image and cash it in memory even if preloadImage is set to false.
- icon (Bimap - 80x80)
You can set if sdk should preload icon or not (depending on your needs and design) use preloadIcon(boolean preload) to turn on and off preloading. By default it is turned on.
When the Ad is loaded you should use displayImage(yourImageView) to display icon. Anyway if for some reason you can't use such method, you can get urlString by getIconUrl() (don't forget to set preloadIcon(false) in this case). 
We recommend always use displayImage. 
displayIcon asynchronously load icon and cash it in memory even if preloadIcon is set to false.

- Now you should register your NativeAd container view by registerView(ViewGroup). 

If you use ListView, GridView, RecyclerView or any other design, where you want to show one add more than one time on the screen, you should call registerView for every container (see sample app).

**IMPORTANT:** if your NativeAd container will not be registered by registerView, ad impression will not be counted. (It is thought that ad was shown, if it was on the screen at least 1 second).

- You should open Ad link by openClickUrl().

You can check if opening the url is possible or not by canOpenClickUrl().
```
if (mFlyMobNativeAd.canOpenClickUrl()) {
    mFlyMobNativeAd.openClickUrl();
}
```

- Don't forget to call onDestroy() on the NativeAd in your Activity’s onDestroy method (use onDestroyView for fragment).
 
Your completed code for displaying ad, can look something like this:

```
...
    @Override
    public void loaded(FlyMobNativeAd nativeAd) {
        ToastHelper.showMessage(NativeAdActivity.this, "loaded");
        LayoutInflater layoutInflater = LayoutInflater.from(NativeAdActivity.this);
        ViewGroup nativeAdView = (ViewGroup) layoutInflater.inflate(R.layout.native_ad, null);
        TextView title = (TextView) nativeAdView.findViewById(R.id.title);
        TextView text = (TextView) nativeAdView.findViewById(R.id.text);
        TextView rating = (TextView) nativeAdView.findViewById(R.id.rating);
        Button openUrlButton = (Button) nativeAdView.findViewById(R.id.button_open_url);
        ImageView icon = (ImageView) nativeAdView.findViewById(R.id.icon);
        ImageView image = (ImageView) nativeAdView.findViewById(R.id.image);
 
        title.setText(mFlyMobNativeAd.getTitle());
        text.setText(mFlyMobNativeAd.getText());
        rating.setText(String.format(getString(R.string.rating), mFlyMobNativeAd.getRating()));
        openUrlButton.setText(mFlyMobNativeAd.getCta());
 
        View.OnClickListener openClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFlyMobNativeAd.canOpenClickUrl()) {
                    mFlyMobNativeAd.openClickUrl();
                }
            }
        };
 
        openUrlButton.setOnClickListener(openClickListener);
        image.setOnClickListener(openClickListener);
 
        mFlyMobNativeAd.displayIcon(icon);
        mFlyMobNativeAd.displayImage(image);
 
        //If you don't call registerView, impression will not be counted!
        mFlyMobNativeAd.registerView(nativeAdView);
 
        mNativeAdPlace.removeAllViews();
        mNativeAdPlace.addView(nativeAdView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }
```
See our sample app code.
