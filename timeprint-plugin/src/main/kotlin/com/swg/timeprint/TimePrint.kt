package com.swg.timeprint

import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import com.swg.timeprint.asm.ScanClassVisitorFactory
import com.swg.timeprint.bean.ScanBean
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
                it.ignoreOwner.set("com/example/fragment/library/common/utils/BuildUtils")
                it.listOfScans.set(
                    listOf(
                        ScanBean(
                            "android/os/Build",
                            "BRAND",
                            "Ljava/lang/String;",
                            Opcodes.INVOKESTATIC,
                            "com/example/fragment/library/common/utils/BuildUtils",
                            "getBrand",
                            "()Ljava/lang/String;"
                        ),
                        ScanBean(
                            "android/os/Build",
                            "MODEL",
                            "Ljava/lang/String;",
                            Opcodes.INVOKESTATIC,
                            "com/example/fragment/library/common/utils/BuildUtils",
                            "getModel",
                            "()Ljava/lang/String;"
                        ),
                        ScanBean(
                            "android/os/Build",
                            "SERIAL",
                            "Ljava/lang/String;",
                            Opcodes.INVOKESTATIC,
                            "com/example/fragment/library/common/utils/BuildUtils",
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
        }

    }

}