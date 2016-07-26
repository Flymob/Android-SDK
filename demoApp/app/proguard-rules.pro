# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/a.baskakov/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep public class com.flymob.sample.** { public *; }

-keep public class com.flymob.sdk.common.** { public *; }

#AdMob
-keep public class com.google.android.gms.ads.** { public *; }
-keep public class com.google.ads.** { public *; }

#MoPub
-keepclassmembers class com.mopub.** { public *; }
-keep public class com.mopub.**
-keep public class android.webkit.JavascriptInterface {}
-keep public class com.mopub.mobileads.* { public *; }

-dontwarn com.inmobi.**
-dontwarn com.facebook.**
-dontwarn com.applovin.**
-dontwarn com.amazon.**
-dontwarn com.chartboost.**
-dontwarn com.avocarrot.**
