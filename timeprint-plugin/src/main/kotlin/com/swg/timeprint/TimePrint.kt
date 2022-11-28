package com.swg.timeprint

import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.swg.timeprint.asm.ScanClassVisitorFactory
import com.swg.timeprint.asm.TimeClassVisitorFactory
import com.swg.timeprint.bean.ScanBean
import com.swg.timeprint.bean.TimeBean
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.Opcodes

class TimePrint : Plugin<Project> {

    override fun apply(project: Project) {
        val androidComponents = project.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
            variant.transformClassesWith(
                ScanClassVisitorFactory::class.java, InstrumentationScope.ALL
            ) {
                it.ignoreOwner.set("com/swg/jetpack/BuildUtils")
                it.listOfScans.set(
                    listOf(
                        ScanBean(
                            "android/os/Build",
                            "BRAND",
                            "Ljava/lang/String;",
                            Opcodes.INVOKESTATIC,
                            "com/swg/jetpack/BuildUtils",
                            "getBrand",
                            "()Ljava/lang/String;"
                        ),
                        ScanBean(
                            "android/os/Build",
                            "MODEL",
                            "Ljava/lang/String;",
                            Opcodes.INVOKESTATIC,
                            "com/swg/jetpack/BuildUtils",
                            "getModel",
                            "()Ljava/lang/String;"
                        ),
                        ScanBean(
                            "android/os/Build",
                            "SERIAL",
                            "Ljava/lang/String;",
                            Opcodes.INVOKESTATIC,
                            "com/swg/jetpack/BuildUtils",
                            "getSerial",
                            "()Ljava/lang/String;"
                        ),
                        ScanBean(  //传感器检测
                            "android/hardware/SensorManager",
                            "getSensorList",
                            "(I)Ljava/util/List;"
                        ),
                    )
                )
            }
            variant.transformClassesWith(
                TimeClassVisitorFactory::class.java, InstrumentationScope.ALL
            ) {
                it.listOfTimes.set(
                    listOf(
                        TimeBean( //具体到方法名称
                            "com/swg/jetpack/MainActivity",
                            "onCreate",
                            "(Landroid/os/Bundle;)V"
                        ),
                        TimeBean( //以包名和执行时间为条件
                            "com/example/fragment/library/base",
                            time = 50L
                        )
                    )
                )
            }
        }

    }

}