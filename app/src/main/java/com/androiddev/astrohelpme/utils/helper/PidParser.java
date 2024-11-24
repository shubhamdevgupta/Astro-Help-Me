package com.androiddev.astrohelpme.utils.helper;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class PidParser {
    public HashMap<String, String> parseXml(String xml) {

  /*      xml = "<PidData>\r\n" +
                "   <Data type=\"X\">MjAyMy0wNi0xMFQxNzoxMTo0Mq3AjYvTDm9JqxPThoj7vYjWLW3+MW1tj6cu0368C9Ra2sZifEnHTpyht31OGufbkQk8ku/6GFAslqwDWkrUWFvsPGPLEdOiLJLBQyswUqMk4aEkb+4ZNXkYIFulM3bvpe6D9z0TZHHoNr3bdTlSZ2HPDiRr7Wos8jNny6Q3YYuU2VKVfKmgPGo2dFLk3WFtAzHx1PVOYASUUUdDIw3xtpRmS99gk5D3wlVL/6L4yseSNwWWc0fOsrvZNYxvlZYb2kkaOZ+PMu8MojbQe7uqHgHF3CfFXtK+8rf+zxPLMb7PYVupclmrO4eNeZh7QZxepC5CXtrm7AUfxkWeZDyQPXUElEX59dwCbs0iXfLKSMUO9gU9BpQ2/qifK33Kyrrg59bkY2QZ0u258ZV9ISJKNBfTHiAeA4L/6QbkjQtYtKd2JKzE+4ftLoDItApAy2KLGHgfMayC0i2Pti/BTGxVYOKpGe6/sFOPAKxuhsTnwbcrORaQ+ecpzXPuFebrvF5jyn7D442LWCL/fMSjdzm7W9Zj+d52aoku3GbAx6a3uvXDUy3kqQItUknmfTZflBzTf0UfGm/KjLGCHWdqSkkg0g1loN12oJ32TFYA4OlrpMUP++IdZUpue7YAJ+K5FdJJk3ytL2pVPATyr44Ajq19lof1EEE0W8d94b5VkGEdE1E310IoJAAmMPmLuv3hwP2eyGQXP6AAWruF9viK0mnEQp/Dz1mQybh4JlPjRJUiDc80JUDANqRn4IKr6bi/p2xd8UGCr5Nem2IijiyLSaiMJeBHv6xNy2rtZk7N+DRGYimNEuyjz5aqPp3K57Ltq4W+fJ2Hp1/hboZhESd+ljL7y9i/TKNl8ouXJYrogu2gBoiEXdrTJa7s2YXnAuMVmJjMcjC3Lnx2iPz9YAcyVf6z3KbfOXXnnnvNlEHsO5NgGNzZsCYX+geUpHys/Gu6937MwEXKPq8ZioAK4kbFuwefDcO/tFhJLtMheJqOHHByUL9eCd1iHFuvRBTMUIYny/mOd5KO5B2ougZifI+fZ82gwJu5vf0lUHknpYi9WE8zoV6FEUa2vSMguaJI+SeIE/czj6gd+0uZtYlpUa1T2iVcldvlQJvaUC642KZRdb2medg94D5LmlvjwBu9hq+v0dh27JeAq+bZEa8QRuf055I+TVPO7hNRtO4ndxfjw/PxhCevgm65KQCve4cbI6Tgtg==</Data>\r\n" +
                "   <DeviceInfo dc=\"c6894e84-a2f7-4697-9b67-1f5340fb86c5\" dpId=\"MANTRA.MSIPL\" mc=\"MIIEGjCCAwKgAwIBAgIGAYik5dpgMA0GCSqGSIb3DQEBCwUAMIHqMSowKAYDVQQDEyFEUyBNQU5UUkEgU09GVEVDSCBJTkRJQSBQVlQgTFREIDMxVTBTBgNVBDMTTEItMjAzIFNoYXBhdGggSGV4YSBPcHBvc2l0ZSBHdWphcmF0IEhpZ2ggQ291cnQgUy5HIEhpZ2h3YXkgQWhtZWRhYmFkIC0zODAwNjAxEjAQBgNVBAkTCUFITUVEQUJBRDEQMA4GA1UECBMHR1VKQVJBVDELMAkGA1UECxMCSVQxJTAjBgNVBAoTHE1BTlRSQSBTT0ZURUNIIElORElBIFBWVCBMVEQxCzAJBgNVBAYTAklOMB4XDTIzMDYxMDEwMjQ1N1oXDTIzMDcxMDEwNDE0NlowgbAxJDAiBgkqhkiG9w0BCQEWFXN1cHBvcnRAbWFudHJhdGVjLmNvbTELMAkGA1UEBhMCSU4xEDAOBgNVBAgTB0dVSkFSQVQxEjAQBgNVBAcTCUFITUVEQUJBRDEOMAwGA1UEChMFTVNJUEwxHjAcBgNVBAsTFUJpb21ldHJpYyBNYW51ZmFjdHVyZTElMCMGA1UEAxMcTWFudHJhIFNvZnRlY2ggSW5kaWEgUHZ0IEx0ZDCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBAKwZPGNvXfTcUjO7gxRuX0/qhDFBS0kfsouVf5PLWeikG6WebG83H0I6X3v19nm8B1P1Lc2DKUwCWrmvMCicUgNHMoXsHQ14xewqo6kP/sbaev5hIa/ZFrJNwDoyaeUEI3PZpWc3/0UpbMmiCsvAMRorx2+nB8Mi1zyMN+t95lzkSx/6Ree451XJWSo/hW8czps8CfD7nDO9RWh1KAb/3CGHCaDNqrnPbSUFhxabgucxxAtyT0/eNH3E/bnNaHjvTeT8/V0q1Ovpbw5Ior1T4gyHG6uWypCWksDTIZ/cjWf42gd1RgRJDdVq2L5nGiMSioLWrrq43LaftsraNPPbJtMCAwEAATANBgkqhkiG9w0BAQsFAAOCAQEAHldFVdWbxKSP1ZsaX+XPC981ARkh6mpfVYH0EVVHcAzP2sKwGIkcEho901U1unou4Z8wgZHnOKJnrZywE8pMlm6dpAqrhzdn0Kte2t1rpU8G4mOMLJ7AUJRiPuFmMW6aWY5YWo1W0+wYv1K0qvtxc0rEgRa6uvQv8sVYqiMUQIfNlvW4IQCK6XLFO3vb6lICcXSCV6c4F3oIWhChUpbZAbxQ7/Q3sxqMru66hYVL5lFhlxO0W84UkQi7w1g39dtqUneFhupMYJWebdGbE4bWiIIN+mBTxgZwxv3XBz/A9b+wK2gefewv/Sg9FDDaFO7Dq2PKvTEq3J8BvveCXyuc0A==\" mi=\"MFS100\" rdsId=\"MANTRA.AND.001\" rdsVer=\"1.0.8\">\r\n" +
                "      <additional_info>\r\n" +
                "         <Param name=\"srno\" value=\"2228532\"/>\r\n" +
                "         <Param name=\"sysid\" value=\"665ffbffe4b2fca4\"/>\r\n" +
                "         <Param name=\"ts\" value=\"2023-06-10T17:11:44+05:30\"/>\r\n" +
                "      </additional_info>\r\n" +
                "   </DeviceInfo>\r\n" +
                "   <Hmac>MlFWYvP3I/oknCYgmObe+tk62Ka6Yond6/iAP+md6CTBrh+y2cD+yMcdlO7MpPLi</Hmac>\r\n" +
                "   <Resp errCode=\"0\" errInfo=\"Capture Success\" fCount=\"1\" fType=\"0\" iCount=\"0\" iType=\"0\" nmPoints=\"43\" pCount=\"0\" pType=\"0\" qScore=\"71\"/>\r\n" +
                "   <Skey ci=\"20250923\">mglf0K+erA7WyNgTyaZHaLvDby9aACz0VqQ/aaAlzOfEylsoffEa0ct73kPhOeMVf6HqnaA94+SHemDEfd5Wv32rl12mYPbOOFyOvDDjJ0HQnjmvHcj4+bqtSMXz/37sW5iheYHmRn4OBslN9xCApvsI1tpj+9CHyjTuhOw/dyxxOyCNYeQoLUpEwxmwEjc1eDDRtG7hk0eSj2rXbqupktWp8nB8RlY9VOeibBHzZiFehIqvnx+pgd6d6ueVVQ3YB/P0nGGzcIfnv0NLmqa2HjXZRqOHW4lLeSR1Iwum9d1UgiJf2veV1ESg8UI5capQpGSGAHYZx8u0931qTgpzbg==</Skey>\r\n" +
                "</PidData> \r\n";*/
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        HashMap<String, String> pidData = null;
        try {
            db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));
            try {
                pidData = new HashMap<>();
                Document doc = db.parse(is);
                String message = doc.getDocumentElement().getTextContent();
                System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
                System.out.println("------");
                NodeList list = doc.getElementsByTagName("PidData");
                Node node = list.item(0);
                Element element = (Element) node;
                String Hmac = element.getElementsByTagName("Hmac").item(0).getTextContent();
                String Data = element.getElementsByTagName("Data").item(0).getTextContent();
                System.out.println("Hmac: " + Hmac);
                System.out.println("Data: " + Data);


                //DeviceInfo
                NodeList DeviceInfo = doc.getElementsByTagName("DeviceInfo");
                Node node1 = DeviceInfo.item(0);
                Element elementDeviceInfo = (Element) node1;
                String dc = elementDeviceInfo.getAttribute("dc");
                String dpId = elementDeviceInfo.getAttribute("dpId");
                String mc = elementDeviceInfo.getAttribute("mc");
                String mi = elementDeviceInfo.getAttribute("mi");
                String rdsId = elementDeviceInfo.getAttribute("rdsId");
                String rdsVer = elementDeviceInfo.getAttribute("rdsVer");


                System.out.println("dc: " + dc);
                System.out.println("dpId: " + dpId);
                System.out.println("mc: " + mc);
                System.out.println("mi: " + mi);
                System.out.println("rdsId: " + rdsId);
                System.out.println("rdsVer: " + rdsVer);
                //Resp
                /*    NodeList Resp = doc.getElementsByTagName("Resp");
                Node nodeResp = Resp.item(0);
                Element elementResp = (Element) nodeResp;
                String errCode = elementResp.getAttribute("errCode");
                String errInfo = elementResp.getAttribute("errInfo");
                String fCount = elementResp.getAttribute("fCount");
                String fType = elementResp.getAttribute("fType");
                String iCount = elementResp.getAttribute("iCount");
                String iType = elementResp.getAttribute("iType");
                String nmPoints = elementResp.getAttribute("nmPoints");
                String pCount = elementResp.getAttribute("pCount");
                String pType = elementResp.getAttribute("pType");
                String qScore = elementResp.getAttribute("qScore");
                System.out.println("errCode : " + errCode);
                System.out.println("errInfo :" + errInfo);
                System.out.println("fCount : " + fCount);
                System.out.println("fType :" + fType);
                System.out.println("iCount :" + iCount);
                System.out.println("iType :" + iType);
                System.out.println("nmPoints :" + nmPoints);
                System.out.println("pCount :" + pCount);
                System.out.println("pType :" + pType);
                System.out.println("qScore :" + qScore);*/
                //Skey
                NodeList Skey = doc.getElementsByTagName("Skey");
                Node nodeSkey = Skey.item(0);
                Element elementSkey = (Element) nodeSkey;
                String ci = elementSkey.getAttribute("ci");
                String SkeyData = element.getElementsByTagName("Skey").item(0).getTextContent();
                System.out.println("ci : " + ci);
                System.out.println("Skey :" + SkeyData);

                NodeList additional_info = doc.getElementsByTagName("Param");
                for (int temp = 0; temp < additional_info.getLength(); temp++) {
                    Node nodeadditional_info = additional_info.item(temp);
                    if (nodeadditional_info.getNodeType() == nodeadditional_info.ELEMENT_NODE) {
                        Element elementadditional_info = (Element) nodeadditional_info;

                        String name = elementadditional_info.getAttribute("name");
                        String value = elementadditional_info.getAttribute("value");
                        if (name.equals("srno")) {
                            pidData.put("SRNO", value);
                        } else if (name.equals("sysid")) {
                            pidData.put("SYSID", value);
                        } else if (name.equals("ts")) {
                            pidData.put("TS", value);
                        }
                        System.out.println("Param key: " + name);
                        System.out.println("param value : " + value);
                    }
                }

                pidData.put("HMAC", Hmac);
                pidData.put("DATA", Data);
                pidData.put("DC", dc);
                pidData.put("DPID", dpId);
                pidData.put("mc", mc);
                pidData.put("MI", mi);
                pidData.put("RDSID", rdsId);
                pidData.put("RDSVER", rdsVer);
                pidData.put("CI", ci);
                pidData.put("SKEY", SkeyData);

            } catch (SAXException e) {
                // handle SAXException
            } catch (IOException e) {
                // handle IOException
            }
        } catch (ParserConfigurationException e1) {
            // handle ParserConfigurationException
        }
        return pidData;
    }
}

