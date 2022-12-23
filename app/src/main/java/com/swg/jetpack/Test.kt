package com.swg.jetpack

import android.util.Log
import com.swg.jetpack.utils.PathUtils
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Opcodes
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class Test {

    fun test() {
        Log.d("DOUBLE_CLICK", "test")
    }

}

//fun main() {
//    val classVisitor = ClassWriter(Opcodes.ASM9)
//    System.out.println("ClassWriter")
//    classVisitor.visit(
//        Opcodes.ASM9,
//        Opcodes.ACC_PUBLIC,
//        "com/swg/jetpack/TestDemo",
//        null,
//        "java/lang/Object",
//        null
//    )
//
//    val visitMethod = classVisitor.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null)
//    visitMethod.visitVarInsn(Opcodes.ALOAD, 0)
//    visitMethod.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
//    visitMethod.visitInsn(Opcodes.RETURN)
//    visitMethod.visitMaxs(0, 0)
//    visitMethod.visitEnd()
//
//    val byteArray = classVisitor.toByteArray()
//    val path =
//        PathUtils.getCurrentClassPath(Human::class.java) + File.separator + "TestDemo.class"
//    val fileOutputStream = FileOutputStream(path)
//    fileOutputStream.write(byteArray)
//}