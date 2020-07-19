package me.ezerror.util.simpleclasslayout;

import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.info.FieldLayout;
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.SortedSet;

/**
 * a simple class layout.
 *
 * @author ez
 */
public class SimpleClassLayout {
    private final ClassData classData;
    private final SortedSet<FieldLayout> fields;
    private final int headerSize;
    private final long size;

    public SimpleClassLayout(ClassData classData, SortedSet<FieldLayout> fields, int headerSize, long instanceSize, boolean check) {
        this.classData = classData;
        this.fields = fields;
        this.headerSize = headerSize;
        this.size = instanceSize;
    }

    public static SimpleClassLayout parseInstance(Object instance) {
        return parseInstance(instance, new SimpleLayouter());
    }

    /**
     * Produce the class layout for the given instance, and given layouter.
     * <p>
     * These methods, along with {@link #parseInstance(Object)} are recommended
     * for use when the shape of the object is not known in advance. For example,
     * variable-sized instances (e.g. Java arrays) would not be parsed by
     * properly, because their lengths are encoded in
     * the instance objects, not in classes.
     *
     * @param instance instance to work on
     * @param layouter class layouter
     * @return class layout
     */
    public static SimpleClassLayout parseInstance(Object instance, SimpleLayouter layouter) {
        return layouter.layout(ClassData.parseInstance(instance));
    }

    public String toPrintSimple() {
        return toPrintSimple(classData.instance(), true);
    }

    public String toPrintSimple(Boolean isMoreInfo) {
        return toPrintSimple(classData.instance(), isMoreInfo);
    }

    /**
     * Answer the set of fields, including those in superclasses
     *
     * @return sorted set of fields
     */
    public SortedSet<FieldLayout> fields() {
        return fields;
    }

    public String toPrintSimple(Object instance, Boolean isMoreInf) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        String MSG_GAP = "(alignment/padding gap)";
        String MSG_NEXT_GAP = "(loss due to the next object alignment)";
        int maxTypeLen = "TYPE".length();
        for (FieldLayout f : fields()) {
            maxTypeLen = Math.max(f.typeClass().length(), maxTypeLen);
        }
        maxTypeLen += 2;

        int maxDescrLen = Math.max(MSG_GAP.length(), MSG_NEXT_GAP.length());
        for (FieldLayout f : fields()) {
            maxDescrLen = Math.max(f.shortFieldName().length(), maxDescrLen);
        }
        maxDescrLen += 2;
        if (instance != null) {
            VirtualMachine vm = VM.current();
            if (isMoreInf) {
                pw.print("\n64位Mark Word信息: ");
            }
            String lockSign = "";
            for (long off = 4; off >= 0; off -= 4) {
                int word = vm.getInt(instance, off);
                pw.printf(toBinary((word >> 24) & 0xFF) + " " +
                        toBinary((word >> 16) & 0xFF) + " " +
                        toBinary((word >> 8) & 0xFF) + " " +
                        toBinary((word >> 0) & 0xFF) + " "
                );
                if (off == 0) {
                    String last8 = toBinary((word >> 0) & 0xFF);
                    lockSign = last8.substring(last8.length() - 3);
                }
            }
            if (isMoreInf) {
                pw.print("\n锁标识位为: " + lockSign);
                String lockName = getLockName(lockSign);
                pw.print("\n锁状态为: " + lockName);
            }
        }
        else {
            pw.printf(" %6d %5d %" + maxTypeLen + "s %-" + maxDescrLen + "s %s%n", 0, headerSize(), "", "(object header)", "N/A");
        }
        pw.close();

        return sw.toString();
    }

    /**
     * 获取锁状态
     *
     * @param lockSign
     * @return
     */
    private String getLockName(String lockSign) {
        if ("000".equals(lockSign)) return "轻量级锁、自旋锁";
        if ("010".equals(lockSign)) return "重量级锁";
        if ("011".equals(lockSign)) return "GC标记信息";
        if ("101".equals(lockSign)) return "偏向锁";
        if ("001".equals(lockSign)) return "无锁";
        return "";
    }

    /**
     * Answer header size
     *
     * @return header size
     */
    public int headerSize() {
        return headerSize;
    }

    // very ineffective, so what?
    private static String toHex(int x) {
        String s = Integer.toHexString(x);
        int deficit = 2 - s.length();
        for (int c = 0; c < deficit; c++) {
            s = "0" + s;
        }
        return s;
    }

    // very ineffective, so what?
    private static String toBinary(int x) {
        String s = Integer.toBinaryString(x);
        int deficit = 8 - s.length();
        for (int c = 0; c < deficit; c++) {
            s = "0" + s;
        }
        return s;
    }

}
