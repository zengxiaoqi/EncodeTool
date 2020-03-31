package com.example.tools.demo;

import com.example.tools.Util.UTF2GBK;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestUnicode {

    public static void main(String[] args){
        TestUnicode test = new TestUnicode();
        //test.testCreateServer();
        //test.testsendRespons();
        //test.teststopServer();
        try {
            test.testHttp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testHttp() throws IOException {
        String strUtf8 = "00<ap>\n" +
                "  <head>\n" +
                "    <tr_code>300001</tr_code>\n" +
                "    <org_code>90000</org_code>\n" +
                "    <cms_corp_no>10000600</cms_corp_no>\n" +
                "    <user_no>P001</user_no>\n" +
                "    <serial_no></serial_no>\n" +
                "    <req_no>P00120150911130723450</req_no>\n" +
                "    <tr_acdt>20150911</tr_acdt>\n" +
                "    <tr_time>130723</tr_time>\n" +
                "    <channel>1</channel>\n" +
                "    <sign>0</sign>\n" +
                "    <file_flag>0</file_flag>\n" +
                "    <reserved></reserved>\n" +
                "    <session_id></session_id>\n" +
                "    <local_ip></local_ip>\n" +
                "    <mac_addr></mac_addr>\n" +
                "    <internet_ip></internet_ip>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <cert_type></cert_type>\n" +
                "    <cert_no>20150911130654764</cert_no>\n" +
                "    <pay_acno>20200226000001</pay_acno>\n" +
                "    <pay_cur_code>01</pay_cur_code>\n" +
                "    <pay_acname>阿里巴巴账户</pay_acname>\n" +
                "    <pay_accaddr></pay_accaddr>\n" +
                "    <as_flag>0</as_flag>\n" +
                "    <as_acno></as_acno>\n" +
                "    <as_acname></as_acname>\n" +
                "    <subject_code></subject_code>\n" +
                "    <vir_acno></vir_acno>\n" +
                "    <vir_acname></vir_acname>\n" +
                "    <bank_flag>0</bank_flag>\n" +
                "    <area_flag>0</area_flag>\n" +
                "    <urgency_flag>0</urgency_flag>\n" +
                "    <rcv_acno>123400000000074</rcv_acno>\n" +
                "    <rcv_cur_code>01</rcv_cur_code>\n" +
                "    <rcv_acname>模拟核心测试48</rcv_acname>\n" +
                "    <rcv_bank_no></rcv_bank_no>\n" +
                "    <rcv_bank_name></rcv_bank_name>\n" +
                "    <mobiles></mobiles>\n" +
                "    <saverecvinfo_flag>0</saverecvinfo_flag>\n" +
                "    <rcv_accaddr></rcv_accaddr>\n" +
                "    <amt>10.00</amt>\n" +
                "    <booking_flag>0</booking_flag>\n" +
                "    <booking_date></booking_date>\n" +
                "    <booking_time></booking_time>\n" +
                "    <delay_flag>0</delay_flag>\n" +
                "    <purpose>测试</purpose>\n" +
                "    <postscript></postscript>\n" +
                "  </body>\n" +
                "</ap>";
        URL url = new URL("http://127.0.0.1:9002/test");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("POST");

        OutputStream os = httpCon.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "unicode");
        String strUcs  = UTF2GBK.utf8ToUnicode(strUtf8);
        osw.write(strUcs);
        osw.flush();
        osw.close();
        os.close();  //don't forget to close the OutputStream
        httpCon.connect();
        System.out.println(httpCon.getResponseCode());
        System.out.println(httpCon.getResponseMessage());
    }
}
