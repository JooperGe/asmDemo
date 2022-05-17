package com.gavin.plugin.lifecycle;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author gavin
 * @date 2019/2/18
 * lifecycle class visitor
 */
public class LifecycleClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;

    public LifecycleClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //System.out.println("LifecycleClassVisitor : visit -----> started ：" + name);
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String methodName, String desc, String signature, String[] exceptions) {
        //System.out.println("LifecycleClassVisitor : visitMethod : " + name);
        MethodVisitor mv = cv.visitMethod(access, methodName, desc, signature, exceptions);
        //匹配FragmentActivity
        if ("android/support/v4/app/FragmentActivity".equals(this.mClassName)) {
            if ("onCreate".equals(methodName) ) {
                //处理onCreate
                System.out.println("LifecycleClassVisitor : change method ----> " + methodName);
                return new LifecycleOnCreateMethodVisitor(mv);
            } else if ("onDestroy".equals(methodName)) {
                //处理onDestroy
                System.out.println("LifecycleClassVisitor : change method ----> " + methodName);
                return new LifecycleOnDestroyMethodVisitor(mv);
            }
        }else if("com/jooper/mylibrary/LibASMUtil".equals(this.mClassName)){
            if ("doTest".equals(methodName) ) {
                //处理doTest
                System.out.println("LifecycleClassVisitor : change method ----> " + methodName);
                return new LifecycleDoTestMethodVisitor(mv);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        //System.out.println("LifecycleClassVisitor : visit -----> end");
        super.visitEnd();
    }
}
