package eg;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.kubernetes.discovery.KubernetesDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
    
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @RequestMapping("/url/{host}/{port}")
    public String url(@PathVariable("host") String host, @PathVariable("port")
    int port/*, @PathVariable("path") String path*/) throws IOException {
    	/*path=path==null?"":path;*/
    	String urlStr="http://"+host+":"+port;//+"/"+path;
    	StringWriter sw= new StringWriter();
    	PrintWriter pw= new PrintWriter(sw);
    	URL url = new URL(urlStr);
    	pw.println("url="+url);
    	try(InputStream is = url.openStream();
    			InputStreamReader isr= new InputStreamReader(is);
    			BufferedReader br=new BufferedReader(isr);
    			)
    	{
    		for (String line=br.readLine(); line!=null; line=br.readLine()) 
    		{
				pw.println(line);
				
			}
    	}
    	pw.flush();
        return sw.getBuffer().toString();
    }
    
    @RequestMapping("/services")
    public List<String> services()
    {
    	
    	List<String> services = discoveryClient.getServices();
    	return services;
    }
}
