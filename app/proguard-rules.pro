# Keep Hilt and dagger generated code
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.HiltAndroidApp { *; }

# Keep Room entities and DAOs
-keep class androidx.room.** { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }

# Keep kotlinx.serialization-related classes
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault
-keepclassmembers @kotlinx.serialization.Serializable class * {
    *** Companion;
    *** INSTANCE;
}

# Ktor
-keepnames class kotlinx.coroutines.** { *; }
-keepnames class kotlinx.serialization.** { *; }

# Compose tooling
-dontwarn androidx.compose.**
