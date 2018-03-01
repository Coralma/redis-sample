package com.cccis.oa.wechat.utils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class KryoSerializeUtil {

    private static Kryo kryo = new Kryo();

    public static byte[] serialize(Object object) {
        ByteArrayOutputStream out = null;
        Output output = null;
        try {
            out = new ByteArrayOutputStream();
            output = new Output(out, 1024);
            kryo.writeClassAndObject(output, object);
            return output.toBytes();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                }
            }
            if (null != output) {
                output.close();
                output = null;
            }
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        Input input = null;
        try {
            input = new Input(bytes, 0, 1024);
            return kryo.readClassAndObject(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != input) {
                input.close();
                input = null;
            }
        }
        return null;
    }
}
