public class Test {
    public static void main(String[] args) {
        int ip = 0x0201A8C0; // 192.168.1.2 in little endian
        String ipStr = (ip & 255) + "." + ((ip >> 8) & 255) + "." + ((ip >> 16) & 255) + "." + ((ip >> 24) & 255);
        System.out.println(ipStr);
        System.out.println(ipStr.startsWith("192.168.1."));
    }
}
