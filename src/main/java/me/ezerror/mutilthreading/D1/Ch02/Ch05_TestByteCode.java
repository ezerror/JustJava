package me.ezerror.mutilthreading.D1.Ch02;

/**
 * 查看synchronized字节码
 */
public class Ch05_TestByteCode {
    static int counter = 0;
    /**
     * 定义一个对象，作为锁
     */
    static final Object lock = new Object();

    public static void main(String[] args) {
        synchronized (lock) {
            counter++;
        }
    }
}
/*
// class version 52.0 (52)
// access flags 0x21
public class me/ezerror/mutilThreading/D0/Ch02/Ch05_TestByteCode {

  // compiled from: Ch05_TestByteCode.java

  // access flags 0x8
  static I counter

  // access flags 0x18
  final static Ljava/lang/Object; lock

  // access flags 0x1
  public <init>()V
   L0
    LINENUMBER 6 L0
    ALOAD 0
    INVOKESPECIAL java/lang/Object.<init> ()V
    RETURN
   L1
    LOCALVARIABLE this Lme/ezerror/mutilThreading/D0/Ch02/Ch05_TestByteCode; L0 L1 0
    MAXSTACK = 1
    MAXLOCALS = 1

  // access flags 0x9
  public static main([Ljava/lang/String;)V
    TRYCATCHBLOCK L0 L1 L2 null
    TRYCATCHBLOCK L2 L3 L2 null
   L4
    LINENUMBER 14 L4
    GETSTATIC me/ezerror/mutilThreading/D0/Ch02/Ch05_TestByteCode.lock : Ljava/lang/Object;
    DUP
    ASTORE 1
    MONITORENTER
   L0
    LINENUMBER 15 L0
    GETSTATIC me/ezerror/mutilThreading/D0/Ch02/Ch05_TestByteCode.counter : I
    ICONST_1
    IADD
    PUTSTATIC me/ezerror/mutilThreading/D0/Ch02/Ch05_TestByteCode.counter : I
   L5
    LINENUMBER 16 L5
    ALOAD 1
    MONITOREXIT
   L1
    GOTO L6
   L2
   FRAME FULL [[Ljava/lang/String; java/lang/Object] [java/lang/Throwable]
    ASTORE 2
    ALOAD 1
    MONITOREXIT
   L3
    ALOAD 2
    ATHROW
   L6
    LINENUMBER 17 L6
   FRAME CHOP 1
    RETURN
   L7
    LOCALVARIABLE args [Ljava/lang/String; L4 L7 0
    MAXSTACK = 2
    MAXLOCALS = 3

  // access flags 0x8
  static <clinit>()V
   L0
    LINENUMBER 7 L0
    ICONST_0
    PUTSTATIC me/ezerror/mutilThreading/D0/Ch02/Ch05_TestByteCode.counter : I
   L1
    LINENUMBER 11 L1
    NEW java/lang/Object
    DUP
    INVOKESPECIAL java/lang/Object.<init> ()V
    PUTSTATIC me/ezerror/mutilThreading/D0/Ch02/Ch05_TestByteCode.lock : Ljava/lang/Object;
    RETURN
    MAXSTACK = 2
    MAXLOCALS = 0
}

 */