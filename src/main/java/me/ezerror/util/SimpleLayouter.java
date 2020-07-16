package me.ezerror.util;

import org.openjdk.jol.info.ClassData;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.FieldData;
import org.openjdk.jol.info.FieldLayout;
import org.openjdk.jol.layouters.Layouter;
import org.openjdk.jol.util.MathUtil;
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * The layouter getting the actual VM layout.
 *
 * @author Aleksey Shipilev
 */
public class SimpleLayouter {


    public SimpleClassLayout layout(ClassData data) {
        VirtualMachine vm = VM.current();

        if (data.isArray()) {
            // special case of arrays
            int base = vm.arrayBaseOffset(data.arrayComponentType());
            int scale = vm.arrayIndexScale(data.arrayComponentType());

            long instanceSize = MathUtil.align(base + data.arrayLength() * scale, vm.objectAlignment());

            SortedSet<FieldLayout> result = new TreeSet<>();
            result.add(new FieldLayout(FieldData.create(data.arrayClass(), "<elements>", data.arrayComponentType()), base, scale * data.arrayLength()));
            return new SimpleClassLayout(data, result, vm.arrayHeaderSize(), instanceSize, false);
        }

        Collection<FieldData> fields = data.fields();

        SortedSet<FieldLayout> result = new TreeSet<>();
        for (FieldData f : fields) {
            result.add(new FieldLayout(f, f.vmOffset(), vm.sizeOfField(f.typeClass())));
        }

        long instanceSize;
        if (result.isEmpty()) {
            instanceSize = vm.objectHeaderSize();
        }
        else {
            FieldLayout f = result.last();
            instanceSize = f.offset() + f.size();
            // TODO: This calculation is incorrect if there is a trailing @Contended field, or the instance is @Contended
        }
        instanceSize = MathUtil.align(instanceSize, vm.objectAlignment());
        return new SimpleClassLayout(data, result, vm.objectHeaderSize(), instanceSize, true);
    }

    @Override
    public String toString() {
        return "Current VM Layout";
    }

}
