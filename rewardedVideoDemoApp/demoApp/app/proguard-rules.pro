# Add project specific ProGuard rules here.

#App
-keep public class com.flymob.sample.** { public *; }

# FlyMob
-keep public class com.flymob.sdk.common.** { public *; }
-keepattributes EnclosingMethod

# Facebook
-dontwarn com.facebook.**
-keep public class com.facebook.** { public *; }

# Amazon
-keep class com.amazon.** { *; }
-dontwarn com.amazon.**

# Mopub
-keep public class com.mopub.**
-keepclassmembers class com.mopub.** { public *; }
-keep public class com.mopub.mobileads.* { public *; }
-dontwarn com.mopub.volley.toolbox.**

# Applovin
-keep class com.applovin.** { *; }
-dontwarn com.applovin.**

# Chartboost
-keep class com.chartboost.** { *; }
-dontwarn com.chartboost.**

# Inmobi
-keepattributes SourceFile,LineNumberTable,InnerClasses
-keep class com.inmobi.** { *; }
-dontwarn com.inmobi.**

# Admob
-keep class com.google.android.gms.ads.** { *; }
-keep public class com.google.ads.mediation.* { public *; }

# Unity Ads
-keepattributes JavascriptInterface
-keepattributes SourceFile,LineNumberTable
-keep class com.unity3d.ads.** { *; }
-keep class com.applifier.** { *; }
-dontwarn com.unity3d.ads.**

# Adcolony
-dontnote com.immersion.**
-dontwarn android.webkit.**
-keep class com.adcolony.** { public *; }
-dontwarn com.adcolony.**
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# Vungle
-keep class com.vungle.** { public *; }
-keep class javax.inject.*
-keepattributes *Annotation*, Signature
-keep class dagger.*
-dontwarn com.vungle.**

# Legacy
-keep class org.apache.http.** { public *; }
-dontwarn org.apache.http.**
-dontwarn android.net.http.**

# Google Play Services library 9.0.0 only
-dontwarn android.security.NetworkSecurityPolicy
-keep public @com.google.android.gms.common.util.DynamiteApi class * { *; }