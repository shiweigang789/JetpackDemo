package com.swg.plugin.doubclick.visitor

import com.swg.plugin.LambdaInsnNode
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class DoubleClickClassVisitor extends ClassVisitor {

    String className

    List<LambdaInsnNode> lists

    DoubleClickClassVisitor(int api, ClassVisitor cv, lambdaInsnNodeList) {
        super(api, cv)
        this.lists = lambdaInsnNodeList
    }

    @Override
    void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces)
        className = name
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions)
        DoubleClickMethodVisitor visitor = new DoubleClickMethodVisitor(Opcodes.ASM9, methodVisitor, access, name, desc, className, lists)
        return visitor
    }
}