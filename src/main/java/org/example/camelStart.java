package org.example;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class camelStart {
    static Logger log = LoggerFactory.getLogger(camelStart.class);
    public static void main(String[] args) {

        final CamelContext camelContext = new DefaultCamelContext();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Введите путь к директории ИЗ которой хотите перенести файл (input): ");
            String inputDir = reader.readLine();

            System.out.println("Введите путь к директории В которую хотите перенести файл (output): ");
            String outputDir = reader.readLine();

            camelContext.addRoutes(
                    new RouteBuilder() {
                        @Override
                        public void configure(){
                            from("file:" + inputDir + "?noop=true").to("file:" + outputDir);
                        }
                    });
            camelContext.start();
            Thread.sleep(1000);
            System.out.println("Complete!");
            log.info("The file was successfully transferred!");
            camelContext.stop();
        } catch (Exception camelException) {
            System.out.println("Error!");
            log.info("Error!");
            System.exit(0);
        }

    }
}
