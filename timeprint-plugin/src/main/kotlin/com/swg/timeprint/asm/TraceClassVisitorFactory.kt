package com.swg.timeprint.asm

import com.android.build.api.instrumentation.InstrumentationParameters
import com.swg.timeprint.bean.TraceBean
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.objectweb.asm.*
import org.objectweb.asm.commons.AdviceAdapter

interface TraceParams : InstrumentationParameters {

    @get:Input
    val packageName: Property<String>

    @get:Input
    val listOfTraces: ListProperty<TraceBean>

}

class TraceClassVisitor(classVisitor: ClassVisitor, val traces: MutableList<TraceBean>) :
    ClassVisitor(Opcodes.ASM9, classVisitor) {

    private var isInterface = false
    private val traceMap: MutableMap<String, TraceBean> = HashMap()

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        isInterface = access and Opcodes.ACC_INTERFACE != 0
        traces.forEach {
            if (it.annotationDesc.isNotBlank()) {
                traceMap[it.annotationDesc] = it
            } else {
                traceMap[it.name + it.desc] = it
            }
        }
    }

    /**
     * 扫描类的方法进行调用
     * @param access 修饰符
     * @param name 方法名字
     * @param descriptor 方法签名
     * @param signature 泛型信息
     * @param exceptions 抛出的异常
     * @return
     */
    override fun visitMethod(
        access: Int,
        name: String,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions)
        val isAbstractMethod = access and Opcodes.ACC_ABSTRACT != 0
        val isNativeMethod = access and Opcodes.ACC_NATIVE != 0
        if (isAbstractMethod || isInterface || isNativeMethod || methodVisitor == null || "<init>" == name || "<clinit>" == name) {
            return methodVisitor
        }
        methodVisitor =
            object : AdviceAdapter(Opcodes.ASM9, methodVisitor, access, name, descriptor) {

                /**
                 * 扫描类的注解时调用
                 * @param annotationDescriptor 注解名称
                 * @param visible
                 * @return
                 */
                override fun visitAnnotation(descriptor: String?, visible: Boolean):
                        AnnotationVisitor {
                    var annotationVisitor = super.visitAnnotation(descriptor, visible)
                    traceMap[descriptor]?.let { trace ->
                        val newTrace = trace.clone()
                        annotationVisitor =
                            object : AnnotationVisitor(Opcodes.ASM9, annotationVisitor) {
                                override fun visit(name: String?, value: Any?) {
                                    super.visit(name, value)
                                    // 保存注解的参数值
                                    name?.let { newTrace.annotationData[it] = value }
                                }

                                override fun visitEnd() {
                                    super.visitEnd()
                                    newTrace.name = name
                                    newTrace.desc = descriptor ?: ""
                                    traceMap[newTrace.name + newTrace.desc] = newTrace
                                }
                            }
                    }
                    return annotationVisitor
                }

                override fun visitInvokeDynamicInsn(
                    name: String?, descriptor: String?, bootstrapMethodHandle: Handle?,
                    vararg bootstrapMethodArguments: Any?
                ) {
                    super.visitInvokeDynamicInsn(
                        name, descriptor, bootstrapMethodHandle, *bootstrapMethodArguments
                    )
                }

                override fun onMethodEnter() {

                }

                override fun onMethodExit(opcode: Int) {

                }
            }
        return methodVisitor
    }

}