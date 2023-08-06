package run;


import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("case")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/cucumber-report/cucumber-report.json")
//@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "junit:target/generated-report/cucumber-reports.html")
//@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME , value = "@test")
//@CucumberOptions(tags = "@test")


public class RunCucumberTest {
    public static String  server;
    public static String  authorization;
    public static String  type;
    public static String  tenant;

    @Before
    public static void setUp(){
        Properties properties = new Properties();
        try {
            FileReader reader  =  new FileReader("src\\test\\resources\\conf\\conf.properties");
            BufferedReader br =  new BufferedReader(reader);
            properties.load(br);
            server = properties.getProperty("server");
            authorization = properties.getProperty("authorization");
            type = properties.getProperty("Content-Type");
            tenant = properties.getProperty("Tenant");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @AfterAll
    public static void teardown(){
        System.out.println("********end all****");
    }
}
