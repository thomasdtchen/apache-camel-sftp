package com.thomasweb.apache.camel.sftp;

 
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
                .setPath("linux".equalsIgnoreCase("linux") ?  "../../" + "/apps/filein2" : "/apps/filein2")
                .addParameter("fileName", "${header.CamelFileRelativePath}") // make it be able to move sub folder
//                .addParameter("initialDelay", "10s")
//                .addParameter("delay", "50")
                .addParameter("repeatCount", "3")
                .addParameter("username", "apprunner")
                //.addParameter("password", "pass")
                .addParameter("privateKeyFile", "C:/Users/lenovo/.ssh/rsa_key")
                .addParameter("privateKeyPassphrase", "changeit")
                .addParameter("jailStartingDirectory", "false") // make it be able to move file to root/other folder, instead of home folder

//                .addParameter("passiveMode", "false")
                //.addParameter("useUserKnownHostsFile", "false")
                .build();
        log.info("toFtpUrl: " + toFtpUrl);

        StringBuilder fromURL = new StringBuilder();
        fromURL.append("file:/F:/apps/transfer/in?");
        //fromURL.append("antInclude=*_TURRETA_*&");
        fromURL.append("moveFailed=F:/apps/transfer/error/${header.CamelFileRelativePath}&");// move the file to error folder after failure, or it will  repeat forever
//        fromURL.append("move=../done&");
//        fromURL.append("preMove=../in-progress&");
        fromURL.append("readLock=changed&");
//        fromURL.append("readLockMinAge=1m&");
        //fromURL.append("readLockTimeout=70000&");
        //fromURL.append("readLockCheckInterval=5000");//when setup this value it will then pick up the file after this interval, i.e. it is not set tp the retry interval when failure.
        fromURL.append("recursive=true"); // recursive all sub folders
        log.info("fromURL: " + fromURL);
        String routeId = "1";
        from(fromURL.toString()).routeId(routeId).to("log:thomas.camel?showHeaders=true").to(toFtpUrl.toString());
    }
}
