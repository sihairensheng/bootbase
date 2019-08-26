package com.bluefox.spring.bootbase.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

//端口随机生成工具类
public class ServerPortUtil {

    Logger logger = LoggerFactory.getLogger(ServerPortUtil.class);

    private final int MAXPORT = 65535;
    private final int MINPORT = 1;

    public int getAvailblePort() {
        Random random = new Random();
        //随机生成1到65535之间的端口
        int port = random.nextInt(MAXPORT - MINPORT + 1) + MINPORT;
        if (!isPortAvailable(port)) getAvailblePort();
        return port;
    }

    private boolean isPortAvailable(int port) {
        //默认不用可用的端口
        boolean isAvailable = false;
        List<String> ipList = getIPs();
        //判断没有被绑定的次数
        int noBindNumber = 0;
        for (String ip : ipList) {
            if (!bindPort(ip, port)) {
                noBindNumber ++;
                continue;
            }else break;
        }
        if(0 < noBindNumber) isAvailable = true;
        return isAvailable;
    }

    private List<String> getIPs() {
        List<String> ipList = new ArrayList<>();
        try {
            //需要考虑多种网卡对应的IP地址
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    String hostAddress = inetAddress.getHostAddress();
                    if ( -1 == hostAddress.indexOf(":") && !inetAddress.isLoopbackAddress()) ipList.add(hostAddress);
                }
            }
        } catch (SocketException socketException) {
            logger.error(socketException.getMessage());
        }
        return ipList;
    }

    private Boolean bindPort(String host, int port)  {
        //默认端口被绑定
        boolean isBind = true;
        try {
            //创建socker如果成功端口就没有被占用
            Socket socket = new Socket();
            socket.bind(new InetSocketAddress(host, port));
            socket.close();
            isBind = false;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return isBind;
    }

}
