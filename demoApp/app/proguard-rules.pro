# Add project specific ProGuard rules here.

#App
-keep public class com.flymob.sample.** { public *; }

# FlyMob
-keep public class com.flymob.sdk.common.** { public *; }

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

# Avocarrot
-keep class com.avocarrot.** { *; }
-keepclassmembers class com.avocarrot.** { *; }
-dontwarn com.avocarrot.**
-keep public class * extends android.view.View {
  public <init>(android.content.Context);
  public <init>(android.content.Context, android.util.AttributeSet);
  public <init>(android.content.Context, android.util.AttributeSet, int);
  public void set*(...);
}

# Admob
-keep class com.google.android.gms.ads.** { *; }
-keep public class com.google.ads.mediation.* { public *; }

# Legacy
-keep class org.apache.http.** { *; }
-dontwarn org.apache.http.**
-dontwarn android.net.http.**

# Google Play Services library 9.0.0 only
-dontwarn android.security.NetworkSecurityPolicy
-keep public @com.google.android.gms.common.util.DynamiteApi class * { *; }
