package sysu.sdcs.sensordatacollector;

import java.util.UUID;

/**
 * Created by justk on 2018/6/13.
 */

public class UUIDUtil {
    public static String generateRandomString(int len) {
        String str = compressedUUID(UUID.randomUUID());
        if(len > str.length()){
            return str;
        }
        else{
            return str.substring(0, len);
        }

    }

    private static String compressedUUID(UUID uuid) {
        byte[] byUuid = new byte[16];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, 8);
        String compressUUID = Base58.encode(byUuid);
        return compressUUID;
    }

    private static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }
}
