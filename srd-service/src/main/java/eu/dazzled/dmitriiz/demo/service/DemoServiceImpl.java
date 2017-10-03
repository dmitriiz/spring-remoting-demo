package eu.dazzled.dmitriiz.demo.service;

import eu.dazzled.dmitriiz.demo.model.DemoRequest;
import eu.dazzled.dmitriiz.demo.model.DemoResponse;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashSet;

public class DemoServiceImpl implements DemoService {

    @Override
    public DemoResponse perform(DemoRequest request) {
        try {
            DemoResponse response = new DemoResponse();
            response.setId(request.getId());
            response.setHostname(InetAddress.getLocalHost().getHostName());
            response.setAddresses(new HashSet<>());
            for (Enumeration<NetworkInterface> it1 = NetworkInterface.getNetworkInterfaces(); it1.hasMoreElements();) {
                NetworkInterface ni = it1.nextElement();
                for (Enumeration<InetAddress> it2 = ni.getInetAddresses(); it2.hasMoreElements();) {
                    InetAddress ia = it2.nextElement();
                    if (!ia.isLoopbackAddress()) {
                        response.getAddresses().add(ia.getHostAddress());
                    }
                }
            }
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
