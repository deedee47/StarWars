package com.deedee.fingertips.deestarwars.utilities;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class IpAddressResolution implements Serializable
{
    public IpAddressResolution(){}

    public String getIpAddress()
    {
        String ipAddress = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteAddr();
        if(ipAddress.equalsIgnoreCase("0:0:0:0:0:0:0:1"))
        {
            try
            {
                return InetAddress.getLocalHost().getHostAddress();
            }
            catch (UnknownHostException e)
            {
                return "";
            }
        }
        return ipAddress;
    }
}
