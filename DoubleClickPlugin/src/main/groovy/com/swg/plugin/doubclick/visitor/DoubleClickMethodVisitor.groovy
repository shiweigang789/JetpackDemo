package com.swg.plugin.doubclick.visitor

import com.swg.plugin.doubclick.DoubleClickConfig
import com.swg.plugin.LambdaInsnNode
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Handle
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter


class DoubleClickMethodVisitor extends AdviceAdapter {

    String className

    public boolean hasCheckClickAnnotation

    long duration

    List<LambdaInsnNode> list

    protected DoubleClickMethodVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor, String className, List<LambdaInsnNode> list) {
        super(api, methodVisitor, access, name, descriptor)
        this.className = className
        this.list = list
    }

    @Override
    void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        if (name == DoubleClickConfig.clickLambdaName && descriptor.contains(DoubleClickConfig.clickLambdaInterfaces)) {

        }
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments)
    }

    @Override
    AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return super.visitAnnotation(descriptor, visible)
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter()
    }

}