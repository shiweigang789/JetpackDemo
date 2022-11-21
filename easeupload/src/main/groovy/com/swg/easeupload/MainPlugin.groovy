package com.swg.easeupload

import org.gradle.api.Plugin
import org.gradle.api.Project

class MainPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
//        def androidComponents = project.extensions.getByType(AndroidComponentsExtension.class)
//        androidComponents.onVariants(new VariantSelectorImpl(),new Function1() {
//            @Override
//            Object invoke(Object o) {
//                return null
//            }
//        })
//        androidComponents.onVariants{ variant ->
//            variant.instrumentation.transformClassesWith(
//                    MethodRecordTransform.class,
//                    InstrumentationScope.PROJECT
//            ) {}
//            variant.instrumentation.setAsmFramesComputationMode(
//                    FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS
//            )
//        }
    }
}