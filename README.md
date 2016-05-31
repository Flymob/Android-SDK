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
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
  <uses-permissionandroid:name="android.permission.ACCESS_COARSE_LOCATION" />    
  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
   
<!--for MRAID store picture-->    
    <uses-permissionandroid:name="android.permission.WRITE_EXTERNAL_STORAGE" />    
<!--for MRAID store picture end-->


<uses-feature android:name="android.hardware.location" android:required="false" />    
<uses-feature android:name="android.hardware.location.gps" android:required="false" />    
    
<permission android:name="${applicationId}.flymob.sdk.broadcast" android:label="FlyMob sdk permission"        android:protectionLevel="signature" /> 
 
 <uses-permission android:name="${applicationId}.flymob.sdk.broadcast"/>
 
 <application>
  <activityandroid:name="net.flymob.sdk.common.ads.interstitial.activity.FlyMobActivity" android:configChanges="keyboardHidden|orientation|screenSize"/>
  
<!--MRAID video-->
    <activityandroid:name="net.flymob.sdk.common.ads.interstitial.activity.FlyMobVideoActivity" android:configChanges="keyboardHidden|orientation|screenSize"/>
<!--MRAID video end-->

<service android:name="net.flymob.sdk.internal.server.FlyMobService" android:exported="false" />    </application>
</manifest>
 
```
**Important: Replace ${applicationId} by your package name if you are  using .notgradleNote: ACCESS_COARSE_LOCATION or 
ACCESS_FINE_LOCATION are only needed if you want the device to automatically sendthe user's location for targeting.**

- Add Google Play SevicesFollow the  on the Android Developer Site for setting up Google Play Services. We recommend always using theinstructionslatest version of Google Play Services.

- You can enable detailed logs by setting FlyMob.setDebugMode(true), or creating empty folder  in the root ofFlyMobSdkLogthe external storage of the device.

##Interstitial

1.The FlyMob SDK provides a custom class, FlyMobInterstitial, that handles fetching and displaying fullscreen interstitial ads.To ensure a smooth experience, you should pre-fetch the content as soon, then display it if the fetchsmoothshould pre-fetchas your Activity is createdwas successful.

In the Activity in which you want to show the interstitial ad, declare a FlyMobInterstitial instance variable:
