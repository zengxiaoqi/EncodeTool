package com.example.tools.demo;

import com.alibaba.fastjson.JSON;
import com.example.tools.component.FieldsCollector;
import com.example.tools.demo.vo.User;
import com.example.tools.entity.FieldEntity;

import java.util.Date;
import java.util.Map;

public class Call {

    public static void main(String[] args) throws Exception {

        String jsonStr = "{\n" +
                "\"headTenantId\":  \"000\",\n" +
                "\"headChannel\":  \"11\",\n" +
                "\"headOrgNo\":  \"9999\",\n" +
                "\"headCustNo\":  \"\",\n" +
                "\"headUserNo\":  \"企业端操作员\",\n" +
                "\"headReqDate\":  \"20200714\",\n" +
                "\"headReqTime\":  \"115812\",\n" +
                "\"headReqSerialNo\":  \"n5X7BG7z7KrSDS6mp2sBpb\",\n" +
                "\"headOrigDate\":  \"20200714\",\n" +
                "\"headOrigTime\":  \"115812\",\n" +
                "\"headOrigSerialNo\":  \"n5X7BG7z7KrSDS6mp2sBpb\",\n" +
                "\"language\":  \"1\",\n" +
                "\"custNo\":  \"\",\n" +
                "\"userNo\":  \"企业端操作员\",\n" +
                "\"passwd\":  \"e6aea5a9d314aa65f927abaaec1c2ff8c6d12750dafc67689f2480414215b870\",\n" +
                "\"verificationCode\":  \"\",\n" +
                "\"ruleXml\":  {\n" +
                "\"show\":  {\n" +
                "\"userNo\":  \"用户名称\"\n" +
                "},\n" +
                "\"sign\":  [\"userNo\",  \"passwd\",  \"verificationCode\"]\n" +
                "},\n" +
                "\"originalXml\":  \"<?xml  version='1.0'  encoding='utf-8'?><SignData><TradeType></TradeType>  <Timestamp></Timestamp><Fields><userNo  name=\\\"用户名称:\\\">企业端操作员</userNo><userNo>企业端操作员</userNo><passwd>e6aea5a9d314aa65f927abaaec1c2ff8c6d12750dafc67689f2480414215b870</passwd><verificationCode></verificationCode></Fields></SignData>\",\n" +
                "\"encryptXml\":  \"MIIFEAYKKoEcz1UGAQQCAqCCBQAwggT8AgEBMQ4wDAYIKoEcz1UBgxEFADCCAWAGCiqBHM9VBgEEAgGgggFQBIIBTDw/eG1sIHZlcnNpb249JzEuMCcgZW5jb2Rpbmc9J3V0Zi04Jz8+PFNpZ25EYXRhPjxUcmFkZVR5cGU+PC9UcmFkZVR5cGU+IDxUaW1lc3RhbXA+PC9UaW1lc3RhbXA+PEZpZWxkcz48dXNlck5vIG5hbWU9IueUqOaIt+WQjeensDoiPuS8geS4muerr+aTjeS9nOWRmDwvdXNlck5vPjx1c2VyTm8+5LyB5Lia56uv5pON5L2c5ZGYPC91c2VyTm8+PHBhc3N3ZD5lNmFlYTVhOWQzMTRhYTY1ZjkyN2FiYWFlYzFjMmZmOGM2ZDEyNzUwZGFmYzY3Njg5ZjI0ODA0MTQyMTViODcwPC9wYXNzd2Q+PHZlcmlmaWNhdGlvbkNvZGU+PC92ZXJpZmljYXRpb25Db2RlPjwvRmllbGRzPjwvU2lnbkRhdGE+oIICsTCCAq0wggJRoAMCAQICBSAxJ2FYMAwGCCqBHM9VAYN1BQAwXTELMAkGA1UEBhMCQ04xMDAuBgNVBAoMJ0NoaW5hIEZpbmFuY2lhbCBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTEcMBoGA1UEAwwTQ0ZDQSBURVNUIFNNMiBPQ0ExMTAeFw0yMDA0MjYwNzE1MDZaFw0yMDA3MjYwNzE1MDZaMGQxCzAJBgNVBAYTAkNOMQ4wDAYDVQQKDAVPQ0ExMTEPMA0GA1UECwwGVFBDLVMzMRkwFwYDVQQLDBBPcmdhbml6YXRpb25hbC0xMRkwFwYDVQQDDBBCT0NEMDU4MTAwMDAwMjEzMFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEXwViODxYE7YmKy4TrMSso7Q3jJA3C1/PIg63J/MH15fvqDnFE86thNDrzatG4Y6n4gDtSIAM9KX/05olJXS6xaOB9DCB8TAfBgNVHSMEGDAWgBS+pn5NPXyPoFXmwS8JLiwgQ7NCfzBIBgNVHSAEQTA/MD0GCGCBHIbvKgECMDEwLwYIKwYBBQUHAgEWI2h0dHA6Ly93d3cuY2ZjYS5jb20uY24vdXMvdXMtMTUuaHRtMDkGA1UdHwQyMDAwLqAsoCqGKGh0dHA6Ly8yMTAuNzQuNDIuMy9PQ0ExMS9TTTIvY3JsMzM1MS5jcmwwCwYDVR0PBAQDAgPoMB0GA1UdDgQWBBSx3X2jnW0xhCET5m8e5yORr8cfRjAdBgNVHSUEFjAUBggrBgEFBQcDAgYIKwYBBQUHAwQwDAYIKoEcz1UBg3UFAANIADBFAiAGrQIsLV4sENUxO+V4P+pXUrtmB6eGB7lVJ0+NsvsQbQIhANC1k+DBas79HvPoCrlwQVLdCrga34iapYUv76bdNvJAMYHNMIHKAgEBMGYwXTELMAkGA1UEBhMCQ04xMDAuBgNVBAoMJ0NoaW5hIEZpbmFuY2lhbCBDZXJ0aWZpY2F0aW9uIEF1dGhvcml0eTEcMBoGA1UEAwwTQ0ZDQSBURVNUIFNNMiBPQ0ExMQIFIDEnYVgwDAYIKoEcz1UBgxEFADANBgkqgRzPVQGCLQEFAARAEFWOCJHLiWrRXh3Bp1nELPuI+YHcanmg2K5FLgy/QRJQYLkQGGHanYNQh1EXA7i1oPUJRLYtOAW9jmtLjg5ozw==\",\n" +
                "\"headMenuCode\":  \"crop\",\n" +
                "\"headTrCode\":  \"user\"\n" +
                "}";
        Map<String, Object> jsonMap = JSON.parseObject(jsonStr);
        User user = new User();
        user.setUsername("user109");
        user.setPassword("pwd109");
        user.seteBlog("http://www.cnblogs.com/nick-huang/");
        user.setRegistrationDate(new Date());

        Map<String, FieldEntity> map = FieldsCollector.getFileds(user);
        System.out.println(map);

    }

}