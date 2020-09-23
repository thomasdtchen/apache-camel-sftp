package com.turreta.camelspringbootdemo.comturretacamelspringbootdemo;

 
import org.apache.camel.builder.RouteBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;
 
import java.net.URI;
 
@Component
public class SFTPUploadFileRoute extends RouteBuilder
{
 
    @Override
    public void configure() throws Exception
    {
        URI toFtpUrl = new URIBuilder()
                .setScheme("sftp")
                .setHost("192.168.126.120")
                .setPort(22)
                .setPath("/apps/filein")
                .addParameter("initialDelay", "10s")
                .addParameter("delay", "50")
                .addParameter("username", "apprunner")
                //.addParameter("password", "pass")
                .addParameter("privateKeyFile", "/apps/ssl/id_rsa")
                .addParameter("passiveMode", "false")
                .addParameter("useUserKnownHostsFile", "false")
                .build();
 
        StringBuilder fromURL = new StringBuilder();
        fromURL.append("file:/F:/apps/transfer/in?");
        //fromURL.append("antInclude=*_TURRETA_*&");
        fromURL.append("moveFailed=../error&");
        fromURL.append("move=../done&");
        fromURL.append("preMove=../in-progress&");
        fromURL.append("readLock=changed&");
        fromURL.append("readLockMinAge=1m&");
        fromURL.append("readLockTimeout=70000&");
        fromURL.append("readLockCheckInterval=5000");
        from(fromURL.toString()).to(toFtpUrl.toString());
    }
}
