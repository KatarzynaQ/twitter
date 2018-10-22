package twitter;

import org.apache.catalina.startup.Tomcat;

public class Main {
    public static void main(String[] args) throws Exception {
        Tomcat tomcat = create();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static Tomcat create() throws Exception {
        return EmbeddedTomcatFactory.create(8080);
    }
}

