package com.sfeir.demo;

import java.io.PrintStream;

/**
 * Created by julienfurgerot on 03/05/2017.
 */
public class HelloWorld {

    private PrintStream out;

    public HelloWorld(PrintStream out) {
        this.out = out;
    }

    public static void main(String[] args) {
        new HelloWorld(System.out).sayHello(" Sfeir");
    }

    public void sayHello(String name) {
        StringBuilder sb = new StringBuilder('H');
        sb.append("Hello").append( " " ).append( name );
        out.println( sb.toString());
    }

    public void sayHelloWithError(String name){
        try {
            checkName(name);
            sayHello(name);
        } catch (Exception e) {
            // do nothing, but sonar will say we should
        }
    }

    private void checkName(String name) throws Exception {
        if( name == null || name.length() > 0 ) {
            throw new Exception("Bad name");
        }

        // Commented code, Sonar do not like it!
//        if( StringUtils.isBlank(name)) {
//            throw new Exception("Bad name");
//        }
    }

}
