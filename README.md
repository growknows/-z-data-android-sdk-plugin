## 快速集成

__Gradle 编译环境（Android Studio）__

(1）在 <font color=red size=4 >  **project**  </font>级别的 build.gradle 文件中添加 android-gradle-plugin 依赖：

```android
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.0'
        //添加 android-gradle-plugin 依赖
        classpath 'com.zalldata.analytics.android:android-gradle-plugin:3.8.0'
    }
}

allprojects {
    repositories {
        jcenter()
    }
}
```

（2）在 <font color=red size=4 > **主 module** </font>的 build.gradle 文件中添加 com.zalldata.analytics.android 插件、ZallData Analytics SDK 依赖：

```android
apply plugin: 'com.android.application'
//添加 com.zalldata.analytics.android 插件
apply plugin: 'com.zalldata.analytics.android'

dependencies {
   //添加 Zall Analytics SDK 依赖
   compile 'com.zalldata.analytics.android:ZallDataSDK:4.0.8'
}
```

## License

Copyright 2015－2021 Zall Data Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.gradle_plugin_android_aspectjx
