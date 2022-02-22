/*
 * Created by guo on 2015/08/12.
 * Copyright 2015－2021 Zall Data Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zalldata.analytics.android.plugin

import com.android.build.gradle.AppExtension
import com.zalldata.analytics.android.plugin.utils.VersionUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.internal.reflect.Instantiator
import org.gradle.invocation.DefaultGradle

class ZallAnalyticsPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        Instantiator ins = ((DefaultGradle) project.getGradle()).getServices().get(Instantiator)
        def args = [ins] as Object[]
        ZallAnalyticsExtension extension = project.extensions.create("zallAnalytics", ZallAnalyticsExtension, args)
        Map<String, ?> properties = project.getProperties()
        boolean disableZallAnalyticsPlugin = Boolean.parseBoolean(properties.getOrDefault("zallAnalytics.disablePlugin", "false")) ||
                Boolean.parseBoolean(properties.getOrDefault("disableZallAnalyticsPlugin", "false"))
        boolean disableZallAnalyticsMultiThreadBuild = Boolean.parseBoolean(properties.getOrDefault("zallAnalytics.disableMultiThreadBuild", "false"))
        boolean disableZallAnalyticsIncrementalBuild = Boolean.parseBoolean(properties.getOrDefault("zallAnalytics.disableIncrementalBuild", "false"))
        boolean isHookOnMethodEnter = Boolean.parseBoolean(properties.getOrDefault("zallAnalytics.isHookOnMethodEnter", "false"))
        boolean isAndroidTv = Boolean.parseBoolean(properties.getOrDefault("zallAnalytics.isAndroidTv", "false"))

        if (!disableZallAnalyticsPlugin) {
            AppExtension appExtension = project.extensions.findByType(AppExtension.class)
            ZallAnalyticsTransformHelper transformHelper = new ZallAnalyticsTransformHelper(extension, appExtension)
            transformHelper.disableZallAnalyticsIncremental = disableZallAnalyticsIncrementalBuild
            transformHelper.disableZallAnalyticsMultiThread = disableZallAnalyticsMultiThreadBuild
            transformHelper.isHookOnMethodEnter = isHookOnMethodEnter
            VersionUtils.isAndroidTv = isAndroidTv
            appExtension.registerTransform(new ZallAnalyticsTransform(transformHelper))
        } else {
            Logger.error("------------您已关闭了卓尔插件--------------")
        }
    }
}